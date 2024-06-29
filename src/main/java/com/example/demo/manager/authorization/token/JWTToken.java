package com.example.demo.manager.authorization.token;

import com.example.demo.entity.User;
import io.jsonwebtoken.Claims;

public interface JWTToken {

    String getToken(User user);

    Claims getClaims(String token);

    boolean isValid(String token);


}
