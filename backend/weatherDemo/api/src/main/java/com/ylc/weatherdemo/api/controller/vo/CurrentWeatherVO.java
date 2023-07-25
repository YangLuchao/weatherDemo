package com.ylc.weatherdemo.api.controller.vo;

import com.ylc.weatherdemo.facade.dto.acl.CurrentWeatherAclDTO;
import com.ylc.weatherdemo.facade.dto.service.CurrentWeatherServiceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrentWeatherVO implements Serializable {

    /**
     * 带有数据计算时间的本地日期时间对象
     */
    private Date calculationTime;

    /**
     * 当前状态
     */
    private String currentState;

    /**
     * 当前状态图标
     */
    private String currentStateIcon;


    /**
     * 最低温
     */
    private BigDecimal minTemperature;

    /**
     * 最高温
     */
    private BigDecimal maxTemperature;

    /**
     * 平均气温
     */
    private BigDecimal avgTemperature;

    /**
     * 体感温度
     */
    private BigDecimal feelsLike;

    /**
     * 气温单位
     */
    private String temperatureUnit;

    /**
     * 湿度
     */
    private Integer humidity;

    /**
     * 云量
     */
    private Byte clouds;

    /**
     * 气压
     */
    private BigDecimal atmosphericPressure;

    public static CurrentWeatherVO from(CurrentWeatherServiceDTO entity){
        if (Objects.isNull(entity)){
            return null;
        }
        CurrentWeatherVO vo = new CurrentWeatherVO();
        // fixme 不建议用copy,查问题很难受
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
