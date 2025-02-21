package stud.ntnu.no.calculator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import stud.ntnu.no.calculator.model.CalculationRequest;
import stud.ntnu.no.calculator.model.CalculationResponse;
import stud.ntnu.no.calculator.service.CalculatorService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/calculator")
public class RestController {
  private static final Logger logger = LoggerFactory.getLogger(RestController.class);
  private final CalculatorService service;

  @Autowired
  public RestController(CalculatorService service) {
    this.service = service;
  }
  @CrossOrigin(origins = "http://localhost:5173/")
  @PostMapping("/calculate")
  public ResponseEntity<?> calculate(@RequestBody CalculationRequest request) {
    logger.info("Received calculation request: {} {} {}", request.getNum1(), request.getOperator(), request.getNum2());
    try {
      double result = service.calculate(request);
      return ResponseEntity.ok(new CalculationResponse(result));
    } catch (ArithmeticException e) {
      logger.error("Calculation error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
    } catch (Exception e) {
      logger.error("Unexpected error: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
    }

  }
}
