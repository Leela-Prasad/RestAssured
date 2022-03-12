Given - I have this information (like parameters)
when - I performed an action (like making get request)
then - this should be the output

In OAuth 
consumer key identifies which application is accessing the application.
access token identifies the user.
user can have multiple applications(i.e., multiple consumer keys) so we need both consumer  key and access token to uniquely identify the action performed.

If you want to log, then you can use this in the Request Builder:
requestBuilder.log(LogDetail.ALL);

If you want the ifValidationFails in all the requests, then you can set it globally in @BeforeClass without putting that in Request Specification like this:
RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

This is Readme File.