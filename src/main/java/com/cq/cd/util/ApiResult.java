package com.cq.cd.util;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ApiResult {
	/**
	 * 错误码，表示一种错误类型
	 * 请求成功，状态码为200
	 */
	private Integer code;

	/**
	 * 对错误代码的详细解释
	 */
	private String message;

	/**
	 * 返回的结果包装在value中，value可以是单个对象
	 */
	private Map<String, Object> data = new HashMap<>();

	public ApiResult() {
	}

	public ApiResult(int code, String message, Map<String, Object> data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}


	public static ApiResult success(Object object) {
        ApiResult apiResult = new ApiResult();
        apiResult.getData().put("data", object);
        apiResult.setCode(200);
        apiResult.setMessage("请求成功");
        return apiResult;
    }

    public static ApiResult success() {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(200);
        apiResult.setMessage("成功");
        return apiResult;
    }

    public static ApiResult buildApiResult(Integer code, String message, Map<String, Object> data) {
        ApiResult apiResult = new ApiResult();

        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setData(data);
        return apiResult;
    }

    public static ApiResult error(ErrorEnum errorEnum) {
        ApiResult apiResult = new ApiResult();

        apiResult.setCode(Integer.parseInt(errorEnum.getErrorCode()));
        apiResult.setMessage(errorEnum.getErrorMsg());
        apiResult.setData(null);
        return apiResult;
    }

    public ApiResult data(String key, Object value){
		this.data.put(key,value);
		return this;
    }
}
