package com.example.weatherapp.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @Expose
    @SerializedName("cod")
    private int cod;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("timezone")
    private int timezone;
    @Expose
    @SerializedName("sys")
    private Sys sys;
    @Expose
    @SerializedName("dt")
    private int dt;
    @Expose
    @SerializedName("clouds")
    private Clouds clouds;
    @Expose
    @SerializedName("wind")
    private Wind wind;
    @Expose
    @SerializedName("visibility")
    private int visibility;
    @Expose
    @SerializedName("main")
    private Main main;
    @Expose
    @SerializedName("base")
    private String base;
    @Expose
    @SerializedName("weather")
    private List<Weather> weather;
    @Expose
    @SerializedName("coord")
    private Coord coord;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public static class Sys {
        @Expose
        @SerializedName("sunset")
        private int sunset;
        @Expose
        @SerializedName("sunrise")
        private int sunrise;
        @Expose
        @SerializedName("country")
        private String country;
        @Expose
        @SerializedName("id")
        private int id;
        @Expose
        @SerializedName("type")
        private int type;

        public int getSunset() {
            return sunset;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }

        public int getSunrise() {
            return sunrise;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    public static class Clouds {
        @Expose
        @SerializedName("all")
        private String all;

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
        }
    }

    public static class Wind {
        @Expose
        @SerializedName("deg")
        private int deg;
        @Expose
        @SerializedName("speed")
        private String speed;

        public int getDeg() {
            return deg;
        }

        public void setDeg(int deg) {
            this.deg = deg;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }
    }

    public static class Main {
        @Expose
        @SerializedName("humidity")
        private String humidity;
        @Expose
        @SerializedName("pressure")
        private int pressure;
        @Expose
        @SerializedName("temp_max")
        private double temp_max;
        @Expose
        @SerializedName("temp_min")
        private double temp_min;
        @Expose
        @SerializedName("feels_like")
        private double feels_like;
        @Expose
        @SerializedName("temp")
        private double temp;

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }
    }

    public static class Weather {
        @Expose
        @SerializedName("icon")
        private String icon;
        @Expose
        @SerializedName("description")
        private String description;
        @Expose
        @SerializedName("main")
        private String main;
        @Expose
        @SerializedName("id")
        private int id;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class Coord {
        @Expose
        @SerializedName("lat")
        private double lat;
        @Expose
        @SerializedName("lon")
        private double lon;

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }
}
