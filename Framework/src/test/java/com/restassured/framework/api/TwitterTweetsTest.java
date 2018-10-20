package com.restassured.framework.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.restassured.framework.constants.FrameworkConstants.STATUS_ENDPOINT_GET_TWEETS;

public class TwitterTweetsTest extends AbstractBaseTest {

	private RequestSpecification requestSpec;
	private ResponseSpecification responseSpec;
	
	@BeforeClass
	public void setup() {
		requestSpec = getRequestSpecification().basePath("/1.1/statuses");
		responseSpec = getResponseSpecification();
	}
	
	@Test
	public void getTweets() {
		given()
				.spec(requestSpec)
				.queryParam("user_id", "leelajobs239")
		.when()
				.get(STATUS_ENDPOINT_GET_TWEETS)
		.then() 
				.spec(responseSpec)
				.body("text", hasItem("Sample Tweet #Testing #multiple!!!"))
				.body("entities.hashtags[0].size()", equalTo(2))
				.body("entities.hashtags[1].size()", lessThan(2));
	}
	
}
