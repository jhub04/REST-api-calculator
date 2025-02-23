package stud.ntnu.no.calculator.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import stud.ntnu.no.calculator.model.User;

@Repository
public class UserRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public void register(User user) {
    String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
    jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
  }
}
