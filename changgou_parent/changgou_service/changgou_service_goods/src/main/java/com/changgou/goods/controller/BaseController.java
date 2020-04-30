package com.changgou.goods.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chayedan666
 * @version 1.0
 * @className: BaseController
 * @description:
 * @date: 2020/4/30
 */
@ControllerAdvice
public class BaseController {

    @ExceptionHandler
    @ResponseBody
    public Result exceptionHandler(Exception e){
        if (e != null){
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR,"系统异常");
    }
}
