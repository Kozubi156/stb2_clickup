package pl.akademiaqa.requests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.akademiaqa.properties.ClickUpProperties;
import pl.akademiaqa.url.ClickUpUrl;

public class BaseRequest {

    private static RequestSpecBuilder requestSpecBuilder;

    public static RequestSpecification requestSpec(){
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(ClickUpUrl.getBaseUrl());
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.addHeader("Authorization", ClickUpProperties.getToken());
        requestSpecBuilder.addFilter(new AllureRestAssured());

        return requestSpecBuilder.build();
    }

    public static RequestSpecification requestSpecWithLogs(){
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(ClickUpUrl.getBaseUrl());
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.addHeader("Authorization", ClickUpProperties.getToken());
        requestSpecBuilder.addFilter(new RequestLoggingFilter());
        requestSpecBuilder.addFilter(new ResponseLoggingFilter());
        requestSpecBuilder.addFilter(new AllureRestAssured());

        return requestSpecBuilder.build();
    }
}
