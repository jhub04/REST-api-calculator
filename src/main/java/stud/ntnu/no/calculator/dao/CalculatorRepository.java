package stud.ntnu.no.calculator.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import stud.ntnu.no.calculator.model.Calculation;

@Repository
public class CalculatorRepository {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public CalculatorRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

}
