package com.ylc.weatherdemo.api.controller;

import com.ylc.weatherdemo.api.controller.request.CityNameRequest;
import com.ylc.weatherdemo.api.controller.response.ResponseCommand;
import com.ylc.weatherdemo.api.controller.vo.CurrentWeatherVO;
import com.ylc.weatherdemo.facade.dto.service.CurrentWeatherServiceDTO;
import com.ylc.weatherdemo.facade.service.service.CurrentWeatherServiceFacade;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("weather/current")
public class CurrentWeatherController {

    @Resource
    private CurrentWeatherServiceFacade currentWeatherServiceFacade;

    @PostMapping(value = "weatherByCityName",name = "load current weather")
    public Object currentWeatherByCityName(@RequestBody CityNameRequest cityName) throws Exception {
        if (StringUtils.isBlank(cityName.getCityName())){
            throw new IllegalAccessException("cityName is blank");
        }
        Optional<CurrentWeatherServiceDTO> entityO = currentWeatherServiceFacade.loadCurrentWeather(cityName.getCityName());
        return entityO.map(CurrentWeatherVO::from).map(ResponseCommand::success).orElseGet(ResponseCommand::success);
    }
}
