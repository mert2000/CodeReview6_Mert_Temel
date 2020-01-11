package sample;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbClass {
    private Connection conn;
    private static final String teacherTable = "teacher";

    public dbClass() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to Database...");
        conn = DriverManager.getConnection
                (
                        "jdbc:mysql://localhost:3306/cr6" +
                                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root",
                        ""
                );
        conn.setAutoCommit(true);
        conn.setReadOnly(false);
    }

    public void closeDb() throws SQLException {
        conn.close();
    }

    public List<schoollist> getAllRows() throws SQLException {
        String sql = "SELECT * FROM cr6.teacher ORDER BY ID";
        PreparedStatement prst = conn.prepareStatement(sql);
        ResultSet rset = prst.executeQuery();
        List<schoollist> list = new ArrayList<>();
        while (rset.next()) {
            int i = rset.getInt("id");
            String name = rset.getString("name");
            String surename = rset.getString("surename");
            String email = rset.getString("email");
            list.add(new schoollist(i, name, surename, email));
        }
        prst.close();
        return list;
    }
}

