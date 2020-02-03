import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "testtest";
        //Запрос на получение имени курса и среднего количества продаж в месяц
        String query = "select course_name, count(subscription_date) / (select count(distinct MONTH(subscription_date))) as avg_value from purchaselist group by course_name order by course_name;";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("course_name");
                String average_sale = resultSet.getString("avg_value");
                System.out.println(name + " " + average_sale);
            }

            connection.close();
            resultSet.close();
            statement.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
