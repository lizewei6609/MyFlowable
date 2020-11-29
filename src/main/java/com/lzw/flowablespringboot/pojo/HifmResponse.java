package com.lzw.flowablespringboot.pojo;

import java.util.HashMap;

/**
 * @auther LZW
 * @date 2020/5/12 19:46
 */
public class HifmResponse extends HashMap<String, Object> {

    public static HifmResponse result(int rows) {
        return rows > 0 ? success() : failure();
    }

    public static HifmResponse success() {
        HifmResponse result = new HifmResponse();
        result.put("code", 200);
        result.put("message", "操作成功!");
        return result;
    }

    public static HifmResponse success(int code, String message) {
        HifmResponse result = new HifmResponse();
        result.put("code", code);
        result.put("message", message);
        return result;
    }

    public static HifmResponse failure() {
        HifmResponse result = new HifmResponse();
        result.put("code", 500);
        result.put("error", "操作失败!");
        return result;
    }

    public static HifmResponse failure(int code, String message) {
        HifmResponse result = new HifmResponse();
        result.put("code", code);
        result.put("error", message);
        return result;
    }

    public HifmResponse message(String message) {
        this.put("message", message);
        return this;
    }

    public HifmResponse data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public HifmResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public String getMessage() {
        return String.valueOf(get("message"));
    }

    public Object getData() {
        return get("data");
    }

    public HifmResponse putCodeData(int code, Object data) {
        super.put("code", code);
        super.put("data", data);
        return this;
    }

    public HifmResponse putCodeMessageData(int code, String message, Object data) {
        super.put("code", code);
        super.put("message", message);
        super.put("data", data);
        return this;
    }
}
