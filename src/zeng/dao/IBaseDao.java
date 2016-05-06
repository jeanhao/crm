package zeng.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface IBaseDao<T> {

	Session getSession();

	Serializable add(T object);
	
	void delete(T object);
	void deleteByHql(String hql);
	void deleteMore(Collection<T> collection);
	
	void addorUupdate(Object object);
	
	void update(T object);
	void update(String hql, Object[] objects);

	<E>List<E> query(String hql,Object...objects);
	<E>List<E>query(String hql,Map<String,Object> paraMap);
	
	<E>List<E>queryList(String hql,Integer firstResult,Integer MaxResult,Object...objects);
	<E>List<E>queryList(String hql,Integer firstResult,Integer MaxResult,Map<String,Object> paraMap);
	Object queryUnique(String hql,Object...objects);
	Object queryUnique(String hql,Map<String,Object> paraMap);
	T queryUnique(Class<T> clazz,String entityId);
}
