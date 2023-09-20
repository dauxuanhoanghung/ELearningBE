package com.dxhh.elearning.dto.response;

import lombok.Data;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final String expirationDate;

    public JwtResponse(String jwtToken, String expirationDate) {
        this.token = jwtToken;
        this.expirationDate = expirationDate;
    }

}