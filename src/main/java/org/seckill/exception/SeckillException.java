package org.seckill.exception;

/**
 * 秒杀相关的异常
 * Created by wuwan on 2016/9/13.
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
