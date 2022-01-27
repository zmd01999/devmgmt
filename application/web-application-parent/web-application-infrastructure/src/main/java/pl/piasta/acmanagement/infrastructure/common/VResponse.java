package pl.piasta.acmanagement.infrastructure.common;

public class VResponse<T> {
    private int code = 1;
    private String msg = "";
    private int count = 1;
    private String time = "";
    private T data;

    //通用错误码
    public static final int SUCCESS = 1;
    public static final String TIME ="1588485027829";

    public VResponse(int errCode, String errMsg, int errCount, String time) {
        this.code = errCode;
        this.msg = errMsg;
        this.count = errCount;
        this.time=time;
    }

    public VResponse() {

    }
    public static <T> VResponse<T> success(T data){
        VResponse<T> response = new VResponse<T>(SUCCESS, null,0,TIME);
        response.data = data;
        return response;
    }
    public static <T> VResponse<T> success(T result,int count){
        VResponse<T> response = new VResponse<T>(SUCCESS, null,0,TIME);
        response.data = result;
        response.count = count;
        return response;
    }

    public static <T> VResponse<T> success(String msg, String time){
        VResponse<T> response = new VResponse<T>(SUCCESS, msg,1,TIME);
        response.time = time;
        return response;
    }

    public static <T> VResponse<T> success(){
        return new VResponse<T>(SUCCESS, null,0,TIME);
    }

    public static <T> VResponse<T> error(int code, String msg){
        VResponse<T> response = new VResponse<T>();
        response.setCode(code);
        response.setMsg(msg);
        return response;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTime(){return time;}

    public void setTime(String time){this.time = time;}
}
