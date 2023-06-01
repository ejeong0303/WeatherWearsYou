package WeatherWearsYou.external;
public class FormInputDTO {
    private String city;
    private String gender;
    private String targetDate;
    private String style;
    private String weather;
    private String precipitationRate;
    private String minTemp;
    private String maxTemp;


    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getTargetDate() {
        return targetDate;
    }
    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }


    public String getWeather() {
        return weather;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }
    public String getPrecipitationRate() {
        return precipitationRate;
    }
    public void setPrecipitationRate(String precipitationRate) {
        this.precipitationRate = precipitationRate;
    }
    public String getMinTemp() {
        return minTemp;
    }
    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }
    public String getMaxTemp() {
        return maxTemp;
    }
    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }
}