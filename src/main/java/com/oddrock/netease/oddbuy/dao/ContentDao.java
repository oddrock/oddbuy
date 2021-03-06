package com.oddrock.netease.oddbuy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.oddrock.netease.oddbuy.entity.Content;

@Repository
public interface ContentDao {
	/*@Select("select id,price,title,icon,abstract as 'digest',text from content a")
	public List<Content> findAllList();*/
	
	@Insert("INSERT INTO content(price, title, icon, abstract, text) VALUES(#{content.price}, #{content.title}, #{content.icon}, #{content.summary}, #{content.text})")
	@Options(useGeneratedKeys = true, keyProperty = "content.id")
	public void insert(@Param("content") Content content);
	
	@Select("select a.id,a.price,a.title,a.icon,a.abstract as 'summary',a.text, (SELECT COUNT(*) FROM trx b WHERE b.contentId=a.id) AS 'buyNum',(SELECT COUNT(*) FROM trx b WHERE b.contentId=a.id) AS 'saleNum',(SELECT price FROM trx b WHERE b.contentId=a.id LIMIT 1) AS 'buyPrice' from content a")
	public List<Content> findAllList();
	
	@Select("select a.id,a.price,a.title,a.icon,a.abstract as 'summary',a.text, (SELECT COUNT(*) FROM trx b WHERE b.contentId=a.id) AS 'buyNum',(SELECT COUNT(*) FROM trx b WHERE b.contentId=a.id) AS 'saleNum',(SELECT price FROM trx b WHERE b.contentId=a.id LIMIT 1) AS 'buyPrice' from content a where a.id=#{id}")
	public Content get(Long id);
	
    @Update("update content set price=#{content.price},title=#{content.title},icon=#{content.icon},abstract=#{content.summary},text=#{content.text} where id=#{content.id}")
    public void update(@Param("content") Content content);
    
    // 删除记录
    @Delete("delete from content where id=#{id}")
    public void delete(@Param("id") int id);    
}
