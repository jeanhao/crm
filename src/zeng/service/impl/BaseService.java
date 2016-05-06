package zeng.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zeng.dao.impl.BaseDao;
import zeng.entity.SysRight;
import zeng.service.IBaseService;
import zeng.tool.Pager;
@Service
public class BaseService<T> implements IBaseService<T> {
	
	@Autowired
	protected BaseDao<T> baseDao;

	@Override
	public Serializable add(T object) {
		return baseDao.add(object);
	}

	@Override
	public void delete(Class<T>clazz,String entityId) {
		T object = baseDao.queryUnique(clazz, entityId);
		baseDao.delete(object);
	}
	@Override
	public void deleteMore(Class<T> clazz, String[] entityIds) {
		for(String entityId:entityIds){
			this.delete(clazz, entityId);
		}
	}

	@Override
	public void addorUupdate(Object object){
		baseDao.addorUupdate(object);
	}
	@Override
	public void update(T object) {
		baseDao.update(object);
	}

	@Override
	public void update(String hql, Object[] objects) {
		baseDao.update(hql,objects);
	}

	@Override
	public <E>List<E> query(String hql, Object... objects) {
		return baseDao.query(hql, objects);
	}

	@Override
	public <E>List<E> query(String hql, Map<String, Object> paraMap) {
		return baseDao.query(hql, paraMap);
	}
	

	@Override
	public <E>List<E>queryList(String hql, Integer firstResult,
			Integer maxResult, Object... objects) {
		return baseDao.queryList(hql, firstResult, maxResult, objects);
	}

	@Override
	public<E>List<E> queryList(String hql, Integer firstResult,
			Integer maxResult, Map<String, Object> paraMap) {
		return baseDao.queryList(hql, firstResult, maxResult, paraMap);
	}

	@Override
	public Object queryUnique(String hql, Object... objects) {
		return baseDao.queryUnique(hql, objects);
	}

	@Override
	public Object queryUnique(String hql, Map<String, Object> paraMap) {
		return baseDao.queryUnique(hql, paraMap);
	}
	@Override
	public T queryUnique(Class<T> clazz,String entityId){
		return baseDao.queryUnique(clazz, entityId);
	}

	@Override
	public Integer Count(String clazz){
		Integer i = ((Long) baseDao.queryUnique("select count(*) from "+clazz)).intValue();
		System.out.println(i);
		return i;
	}
	/**
	 * 根据pager里面的内容进行查询
	 * 
	 */
	@Override
	public List<T> list(String clazz,Pager pager){
		String hql="from "+clazz;
		String searchColumn = pager.getSearchColumn();//搜索字符串列
		String sort = pager.getSort();//排序字段
		String dir = pager.getDir();//排序方向
	//	String orderColumn = pager.getOrderColumn();//排序字符串列
		String key ;//操作json的键值
		Map<String,Object> paraMap = new HashMap<String, Object>();
		System.out.println(searchColumn);
		//判断是否需添加搜索内容
		if(searchColumn != null && !"".equals(searchColumn)){
			hql +="where 1=1";
			JSONObject jsonObject = JSONObject.fromObject(searchColumn);
			for(Iterator<String> iterator = jsonObject.keys();iterator.hasNext();){
				key = iterator.next();
				hql += " and "+key+" like:"+key.trim();
				paraMap.put(key,"%"+jsonObject.get(key)+"%");
			}
		}
		if(sort != null && "".equals(sort) && dir != null){
			hql += " order by "+sort+" "+dir ;
		}
//		if(orderColumn != null && !"".equals(orderColumn)){
//			hql +=" order by ";
//			JSONObject jsonObject = JSONObject.fromObject(orderColumn);
//			for(Iterator<String> iterator = jsonObject.keys();iterator.hasNext();){
//				key = iterator.next();
//				hql += key+" "+jsonObject.get(key)+",";
//			}
//			//去掉最后多出的逗号
//			hql = hql.substring(0,hql.length()-1);
//		}
		return baseDao.queryList(hql, pager.getStart(),pager.getLimit(), paraMap);
	}
	@Override
	public void deleteByHql(String hql) {
		baseDao.deleteByHql(hql);
	}
	
}
