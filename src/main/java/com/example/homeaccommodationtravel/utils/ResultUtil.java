package com.example.homeaccommodationtravel.utils;

public class ResultUtil {

    //返回信息常量
    private static final String SUCCESS_MESSAGE = "success";
    private static final String FAIL_MESSAGE = "fail";
    //返回状态码常量
    private static final int SUCCESS_CODE = 200;
    private static final int FAIL_CODE = 500;
    //异常
    public static final int ERROR = 601;
    //用户未登录
    public static final int NO_LOGIN = 602;
    //登录超时
    public static final int HAVE_COLLECTION = 604;

    //状态码
    private Integer code;
    //返回信息
    private String msg;
    //返回的数据
    private Object obj;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    //私有化构造
    private ResultUtil() {
    }

    //全参构造
    public ResultUtil(Integer code, String msg, Object obj) {
        this.code = code;
        this.msg = msg;
        this.obj = obj;
    }

    //状态码+数据构造
    public ResultUtil(Integer code, Object obj) {
        this.code = code;
        this.obj = obj;
    }

    //状态码+错误信息构造
    public ResultUtil(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 默认成功响应
     *
     * @return
     */
    public static ResultUtil respinseSuccess() {
        return new ResultUtil(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    /**
     * 默认失败相应
     *
     * @return
     */
    public static ResultUtil respinseFail() {
        return new ResultUtil(FAIL_CODE, FAIL_MESSAGE);
    }

    /**
     * 自定义相应信息，成功响应
     *
     * @return
     */
    public static ResultUtil respinseSuccess(String msg,Object obj) {
        return new ResultUtil(SUCCESS_CODE, msg,obj);
    }

    /**
     * 自定义相应信息，成功响应
     *
     * @return
     */
    public static ResultUtil respinseSuccess(Integer code,String msg) {
        return new ResultUtil(code, msg);
    }

    /**
     * 自定义相应信息，成功响应
     *
     * @return
     */
    public static ResultUtil respinseSuccess(Object obj) {
        return new ResultUtil(SUCCESS_CODE, obj);
    }

    /**
     * 自定义相应信息，成功响应
     *
     * @return
     */
    public static ResultUtil respinseSuccess(String msg) {
        return new ResultUtil(SUCCESS_CODE, msg);
    }

    /**
     * 自定义信息，失败响应
     *
     * @return
     */
    public static ResultUtil respinseFail(String msg) {
        return new ResultUtil(ERROR, msg);
    }

    /**
     * 自定义状态吗和信息，成功响应
     *
     * @return
     */
    public static ResultUtil respinseSuccess(Integer code, String msg,Object obj) {
        return new ResultUtil(code, msg,obj);
    }

    /**
     * 自定义状态码和信息，失败响应
     *
     * @return
     */
    public static ResultUtil respinseFail(Integer code, String msg) {
        return new ResultUtil(code, msg);
    }

    /**
     * 自定义状态码和信息响应
     * 推荐使用
     * @return
     */
    public static ResultUtil getInstance(Integer code, String msg) {
        return new ResultUtil(code, msg);
    }
}
