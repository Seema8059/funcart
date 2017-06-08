package com.funcart.dao;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.funcart.domain.Customer;
import com.funcart.domain.Order;
import com.funcart.domain.dto.CheckoutDto;


@Repository
public class CheckoutDao {

	private static final String String = null;
	@PersistenceContext
	private EntityManager em;
	private String errorMsg;
	
	
	@Transactional(rollbackOn = Exception.class)
	public boolean customerCheckout(CheckoutDto paymentDto) throws Exception {
		boolean flag = false;
	
		Customer customer = new Customer();
		customer.setEmail(paymentDto.getEmail());
		customer.setBillingAddress(paymentDto.getBillingaddress());
		customer.setShippingAddress(paymentDto.getShippingaddress());
		customer.setPaymentBy(paymentDto.getPaymentBy());
		
		
		
	try {

		if (checkShipDetail(paymentDto)) {

			int result = em
					.createQuery(
							"UPDATE Customer c  SET c.paymentBy = :paymentBy, c.billingAddress = :billingAddress,c.shippingAddress = :shippingAddress"	+ " where  c.email =:email)")
					.setParameter("email", paymentDto.getEmail())
					.setParameter("paymentBy", paymentDto.getPaymentBy())
					.setParameter("shippingAddress", paymentDto.getShippingaddress())
					.setParameter("billingAddress", paymentDto.getBillingaddress())
					.executeUpdate();

			if (result >= 0)
				flag = true;
		}
	}
	catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
	
	return flag;
	
}

	public boolean checkShipDetail(CheckoutDto paymentDto) {
		boolean flag = false;
		if (!paymentDto.getBillingaddress().isEmpty()||!paymentDto.getPaymentBy().isEmpty()||!paymentDto.getShippingaddress().isEmpty()) {
			flag = true;
		}else {
			flag = false;
		}
		return flag;
	}

	public boolean checkCustomer(String email,String password)throws Exception{
		boolean flag = false;
		Customer customer = null;
		customer = (Customer) em.createQuery("Select c From Customer As c Where c.email = ? And c.password = ?")
				.setParameter(0, email)
				.setParameter(1, password)
				.getSingleResult();
		if(customer != null)
			flag = true;
		return flag;
	}
	
}


