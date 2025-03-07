package stud.ntnu.no.calculator.dao;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import stud.ntnu.no.calculator.model.Calculation;
import stud.ntnu.no.calculator.model.CalculationRequest;

@Repository
public class CalculatorRepository {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public CalculatorRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

  public List<Calculation> findCalculationsByUserName(String userName, int limit, int offset) {
    String userIdQuery = "SELECT id FROM users WHERE username = ?";
    int userId;
    try {
      userId = jdbcTemplate.queryForObject(userIdQuery, int.class, userName);
    } catch (EmptyResultDataAccessException e) {
      return Collections.emptyList();
    }

    String sql = "SELECT * FROM calculations WHERE user_id = ? ORDER BY created_at DESC LIMIT ? OFFSET ?";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Calculation.class), userId, limit, offset);
  }

  public void saveCalculation(CalculationRequest request) {
    String userIdQuery = "SELECT id FROM users WHERE username = ?";
    int userId;
    try {
      userId = jdbcTemplate.queryForObject(userIdQuery, int.class, request.getUserName());
    } catch (EmptyResultDataAccessException e) {
      throw new EmptyResultDataAccessException(1);
    }

    String sql = "INSERT INTO calculations (user_id, expression, result) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, userId, request.getExpression(), request.getResult());
  }

}
