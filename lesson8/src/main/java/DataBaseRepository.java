import entity.Weather;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseRepository {
    private String insertWeather = "insert into weather (city, date, generalForecast, temperatureMin," +
            "temperatureMax, dayForecast, nightForecast) values (?, ?, ?, ?, ?, ?, ?)";
    private String getWeather = "select * from weather";
    private static final String DB_PATH = "jdbc:sqlite:weather.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean saveWeatherToDataBase(Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getDate());
            saveWeather.setString(3, weather.getGeneralForecast());
            saveWeather.setString(4, weather.getTemperatureMin());
            saveWeather.setString(5, weather.getTemperatureMax());
            saveWeather.setString(6, weather.getDayForecast());
            saveWeather.setString(7, weather.getNightForecast());
            return saveWeather.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new SQLException("Сохранение погоды в базу данных не выполнено!");
    }

    public List<Weather> getSavedToDBWeather() {
        List<Weather> weathers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getWeather);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.println(" ");
                System.out.print(resultSet.getString("city"));
                System.out.println(" ");
                System.out.print(resultSet.getString("date"));
                System.out.println(" ");
                System.out.print(resultSet.getString("generalforecast"));
                System.out.println(" ");
                System.out.print(resultSet.getString("temperaturemin"));
                System.out.println(" ");
                System.out.print(resultSet.getString("temperaturemax"));
                System.out.println(" ");
                System.out.print(resultSet.getString("dayforecast"));
                System.out.println(" ");
                System.out.print(resultSet.getString("nightforecast"));
                System.out.println(" ");
                weathers.add(new Weather(resultSet.getString("city"),
                        resultSet.getString("date"),
                        resultSet.getString("generalforecast"),
                        resultSet.getString("temperaturemin"),
                        resultSet.getString("temperaturemax"),
                        resultSet.getString("dayforecast"),
                        resultSet.getString("nightforecast")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weathers;
    }

    public static void printDBWeather(int i) {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            Statement statement = connection.createStatement();

            if (i == 1 | i == 5) {
                String number = Integer.toString(i);
                System.out.println("Данные по погоде из базы:\n");
                ResultSet resultSet = statement.executeQuery("select * from weather order by id desc limit " + number + ";");
                while (resultSet.next()) {
                        System.out.print("Город: " + resultSet.getString("city") + "\n");
                        System.out.print("Дата: " + resultSet.getString("date") + "\n");
                        System.out.print("Общий прогноз: " + resultSet.getString("generalforecast") + "\n");
                        System.out.print("Температурный минимум: " + resultSet.getString("temperaturemin") + "°C\n");
                        System.out.print("Температурный максимум: " + resultSet.getString("temperaturemax") + "°C\n");
                        System.out.print("День: " + resultSet.getString("dayforecast") + "\n");
                        System.out.print("Ночь: " + resultSet.getString("nightforecast") + "\n");
                        System.out.println();
                    }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
