package mindigulov.bot.botsell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQL {
    Connection connection;
    PreparedStatement statement;
    final String url = "jdbc:postgresql://localhost:5432/Sibset";
    final String user = "postgres";
    final String password = "230493den";

    public SQL() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("Коннект выполнен");

    }

    public void setName(String name) throws SQLException {
        statement = connection.prepareStatement("INSERT INTO public.users (user_name) VALUES (" + name + ");");
        statement.executeUpdate();
        connection.close();
    }
    public void setSQL(String sql) throws SQLException {

    }
}
