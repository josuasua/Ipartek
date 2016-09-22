package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dao.persistence.Ejemplar;

public class EjemplarValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Ejemplar.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "editorial", "editorialInvalido", "Editorial requerido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numeropaginas", "cantidadInvalido", "Numero de paginas requerido");
		Ejemplar ejemplar = (Ejemplar) obj;
		if(ejemplar.getId()<-1){
			errors.rejectValue("id", "ValorNoValido", new Object[]{"'id'"}, "No puede usar ese valor");
			System.out.println("id incorrecto");
		}
		if(ejemplar.getEditorial().length()<=3){
			errors.rejectValue("editorial", "editorialCorto", new Object[]{"'editorial'"}, "Debe contener mas de 3 caracteres");
		}
		if(ejemplar.getNumeropaginas()<5 || ejemplar.getNumeropaginas()>5000){
			errors.rejectValue("numeropaginas", "NumeropaginasNoValida", new Object[]{"'numeropaginas'"}, "El numero de paginas debe estar entre 5 y 5000 hojas.");
		}
	}

}
