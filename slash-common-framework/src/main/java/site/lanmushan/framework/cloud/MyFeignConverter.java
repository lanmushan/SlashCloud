package site.lanmushan.framework.cloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import site.lanmushan.framework.constant.HTTPCode;
import site.lanmushan.framework.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.lang.Nullable;
import site.lanmushan.framework.util.ReflectionUtil;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Feign 返回数据情况自定义解析器
 *
 * @Author dy
 * @Date 2020/6/24 21:01
 * @Version 1.0
 */
@Slf4j
@ConditionalOnProperty(prefix = "slash", name = "cloud", havingValue = "true")
public class MyFeignConverter extends MappingJackson2HttpMessageConverter {
    public MyFeignConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected JavaType getJavaType(Type type, Class<?> contextClass) {
        return super.getJavaType(type, contextClass);
    }

    @Override
    public Object read(Type type, @Nullable Class<?> contextClass, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        System.out.println("获取 shuju.......");
        JavaType javaType = getJavaType(type, contextClass);
        return readJavaType(javaType, inputMessage);
    }

    private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) throws IOException {
        try {
            if (inputMessage instanceof MappingJacksonInputMessage) {
                Class<?> deserializationView = ((MappingJacksonInputMessage) inputMessage).getDeserializationView();
                if (deserializationView != null) {
                    return this.objectMapper.readerWithView(deserializationView).forType(javaType).
                            readValue(inputMessage.getBody());
                }
            }

            //默认情况 使用Message接收数据
            if (javaType.isTypeOrSuperTypeOf(Message.class)) {
                return this.objectMapper.readValue(inputMessage.getBody(), javaType);
            }

            //使用List接收数据
            if (javaType.isTypeOrSuperTypeOf(java.util.List.class)) {

                JavaType type = super.getJavaType(Message.class, Message.class);
                Message message = objectMapper.readValue(inputMessage.getBody(), type);
                if (message.getCode() == HTTPCode.E204.code) {
                    return new ArrayList<>();
                } else if (message.getCode() == HTTPCode.E200.code) {   //这里会自动转换
                    return message.getRows();
                } else {
                    throw new HttpMessageConversionException("远程接口返回错误!code=" + message.getCode() + ",msg=" + message.getMsg());
                }

            } else {
                //其他Object情况，所有接口返回Message,所以进行特殊处理
                JavaType type = super.getJavaType(Message.class, Message.class);
                Message message = objectMapper.readValue(inputMessage.getBody(), type);
                if (message.getCode() == HTTPCode.E200.code || message.getCode() == HTTPCode.E204.code) {
                    Class<?> reslutClass = (Class<?>) ReflectionUtil.getFieldValue(javaType, "_class");
                    //请求成功，但是无数据或有数据情况直接返回数据
                    return JSONObject.toJavaObject((JSON) JSONObject.toJSON(message.getRow()), reslutClass);

                } else {
                    //远程接口出现错误，直接抛出异常
                    throw new HttpMessageConversionException("远程接口返回错误!code=" + message.getCode() + ",msg=" + message.getMsg());
                }

            }

        } catch (InvalidDefinitionException ex) {
            throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
        } catch (JsonProcessingException ex) {
            throw new HttpMessageNotReadableException("JSON parse error: " + ex.getOriginalMessage(), ex, inputMessage);
        }
    }

}
