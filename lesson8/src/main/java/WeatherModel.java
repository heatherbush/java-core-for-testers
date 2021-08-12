import java.io.IOException;
import java.sql.SQLException;

public interface WeatherModel {
    void getWeather(String selectedCity, Period period, String base) throws IOException, SQLException;
}
