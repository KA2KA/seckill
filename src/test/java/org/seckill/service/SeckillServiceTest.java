package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.junit.Assert.*;

/**
 * Created by wuwan on 2016/9/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillAll() throws Exception {

        List<Seckill> seckillAll = seckillService.getSeckillAll(0, 4);
        log.info("list={}", seckillAll);
    }

    @Test
    public void getSeckillById() throws Exception {
        Seckill seckill = seckillService.getSeckillById(1000);
        log.info("seckill={}", seckill);

    }

    @Test
    public void exposeSeckillUrl() throws Exception {


        Exposer exposer = seckillService.exposeSeckillUrl(1000);
        if (exposer.isExposed()) {
            log.info("exposer={}", exposer);
            long userPhone = 18770006910L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(1000, userPhone, md5);
                log.info("result={}", execution);
            } catch (RepeatKillException e) {
                log.error(e.getMessage());
            } catch (SeckillCloseException e) {
                log.error(e.getMessage());
            }
        } else {
//            秒杀未开启
            log.warn("exposer={}", exposer);
        }


    }


}