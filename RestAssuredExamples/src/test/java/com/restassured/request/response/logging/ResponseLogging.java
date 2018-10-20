package com.restassured.request.response.logging;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ResponseLogging {

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
	public void postTweet_showing_different_options_for_response_logging() {
		given()
				.queryParam("status", "My Second Tweet 4!!!")
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.post("/update.json")
		.then()
				.log()
				//.body()
				//.headers()
				//.cookies()
				.all()
				.statusCode(200);
	}
	
	@Test
	public void postTweet() {
		given()
				.log()
				.ifValidationFails()
				.queryParam("status", "Sample Tweet #Testing #multiple!!!")
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.post("/update.json")
		.then()
				.log()
				//.ifError()	//ifError will work only when there is a problem in posting the request.
				//.log()
				.ifValidationFails()
				.statusCode(200);
	}
}
