package com.ravenson.billgenerator.SharedTools.helpers;


import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static <T> List<T> ListConverter(Iterable<T> iterable) {
    List<T> result = new ArrayList<>();
    iterable.forEach(result::add);
    return result;
    }
}
