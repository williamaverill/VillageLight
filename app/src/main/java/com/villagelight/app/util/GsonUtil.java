package com.villagelight.app.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * 使用例子
 * 要获得的对象
 * ResultData<User> rd = new ResultData<User>();
 * 获得对象类型
 * Type type = new TypeToken<ResultData<User>>() {}.getType();
 * 将json字符串转换成对象
 * rd = GsonUtil.fromJson(json, type);
 * 获得User对象
 * User user=rd.getData();
 */
public class GsonUtil {
    public static Gson gson;
    private static final GsonUtil utils = new GsonUtil();

    private GsonUtil() {
        gson = createGson();
    }

    /**
     * 创建gson对象
     *
     * @return
     */
    public static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        DateDeserializer ds = new DateDeserializer();
        gsonBuilder.registerTypeAdapter(Date.class, ds);
        return gsonBuilder.create();
    }

    /**
     * 将对象转换为json串
     *
     * @param src 待转换对象
     * @return
     */
    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    /**
     * 将对象转换为json串
     *
     * @param src       待转换对象
     * @param typeOfSrc 对象的type类型
     * @return
     */
    public static String toJson(Object src, Type typeOfSrc) {
        return gson.toJson(src, typeOfSrc);
    }

    /**
     * 将json串转换为对象
     *
     * @param json     待转换json串
     * @param classOfT 对象的class类型
     * @param <T>      T
     * @return
     * @throws JsonSyntaxException
     */
    public synchronized static <T> T fromJson(String json, Class<T> classOfT) {

        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json穿转换为对象
     *
     * @param json    待转换json串
     * @param typeOfT 对象的type类型
     * @param <T>     T
     * @return
     * @throws JsonSyntaxException
     */
    public synchronized static <T> T fromJson(String json, Type typeOfT)
            throws JsonSyntaxException {
        return gson.fromJson(json, typeOfT);
    }
}
