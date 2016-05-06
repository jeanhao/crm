package zeng.dao.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import zeng.dao.IBaseDao;
@Repository
public class BaseDao<T> implements IBaseDao<T>{

	@Autowired 
	private SessionFactory sessionFactory;
	@Override
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Serializable add(T object) {
		return getSession().save(object);
	}
		
	@Override
	public void delete(T object) {
		getSession().delete(object);
	}
	@Override
	public void deleteMore(Collection<T> collection) {
		for(T object:collection){
			this.delete(object);
		}
	}
	
	@Override
	public void addorUupdate(Object object){
		getSession().saveOrUpdate(object);
	}
	@Override
	public void update(T object) {
		getSession().merge(object);
	}
	@Override
	public void update(String hql,Object...objects){
		forEachObjects(getSession().createQuery(hql), objects).executeUpdate();
	}
	@Override
	public<E> List<E> query(String hql, Object... objects) {
		return forEachObjects(getSession().createQuery(hql), objects).list();
	}
	@Override
	public<E>List<E>query(String hql, Map<String, Object> paraMap) {
		return forEachMap(getSession().createQuery(hql), paraMap).list();
	}
	@Override
	public <E>List<E> queryList(String hql, Integer firstResult, Integer MaxResult,
			Object... objects) {
		Query query = getSession().createQuery(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(MaxResult);
		return forEachObjects(query, objects).list();
	}
	@Override
	public <E>List<E> queryList(String hql, Integer firstResult, Integer maxResult,
			Map<String, Object> paraMap) {
		Query query = getSession().createQuery(hql);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult);
		List list =  forEachMap(query, paraMap).list();
		return list;
	}
	//根据类型为查询语句占位符绑定响应类型的值
		public void setParameterValue(Query query, String key, Object value) {
			query.setParameter(key, value);
			if (null == key || null == value) {
				return;
			} else if (value instanceof String) {
				query.setString(key, (String) value);
			} else if (value instanceof List) {
				query.setParameterList(key, (List) value);
			} else if (value instanceof String[]) {
				query.setParameterList(key, (String[]) value);
			} else if (value instanceof Date) {
				query.setDate(key, (Date) value);
			} else if (value instanceof Boolean) {
				query.setBoolean(key, ((Boolean) value).booleanValue());
			} else if (value instanceof Integer) {
				query.setInteger(key, ((Integer) value).intValue());
			} else if (value instanceof Long) {
				query.setLong(key, ((Long) value).longValue());
			} else if (value instanceof Float) {
				query.setFloat(key, ((Float) value).floatValue());
			} else if (value instanceof Double) {
				query.setDouble(key, ((Double) value).doubleValue());
			} else if (value instanceof BigDecimal) {
				query.setBigDecimal(key, (BigDecimal) value);
			} else if (value instanceof Byte) {
				query.setByte(key, ((Byte) value).byteValue());
			} else if (value instanceof Calendar) {
				query.setCalendar(key, (Calendar) value);
			} else if (value instanceof Character) {
				query.setCharacter(key, ((Character) value).charValue());
			} else if (value instanceof Timestamp) {
				query.setTimestamp(key, (Timestamp) value);
			} else if (value instanceof Short) {
				query.setShort(key, ((Short) value).shortValue());
			} else {
				query.setParameter(key, value);
			}
		}
	@Override
	public Object queryUnique(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		return forEachObjects(query, objects).uniqueResult();
	}
	@Override
	public Object queryUnique(String hql, Map<String, Object> paraMap) {
		Query query = getSession().createQuery(hql);
		return forEachMap(query, paraMap).uniqueResult();
	}
	@Override
	public T queryUnique(Class<T> clazz, String entityId) {
		return (T) getSession().get(clazz, entityId);
	}
	/**
	 * 遍历数组参数，填入占位
	 * @param query
	 * @param objects
	 * @return
	 */
	public Query forEachObjects(Query query , Object...objects){
		for(int i = 0 ; i < objects.length ; i++){
			query.setParameter(i, objects[i]);
		}
		return query;
	}
	/**
	 * 遍历Map参数，填入占位
	 * @param query
	 * @param paraMap
	 * @return
	 */
	public Query forEachMap(Query query, Map<String, Object> paraMap){
		for(Map.Entry<String,Object> entry : paraMap.entrySet()){
			setParameterValue(query, entry.getKey(),entry.getValue());
	//		query.setParameter(entry.getKey(),entry.getValue());
		}
		return query;
	}
	@Override
	public void deleteByHql(String hql) {
		getSession().createQuery(hql).executeUpdate();
	}
}
