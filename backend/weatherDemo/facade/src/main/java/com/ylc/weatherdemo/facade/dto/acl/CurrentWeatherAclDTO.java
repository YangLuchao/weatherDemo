package com.ylc.weatherdemo.facade.dto.acl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrentWeatherAclDTO implements Serializable {

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
     * 气压
     */
    private Integer humidity;

    /**
     * 湿度
     */
    private Byte clouds;

    /**
     * 气压
     */
    private BigDecimal atmosphericPressure;


}
