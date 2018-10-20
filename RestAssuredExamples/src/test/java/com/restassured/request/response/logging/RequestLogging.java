package com.restassured.request.response.logging;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RequestLogging {

	private String consumerKey = "WPstBmN3VyuOxUrfQB6Azs2oZ";
	private String consumerSecret = "M7ueNtmLkoFfTXzXxdTSJRGefzJ9GnXOeIP9JWOkdXBwAZyoAe";
	private String accessToken = "3225415232-QqABmfupE3xN6CZJR7AhLRcVsZ2OcxHKeEbeZg1";
	private String secretToken = "80O7cI47XgmZ7HaOn07NpW0uGsk8iibz160UyeSLqhG8K";
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
	}
	
	@Test(enabled=false)
	public void postTweet_showing_different_options_for_request_log_method() {
		given()
				.log()
				//.headers()
				//.body()
				//.method()
				//.params()
				//.uri()
				.all()
				.queryParam("status", "My First Tweet!!!")
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.post("/update.json")
		.then()
				.statusCode(200);
	}
	
	@Test
	public void postTweet() {
		given()
				.log()
				.ifValidationFails()
				.queryParam("status", "My Second Tweet!!!")
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.post("/update.json")
		.then()
				.statusCode(200);
	}
}
