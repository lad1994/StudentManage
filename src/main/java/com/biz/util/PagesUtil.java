package com.biz.util;

import redis.clients.jedis.Jedis;

public class PagesUtil {

	public static long pagesUtil()
	{
		//定义单个页面最大记录数
		int i =10;
		Jedis jedis = RedisUtil.getJedis();
		//获取student的数据条数
		long count = jedis.zcard("student");
		long s=0L;
		//判断是不是整除
		if(count%i==0)
		{
			s=count/i;
		}else
		{
			s=count/i+1;
		}
		return s;
	}
}
