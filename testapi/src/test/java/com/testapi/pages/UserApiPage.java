package com.testapi.pages;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class UserApiPage {
    private static final String BASE_URL = "https://fake-json-api.mock.beeceptor.com/users";

    public Response getAllUsers() {
        return SerenityRest.when().get(BASE_URL);
    }

    public Response getUserById(int id) {
        return SerenityRest.when().get(BASE_URL + "/" + id);
    }

    public Response createUser(String name, String email) {
        return SerenityRest.given()
            .contentType("application/json")
            .body("{\"name\": \"" + name + "\", \"email\": \"" + email + "\"}")
            .post(BASE_URL);
    }

    public Response updateUser(int id, String name) {
        return SerenityRest.given()
            .contentType("application/json")
            .body("{\"name\": \"" + name + "\"}")
            .put(BASE_URL + "/" + id);
    }

    public Response deleteUser(int id) {
        return SerenityRest.when().delete(BASE_URL + "/" + id);
    }
}
