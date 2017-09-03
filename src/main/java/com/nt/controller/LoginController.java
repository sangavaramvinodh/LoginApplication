package com.nt.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nt.command.User;
import com.nt.dto.UserDTO;
import com.nt.service.LoginService;
import com.nt.validator.LoginValidator;

@Controller
public class LoginController {
	
	@Inject
	private LoginService service;
	@Inject
	private LoginValidator validator;
	
	@RequestMapping(value="/login.htm",method=RequestMethod.GET)
	public String showForm(Model model){
		User user=null;
		//create Empty Cmd  obj
				user=new User();
				user.setUname("vinodh");
				user.setPwd("vinodh");
				//keep Cmd obj in model attribute
		model.addAttribute("userCmd",user);
		return "login";
	}
	
	@RequestMapping(value="/login.htm",method=RequestMethod.POST)
	public String  processForm(Model model,
			                                        @ModelAttribute("userCmd") User user,
			                                        BindingResult errors){
		UserDTO dto=null;
		String msg=null;
		//perform form validation
		if(validator.supports(User.class)){
			validator.validate(user, errors);
			if(errors.hasErrors()){
				return "login";
				
			}
		}
		
		//convert Cmd obj to DTO obj
		dto=new UserDTO();
		dto.setUname(user.getUname());
		dto.setPwd(user.getPwd());
		//use Service class
		msg=service.authenticate(dto);
		//keep result in model attribute
		model.addAttribute("msg",msg);
		
		return "login";
	}//method
	
	@ModelAttribute("domains")
	public List<String> getDomains(){
		List<String> list=null;
				//prepare list of Domains
				list=new ArrayList<String>();
				list.add("gmail"); list.add("rediff");list.add("yahoo");
				return list;
   	}//method
	
	@InitBinder
	public void  myDateBinder(WebDataBinder binder){
		SimpleDateFormat sdf=null;
		sdf=new SimpleDateFormat("dd-MM-yyyy");
		//Register CustomDateEditor
		binder.registerCustomEditor(Date.class,new CustomDateEditor(sdf,true));
	}
	
}//class

