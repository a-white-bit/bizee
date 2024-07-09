package com.sparta.bizee.exception;

import com.sparta.bizee.dto.response.ResponseCodeEnum;
import lombok.Getter;

@Getter
public class ScheduleServiceException extends CustomException {
    public ScheduleServiceException(ResponseCodeEnum code) {
        super(code);
    }
}