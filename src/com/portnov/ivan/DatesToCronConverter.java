package com.portnov.ivan;

public interface DatesToCronConverter {
    String convert(String[] dates) throws DatesToCronConvertException;
    String getImplementationInfo();
}
