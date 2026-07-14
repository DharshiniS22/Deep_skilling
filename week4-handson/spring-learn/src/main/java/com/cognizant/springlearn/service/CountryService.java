package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
    private final List<Country> countryList;

    @SuppressWarnings("unchecked")
    public CountryService() {
        LOGGER.info("CountryService constructor start - loading countryList from country.xml.");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        this.countryList = (List<Country>) context.getBean("countryList", List.class);
        LOGGER.info("CountryService constructor end - loaded countries: {}", countryList);
    }

    public Country getCountry(String code) {
        LOGGER.info("getCountry service method start. Searching for code: {}", code);
        Country result = countryList.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.warn("Country with code {} not found.", code);
                    return new CountryNotFoundException("Country not found");
                });
        LOGGER.info("getCountry service method end. Found: {}", result);
        return result;
    }
}
