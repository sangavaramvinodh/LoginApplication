package com.nt.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.nt.bo.UserBO;
import com.nt.dao.LoginDAO;
import com.nt.dto.UserDTO;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	@Inject
	private LoginDAO dao;

	@Override
	public String authenticate(UserDTO dto) {
		int cnt=0;
		UserBO bo=null;
		//convert DTO to BO
		bo=new UserBO();
		bo.setUname(dto.getUname());
		bo.setPwd(dto.getPwd());
		//use DAO
		cnt=dao.validate(bo);
		return  cnt==0?"InValid Credentials":"Valid Credentails";
	}

}
