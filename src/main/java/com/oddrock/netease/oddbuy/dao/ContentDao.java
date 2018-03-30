package com.oddrock.netease.oddbuy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.oddrock.netease.oddbuy.entity.Content;

@Repository
public interface ContentDao {
	@Select("select id,price,title,icon,abstract as 'digest',text from content a")
	public List<Content> findAllList();
	
	@Insert("INSERT INTO content(price, title, icon, abstract, text) VALUES(#{content.price}, #{content.title}, #{content.icon}, #{content.digest}, #{content.text})")
	@Options(useGeneratedKeys = true, keyProperty = "content.id")
	public void insert(@Param("content") Content content);
}
