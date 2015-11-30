package com.i9144.xint.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.i9144.xint.dao.UserDAO;
import com.i9144.xint.model.User;

@Service
public class UserService {
	@Resource
	private UserDAO userDao;
	
	public List<User> list(int page, int rows, String sord, String sidx) {
		int offset = (page -1) * rows;
		return userDao.list(offset, rows);
	}
}
