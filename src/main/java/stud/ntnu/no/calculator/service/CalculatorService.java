package stud.ntnu.no.calculator.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import stud.ntnu.no.calculator.dao.CalculatorRepository;
import stud.ntnu.no.calculator.model.Calculation;

@Service
public class CalculatorService {
  private final static Logger logger = LoggerFactory.getLogger(CalculatorService.class);
  private final CalculatorRepository calculatorRepository;

  public CalculatorService(CalculatorRepository calculatorRepository) {this.calculatorRepository = calculatorRepository;}

  public List<Calculation> getCalculations(int userId, int page, int size) {
    return calculatorRepository.findCalculationsByUserId(userId, size, page*size);
  }

  public void saveCalculation(Calculation calculation) {
    calculatorRepository.saveCalculation(calculation);
  }
}
