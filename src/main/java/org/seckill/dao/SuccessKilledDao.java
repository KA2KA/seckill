package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

/**
 */
public interface SuccessKilledDao {
    /**
     * 插入购买明细，可过滤重复
     * @param seckilled
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(@Param("seckillId") long seckilled ,@Param("userPhone") long userPhone);

    /**
     * 
     * @param seckilled
     * @param userPhone
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckilled ,@Param("userPhone") long userPhone);

}
