package com.fabiit.fabschoolapp.utils;

public class ResponseUtil {
	public static ResponseDto createEmptyResponse() {
		return new ResponseDto();

	}

	public static ResponseDto createResponse(MessageConstants msg, Object data, boolean status) {
		return new ResponseDto(msg, data, status);

	}

	public static ResponseDto createExceptionResponse(MessageConstants exceptionOccured, String message,
			boolean status) {
		return new ResponseDto(exceptionOccured.getMsg(), message, status);
	}
}
