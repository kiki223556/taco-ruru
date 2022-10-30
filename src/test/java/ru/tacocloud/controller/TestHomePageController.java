package ru.tacocloud.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

//@WebMvcTest：測試Spring MVC application內容，括號放(RegisterRestController.class)，
//告訴Spring boot只需要測試此特定controller bean，以及其他mvc所需框架bean
//如果沒有寫特定的controller，則程式會跑全部的controller，浪費時間且又沒效率
@AutoConfigureMockMvc
@WebMvcTest(HomePageController.class)
public class TestHomePageController {

    // 建立執行此controller所需模擬MVC的環境
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk()) // http 200
                .andExpect(view().name("home"))
                .andExpect(content().string(
                        containsString("Welcome to...")));
    }
}
