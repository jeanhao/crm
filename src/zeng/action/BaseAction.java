package zeng.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;

import zeng.entity.SysRight;
import zeng.entity.SysRole;
import zeng.entity.SysUser;
import zeng.service.impl.BaseService;
import zeng.tool.BeanConvertor;
import zeng.tool.Pager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
/**
 * 列表查看集成了基础查看（包含分页）、搜索、改变页面大小等功能（后期拓展上下条）
 * @author Administrator
 *
 * @param <T>
 * @param <PK>
 */
public abstract class BaseAction<T> extends ActionSupport implements ServletRequestAware,ServletResponseAware
		,Preparable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	protected BaseService< T> baseService;
	
	protected HttpSession session;
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	protected Pager pager ;	//数据处理pager
	protected Map<String,Object>jsonMap = new HashMap<String, Object>();	//向前端输出的jsonMap
	protected String[] entityIds;	//用于进行操作的id集
	public String[] getEntityIds() {
		return entityIds;
	}

	public void setEntityIds(String[] entityIds) {
		this.entityIds = entityIds;
	}

	/**
	 * 获取向后台输出数据的writer
	 * @return
	 * @throws IOException
	 */
	public PrintWriter getWriter() throws IOException{
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type","text/html;charset=UTF-8");
		return response.getWriter();
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
	
	protected String getLoginUserName(){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		String userName = securityContextImpl.getAuthentication().getName();
		return userName;
	}
	protected List<String> getLoginUserRight(){
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl.getAuthentication().getAuthorities();
		List<String> rights = new ArrayList<String>();
		for(GrantedAuthority grantedAuthority: authorities){
			rights.add(grantedAuthority.getAuthority());
		}
		return rights;
	}
	protected String getClassFromUrl(){
		//从url中获取需要的类
		String url = request.getRequestURI().toString();
		String[]urlPart1 = url.split("/");
		String[] urlPart2 = urlPart1[urlPart1.length - 1].split("_");
		String clazz = urlPart2[0];
		return clazz;
	}
	/**
	 * 进入新面板前的数据准备或处理
	 * 1、清空pagerMap里的搜索内容
	 * @throws IOException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	public String toList() throws IOException, IllegalAccessException, InvocationTargetException{
		Map<String,Object> pagerMap = (Map<String, Object>) session.getAttribute("pagerMap");
		if(pagerMap!= null){
			pagerMap.remove("searchColumn");
			pagerMap.remove("dir");
			pagerMap.remove("sort");
		}else{
			pagerMap = new HashMap<String, Object>();
		}
		BeanUtils.populate(pagerMap, request.getParameterMap());
		session.setAttribute("pagerMap",pagerMap);
		session.removeAttribute("rowCount");
		return SUCCESS;
	}
	public String list(){
		String clazz = this.getClassFromUrl();
		//获取总数
		Integer rowCount = (Integer) session.getAttribute("rowCount");
		if(rowCount == null){
			rowCount = baseService.Count(clazz);
			session.setAttribute("rowCount",rowCount);
		}
		List rights =baseService.list(clazz,pager) ;
		jsonMap.put("rowCount", rowCount);
		jsonMap.put("entity",rights);
		return SUCCESS;
	}
	/**
	 *1、先从session中获取pagerMap,若pagerMap为null，则初始化一个，
	 *2、获取前台传来的参数列，覆盖到pagerMap中
	 *3、将pagerMap转换成pager,以供查询使用
	 *4、将新的pagerMap覆盖到session
	 * @throws Exception 
	 */
	public void prepareList() throws Exception{
		Map<String,Object> paraMap = new HashMap<String, Object>();
		Map<String,Object> pagerMap = (Map<String, Object>) session.getAttribute("pagerMap");
		if(pagerMap == null){
			//初始化pager
			pager = new Pager(0,5);
			//获取pager对应的map集合
			pagerMap = BeanConvertor.convertBeanToMap(pager);
		}
		//获取前台传来的参数集，填充到paraMap中
		BeanUtils.populate(paraMap, request.getParameterMap());
	//	paraMap.remove(key)
		//遍历paraMap,将传入的参数覆盖入pagerMap中
		for(Map.Entry<String,Object> entry : paraMap.entrySet()){
			pagerMap.put(entry.getKey(),entry.getValue());
		}
		//转换pagerMap到pager
		pager = new Pager();
		BeanUtils.populate(pager,pagerMap);
		//将修改后的pagerMap存入session中
		session.setAttribute("pagerMap",pagerMap);
	}
	/**
	 * 修改或置空条目总量（在添加、删除条目、更换查看其它内容时调用）
	 * @param count	进行操作数，正数(添加)和负数(删除)对应，0(更换查看内容)则置空
	 */
	public void initPagerTotalCount(Integer count){
		Integer rowCount = (Integer) session.getAttribute("rowCount");
		if(rowCount != null){
			if(count != 0){
				session.setAttribute("rowCount",rowCount+count);
			}else{
				session.removeAttribute("rowCount");
			}
		}
	}
	/**
	 * 执行添加或更新操作
	 * @return
	 * @throws Exception
	 */
	public String saveorUpdate() throws Exception{
		Map<String,Object> entityMap = new HashMap<String, Object>();
		BeanUtils.populate(entityMap, request.getParameterMap());
		if(entityMap.get("id") != null && "".equals(entityMap.get("id"))){
			entityMap.remove("id");
		}
		Object entity = this.getEntityByClassName();
		BeanUtils.populate(entity, entityMap);
		baseService.addorUupdate(entity);
		jsonMap.put("msg","操作成功！");
		jsonMap.put("success",true);
		return SUCCESS;
	}
	/**
	 * 执行删除操作
	 * @return
	 */
	public String delete(){
		String className = this.getClassFromUrl();
		String hql = "delete "+className+" where id in ( "+makeString(entityIds)+" )";
		baseService.deleteByHql(hql);
		jsonMap.put("msg","删除成功!");
		initPagerTotalCount(-entityIds.length);
		return SUCCESS;
	}
	
	/**
	 * 数组处理工具，将数组转换成用逗号分隔的字符串
	 * @param array
	 * @return
	 */
	public String makeString(String[] array){
		String str = "";
		for(int i = 0;i < array.length;i++){
			str += "'"+array[i] + "',";
		}
		str = str.substring(0,str.length()-1);
		return str;
	}
	/**
	 * 通过类名获取相应类的Object实例
	 * @param className
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	protected Object getEntityByClassName() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		String className = this.getClassFromUrl();
		Class<? extends Serializable> clazz = (Class<? extends Serializable>) Class.forName("zeng.entity."+className);
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
		return entity;
	}
	
	@Override
	public void prepare(){
	}

//	@Override
//	public T getModel(){
//		return null;
//	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	
}
