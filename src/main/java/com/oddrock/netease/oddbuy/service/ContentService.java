package com.oddrock.netease.oddbuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oddrock.netease.oddbuy.dao.ContentDao;
import com.oddrock.netease.oddbuy.entity.Content;


@Service
@Transactional(readOnly = true)
public class ContentService {
	@Autowired
    private ContentDao dao;
	
	public Content insert(Content content) {
		dao.insert(content);
		return content;
	}
	
	public List<Content> findAllList(){
		return dao.findAllList();
	}
}
