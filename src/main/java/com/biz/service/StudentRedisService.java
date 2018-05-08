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
	     * ����redis������
	     */
	    public void connectRedis() {
	        jedis=RedisUtil.getJedis();
	        System.out.println("��������������"+jedis.ping());
	    }
	    
	    /**
	     * ɾ��ѧ����Ϣ
	     */
	   public void DeleteStudent(Student student,String member)
	   {
		   jedis=RedisUtil.getJedis();
		   jedis.zrem("student", member);
		   
	   }
	   
	   /**
	     * �޸�ѧ����Ϣ
	     */
	   public void UpdateStudent(Student student)
	   {
		   
	   }
	   
	   /**
	     * ��ѯѧ����Ϣ
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
	     * ����ҳ���ѯѧ����Ϣ
	     */
	   public List<Student> QueryStudentByPages(int pageNumber)
	   {
		   //����list����
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
	     * ���ѧ����Ϣ
	     */
	   public void AddStudents(Student student,double score,String member)
	   {
		   jedis = RedisUtil.getJedis();
		   jedis.zadd("student", score, member);
	   }
	   
	    
	    
	    /**
	     * redis����map���� //���ѧ����Ϣ
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
	        //ȡ��users�е�Name,ִ�н��:[minxr]-->ע������һ�����͵�List
	        //��һ�������Ǵ���redis��map�����key,��������Ƿ���map�ж����key,�����key�����Ƕ�����ǿɱ��
	        List<String> rsmap = jedis.hmget("user", "name","age","qq");
	        System.out.println(rsmap);	        
	        //����	        
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
	     * redis����
	     */
	    public void testSort() {
	        
	        //jedis ����
	        //ע�⣬�˴���rpush��lpush��List�Ĳ�������һ��˫���������ӱ���������)
	        jedis.del("a");//��������ݣ��ټ������ݽ��в���
	        jedis.rpush("a", "1");
	        jedis.lpush("a", "6");
	        jedis.lpush("a", "3");
	        jedis.lpush("a", "9");
	        System.out.println(jedis.lrange("a", 0, -1));
	        System.out.println(jedis.sort("a"));//[1,3,6,9] //�����������
	        System.out.println(jedis.lrange("a", 0, -1));	        
	    }
	    
	    
	    /**
	     * redis���ӳ�
	     */
	    public void testRedisPool() {
	        RedisUtil.getJedis().set("newname", "test");
	        System.out.println(RedisUtil.getJedis().get("newname"));        
	    }
}
