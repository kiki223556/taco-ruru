package ru.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication為executable JAR運行時的切入點，包含以下3個註解
    @SpringBootConfiguration 添加配置
    @EnableAutoConfiguration 自動配置
    @ComponentScan 宣告註解時，能自動掃描發現並註冊
*/
@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

}
