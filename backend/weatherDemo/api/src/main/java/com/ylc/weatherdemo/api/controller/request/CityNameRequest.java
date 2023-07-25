package com.ylc.weatherdemo.api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CityNameRequest implements Serializable {
    private String cityName;
}
