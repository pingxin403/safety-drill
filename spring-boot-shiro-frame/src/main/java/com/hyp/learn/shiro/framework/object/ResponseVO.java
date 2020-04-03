package com.hyp.learn.shiro.framework.object;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hyp.learn.shiro.business.enums.BaseResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.List;

/**
 * controller返回json模板
 *
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.shiro.framework.object
 * hyp create at 20-3-29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ResponseVO<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseVO(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseVO(BaseResponseCode status, T data) {
        this(status.getCode(), status.getMsg(), data);
    }

    public String toJson() {
        T t = this.getData();
        if (t instanceof List || t instanceof Collection) {
            return JSONObject.toJSONString(this, SerializerFeature.WriteNullListAsEmpty);
        } else {
            return JSONObject.toJSONString(this, SerializerFeature.WriteMapNullValue);
        }
    }
}
