package com.ipartek.formacion.controller.validator;

import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dao.persistence.Ejemplar;



public class EjemplarValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Ejemplar.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		// TODO Auto-generated method stub
		
	}

	
	

}
