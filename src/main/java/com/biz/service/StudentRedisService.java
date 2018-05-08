package com.biz.service;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Set;

//import org.springframework.data.redis.connection.jedis.JedisUtils;

import com.biz.entity.Student;
import com.biz.util.DbUtil;
import com.biz.util.RedisUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class StudentRedisService {
	    private Jedis jedis;
	 
	    /**
	     * 连接redis服务器
	     */
	    public void connectRedis() {
	        jedis=RedisUtil.getJedis();
	        System.out.println("服务器正在运行"+jedis.ping());
	    }
	    
	    /**
	     * 删除学生信息
	     */
	   public void DeleteStudent(Student student,String member)
	   {
		   jedis=RedisUtil.getJedis();
		   jedis.zrem("student", member);
		   
	   }
	   
	   /**
	     * 修改学生信息
	     */
	   public void UpdateStudent(Student student)
	   {
		   
	   }
	   
	   /**
	     * 查询学生信息
	     */
	   public List<Student> QueryStudent(Student student)
	   {
		   
		   List<Student> list = new ArrayList<Student>();
		   jedis = RedisUtil.getJedis();
		   Set<String> set = jedis.zrevrange("student", 0, -1);
		   int i=0;
		   for(Iterator iterator=set.iterator();iterator.hasNext();i++)
		   {
			   String body = (String) iterator.next();
			   Student student2 = DbUtil.backResults(body);
			   list.add(student2);
		   }
		   for(Student student1 : list)
		   {
			   System.out.println(student1);
			   
		   }
		   return list;
	   }
	   

	   /**
	     * 根据页码查询学生信息
	     */
	   public List<Student> QueryStudentByPages(int pageNumber)
	   {
		   //建立list集合
		   List<Student> list = new ArrayList<Student>();
		   jedis = RedisUtil.getJedis();
		   Set<Tuple> set = jedis.zrevrangeWithScores("student", (pageNumber-1)*10, pageNumber*10-1);
		   int i=0;
//		   for(Iterator iterator=set.iterator();iterator.hasNext();i++)
//		   {
//			   String body = (String) iterator.next();
//			   Student student2 = DbUtil.backResults(body);
//			   list.add(student2);
//		   }
		   for(Tuple t:set){
			   double score=t.getScore();
			   String member= t.getElement();
			   Student student2 = DbUtil.backResults(member);
			   list.add(student2);
		   }
		   for(Student student1 : list)
		   {
			   System.out.println(student1);
			   
		   }
		   return list;
	   }
	   
	   
	   /**
	     * 添加学生信息
	     */
	   public void AddStudents(Student student,double score,String member)
	   {
		   jedis = RedisUtil.getJedis();
		   jedis.zadd("student", score, member);
	   }
	   
	    
	    
	    /**
	     * redis操作map集合 //添加学生信息
	     */
	    public int AddStudent(Student student ) {
	        
	    	int results=0;
	        Map<String,String> map = new HashMap<String,String>();        
	        map.put("id", student.getId());
	        map.put("name", student.getName());
	        map.put("birthday", student.getBirthday());
	        map.put("description", student.getDescription());
	        String avgscore =Integer.toString(student.getAvgescore()); 
	        map.put("avgscore", avgscore);
	       
	        	
	        jedis.hmset("user", map);
	        //取出users中的Name,执行结果:[minxr]-->注意结果是一个泛型的List
	        //第一个参数是存入redis中map对象的key,后面跟的是放入map中对象的key,后面的key可以是多个，是可变的
	        List<String> rsmap = jedis.hmget("user", "name","age","qq");
	        System.out.println(rsmap);	        
	        //遍历	        
	        Iterator<String> iter = jedis.hkeys("user").iterator();  
	        while(iter.hasNext()) {
	            String key = iter.next();
	            System.out.println(key+":" + jedis.hmget("user", key));  
	        }
	        if(results!=0)
	        {
	        	return results;
	        }else
	        {
	        	return 0;
	        }
	    }
	    
	    
	    /**
	     * redis排序
	     */
	    public void testSort() {
	        
	        //jedis 排序
	        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的)
	        jedis.del("a");//先清除数据，再加入数据进行测试
	        jedis.rpush("a", "1");
	        jedis.lpush("a", "6");
	        jedis.lpush("a", "3");
	        jedis.lpush("a", "9");
	        System.out.println(jedis.lrange("a", 0, -1));
	        System.out.println(jedis.sort("a"));//[1,3,6,9] //输入排序后结果
	        System.out.println(jedis.lrange("a", 0, -1));	        
	    }
	    
	    
	    /**
	     * redis连接池
	     */
	    public void testRedisPool() {
	        RedisUtil.getJedis().set("newname", "test");
	        System.out.println(RedisUtil.getJedis().get("newname"));        
	    }
}
