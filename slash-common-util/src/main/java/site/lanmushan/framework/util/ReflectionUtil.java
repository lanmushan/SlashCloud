package site.lanmushan.framework.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 反射的Utils函数集合. 提供访问私有变量,获取泛型类型Class,提取集合中元素的属性等Utils函数.
 *
 * @author lei
 */
@Slf4j
public class ReflectionUtil {

 

    private ReflectionUtil() {
    }

    /**
     * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
     */
    public static Object getFieldValue(final Object object, final String fieldName) {
        Field field = getDeclaredField(object, fieldName);

        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }

        makeAccessible(field);

        Object result = null;
        try {
            result = field.get(object);
        } catch (IllegalAccessException e) {
            log.error("不可能抛出的异常{}", e.getMessage());
        }
        return result;
    }

    public static String getFieldType(final Object object, final String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        return field.getType().toString();
    }

    /**
     * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
     */
    public static void setFieldValue(final Object object, final String fieldName, final Object value) {
        Field field = getDeclaredField(object, fieldName);
        if (field == null) {
            throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
        }
        makeAccessible(field);

        try {
            if (field.getType().toString().equals("class java.lang.Integer")) {
                field.set(object, Integer.parseInt(value.toString()));
            } else if (field.getType().toString().equals("class java.lang.String")) {
                field.set(object, value.toString());
            } else if (field.getType().toString().equals("class java.lang.Long")) {
                field.set(object, Long.parseLong(value.toString()));
            } else if (field.getType().toString().equals("class java.util.Date")) {
                if (value instanceof Long) {
                    field.set(object, value);
                }
                if (value instanceof Timestamp) {
                    field.set(object, value);
                } else {
                    SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss.S");
                    field.set(object, sdf.parse(value.toString()));
                }

            } else {
                field.set(object, value);
            }


        } catch (IllegalAccessException e) {
            log.error("不可能抛出的异常:{}", e.getMessage());
        } catch (ParseException e) {
            log.error("日期转换出错:{}", e.getMessage());
        }
    }

    /**
     * 循环向上转型,获取对象的DeclaredField.
     */
    protected static Field getDeclaredField(final Object object, final String fieldName) {
        return getDeclaredField(object.getClass(), fieldName);
    }

    /**
     * 循环向上转型,获取类的DeclaredField.
     */
    @SuppressWarnings("unchecked")
    protected static Field getDeclaredField(final Class clazz, final String fieldName) {
        for (Class superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                // Field不在当前类定义,继续向上转型
            }
        }
        return null;
    }

    /**
     * 强制转换fileld可访问.
     */
    protected static void makeAccessible(final Field field) {
        if (!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
            field.setAccessible(true);
        }
    }

    /**
     * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends HibernateDao<User>
     *
     * @param clazz The class to introspect
     * @return the first generic declaration, or Object.class if cannot be
     * determined
     */
    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(final Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }

    /**
     * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends
     * HibernateDao<User,Long>
     *
     * @param clazz clazz The class to introspect
     * @param index the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     * determined
     */

    @SuppressWarnings("unchecked")
    public static Class getSuperClassGenricType(final Class clazz, final int index) {

        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            log.warn(clazz.getSimpleName() + "'s superclass not ParameterizedType");
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            log.warn("Index: " + index + ", Size of " + clazz.getSimpleName() + "'s Parameterized Type: " + params.length);
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            log.warn(clazz.getSimpleName() + " not set the actual class on superclass generic parameter");
            return Object.class;
        }
        return (Class) params[index];
    }

    /**
     * 提取集合中的对象的属性,组合成List.
     *
     * @param collection   来源集合.
     * @param propertyName 要提取的属性名.
     */
    @SuppressWarnings("unchecked")
    public static List fetchElementPropertyToList(final Collection collection, final String propertyName) throws Exception {

        List list = new ArrayList();

        for (Object obj : collection) {
            list.add(PropertyUtils.getProperty(obj, propertyName));
        }

        return list;
    }

    /**
     * 提取集合中的对象的属性,组合成由分割符分隔的字符串.
     *
     * @param collection   来源集合.
     * @param propertyName 要提取的属性名.
     * @param separator    分隔符.
     */
    @SuppressWarnings("unchecked")
    public static String fetchElementPropertyToString(final Collection collection, final String propertyName, final String separator) throws Exception {
        List list = fetchElementPropertyToList(collection, propertyName);
        return StringUtils.join(list.toArray(), separator);
    }
}