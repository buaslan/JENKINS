package jenkins;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

public class JenkinsTests {



    @Test
    public void apiTest(){

        RequestSpecification spec  = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();

        //Set the base url and path params
        spec.pathParams("first", "booking", "second",3);

        //Send the Get request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);

        Map<String, Object> bookingdates = (Map)actualData.get("bookingdates");


        assertEquals("Sally", actualData.get("firstname"));
        assertEquals("Jones", actualData.get("lastname"));
        assertEquals(683, actualData.get("totalprice"));
        assertEquals(true, actualData.get("depositpaid"));

        assertEquals("2022-05-05", bookingdates.get("checkin"));
        assertEquals("2022-05-19", bookingdates.get("checkout"));
        // not working for Jenkins //

    }

}