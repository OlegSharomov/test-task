package com.superkassa.testtask.service;

import com.superkassa.testtask.dto.RequestDto;
import com.superkassa.testtask.dto.ResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface ServiceClass {
    @Transactional(readOnly = false)
    ResponseDto changeValue(RequestDto request);
}
