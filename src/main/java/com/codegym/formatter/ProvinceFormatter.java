package com.codegym.formatter;

import com.codegym.exception.CustomerNotFound;
import com.codegym.model.Province;
import com.codegym.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class ProvinceFormatter implements Formatter<Province> {
    private IProvinceService provinceService;

//    Tiêm bằng Constructor
    @Autowired
    public ProvinceFormatter(IProvinceService provinceService){
        this.provinceService = provinceService;
    }

//    Cơ chế chuyển đổi từ String sang Object
    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        Optional<Province> provinceOptional = null;
        try {
            provinceOptional = provinceService.findById(Long.parseLong(text));
        } catch (CustomerNotFound e) {
            e.printStackTrace();
        }
        return provinceOptional.orElse(null);
    }

    @Override
    public String print(Province object, Locale locale) {
        return null;
    }


//    Chuyển từ String sang dạng ngày tháng thì dùng Converter
}
