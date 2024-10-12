package com.huntercodexs.codexstester.dto;

public class JwtResponseDto {
    String jwt;

    public JwtResponseDto() {
    }

    public JwtResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "JwtResponseDto(" +
                    "jwt=" + jwt +
                ")";
    }
}
