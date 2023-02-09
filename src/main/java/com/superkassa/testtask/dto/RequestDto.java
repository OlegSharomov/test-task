package com.superkassa.testtask.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class RequestDto {
    @NotNull
    Long id;
    @NotNull
    Double add;
}
