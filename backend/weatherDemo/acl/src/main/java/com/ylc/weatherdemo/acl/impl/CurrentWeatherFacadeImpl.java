package com.ylc.weatherdemo.acl.impl;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import com.ylc.weatherdemo.acl.convert.CurrentWeatherConvertUtil;
import com.ylc.weatherdemo.base.exception.BaseExceptionEnum;
import com.ylc.weatherdemo.base.exception.BaseRuntimeException;
import com.ylc.weatherdemo.facade.dto.acl.CurrentWeatherAclDTO;
import com.ylc.weatherdemo.facade.service.acl.CurrentWeatherAclFacade;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class CurrentWeatherFacadeImpl implements CurrentWeatherAclFacade {
    @Value("${weather_token}")
    public String apiToken;

    private static OpenWeatherMapClient openWeatherClient;

    private OpenWeatherMapClient getOpenWeatherClient(){
        if(Objects.isNull(openWeatherClient)){
            if (StringUtils.isBlank(apiToken)){
                throw BaseRuntimeException.getInstance(BaseExceptionEnum.TOKEN_ERROR);
            }
            openWeatherClient = new OpenWeatherMapClient(apiToken);
            openWeatherClient.setReadTimeout(10000);
            openWeatherClient.setConnectionTimeout(10000);
        }
        return openWeatherClient;
    }


    @Override
    public Optional<CurrentWeatherAclDTO> loadCurrentWeatherByCityName(String cityName) {
        log.info("load current weather city: " + cityName);
        Weather weather;
        try {
            weather = getOpenWeatherClient()
                    .currentWeather()
                    .single()
                    .byCityName(cityName)
                    .language(Language.CHINESE_SIMPLIFIED)
                    .unitSystem(UnitSystem.METRIC)
                    .retrieve()
                    .asJava();
        } catch (Exception e){
            log.error("load " +cityName + " weather error;" , e);
            if (e instanceof NoDataFoundException){
                if (StringUtils.equals(e.getMessage(), "Connect timed out")){
                    throw BaseRuntimeException.getInstance(BaseExceptionEnum.TIME_OUT);
                }
                if(StringUtils.startsWith(e.getMessage(), "Data for provided parameters wasn't found")){
                    throw BaseRuntimeException.getInstance(BaseExceptionEnum.NOT_LOCATION);
                }
            }
            throw BaseRuntimeException.getInstance(BaseExceptionEnum.SERVER_ERROR);
        }
        if (Objects.isNull(weather)){
            return Optional.empty();
        }
        return CurrentWeatherConvertUtil.from(weather);
    }


}
