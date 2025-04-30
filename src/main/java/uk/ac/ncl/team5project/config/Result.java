package uk.ac.ncl.team5project.config;
/**
 * Class: Result
 * File: Result.java
 * Created on: 2025/4/24
 * Author: menghui yao
 *
 * Description:
 * <pre>
 *     Function: A generic response wrapper used to standardize API responses by
 *                  including status codes, messages, and data payloads.
 *     Interface Description:
 *         - Calling Sequence: success()-> Result(200,"success") success(data)->Result(200,"success",data)
 *                             error() -> Result(500,"error") error(message) ->Result(500, message) error(code, message)->Result(code, message)
 *         - Argument Description: code=200 means success; code=500 means failed;
 *                                  message: "success" or any word wanted to be shown;
 *                                  data: the data retrieved from database
 *         - List of Subordinate Classes: none
 * </pre>
 *
 * Development History:
 * <pre>
 *     Designer: menghui yao
 *     Reviewer: menghui yao
 *     Review Date: 2025/4/24
 *     Modification Date: 2025/4/24
 *     Modification Description: 2025/4/24
 * </pre>
 */
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        return result;
    }
    public static Result success(Object data) {
        Result result= success();
        result.setData(data);
        return result;
    }
    public static Result error() {
        Result result = new Result();
        result.setCode(500);
        result.setMessage("error");
        return result;
    }

    public static Result error(String message) {
        return error(500, message);
    }

    public static Result error(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
