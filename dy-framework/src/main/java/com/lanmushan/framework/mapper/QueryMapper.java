package com.lanmushan.framework.mapper;

import com.lanmushan.framework.util.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import sun.reflect.generics.repository.ClassRepository;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * mapper基类
 * @author Administrator
 */

public interface QueryMapper<T> extends SelectQueryMapper<T>, Mapper<T> {


}
