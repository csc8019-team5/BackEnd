package uk.ac.ncl0417.team50417.entities;


import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String token;
}
