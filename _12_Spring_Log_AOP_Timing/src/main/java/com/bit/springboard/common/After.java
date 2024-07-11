package com.bit.springboard.common;

public class After {
    public void afterLog() {
        System.out.println("[Log] 로직 처리 중 예외가 발생하던, 발생하지 않던 상관없이 로직이 종료되면 실행");
    }
}
