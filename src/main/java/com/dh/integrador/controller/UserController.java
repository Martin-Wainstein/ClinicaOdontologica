package com.dh.integrador.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//    @GetMapping("/login")
//    public String login(){
//        return "login";
//    }

    @GetMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("<h1>INGRESASTE AL SISTEMA</h1> <br> <br> <a href='http://localhost:8080/Listas.html'> IR A LAS LISTAS </a>");
    }

}
