package com.oddrock.netease.oddbuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oddrock.netease.oddbuy.dao.PersonDao;
import com.oddrock.netease.oddbuy.entity.Person;

@Service
@Transactional(readOnly = true)
public class PersonService {
	@Autowired
    private PersonDao dao;
	
	public List<Person> getUser(String userName) {
		return dao.getUser(userName);
	}

	public List<Person> findAllList(){
		return dao.findAllList();
	}
	public List<Person> checkUser(String userName, String password) {
		return dao.checkUser(userName, password);
	}
}
