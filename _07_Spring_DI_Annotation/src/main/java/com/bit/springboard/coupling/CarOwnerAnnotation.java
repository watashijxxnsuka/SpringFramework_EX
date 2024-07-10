package com.bit.springboard.coupling;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarOwnerAnnotation {
    public static void main(String[] args) {
        AbstractApplicationContext factory =
                new ClassPathXmlApplicationContext("root-context.xml");

        HyundaiCar hCar = (HyundaiCar) factory.getBean("hyundaiCar");
        hCar.volumeUp();
        hCar.volumeDown();

        factory.close();
    }
}
