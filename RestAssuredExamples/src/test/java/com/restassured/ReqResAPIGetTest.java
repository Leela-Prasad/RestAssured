package com.restassured;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
public class ReqResAPIGetTest {

	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://reqres.in";
		RestAssured.basePath="/api";
	}
	
	@Test
	public void checkStatusCode() {
		given()
				.queryParam("page", 2)
		.when()
				.get("/users")
		.then()
				.statusCode(200);
	}
	
	@Test
	public void getResponseBody() {
		given()
				.queryParam("page", 2)
		.when()
				.get("/users")
		.thenReturn()
				.getBody().prettyPrint();	
	}
	
	@Test
	public void validateResponseBodyContent() {
		given()
				.queryParam("page", 2)
		.when()
				.get("/users")
		.then()
				.statusCode(200)
				.and()
				.contentType(ContentType.JSON)
				.and()
				.body("data[0].id", equalTo(4));
	}
}
