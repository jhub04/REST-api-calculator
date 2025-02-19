package stud.ntnu.no.calculator;
import java.sql.*;




public class DatabaseWrapper {
  public static final String sql = "select * from users";

  public static void main(String[] args) throws Exception {
    Connection con = DriverManager.getConnection("jdbc:mysql://namox.idi.ntnu.no:3306/jonathhu?user=jonathhu&password=AhezGyNH");

    Statement stmt = con.createStatement();
    ResultSet res = stmt.executeQuery(sql);

    while (res.next()) {
      System.out.println(res.getString("id"));
      System.out.println(res.getString("username"));
      System.out.println(res.getString("password"));
    }

  }
}
