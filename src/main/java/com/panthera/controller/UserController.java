package com.panthera.controller;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.web.bind.annotation.GetMapping;
import com.panthera.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import java.security.Principal;

@RestController
public class UserController {
  @Autowired
  private UserRepository userRepository;
//
//  @GetMapping("/")
//  public String echoTheUsersEmailAddress(Principal principal) {
//    return "Hey there! Your email address is: " + principal.getName();
//  }
}
