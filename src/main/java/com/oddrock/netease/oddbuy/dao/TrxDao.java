package com.oddrock.netease.oddbuy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.oddrock.netease.oddbuy.entity.Trx;
import com.oddrock.netease.oddbuy.impure.Account;

@Repository
public interface TrxDao {
	@Insert("INSERT INTO trx(id, contentId, personId, price, time) VALUES(#{trx.id}, #{trx.contentId}, #{trx.personId}, #{trx.price}, #{trx.time})")
	@Options(useGeneratedKeys = true, keyProperty = "trx.id")
	public void insert(@Param("trx") Trx trx);
	
	@Select("SELECT a.contentId AS 'id',a.price AS 'buyPrice',a.time AS 'buyTime',COUNT(*) AS 'buyNum',b.title,b.icon FROM trx a,content b WHERE a.contentId=b.id GROUP BY a.contentId,a.price,a.time order by a.time asc")
	public List<Account> findAllList();
}
