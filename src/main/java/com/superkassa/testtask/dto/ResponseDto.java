package com.superkassa.testtask.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ResponseDto {
    @NotNull
    Double current;
}
