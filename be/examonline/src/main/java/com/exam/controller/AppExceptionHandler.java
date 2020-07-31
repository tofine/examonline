package com.exam.controller;

import com.exam.commons.Result;
import com.exam.exception.DuplicateKeyException;
import com.exam.exception.TargetResourceNotExitException;
import com.exam.exception.UserExistException;
import com.exam.exception.UserOrPwdErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<Result> userExit(){
        return new ResponseEntity<Result>(Result.error("用户名已存在"), HttpStatus.OK);
    }

    @ExceptionHandler(UserOrPwdErrorException.class)
    public ResponseEntity<Result> userError(){
        return new ResponseEntity<Result>(Result.error("用户名或密码错误"), HttpStatus.OK);
    }

    @ExceptionHandler(TargetResourceNotExitException.class)
    public ResponseEntity<Result> operateError(){
        return new ResponseEntity<Result>(Result.error("操作失败，目标不存在"), HttpStatus.OK);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Result> insertError(){
        return new ResponseEntity<Result>(Result.error("添加失败，目标已存在"), HttpStatus.OK);
    }

    @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Result> op(){
        return new ResponseEntity<Result>(Result.error("异常操作"), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> unknownError(){
        return new ResponseEntity<Result>(Result.error("未知错误"), HttpStatus.OK);
    }

}
