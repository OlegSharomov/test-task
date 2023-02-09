package com.superkassa.testtask.controller;

import com.superkassa.testtask.dto.RequestDto;
import com.superkassa.testtask.dto.ResponseDto;
import com.superkassa.testtask.service.ServiceClass;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ControllerClass {
    private final ServiceClass service;

    // Why should POST method change the value in the task? Why not PUT or PATCH?
    @PostMapping("/modify")
    public ResponseDto postRequestProcessing(@Valid @RequestBody RequestDto request) {
        log.info(String.format("Received a request: %s", request));
        ResponseDto responseDto = service.changeValue(request);
        log.info(String.format("Response sent: %s", responseDto));
        return responseDto;
    }
}
