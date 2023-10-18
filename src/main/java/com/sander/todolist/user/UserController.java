package com.sander.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/users")
  public ResponseEntity CreateUser(@RequestBody UserModel userModel, HttpServletRequest request) {

    var authorization = request.getHeader("Authorization");
    var token = authorization.substring("Baerer".length()).trim();

    if (!token.equals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9")) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("Sem permissão para cadastrar um usuário. Verifique seu acesso /token/.");
    }

    var user = this.userRepository.findByUsername(userModel.getUsername());

    if (user != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existente.");
    }

    var passwordhashed = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());

    userModel.setPassword(passwordhashed);

    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
  }

  @GetMapping("/users")
  public ResponseEntity GetUser(UserModel userModel) {

    var users = this.userRepository.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(users);
  }
}
