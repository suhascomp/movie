package com.movie.moviecatalogservice.resource;

import com.movie.moviecatalogservice.models.DBSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/greet")
public class GreetController {

    @Value("${my.greetings}")
    private String greetings;

    @Value("${my.default: default value }")
    private String defaultVal;

    @Value(" Static string")
    private String staticString;

    @Value("${my.list.numbers}")
    private List<String> numbers;

    @Value("#{${my.map.dbValues}}")
    private Map<String, String> dbDetails;

    @Autowired
    private DBSettings dbSettings;

    @GetMapping("/welcome")
    public String greetings() {
        return greetings + defaultVal + staticString;
    }

    @GetMapping("/details")
    public String getDetails() {
        return " " + numbers + dbDetails;
    }

    @GetMapping("/dbSettings")
    public  String getDbSettings() {
        return dbSettings.toString();
    }

}
