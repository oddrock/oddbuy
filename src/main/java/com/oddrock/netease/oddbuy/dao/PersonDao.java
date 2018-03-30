package com.oddrock.netease.oddbuy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.oddrock.netease.oddbuy.entity.Person;

@Repository
public interface PersonDao {
	@Select("select * from person a where a.id=#{id")
	public Person get(@Param("id")  String id);
	@Select("select * from person a WHERE a.userName=#{userName}")
	public List<Person> getUser(@Param("userName")  String userName);
	
	@Select("select * from person a WHERE a.userName=#{userName} and a.password = #{password}")
	public List<Person> checkUser(@Param("userName")  String userName, @Param("password")  String password);
	
	@Select("select * from person a")
	public List<Person> findAllList();
}
