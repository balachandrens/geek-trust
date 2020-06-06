package com.geektrust.lengaburutraffic.weather;

import java.util.HashMap;
import java.util.Map;

public class WeatherInfo {

    private final Map<WeatherType, Double> weatherInfo;

    private WeatherInfo(WeatherInfoBuilder weatherInfoBuilder){
        this.weatherInfo = weatherInfoBuilder.weatherInfo;
    }

    public Double getCraterInfluence(WeatherType weatherType){
        if (!weatherInfo.containsKey(weatherType)) return 0.0;
        return weatherInfo.get(weatherType);
    }

    public static class WeatherInfoBuilder{
        private final Map<WeatherType, Double> weatherInfo = new HashMap<>();
        public WeatherInfoBuilder addWeather(WeatherType weatherType, Double craterInfluence){
            weatherInfo.put(weatherType,craterInfluence);
            return this;
        }
        public WeatherInfo build(){
            return new WeatherInfo(this);
        }
    }

}
