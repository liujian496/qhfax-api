package com.qhfax.api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MyTest {

    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashPass = encoder.encode("123456");
        System.out.println(hashPass);
        System.out.println(encoder.matches("123456","$2a$10$Ggtfogs6goDRxPSxtrS60.I/6ll9CFEIXENZhX683LrfsCPG1nIJK"));
    }
}
