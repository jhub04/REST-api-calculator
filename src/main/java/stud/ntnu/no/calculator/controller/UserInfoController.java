package stud.ntnu.no.calculator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stud.ntnu.no.calculator.dao.UserRepository;
import stud.ntnu.no.calculator.model.User;

@RestController
@RequestMapping(value = "/users")
@EnableAutoConfiguration
@CrossOrigin
public class UserInfoController {

  private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

  @CrossOrigin(origins = "http://localhost:5173/")
  @GetMapping("/{userName}")
  public ResponseEntity<?> getUser(@PathVariable String userName){
    logger.info("PathVariable received: {}", userName);
    return ResponseEntity.ok(userName);
  }

}
