package com.nt.dao;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nt.bo.UserBO;

@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {
	private static final String  AUTH_QRY="SELECT COUNT(*) FROM USERLIST WHERE UNAME=? AND PWD=?";
	@Inject
   private JdbcTemplate jt;
	@Override
	public int validate(UserBO bo) {
	int cnt=0;
	cnt=jt.queryForObject(AUTH_QRY,Integer.class,bo.getUname(),bo.getPwd());
		return cnt;
	}//validate(-)
}//class
