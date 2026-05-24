import java.sql.*;

class dao {
    public static Connection createconnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/emp?useSSL=false&serverTimezone=UTC";
            String user = "username";
            String pass = "password";

            con = DriverManager.getConnection(url, user, pass);
           
        } catch (Exception e) {
            System.out.println("❌ Connection failed:");
            e.printStackTrace();
        }
        return con;
    }
}
