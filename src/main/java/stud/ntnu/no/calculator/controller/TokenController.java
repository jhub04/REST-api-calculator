package stud.ntnu.no.calculator.controller;

import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import stud.ntnu.no.calculator.model.User;
import stud.ntnu.no.calculator.service.AuthService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin
public class TokenController {

  AuthService authService;

  @Autowired
  public TokenController(AuthService authService) {
    this.authService = authService;
  }
  private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
  public static final String keyStr = "testsecrettestsecrettestsecrettestsecrettestsecret";
  private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);

  @CrossOrigin(origins = "http://localhost:5173/")
  @PostMapping(value = "")
  @ResponseStatus(value = HttpStatus.CREATED)
  public String generateToken(@RequestBody User user) {
    if (this.authService.authenticate(user.getUsername(), user.getPassword()) != null) {
      logger.info("Generating token...");
      return generateToken(user.getUsername());
    }
    logger.info("Token couldn't be generated...");
    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
  }

  public String generateToken(final String userId) {
    final Instant now = Instant.now();
    final Algorithm hmac512 = Algorithm.HMAC512(keyStr);;
    final JWTVerifier verifier = JWT.require(hmac512).build();
    return JWT.create()
        .withSubject(userId)
        .withIssuer("idatt2105_token_issuer_app")
        .withIssuedAt(now)
        .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
        .sign(hmac512);
  }

}
