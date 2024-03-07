package org.catile.shortme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class ShortmeApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void createIdTest() {
        var nextInt = 1048575; //random.nextInt(1048576);
        var string = Integer.toHexString(nextInt);
        var result = "0".repeat(string.length() - 5) + string;
        Assert.isTrue(result.equals("fffff"), "result.equals(\"fffff\")");
    }
}
