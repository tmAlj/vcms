package com.wsd.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestMain {
    public static void main(String[] args) {
        String pass = "admin";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("admin", hashPass);
        System.out.println(f);

    }
}
