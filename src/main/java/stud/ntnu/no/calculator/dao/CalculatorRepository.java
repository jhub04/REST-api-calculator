package stud.ntnu.no.calculator.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import stud.ntnu.no.calculator.model.Calculation;

@Repository
public class CalculatorRepository {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public CalculatorRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

  public List<Calculation> findCalculationsByUserId(int userId, int limit, int offset) {
    String sql = "SELECT * FROM calculations WHERE user_id = ? ORDER BY created_at DESC LIMIT ? OFFSET ?";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Calculation.class), userId, limit, offset);
  }

  public void saveCalculation(Calculation calculation) {
    String sql = "INSERT INTO calculations (user_id, expression, result) VALUES (?, ?, ?)";
    jdbcTemplate.update(sql, calculation.getUserId(), calculation.getExpression(), calculation.getResult());
  }

}
