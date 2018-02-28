package com.ridecell.parkingassistant.utils;

import com.ridecell.parkingassistant.dto.ErrorResponseDTO;
import com.ridecell.parkingassistant.dto.ErrorResponseDTO.HTTP_STATUS_CODE;


public class RestUtils {

	public static ErrorResponseDTO createErrorResponseDTO(HTTP_STATUS_CODE errorcode, String errorMessage) {
		ErrorResponseDTO errorDTO = new ErrorResponseDTO();
		errorDTO.setCode(errorcode.getCode());
		errorDTO.setMessage(errorMessage);
		return errorDTO;
		
	}
	
	public static ErrorResponseDTO createInternalServerErrorResponseDTO(){
		return RestUtils.createErrorResponseDTO(HTTP_STATUS_CODE.INTERNAL_SERVER_ERROR, "Couldn't fulfill request right now. Please try again!");
	}
	
	public static ErrorResponseDTO createForbiddenResponseDTO(){
		return RestUtils.createErrorResponseDTO(HTTP_STATUS_CODE.FORBIDDEN, "Forbidden! Invalid access!");
	}
	
	public static ErrorResponseDTO createUnauthorizedResponseDTO(){
		return RestUtils.createErrorResponseDTO(HTTP_STATUS_CODE.UNAUTHORIZED, "Un-authorized access!");
	}
	
	public static ErrorResponseDTO createBadRequestResponseDTO(){
		return RestUtils.createErrorResponseDTO(HTTP_STATUS_CODE.BAD_REQUEST, "Invalid request received! Please verify your input.");
	}
	
	public static ErrorResponseDTO createBadRequestResponseDTO(String message){
		return RestUtils.createErrorResponseDTO(HTTP_STATUS_CODE.BAD_REQUEST, message);
	}
}
