package com.ravenson.billgenerator.SharedTools.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppProperties {

    private int pdv = 20;
    private int discountByChild = 10;
    private int paymentCode = 189;
    private String valute = "rsd";
    private  Integer businessActivityCode = 8510;
}
