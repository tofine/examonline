package com.exam.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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

    /**
     * 返回结果
     * @param code 状态
     * @param msg  描述信息
     * @param data 返回数据
     */
    public Result(int code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
        this.count=0;
    }

    public Result(int code,String msg){
        this.code=code;
        this.msg=msg;
        this.data=null;
        this.count=0;
    }
}

