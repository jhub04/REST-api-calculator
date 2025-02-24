package stud.ntnu.no.calculator.controller;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stud.ntnu.no.calculator.dao.UserRepository;
import stud.ntnu.no.calculator.model.User;
import stud.ntnu.no.calculator.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {this.authService = authService;}

  @CrossOrigin(origins = "http://localhost:5173/")
  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody User request) {
    User user = authService.authenticate(request.getUsername(), request.getPassword());
    return ResponseEntity.ok(user);
  }

  @CrossOrigin(origins = "http://localhost:5173/")
  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user) {
    authService.register(user);
    return ResponseEntity.ok().build();

  }
}
