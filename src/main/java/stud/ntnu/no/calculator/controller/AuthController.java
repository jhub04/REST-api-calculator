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
  public String login(@RequestBody User user) {
    logger.info("Received login request {} {}", user.getUsername(), user.getPassword());
    Optional<User> dbUser = userRepository.findByUsername(user.getUsername());
    if (dbUser.isPresent() && dbUser.get().getPassword().equals(user.getPassword())) {
      return "Login successful";
    }
    return "Invalid username or password";
  }

  @CrossOrigin(origins = "http://localhost:5173/")
  @PostMapping("/register")
  public String register(@RequestBody User user) {
    logger.info("Received registration request {} {}", user.getUsername(), user.getPassword());
    userRepository.save(user);
    return "User registered successfully";
  }
}
