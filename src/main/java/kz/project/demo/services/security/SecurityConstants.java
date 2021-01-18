package kz.project.demo.services.security;

public class SecurityConstants {
    public static final String SECRET = "nUYZsOzDX2x9ThH0dL2H2e1ivTFvI5gx";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URL = "/login";
}
