package com.lanmushan.cypher.aes;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author dy
 * @Date 2020/6/21 12:18
 * @Version 1.0
 */
@Slf4j
public class AesUtilTest {

    @Test
    public void encryptString() {
        String content = "sdaflsdjflasj";
        String passowrd = "asdflasjlasjlj";
        String temp;

        temp = AESUtil.encryptString(content, passowrd);
        log.info("密文:{}", temp);
        temp = AESUtil.decryptString(temp, passowrd);
        log.info("明文:{}", temp);

    }

}