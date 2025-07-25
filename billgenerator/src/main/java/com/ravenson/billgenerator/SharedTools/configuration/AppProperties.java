package com.ravenson.billgenerator.SharedTools.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppProperties {

    private Integer pdv = 20;
    private Integer discountByChild = 10;
}
