package com.testapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testapi.models.User;
import java.io.IOException;

public class JsonUtils {
    public static User fromJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, User.class);
    }
}
