package stud.ntnu.no.calculator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import stud.ntnu.no.calculator.model.Calculation;

@Service
public class CalculatorService {
  private final static Logger logger = LoggerFactory.getLogger(CalculatorService.class);

  public double calculate(Calculation calculation) {
    String operator = calculation.getOperator();
    double num1 = calculation.getNum1();
    double num2 = calculation.getNum2();
    double result;
    switch (operator) {
      case "+":
        result = num1 + num2;
        break;
      case "-":
        result = num1 - num2;
        break;
      case "*":
        result = num1 * num2;
        break;
      case "/":
        if (num2 == 0) {
          throw new ArithmeticException("Divisor can not be zero");
        } 
        result = num1 / num2;
        break;
      default:
        throw new IllegalStateException("Illegal operator: " + operator);
    }
    logger.info("Calculated {} {} {} = {}", num1, operator, num2, result);
    return result;
  }
}
