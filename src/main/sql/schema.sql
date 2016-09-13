--数据库初始化脚本
--创建数据库
CREATE  DATABASE seckill;
--使用数据库
use seckill;
--创建秒杀库存表
CREATE TABLE  seckill(
  `seckill_id` bigint NOT NULL  AUTO_INCREMENT COMMENT'商品库存id',
  `name` VARCHAR(120) NOT NULL  COMMENT'商品名称',
  `number` INT NOT NULL COMMENT'库存数量',
  `start_time` TIMESTAMP  NOT NULL  COMMENT'秒杀开启时间',
  `end_time` TIMESTAMP  NOT NULL  COMMENT'秒杀结束时间',
  `create_time` TIMESTAMP not NULL DEFAULT  CURRENT_TIMESTAMP  COMMENT'创建时间',
  PRIMARY  KEY (seckill_id),
  KEY  idx_start_time(start_time),
  KEY  idx_end_time(end_time),
  KEY  idx_create_time(create_time)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT  CHARSET=UTF8 COMMENT='秒杀库存表'

--show create  table seckill:查看创建表语句
--初始化数据
insert INTO
  seckill(name,number,start_time,end_time)
  VALUE
  ('600元秒杀iphone6',1000,'2016-09-09 00:00:00','2016-09-10 00:00:00'),
  ('500元秒杀iphone5',1000,'2016-09-09 00:00:00','2016-09-10 00:00:00'),
  ('400元秒杀iphone4',1000,'2016-09-09 00:00:00','2016-09-10 00:00:00');

--秒杀成功明细表
CREATE  TABLE success_killed(
`seckill_id` bigint NOT NULL  comment'秒杀商品id',
`user_phone` bigint NOT NULL  comment'用户手机号',
`state` tinyint NOT NULL  comment'状态标识：-1(无效) 0(成功) 1(已付款)',
`create_time` TIMESTAMP NOT NULL  comment'创建时间',
PRIMARY KEY (seckill_id,user_phone),
KEY  idx_create_time(create_time)
)ENGINE = InnoDB  DEFAULT  CHARSET=UTF8 COMMENT='秒杀库存表'

--连接数据库控制台
mysql -uroot -p