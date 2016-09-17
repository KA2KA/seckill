package org.seckill.exception;

/**
 * 重复提交异常
 * Created by wuwan on 2016/9/13.
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
