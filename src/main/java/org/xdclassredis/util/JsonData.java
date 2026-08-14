package org.xdclassredis.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JsonData implements Serializable {

    private Integer code;
    private Object data;
    private String msg;

    public JsonData(int code,Object data,String msg){
        this.data = data;
        this.msg = msg;
        this.code = code;
    }
    /**
     * 成功，不传入数据
     * @return
     */
    public static JsonData buildSuccess() {
        return new JsonData(0, null, null);
    }
    /**
     *  成功，传入数据
     * @param data
     * @return
     */
    public static JsonData buildSuccess(Object data) {
        return new JsonData(0, data, null);
    }
    /**
     * 失败，传入描述信息
     * @param msg
     * @return
     */
    public static JsonData buildError(String msg) {
        return new JsonData(-1, null, msg);
    }

}
