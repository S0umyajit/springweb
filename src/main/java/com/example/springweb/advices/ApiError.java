package com.example.springweb.advices;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Getter
@Builder
public class ApiError {

  private HttpStatus status;
  private String message;
  private List<String> subErrors;
}
