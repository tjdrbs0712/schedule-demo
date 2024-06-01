package com.sparta.scheduleproject2;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResponse {
    private Integer statusCode;
    private String msg;
    private Object data;
}
