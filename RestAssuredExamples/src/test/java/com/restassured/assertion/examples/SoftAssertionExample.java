package com.restassured.assertion.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class SoftAssertionExample {

	private String consumerKey = "WPstBmN3VyuOxUrfQB6Azs2oZ";
	private String consumerSecret = "M7ueNtmLkoFfTXzXxdTSJRGefzJ9GnXOeIP9JWOkdXBwAZyoAe";
	private String accessToken = "3225415232-QqABmfupE3xN6CZJR7AhLRcVsZ2OcxHKeEbeZg1";
	private String secretToken = "80O7cI47XgmZ7HaOn07NpW0uGsk8iibz160UyeSLqhG8K";
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
	}
	
	@Test
	public void getTweet() {
		given()
				.queryParam("user_id", "leelajobs239")
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.get("/user_timeline.json")
		.then()
				.statusCode(200)
				.body("text", hasItem("Sample Tweet #Testing #multiple!!!"),
				"entities.hashtags[0].size()", equalTo(2),
				"entities.hashtags[1].size()", lessThan(2));
	}
	
}
