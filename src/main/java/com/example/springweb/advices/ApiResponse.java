package com.example.springweb.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    @JsonFormat(pattern = "hh:mm:ss dd:MM:yyyy")
    private LocalDateTime localDateTime;
    private T data;
    private ApiError error;

    public ApiResponse(){
        this.localDateTime=LocalDateTime.now();
    }

    public ApiResponse(T data){
        this();
        this.data=data;
    }

    public ApiResponse(ApiError error){
        this();
        this.error=error;
    }

}
