package com.ytmproje.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class KullaniciTestleri {

    // 1. TEST: Veri Çekme (GET)
    @Test
    public void apiGetTesti() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        System.out.println("----- GET TESTİ BAŞLIYOR -----");

        given()
                .when()
                .get("/todos/1")
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("title", notNullValue());

        System.out.println("GET Testi Başarılı!");
    }

    // 2. TEST: Veri Gönderme (POST) - KRİTİK KISIM BURASI
    @Test
    public void apiPostTesti() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Göndereceğimiz Veri (JSON Formatında String)
        // Proje isterlerindeki "Request Body" burasıdır.
        String yeniKayit = "{\n" +
                "  \"title\": \"YTM Proje Odevi\",\n" +
                "  \"body\": \"Yapay Zeka Destekli Test\",\n" +
                "  \"userId\": 1\n" +
                "}";

        System.out.println("----- POST TESTİ BAŞLIYOR -----");

        given()
                .contentType(ContentType.JSON) // "Ben sana JSON yolluyorum" diyoruz
                .body(yeniKayit) // Hazırladığımız veriyi pakete koyuyoruz
                .when()
                .post("/posts") // POST isteği atıyoruz
                .then()
                .statusCode(201) // 201: "Created" (Oluşturuldu) kodu gelmeli
                .body("title", equalTo("YTM Proje Odevi")) // Gönderdiğimiz başlık geri döndü mü?
                .log().body(); // Cevabı konsola bas

        System.out.println("POST Testi Başarılı!");
    }
}