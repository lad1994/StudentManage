package com.biz.util;

import redis.clients.jedis.Jedis;

public class PagesUtil {

	public static long pagesUtil()
	{
		//���嵥��ҳ������¼��
		int i =10;
		Jedis jedis = RedisUtil.getJedis();
		//��ȡstudent����������
		long count = jedis.zcard("student");
		long s=0L;
		//�ж��ǲ�������
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
