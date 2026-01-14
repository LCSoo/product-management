package com.lcsoo.product_management;

import org.junit.jupiter.api.Test;

public class test {
    @Test
    void testCode() throws Exception {
       // System.out의 인코딩 (Java 18+)
        System.out.println("System.out 인코딩: " + System.getProperty("stdout.encoding"));
        
        // 운영체제 기본 인코딩 (Java 17+)
        System.out.println("OS 기본 인코딩: " + System.getProperty("native.encoding"));
        
        // JVM 기본 인코딩
        System.out.println("JVM 기본 인코딩(file.encoding): " + System.getProperty("file.encoding"));
        
        // Charset 클래스를 통한 확인
        System.out.println("Charset 기본값: " + java.nio.charset.Charset.defaultCharset());
    }
}
