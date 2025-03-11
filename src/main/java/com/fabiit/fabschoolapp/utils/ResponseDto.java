package com.fabiit.fabschoolapp.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseDto {

	private long code;
	private String msg;
	private Object data;
	private boolean status;

	public ResponseDto(MessageConstants msg, Object data, boolean status) {
		this.code = msg.getCode();
		this.msg = msg.getMsg();
		this.data = data;
		this.status = status;
	}

	public ResponseDto(String msg, Object data, boolean status) {
		this.msg = msg;
		this.data = data;
		this.status = status;
	}
}
