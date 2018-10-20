package com.restassured.twitter.examples;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TwitterEndToEndWorkflow {

	private String consumerKey = "WPstBmN3VyuOxUrfQB6Azs2oZ";
	private String consumerSecret = "M7ueNtmLkoFfTXzXxdTSJRGefzJ9GnXOeIP9JWOkdXBwAZyoAe";
	private String accessToken = "3225415232-QqABmfupE3xN6CZJR7AhLRcVsZ2OcxHKeEbeZg1";
	private String secretToken = "80O7cI47XgmZ7HaOn07NpW0uGsk8iibz160UyeSLqhG8K";
	private String tweetId = "";
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
	}
	
	@Test
	public void postTweet() {
		Response response = given()
				.queryParam("status", "My First Tweet!!!")
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.post("/update.json")
		.then()
				.statusCode(200)
				.extract()
				.response();
		
		tweetId = response.path("id_str");
		System.out.println("Id : " + (String)response.path("id_str"));
		System.out.println("User Name : " + (String)response.path("user.name"));
		System.out.println("Screen Name : " + (String)response.path("user.screen_name"));
		
		/*System.out.println("Using Json Path");
		JsonPath jsonPath = new JsonPath(response.asString());
		
		System.out.println("Id : " + jsonPath.get("id_str"));
		System.out.println("User Name : " + jsonPath.get("user.name"));
		System.out.println("Screen Name : " + jsonPath.get("user.screen_name"));*/
		
	}
	
	@Test(dependsOnMethods= {"postTweet"})
	public void getTweet() {
		Response response = given()
				.queryParam("id", tweetId)
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.get("/show.json")
		.then()
				.extract()
				.response();
		String text = response.path("text");
		System.out.println("Tweet text : " + text);
	}
	
	@Test(dependsOnMethods= {"getTweet"})
	public void deleteTweet() {
		given()
				.pathParam("id", tweetId)
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.post("/destroy/{id}.json")
		.then()
				.statusCode(200);
	}
	
}
