package com.ylc.weatherdemo.acl.convert;

import com.github.prominence.openweathermap.api.model.*;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.ylc.weatherdemo.facade.dto.acl.CurrentWeatherAclDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class CurrentWeatherConvertUtil {
    public static Optional<CurrentWeatherAclDTO> from(Weather weather){
        // todo 建议用这种
        if(Objects.isNull(weather)){
            return Optional.empty();
        }
        CurrentWeatherAclDTO currentWeather = new CurrentWeatherAclDTO();

        LocalDateTime calculationTime = weather.getCalculationTime();
        currentWeather.setCalculationTime(Date.from(calculationTime.atZone(ZoneId.systemDefault()).toInstant()));

        WeatherState weatherState = weather.getWeatherState();
        currentWeather.setCurrentState(weatherState.getDescription());
        currentWeather.setCurrentStateIcon(weatherState.getWeatherIconUrl());

        Temperature temperature = weather.getTemperature();
        currentWeather.setMinTemperature(BigDecimal.valueOf(temperature.getMinTemperature()));
        currentWeather.setMaxTemperature(BigDecimal.valueOf(temperature.getMaxTemperature()));
        currentWeather.setAvgTemperature(BigDecimal.valueOf(temperature.getValue()));
        currentWeather.setFeelsLike(BigDecimal.valueOf(temperature.getFeelsLike()));
        currentWeather.setTemperatureUnit(temperature.getUnit());

        Humidity humidity1 = weather.getHumidity();
        currentWeather.setHumidity(humidity1.getValue());

        Clouds clouds1 = weather.getClouds();
        currentWeather.setClouds(clouds1.getValue());

        AtmosphericPressure atmosphericPressure1 = weather.getAtmosphericPressure();
        currentWeather.setAtmosphericPressure(BigDecimal.valueOf(atmosphericPressure1.getValue()));

        return  Optional.of(currentWeather);
    }

}
