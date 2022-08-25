package mindigulov.bot.botsell;

import java.sql.*;

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

        statement = connection.prepareStatement("INSERT INTO public.users (user_name) VALUES ('" +name+ "'::text)");
        statement.executeUpdate();
        System.out.println("запрос улетел");
        connection.close();
    }
    public void setSQL(String sql) throws SQLException {

    }

    public boolean checkId(Long id) throws SQLException {
        statement = connection.prepareStatement("SELECT EXISTS(SELECT user_telegram_id FROM public.users WHERE user_telegram_id = "+ id.toString() +" );");
        ResultSet resultSet = statement.executeQuery();
        Boolean checkId = false;
        while (resultSet.next()) {
            checkId = resultSet.getBoolean(1);
        }
            return checkId;
    }

    public void addUser(Long id, String name) throws SQLException {
        statement = connection.prepareStatement ("INSERT into public.users (user_name, user_telegram_id) VALUES ('" + name + "'," + id + ");");
        statement.executeUpdate();
        System.out.println(name + " с айдишником " + id + " добавлен");
    }

}
