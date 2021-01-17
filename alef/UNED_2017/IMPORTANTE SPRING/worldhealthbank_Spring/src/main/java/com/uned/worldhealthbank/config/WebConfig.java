package com.uned.worldhealthbank.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebConfig {
	@RequestMapping("/")
    String home() {
        return "index.html";
    }

}
