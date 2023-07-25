package com.ylc.weatherdemo.facade.service.service;

import com.ylc.weatherdemo.facade.dto.acl.CurrentWeatherAclDTO;
import com.ylc.weatherdemo.facade.dto.service.CurrentWeatherServiceDTO;

import java.util.Optional;

public interface CurrentWeatherServiceFacade {

    Optional<CurrentWeatherServiceDTO> loadCurrentWeather(String cityName) throws Exception;

}
