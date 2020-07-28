package site.lanmushan.authservice.controller;

import site.lanmushan.authservice.AuthServiceApplication;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loginOut() {
    }

    @Test
    public void userLogin() {

        String loginPassword = new Md5Hash("e10adc3949ba59abbe56e057f20f883e", "3dbi17ad5lp2", 3).toString();
        System.out.println("登录密码" + loginPassword);
    }

    @Test
    public void getSysManageLoginCode() {
    }
}