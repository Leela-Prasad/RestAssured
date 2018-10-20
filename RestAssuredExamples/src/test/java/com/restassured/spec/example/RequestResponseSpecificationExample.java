package com.restassured.spec.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseSpecificationExample {

	private String consumerKey = "WPstBmN3VyuOxUrfQB6Azs2oZ";
	private String consumerSecret = "M7ueNtmLkoFfTXzXxdTSJRGefzJ9GnXOeIP9JWOkdXBwAZyoAe";
	private String accessToken = "3225415232-QqABmfupE3xN6CZJR7AhLRcVsZ2OcxHKeEbeZg1";
	private String secretToken = "80O7cI47XgmZ7HaOn07NpW0uGsk8iibz160UyeSLqhG8K";
	
	private RequestSpecification requestSpec = null;
	private ResponseSpecification responseSpec=null;
	
	@BeforeClass
	public void setup() {
		AuthenticationScheme authScheme = RestAssured.oauth(consumerKey, consumerSecret, accessToken, secretToken);
		
		RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
		requestBuilder.setBaseUri("https://api.twitter.com")
						.setBasePath("/1.1/statuses")
						.setAuth(authScheme);
						//.addFilter(new RequestLoggingFilter(logConfig.logDetailOfRequestAndResponseIfValidationFails()));
		requestSpec = requestBuilder.build();
		
		ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectStatusCode(200);
		responseSpec = responseBuilder.build();
	}
	
	@Test
	public void getTweet() {
		given()
				.spec(requestSpec)
				.queryParam("user_id", "leelajobs239")
		.when()
				.get("/user_timeline.json")
		.then() 
				.spec(responseSpec)
				.body("text", hasItem("Sample Tweet #Testing #multiple!!!"));
	}
	
}
