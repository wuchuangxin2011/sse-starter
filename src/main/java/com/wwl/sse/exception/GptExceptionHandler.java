package com.wwl.sse.exception;


import com.wwl.sse.model.ApiResponse;
import com.wwl.sse.model.SseBizException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wwl
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GptExceptionHandler {
    final static String NOT_FOUND_MESSAGE = "not found";
    final static Integer BAD_REQUEST_CODE = 400;

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiResponse> handleAllExceptions(Exception ex, HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.setErrorResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "系统错误:" + ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * 404
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<ApiResponse> handleNotFoundException(Exception ex, HttpServletRequest request) {
        ApiResponse apiResponse = ApiResponse.setErrorResult(HttpStatus.NOT_FOUND.value(), NOT_FOUND_MESSAGE);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * SseBizException
     * 自定义
     * @param ex
     * @return
     */
    @ExceptionHandler(SseBizException.class)
    public ResponseEntity<ApiResponse> handleClubBizException(
            SseBizException ex) {
        ApiResponse apiResponse = ApiResponse.setErrorResult(ex.getCode(), ex.getMessage());

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    /**
     * 参数类验证
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ApiResponse> handleValidationExceptions(BindException  ex) {
        List<String> errorMessages = ex.getBindingResult().getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        String errorMessage = String.join(", ", errorMessages);
        ApiResponse apiResponse =  ApiResponse.setErrorResult(BAD_REQUEST_CODE, errorMessage);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}