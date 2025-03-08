package stud.ntnu.no.calculator.model;

public class CalculationRequest {
  private String userName;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

  private String expression;

  public CalculationRequest(String userName, String expression) {
    this.userName = userName;
    this.expression = expression;
  }




}
