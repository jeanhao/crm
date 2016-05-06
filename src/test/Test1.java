package test;

import java.util.List;
import java.util.Locale;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import zeng.dao.impl.BaseDao;
import zeng.entity.SysUser;
public class Test1 {
//	@Autowired
//	private AccountService accountService;
//	@Test
	public void testspring(){
	//ApplicationContext  ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ApplicationContext  ac = new FileSystemXmlApplicationContext("/src/config/spring-datasource.xml");
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
//	BaseDao<SysUser> dao  = (BaseDao<SysUser>) ac.getBean("baseDao"); 
//	System.out.println(dao.query("from SysUser").size());
	Session session = sessionFactory.openSession();
	Transaction tx = session.beginTransaction();
	//权限表的id等于角色权限表中权限id,角色权限表中角色id等于用户的角色id
//	List<String> list = session.createQuery("select r.rightText from SysRight r , SysRoleRight rr , SysUser u where " +
//			"r.rightCode = rr.sysRight.id and rr.sysRole.id = u.sysRole.roleId and u.usrName = ?").setParameter(0, "wangqinghai").list();
	List<String>list = session.createQuery("select rightText from SysRight").list();
	for(String str:list){
		System.out.println(str);
	}
	tx.commit();
	}
	@Test
	public void teststatic(){
		System.out.println(Tests.a);
	}
//	@Test
	public void testservice(){
		Locale locale1 = new Locale("zh","CN");
		System.out.println(locale1);
		Locale locale2 = Locale.getDefault();
		System.out.println(locale2);
	}
//	@Test
	public void testjson(){
		String str = "{'string':'JSON', 'integer': 1, 'double': 2.0, 'boolean': true}"; 
		JSONObject jsonObject = JSONObject.fromObject(str);
		System.out.println(jsonObject.get("string"));
	      /* JsonConfig jsonConfig = new JsonConfig();  
	       jsonConfig.setExcludes(new String[] { "double", "boolean" });  
	       JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(str, jsonConfig);  
	       System.out.println(jsonObject.getString("string"));  
	       System.out.println(jsonObject.getInt("integer"));  
	       System.out.println(jsonObject.has("double"));  
	       System.out.println(jsonObject.has("boolean"));  */
	}
	//@Test
	public void testjson2(){
		JSONArray jsonArray = JSONArray.fromObject("[{\"admin\":123,\"id\":\"81dc9bdb52d04dc20036dbd8313ed055\"}" +
				",{\"test\":123}]");
		JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(1));
		System.out.println(jsonArray.get(0).toString());
		System.out.println(jsonObject.get("test"));
	}
	private String variable = "qwe";
	//@Test
	public void testarea(){
		testarea1();
		System.out.print(variable);
	}
	public void testarea1(){
		variable = "testt";
	}
}
