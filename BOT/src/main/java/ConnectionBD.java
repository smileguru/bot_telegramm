import java.sql.*;

public class ConnectionBD {
    private String usname = "postgres";
    private String usPassword = "262824";
    private String host = "jdbc:postgresql://localhost:5432/telegram";
    private String driver = "org.postgresql.Driver";

    public ResultSet getResult (String query) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection con = DriverManager.getConnection(host, usname, usPassword);
        Statement stmt = con.createStatement();
        return stmt.executeQuery(query);
    }

    public void setResult (String query) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection con = DriverManager.getConnection(host, usname, usPassword);
        Statement stmt = con.createStatement();
        stmt.executeUpdate(query);
    }
}
