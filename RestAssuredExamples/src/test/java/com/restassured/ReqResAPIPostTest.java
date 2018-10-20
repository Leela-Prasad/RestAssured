package com.restassured;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ReqResAPIPostTest {

	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://reqres.in";
		RestAssured.basePath="/api";
	}
	
	@Test
	public void validateStatusCode() {
		given()
				.body("{"
						+ "\"name\": \"morpheus\","
						+ "\"job\": \"leader\""
						+ "}")
		.when()
				.post("/users")
		.then()
				.statusCode(201)
				.and()
				.contentType(ContentType.JSON);
		//.thenReturn().getBody().prettyPrint();		
	}
}
