package com.restassured.response.time.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class ResponseTimeExample {

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
	public void getResponseTime() {
		Long responseTime = 
		given()
				.queryParam("user_id", "leelajobs239")
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.get("/user_timeline.json")
				//.time(); // This will give response time in milliseconds
				.timeIn(TimeUnit.SECONDS); //This will give response time that is defined by TimeUnit. 
		
		System.out.println("Response Time : " + responseTime);		
	}
	
	@Test
	public void failTestIfResponseTimeExceeds() {
		given()
				.queryParam("user_id", "leelajobs239")
				.auth()
				.oauth(consumerKey, consumerSecret, accessToken, secretToken)
		.when()
				.get("/user_timeline.json")
		.then() 
				.statusCode(200)
				.time(lessThan(3L), TimeUnit.SECONDS) //This test will fail if response time is greater than 3 seconds
				.body("text", hasItem("Sample Tweet #Testing #multiple!!!"))
				.body("user.name", hasItem("Leela Prasad"))
				.body("user.screen_name", hasItem("leelajobs239"))
				.body("entities.hashtags[0].size()", equalTo(2))
				.body("entities.hashtags[1].size()", lessThan(2));		
	}
	
}
