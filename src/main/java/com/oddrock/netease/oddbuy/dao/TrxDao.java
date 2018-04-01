package com.oddrock.netease.oddbuy.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.oddrock.netease.oddbuy.entity.Trx;

@Repository
public interface TrxDao {
	@Insert("INSERT INTO trx(id, contentId, personId, price, time) VALUES(#{trx.id}, #{trx.contentId}, #{trx.personId}, #{trx.price}, #{trx.time})")
	@Options(useGeneratedKeys = true, keyProperty = "trx.id")
	public void insert(@Param("trx") Trx trx);
}
