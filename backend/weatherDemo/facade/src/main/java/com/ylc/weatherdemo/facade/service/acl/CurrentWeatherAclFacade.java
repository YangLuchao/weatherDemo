package com.ylc.weatherdemo.facade.service.acl;

import com.ylc.weatherdemo.facade.dto.acl.CurrentWeatherAclDTO;

import java.util.Optional;

public interface CurrentWeatherAclFacade {

    /**
     * 查询当前天气
     * @param cityName
     * @return
     */
    Optional<CurrentWeatherAclDTO> loadCurrentWeatherByCityName(String cityName);
}
