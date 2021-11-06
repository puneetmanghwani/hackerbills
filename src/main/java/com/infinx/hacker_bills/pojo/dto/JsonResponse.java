package com.infinx.hacker_bills.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class JsonResponse {
    private boolean success;
    private String msg;
    private Object data;
    private HttpStatus status;
    private Integer code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
    private String debugMessage;
    private JsonResponse()
    {
        timeStamp = LocalDateTime.now();
    }
    public JsonResponse(boolean success, String msg, Object data, HttpStatus status, Integer code)
    {
        this();
        this.success = success;
        this.msg = msg;
        this.data = data;
        this.status = HttpStatus.OK;
        this.code = code;
    }
    public JsonResponse(HttpStatus status)
    {
        this();
        this.status = status;
    }

    public JsonResponse(boolean success, String msg, Object data)
    {
        this();
        this.success = success;
        this.msg = msg;
        this.data = data;
        this.status = HttpStatus.OK;
        if(success == true)
            this.code = 1;
    }
    public JsonResponse(HttpStatus status, Throwable ex)
    {
        this();
        this.success=false;
        this.status=status;
        this.msg = "Unexpected error";
        this.data=null;
        this.debugMessage = ex.getLocalizedMessage();
    }
    public JsonResponse(HttpStatus status, String msg, Throwable ex)
    {
        this();
        this.success = false;
        this.status = status;
        this.msg = msg;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public JsonResponse(Boolean success, String msg) {
        this();
        this.success=success;
        this.msg=msg;
    }
}
