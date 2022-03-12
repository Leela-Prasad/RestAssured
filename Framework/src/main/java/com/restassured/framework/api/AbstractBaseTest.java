package com.restassured.framework.api;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.restassured.framework.constants.FrameworkConstants.*;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

public abstract class AbstractBaseTest {

	public ResponseSpecification getResponseSpecification() {
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		
		builder.expectStatusCode(200)
				.expectResponseTime(lessThan(3L), TimeUnit.SECONDS);
		return builder.build();
	}
	
	public RequestSpecification getRequestSpecification() {
		AuthenticationScheme authScheme = RestAssured.oauth(CONSUMER_KEY, CONSUMER_SECRET, 
															ACCESS_TOKEN, SECRET_TOKEN);
		
		RequestSpecBuilder builder = new RequestSpecBuilder();
		builder.setBaseUri(BASE_URI)
				.setAuth(authScheme);
		return builder.build();
	}
	
	
}
