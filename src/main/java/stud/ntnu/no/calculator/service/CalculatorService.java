package stud.ntnu.no.calculator.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import stud.ntnu.no.calculator.dao.CalculatorRepository;
import stud.ntnu.no.calculator.model.Calculation;
import stud.ntnu.no.calculator.model.CalculationRequest;

@Service
public class CalculatorService {
  private final static Logger logger = LoggerFactory.getLogger(CalculatorService.class);
  private final CalculatorRepository calculatorRepository;

  public CalculatorService(CalculatorRepository calculatorRepository) {this.calculatorRepository = calculatorRepository;}

  public List<Calculation> getCalculations(String userName, int page, int size) {
    return calculatorRepository.findCalculationsByUserName(userName, size, page*size);
  }

  public void saveCalculation(CalculationRequest request) {
    calculatorRepository.saveCalculation(request);
  }
}
