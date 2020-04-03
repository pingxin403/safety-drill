package com.hyp.learn.shiro.framework.redis.serializer;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.*;

/**
 * 重写序列化 序列化为字节码
 *
 * @author hyp
 */
public class MyRedisSerializer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut;
        try {
            objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteOut.toByteArray();
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn;
        Object obj;
        try {
            objIn = new ObjectInputStream(byteIn);
            obj = objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return obj;
    }


}

