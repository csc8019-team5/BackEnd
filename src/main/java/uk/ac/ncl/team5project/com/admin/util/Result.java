package uk.ac.ncl.team5project.com.admin.util;

import lombok.Data;
/**
 * @file Result.java
 * @date 2025-04-10 10:55
 * @function_description: The data returned to the front-end will adopt a unified format: status code + message + data.
 * @interface_description: 
 *     @calling_sequence: 
 *     @arguments_description: 
 *     @list_of_subordinate_classes: 
 * @discussion: 
 * @development_history: 
 *     @designer Qingyu Cao 
 *     @reviewer: 
 *     @review_date: 
 *     @modification_date: 
 *     @description: 
 */

@Data
public class Result {
    private String code;
	private String message;
	private Object data;

	public Result(String code,String message,Object data){
		this.code=code;
		this.message=message;
		this.data=data;
	}
	public static Result success(String message,Object data){
		return new Result("200",message,data);
	}
	public static Result fail(String message,String data){
		return new Result("500",message,data);
	}
	public static Result noLogin(){return new Result("401","Not logged in","");}
	public static Result noPermission(){return new Result("402","No Permission","");}
}
