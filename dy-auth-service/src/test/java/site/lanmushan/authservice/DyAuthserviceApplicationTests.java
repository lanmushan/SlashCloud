package site.lanmushan.authservice;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DyAuthserviceApplicationTests {
    public DyAuthserviceApplicationTests() {
    }

    @Before
    public void befor() {
        throw new RuntimeException("11`");
    }

    @Test
    public void contextLoads() {


    }

}
