package org.xdclassredis.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Description TODO
 * @Author renxiaoqing
 * @Date 2026/8/14 18:00
 * @Version 1.0
 **/
public class JsonUtil {
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    /**
     * 将对象转换成json字符串。

     * @return
     */
    public static String objectToJson(Object data) {
        try {
            String string = MAPPER.writeValueAsString(data);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 将json结果集转化为对象
     *
     * @param jsonData json数据
     * @param beanType 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
