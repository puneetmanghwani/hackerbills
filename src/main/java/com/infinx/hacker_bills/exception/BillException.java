package com.infinx.hacker_bills.exception;

import lombok.Data;


@Data
public class BillException extends Exception{

    Integer code;

    public BillException(String message, Integer code){
        super(message);
        this.code = code;
    }

}
