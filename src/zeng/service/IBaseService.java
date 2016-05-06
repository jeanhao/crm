package zeng.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import zeng.entity.SysRight;
import zeng.tool.Pager;

public interface IBaseService<T> {

	public  Serializable add(T object);
	
	public void delete(Class<T> clazz,String entityId );
	void deleteMore(Class<T> clazz, String[] entityIds);
	
	public void addorUupdate(Object object);
	
	public void update(T object);
	public void update(String hql, Object[] objects);

	public <E>List<E> query(String hql,Object...objects);
	public <E>List<E> query(String hql,Map<String,Object> paraMap);
	
	public <E>List<E> queryList(String hql,Integer firstResult,Integer MaxResult,Object...objects);
	public <E>List<E> queryList(String hql,Integer firstResult,Integer MaxResult,Map<String,Object> paraMap);
	public Object queryUnique(String hql,Object...objects);
	public Object queryUnique(String hql,Map<String,Object> paraMap);
	public T queryUnique(Class<T> clazz,String entityId);

	List<T> list(String clazz,Pager pager);

	Integer Count(String clazz);

	void deleteByHql(String hql);

	
}
