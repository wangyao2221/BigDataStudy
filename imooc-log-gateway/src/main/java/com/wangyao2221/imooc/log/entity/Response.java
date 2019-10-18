package com.wangyao2221.imooc.log.entity;

public class Response<T> {
    public static final int DEFAULT = 0;
    public static final int INFO = -1;
    public static final int ERROR = 1;
    public static final int WARN = 2;

    private int code;
    private String msg;
    private T result;

    public Response() {
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, T t, String msg) {
        this.code = code;
        this.result = t;
        this.msg = msg;
    }

    /**
     * 这个是啥意思
     * @param code
     * @param msg
     * @return
     */
    public static Response Check(int code, String msg){
        Response response = new Response(code, msg);
        return new Response(DEFAULT, response, "finish");
    }
    public static Response Result(int code, Object result) {
        return new Response(code, result, null);
    }
    public static Response Success() {
        return new Response(DEFAULT, "success");
    }
    public static Response Info(String msg) {
        return new Response(INFO, msg);
    }
    public static Response Warn(String msg) {
        return new Response(WARN, msg);
    }
    public static Response Error(String msg) {
        return new Response(ERROR, msg);
    }
    public static Response Error() {
        return new Response(ERROR, "fail");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
