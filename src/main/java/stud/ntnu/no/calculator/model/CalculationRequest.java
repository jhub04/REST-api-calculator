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

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  private String expression;
  private String result;

  public CalculationRequest(String userName, String expression, String result) {
    this.userName = userName;
    this.expression = expression;
    this.result = result;
  }




}
