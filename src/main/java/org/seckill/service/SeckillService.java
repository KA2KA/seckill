package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * 业务接口："站在使用者角度设计接口"
 * 三个方面：方法定义粒度、参数、返回类型（return 类型/异常）
 * <p>
 * Created by wuwan on 2016/9/13.
 */
public interface SeckillService {

    /**
     * 查询根据偏移量查询记录
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> getSeckillAll(int offset, int limit);

    /**
     * 根据id生成信函记录
     *
     * @param id
     * @return
     */
    Seckill getSeckillById(long id);

    /**
     * 秒杀开启是输出秒杀接口的地址
     * 否则输出系统时间和秒杀时间
     *
     * @param seckillId
     * @return
     */
    Exposer exposeSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillCloseException, RepeatKillException, SeckillException;


}
