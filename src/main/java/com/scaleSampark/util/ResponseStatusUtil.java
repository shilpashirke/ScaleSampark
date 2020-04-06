package com.scaleSampark.util;

import com.scaleSampark.response.ResponseStatus;

public class ResponseStatusUtil {

	public static ResponseStatus setResponseStatus() {
		ResponseStatus responseStatus= new ResponseStatus();
		responseStatus.setMessage(ScaleSamparkConstant.SUCCESS);
		responseStatus.setStatus(ScaleSamparkConstant.SUCCESS);
		responseStatus.setStatuscode(200);
		return responseStatus;
	}

	public static ResponseStatus setErrorResponseStatus(Exception e) {
		ResponseStatus responseStatus= new ResponseStatus();
		responseStatus.setMessage(e.getMessage());
		responseStatus.setStatus(ScaleSamparkConstant.FAIL);
		responseStatus.setStatuscode(e.hashCode());
		return responseStatus;
	}
}
