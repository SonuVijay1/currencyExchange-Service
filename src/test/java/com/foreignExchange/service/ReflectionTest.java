package com.foreignExchange.service;

import com.foreignExchange.controller.FxRateController;

import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        Method method = FxRateController.class.getMethod("getFxRate", String.class);
        System.out.println(method.getParameters()[0].getName());
    }
}
