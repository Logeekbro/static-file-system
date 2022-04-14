package com.db.sfs.common;


public class Result<T> {
    // 状态码
    private int status;
    // 是否成功
    private boolean success = false;
    // 成功或失败的提示消息
    private String msg = "";
    // 返回的数据
    private T data;

    public Result() {
    }

    public Result(int status, boolean success, String msg, T data) {
        this.status = status;
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    // 设置默认的成功状态码
    public void ok(){
        this.status = StatusCode.OK;
        this.success = true;
    }

    // 设置默认的失败状态码
    public void error(){
        this.status = StatusCode.ERROR;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
