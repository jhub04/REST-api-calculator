package stud.ntnu.no.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import stud.ntnu.no.calculator.dao.UserRepository;
import stud.ntnu.no.calculator.model.User;

@Service
public class AuthService {
  private final UserRepository userRepository;

  @Autowired
  public AuthService(UserRepository userRepository) {this.userRepository = userRepository;}

  public User authenticate(String username, String password) {
    return userRepository.findUserByUsername(username)
        .filter(user -> user.getPassword().equals(password))
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials!"));
  }
}
