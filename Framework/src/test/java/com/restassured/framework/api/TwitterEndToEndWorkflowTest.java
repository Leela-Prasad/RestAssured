package com.restassured.framework.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.restassured.framework.constants.FrameworkConstants.STATUS_ENDPOINT_CREATE_TWEET;
import static com.restassured.framework.constants.FrameworkConstants.STATUS_ENDPOINT_GET_TWEET;
import static com.restassured.framework.constants.FrameworkConstants.STATUS_ENDPOINT_DELETE_TWEET;

public class TwitterEndToEndWorkflowTest extends AbstractBaseTest {

	private RequestSpecification requestSpec;
	private ResponseSpecification responseSpec;
	private String tweetId = "";
	
	@BeforeClass
	public void setup() {
		requestSpec = getRequestSpecification().basePath("/1.1/statuses");
		responseSpec = getResponseSpecification();
	}
	
	@Test
	public void postTweet() {
		Response response = 
		given()
				.spec(requestSpec)
				.queryParam("status", "Test Tweet!!!")
		.when()
				.post(STATUS_ENDPOINT_CREATE_TWEET)
		.then()
				.spec(responseSpec)
				.extract()
				.response();
		
		tweetId = response.path("id_str");
		System.out.println("Id : " + (String)response.path("id_str"));
		System.out.println("User Name : " + (String)response.path("user.name"));
		System.out.println("Screen Name : " + (String)response.path("user.screen_name"));
	}
	
	@Test(dependsOnMethods= {"postTweet"})
	public void getTweet() {
		Response response  = 
		given()
				.spec(requestSpec)
				.queryParam("id", tweetId)
		.when()
				.get(STATUS_ENDPOINT_GET_TWEET)
		.then()
				.spec(responseSpec)
				.extract()
				.response();
		String text = response.path("text");
		System.out.println("Tweet text : " + text);
	}
	
	@Test(dependsOnMethods= {"getTweet"})
	public void deleteTweet() {
		given()
				.spec(requestSpec)
				.pathParam("id", tweetId)
		.when()
				.post(STATUS_ENDPOINT_DELETE_TWEET)
		.then()
				.spec(responseSpec);
	}
	
}
