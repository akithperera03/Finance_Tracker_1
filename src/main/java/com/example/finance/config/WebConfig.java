package com.example.finance.config;

import com.example.finance.converter.StringToTransactionTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StringToTransactionTypeConverter stringToTransactionTypeConverter;

    public WebConfig(StringToTransactionTypeConverter stringToTransactionTypeConverter) {
        this.stringToTransactionTypeConverter = stringToTransactionTypeConverter;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/categories").setViewName("category_management");
        registry.addViewController("/transactions-entry").setViewName("transaction_entry");
        registry.addViewController("/transactions-history").setViewName("transaction_history");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToTransactionTypeConverter);
    }
}