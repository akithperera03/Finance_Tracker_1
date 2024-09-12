package com.example.finance.converter;

import com.example.finance.model.TransactionType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTransactionTypeConverter implements Converter<String, TransactionType> {

    @Override
    public TransactionType convert(String source) {
        try {
            return TransactionType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }}