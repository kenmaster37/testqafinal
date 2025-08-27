package com.testapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public int id;
    public String name;
    public String email;
    public String phone;
    public String company;
}
