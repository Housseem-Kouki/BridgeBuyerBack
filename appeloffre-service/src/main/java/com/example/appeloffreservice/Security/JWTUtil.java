package com.example.appeloffreservice.Security;

public class JWTUtil {
    public  static final String SECRET="mySecret1234";
    public  static final String AUTH_HEADER="Authorization";
    public  static final String PREFIX_HEADER="Bearer ";
    public  static final long EXP_ACCESS_TOKEN=15*60*1000;
    public  static final long EXP_REFRESH_TOKEN=15*60*1000;
}
