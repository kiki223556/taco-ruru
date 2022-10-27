package ru.tacocloud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/*
@SpringBootTest 使用JUnit測試框架，執行單元測試,整合測試,分類測試等。3點應注意：
    1.測試類別必須是公開的(public)
    2.在測試類別後加上點綴字(Test)來命名
    3.測試類別的package，和待測類別的路徑相同
command line：$ ./mvnw test
 */

@SpringBootTest
class TacoCloudApplicationTests {

    @Test
    void contextLoads() {
    }

}
