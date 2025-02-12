package stud.ntnu.no.calculator.service;

import org.springframework.stereotype.Service;
import stud.ntnu.no.calculator.model.CalculationRequest;

@Service
public class CalculatorService {

  public double calculate(CalculationRequest request) {
    String operator = request.getOperator();
    double num1 = request.getNum1();
    double num2 = request.getNum2();
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
    return result;
  }
}
