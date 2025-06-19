package org.learning.spring.boot.learning;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class ApplicationTests {

    @Test
    void contextLoads() {
        // This test will simply check if the application context loads successfully.
        // No additional assertions are needed here.
        assertEquals(1,1);
    }
}
