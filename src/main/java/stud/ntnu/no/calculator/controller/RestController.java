package stud.ntnu.no.calculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import stud.ntnu.no.calculator.model.CalculationRequest;
import stud.ntnu.no.calculator.model.CalculationResponse;
import stud.ntnu.no.calculator.service.CalculatorService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/calculator")
public class RestController {
  private final CalculatorService service;

  @Autowired
  public RestController(CalculatorService service) {
    this.service = service;
  }

  @PostMapping("/calculate")
  public CalculationResponse calculate(@RequestBody CalculationRequest request) {
    System.out.println(request.getNum1() + " " + request.getOperator() + " " + request.getNum2());
    double result = service.calculate(request);
    return new CalculationResponse(result);
  }
}
