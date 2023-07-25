package com.ylc.weatherdemo.server.service.impl;

import com.ylc.weatherdemo.facade.dto.acl.CurrentWeatherAclDTO;
import com.ylc.weatherdemo.facade.dto.service.CurrentWeatherServiceDTO;
import com.ylc.weatherdemo.facade.service.service.CurrentWeatherServiceFacade;
import com.ylc.weatherdemo.facade.service.acl.CurrentWeatherAclFacade;
import com.ylc.weatherdemo.server.entity.CurrentWeatherEntity;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CurrentWeatherServiceImpl implements CurrentWeatherServiceFacade {

    @Resource
    private CurrentWeatherAclFacade currentWeatherFacade;
    @Override
    public Optional<CurrentWeatherServiceDTO> loadCurrentWeather(String cityName) throws Exception {
        if (StringUtils.isBlank(cityName)){
            throw new IllegalAccessException("city name is blank!");
        }
        if(hasChinese(cityName)){
            cityName = chinese2pinyin(cityName);
        }
        // 调防腐层
        Optional<CurrentWeatherAclDTO> dtoO = currentWeatherFacade.loadCurrentWeatherByCityName(cityName);
        if (dtoO.isEmpty()){
            return Optional.empty();
        }
        CurrentWeatherAclDTO dto = dtoO.get();
        CurrentWeatherEntity entity = new CurrentWeatherEntity();
        // fixme 不建议用copy,查问题很难受
        BeanUtils.copyProperties(dto, entity);
        // 处理service逻辑
        //...
        CurrentWeatherServiceDTO serviceDTO = new CurrentWeatherServiceDTO();
        // fixme 不建议用copy,查问题很难受
        BeanUtils.copyProperties(entity, serviceDTO);
        return Optional.of(serviceDTO);
    }

    public static String chinese2pinyin(String  chinese){
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c : chinese.toCharArray()) {
            try {
                String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (pinyinArray != null && pinyinArray.length > 0) {
                    pinyin.append(pinyinArray[0]);
                } else {
                    pinyin.append(c);
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                pinyin.append(c);
            }
        }
        return pinyin.toString();
    }

    public static boolean hasChinese(String str) {
        // 使用正则表达式判断是否含有中文字符
        return Pattern.matches(".*[\u4e00-\u9fa5]+.*", str);
    }
}
