package stud.ntnu.no.calculator.controller;

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

@RestController
@RequestMapping("/auth")
public class AuthController {
  private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
  private final UserRepository userRepository;

  @Autowired
  public AuthController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @CrossOrigin(origins = "http://localhost:5173/")
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody User request) {
    logger.info("Received login request {} {}", request.getUsername(), request.getPassword());
    userRepository.register(new User(request.getUsername(), request.getPassword()));
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
