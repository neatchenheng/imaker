package com.i9144.xpage.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;

public abstract class AbstractDAO<T> {
	@Resource(name = "sqlMapClient")
	private SqlMapClientTemplate sqlMapClient;
	protected String daoName;

	public AbstractDAO() {
		String name = getClass().getName();
		String strs[] = name.split("\\.");
		daoName = strs[strs.length - 1];
	}

	public Object add(T t) {
		Object result = sqlMapClient.insert(daoName + ".add", t);
		return result;
	}

	@SuppressWarnings("unchecked")
	public T get(int id) {
		return (T) sqlMapClient.queryForObject(daoName + ".get", id);
	}

	public int update(T t) {
		return sqlMapClient.update(daoName + ".update", t);
	}

	public int delete(int id) {
		return sqlMapClient.delete(daoName + ".delete", id);
	}

	@SuppressWarnings("unchecked")
	public List<T> list() {
		return sqlMapClient.queryForList(daoName + ".list");
	}

	public int count() {
		return (Integer) sqlMapClient.queryForObject(daoName + ".count");
	}

	public SqlMapClientTemplate getSqlMapClient() {
		return sqlMapClient;
	}

	public int getMaxPosition(Map<String, Object> params) {
		Integer pos = (Integer) sqlMapClient.queryForObject(daoName
				+ ".getMaxPosition", params);
		if (pos == null) {
			return 0;
		}
		return pos;
	}
}
