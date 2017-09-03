package com.nt.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.nt.command.User;

@Component
public class LoginValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		
		return clazz.isAssignableFrom(User.class);
	}
	
	@Override
	public void validate(Object cmd, Errors errors) {
		  User user=null;
	   //types casting
		  user=(User)cmd;
		  if(user.getUname().length()==0){
			  errors.rejectValue("uname","user.required");
		  }
		  
		  if(user.getPwd().length()==0){
			  errors.rejectValue("pwd","pwd.required");
		  }

		  if(user.getDmn()==null){
			  errors.rejectValue("dmn","dmn.required");
		  }

		  if(user.getDob()==null){
			  errors.rejectValue("dob","dob.required");
		  }

		
	}

}
