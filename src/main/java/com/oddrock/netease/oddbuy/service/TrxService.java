package com.oddrock.netease.oddbuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.oddrock.netease.oddbuy.dao.TrxDao;
import com.oddrock.netease.oddbuy.entity.Trx;
import com.oddrock.netease.oddbuy.impure.Account;

@Service
@Transactional(readOnly = true)
public class TrxService {
	@Autowired
    private TrxDao dao;

	public Trx insert(Trx trx) {
		dao.insert(trx);
		return trx;
	}
	
	public List<Account> findAllList(){
		return dao.findAllList();
	}
}
