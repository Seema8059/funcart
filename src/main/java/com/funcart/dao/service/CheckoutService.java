package com.funcart.dao.service;


import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.funcart.dao.CheckoutDao;

import com.funcart.domain.dto.CheckoutDto;
import com.funcart.validator.Validator;

@Service
public class CheckoutService {
	
	@Autowired
	private CheckoutDao checkDao;

	
	private String errorMsg;
	
	public boolean paymentMode(CheckoutDto paymentDto) throws Exception{
		boolean flag = false;
		
		//if(Validator.emailValidate(paymentDto.getEmail())){
			try{
				if(checkDao.checkCustomer(paymentDto.getEmail(),paymentDto.getPassword())){
					if(checkDao.customerCheckout(paymentDto)){
					flag = true;
				}
				else{
					flag = false;
					errorMsg = "Error In Finding Customer";
				}
			}}catch(NoResultException e){
				flag = false;
				errorMsg = "Customer Not_Found";
			}
			return flag;

	}
	
public CheckoutDao getCheckDao() {
	return checkDao;
}
public void setCheckDao(CheckoutDao checkDao) {
	this.checkDao = checkDao;
}


public String getErrorMsg() {
	return errorMsg;
}
public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
}
}
