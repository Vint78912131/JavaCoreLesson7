package YandexWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    @SerializedName("city")
    private String CITY;
    @SerializedName("date")
    private String DATE;
    @SerializedName("condition")
    private String WEATHER_TEXT;
    @SerializedName("temp_avg")
    private Integer TEMPERATURE;

    public WeatherResponse(String DATE, String CITY, String WEATHER_TEXT, Integer TEMPERATURE) {
        this.DATE = DATE;
        this.CITY = CITY;
        this.WEATHER_TEXT = WEATHER_TEXT;
        this.TEMPERATURE = TEMPERATURE;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public Integer getTEMPERATURE() {
        return TEMPERATURE;
    }

    public void setTEMPERATURE(Integer TEMPERATURE) {
        this.TEMPERATURE = TEMPERATURE;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getWEATHER_TEXT() {
        return WEATHER_TEXT;
    }

    public void setWEATHER_TEXT(String WEATHER_TEXT) {
        this.WEATHER_TEXT = WEATHER_TEXT;
    }

    @Override
    public String toString() {
        return "В городе " + CITY + "\n" +
                "на дату " + DATE + "\n" +
                "ожидается " + WEATHER_TEXT + "\n" +
                "температура " + TEMPERATURE + "\n";
    }
}
