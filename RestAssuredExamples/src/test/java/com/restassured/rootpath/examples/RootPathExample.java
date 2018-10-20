package com.restassured.rootpath.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RootPathExample {

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
		/*.then() 
				.statusCode(200)
				.body("text", hasItem("Sample Tweet #Testing #multiple!!!"))
				.body("user.name", hasItem("Leela Prasad"))
				.body("user.screen_name", hasItem("leelajobs239"))
				.body("entities.hashtags[0].size()", equalTo(2))
				.body("entities.hashtags[1].size()", lessThan(2));*/
		
		.then() 
				.statusCode(200)
				.body("text", hasItem("Sample Tweet #Testing #multiple!!!"))
				.rootPath("user") // this will be prefixed with the path we mention and scope will be valid until next root path is encountered.
				.body("name", hasItem("Leela Prasad"))
				.body("screen_name", hasItem("leelajobs239"))
				.rootPath("entities") //Here this root path will be applied and "user" root path scope is completed.
				.body("hashtags[0].size()", equalTo(2))
				.body("hashtags[1].size()", lessThan(2));		
	}
	
}
