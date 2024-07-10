package com.bit.springboard.coupling;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarOwner_Solve_Coupling {
    public static void main(String[] args) {
        // 1. 스프링 컨테이너 구동
        // 스프링 컨테이너 객체 생성.
        // bean 엘리먼트로 등록되어 있는 클래스들의 객체를 자동으로 생성
        AbstractApplicationContext factory = 
                // 어떠한 설정파일로 스프링 컨테이너를 구동할지 지정
                new ClassPathXmlApplicationContext("root-context.xml");
        
        // 2. 의존성 검색(DL: Dependency Lookup): bean 객체로 생성된 객체를 찾는 기능
        // 의존성 주입(DI: Dependency Injection): 의존성 검색으로 찾은 객체를 변수에 담아주는 기능
        KiaCar kCar = (KiaCar) factory.getBean("kiaCar");
//        Car car = (Car)factory.getBean("kiaCar"); - 위 코드랑 동일.(강제 형변환)

        kCar.engineOn();
        kCar.speedUp();
        kCar.speedDown();
        kCar.engineOff();
        kCar.getCarAudio().volumeUp();
        kCar.getCarAudio().volumeDown();
        System.out.println(kCar.getColor());
        
//        // scope: "prototype" 객체를 요청할 때마다 새로운 객체 생성
//        car = (Car)factory.getBean("kiaCar");

        // lazy-init: "true" 스프링 컨테이너 구동 시 객체를 생성하지 않고 요청 시에 객체를 생성한다.
        HyundaiCar hCar = factory.getBean("hyundaiCar", HyundaiCar.class);

        hCar.engineOn();
        hCar.speedUp();
        hCar.speedDown();
        hCar.engineOff();
        hCar.volumeUp();
        hCar.volumeDown();
        System.out.println(hCar.getColor());

        // 3. Spring Container 구동 종료
        // 스프링 컨테이너가 종료되면서 생성되어 있던 bean 객체들을 모두 삭제한다.
        factory.close();
    }
}
