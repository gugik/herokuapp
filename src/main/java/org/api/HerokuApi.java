package org.api;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;

import static io.restassured.RestAssured.given;


public class HerokuApi {
    public Booking createBooking(BookingBody bookingBody) {
        JSONObject requestBody = new JSONObject()
                .put("firstname", bookingBody.getFirstName())
                .put("lastname", bookingBody.getLastName())
                .put("totalprice", bookingBody.getTotalPrice())
                .put("depositpaid", bookingBody.getDepositPaid())
                .put("bookingdates",
                        new JSONObject()
                                .put("checkin", bookingBody.getBookingDates().getCheckin())
                                .put("checkout", bookingBody.getBookingDates().getCheckout()))
                .put("additionalneeds", bookingBody.getAdditionalNeeds());

        Response response2 = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody.toString())
                .when()
                .post("booking")
                .then()
                .extract().response();
        Assert.assertEquals(200, response2.statusCode());
        JsonPath jsonPathEvaluator = response2.jsonPath();

        return new Booking(jsonPathEvaluator.get("bookingid"),
                new BookingBody(jsonPathEvaluator.get("booking.firstname"),
                        jsonPathEvaluator.get("booking.lastname"), jsonPathEvaluator.get("booking.totalprice"),
                        jsonPathEvaluator.get("booking.depositpaid"),
                        new BookingDates(jsonPathEvaluator.get("booking.bookingdates.checkin"),
                                jsonPathEvaluator.get("booking.bookingdates.checkout")),
                        jsonPathEvaluator.get("booking.additionalneeds"))
        );
    }

    public BookingBody getBookingById(int bookingId) {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("booking/" + bookingId)
                .then()
                .extract().response();
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPathEvaluator = response.jsonPath();
        return new BookingBody(jsonPathEvaluator.get("firstname"),
                jsonPathEvaluator.get("lastname"), jsonPathEvaluator.get("totalprice"),
                jsonPathEvaluator.get("depositpaid"),
                new BookingDates(jsonPathEvaluator.get("bookingdates.checkin"),
                        jsonPathEvaluator.get("bookingdates.checkout")),
                jsonPathEvaluator.get("additionalneeds")
        );
    }
}
