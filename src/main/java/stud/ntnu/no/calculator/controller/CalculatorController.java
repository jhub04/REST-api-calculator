package stud.ntnu.no.calculator.controller;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import stud.ntnu.no.calculator.model.Calculation;
import stud.ntnu.no.calculator.model.CalculationRequest;
import stud.ntnu.no.calculator.model.Result;
import stud.ntnu.no.calculator.service.CalculatorService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/calculator")
public class CalculatorController {
  private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
  private final CalculatorService calculatorService;

  @Autowired
  public CalculatorController(CalculatorService calculatorService) {
    this.calculatorService = calculatorService;
  }
  @CrossOrigin(origins = "http://localhost:5173/")
  @GetMapping
  public ResponseEntity<List<Calculation>> getCalculations(
      @RequestParam String userName,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    logger.info("Request to fetch calculations");
    return ResponseEntity.ok(calculatorService.getCalculations(userName, page, size));
  }

  @CrossOrigin(origins = "http://localhost:5173/")
  @PostMapping("/calculate")
  public ResponseEntity<?> calculateAndSave(@RequestBody CalculationRequest request) {
    logger.info("User with username {} Requested to calculate: {}", request.getUserName(), request.getExpression());

    // Compute the result
    double result;
    try {
      result = calculatorService.evaluateExpression(request.getExpression());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body("Invalid expression");
    }

    // Save the calculation
    calculatorService.saveCalculation(request.getUserName(), request.getExpression(), result);

    // Return the calculated result
    return ResponseEntity.ok(Map.of("expression", request.getExpression(), "result", result));
  }

}
