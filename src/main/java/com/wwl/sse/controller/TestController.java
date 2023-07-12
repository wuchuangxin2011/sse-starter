package com.wwl.sse.controller;

import com.wwl.sse.model.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("health")
    public ApiResponse<Object> health() {
        return ApiResponse.setSuccessResult(new Date());
    }
}
