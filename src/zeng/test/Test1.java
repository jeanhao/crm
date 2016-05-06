package zeng.test;

import java.beans.IntrospectionException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import zeng.entity.SysRight;
import zeng.entity.SysRole;
import zeng.entity.SysUser;
import zeng.tool.BeanConvertor;

public class Test1 {
	public String makeString(String[] array){
		String str = "";
		for(int i = 0;i < array.length;i++){
			str += "'"+array[i] + "',";
		}
		str = str.substring(0,str.length()-1);
		return str;
	}
	//@Test
	public void testStr(){
		String[] ids = new String[]{"A","S","D"};
		System.out.println(makeString(ids));
	}
	@Test
	public void testBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
		ApplicationContext ac = new FileSystemXmlApplicationContext(
				"/src/config/spring-dataSource.xml");
		SessionFactory sessionFactory = (SessionFactory) ac
				.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String clazz = "SysRight";
		String[] ids = new String[]{"A","S","D"};
		String hql = "delete "+clazz+" where id in ( "+makeString(ids)+" )";
		//String hql = "delete "+clazz+" where id in ( 'A','B' )";
		//for()
		System.out.println(session.createQuery(hql).executeUpdate());
//		Class clazz = Class.forName("zeng.entity.SysRight");
//		Map<String,Object> entityMap = new HashMap<String, Object>();
//		entityMap.put("name","ROLE_hehe");
//		entityMap.put("text","呵呵");
//		Object instance = clazz.newInstance();
//		Object entity;
//		if(instance instanceof SysRight){
//			entity = new SysRight();
//		}else if(instance instanceof SysUser){
//			entity = new SysUser();
//		}else// if(instance instanceof SysRole)
//		{
//			entity = new SysRole();
//		}
//		BeanUtils.populate(entity, entityMap);
//		session.saveOrUpdate(entity);
//		SysRight newright = (SysRight) session.get(SysRight.class,"123");
//		System.out.println(newright.getName());
//		newright.setName("different");
//		session.update(newright);
//		Query query = session.createQuery(
//				"");
//		query.setString("rightName","%LE%");
//		List<SysRight> list = query.list();
//		for(SysRight s : list){
//		}
		// for(SysRight r : lists){
		// System.out.println(r.getRightText());
		// }
		tx.commit();
	}
	//@Test
	public void testReflect() throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException{
		Class clazz = Class.forName("zeng.entity.SysRight");
		Map<String,Object> entityMap = new HashMap<String, Object>();
		entityMap.put("name","ROLE_hehe");
		entityMap.put("text","呵呵");
		Object instance = clazz.newInstance();
		Object entity;
		if(instance instanceof SysRight){
			entity = new SysRight();
		}else if(instance instanceof SysUser){
			entity = new SysUser();
		}else// if(instance instanceof SysRole)
		{
			entity = new SysRole();
		}
		BeanUtils.populate(entity, entityMap);
	}
//	@Test
	public void testHql(){
		String hql = "from SysRight s where 1=1 and rightName like :rightName order by s.rightName desc,s.rightText desc,";
		System.out.println(hql);
		hql = hql.substring(0,hql.length()-1);
		System.out.println(hql);
	}
	//@Test
	public void testBeanConvert() throws Exception {
		SysUser u = new SysUser();
		Map<String, Object> map = BeanConvertor.convertBeanToMap(u);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

	//@Test
	public void testBeanUtils() throws IllegalAccessException, InvocationTargetException {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String[]> maps = new HashMap<String, String[]>();
		maps.put("test1", new String[] { "test11", "test12" });
		maps.put("test2", new String[] { "test21", "test22" });
		BeanUtils.populate(map, maps);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
//@Test
	public void testEmpty(){
		String a = "";
		String b = null;
		String c = "123";
		System.out.println(a.isEmpty());
		System.out.println(b.isEmpty() ||b == null );
		System.out.println(c.isEmpty());
	}
//	@Test
	public void testJson(){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		for(int i = 1;i < 5 ;i ++){
			jsonObject.put("name","num"+i);
			jsonObject.put("value","val"+ i);
			jsonArray.add(jsonObject);
		}
		String jsonStr = jsonArray.toString();
		JSONArray jsonArray2 = JSONArray.fromObject(jsonStr);
		JSONObject jsonObject2 = (JSONObject) jsonArray2.get(1);
		System.out.println(jsonObject2.toString());
	}
}
