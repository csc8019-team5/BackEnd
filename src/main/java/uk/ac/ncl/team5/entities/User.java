package uk.ac.ncl.team5.entities;


import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String token;
}
