package com.exam.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 返回结果
 * msg  结果描述
 * code 执行状态
 * data 返回的数据
 * count 数据总量（数据表格分页时选用）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result{

    private Integer code;
    private String msg;
    private Integer count;
    private Object data;

    public static final Integer OK=1;
    public static final Integer FAIL=0;

    public static Result success(String msg){
        return new Result(OK,msg);
    }
    public static Result success(String msg,Object data){
        return new Result(OK,msg,data);
    }
    public static Result success(String msg,Integer count,Object data){
        return new Result(OK,msg,count,data);
    }

    public static Result error(String msg){
        return new Result(FAIL,msg);
    }
   /* public static Result error(String msg,Object data){
        return new Result(FAIL,msg,data);
    }
    public static Result error(String msg,Integer count,Object data){
        return new Result(FAIL,msg,count,data);
    }*/

    public Result(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public Result(int code,String msg){
        this.code=code;
        this.msg=msg;
    }
}

