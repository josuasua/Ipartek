package com.ipartek.formacion.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dao.persistence.Libro;



public class LibroValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return Libro.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titulo", "tituloInvalido", "Titulo requerido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "autor", "autorInvalido", "Autor requerido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "isbnInvalido", "ISBN requerido");
		Libro libro = (Libro) obj;
		if(libro.getId()<-1){
			errors.rejectValue("id", "ValorNoValido", new Object[]{"'id'"}, "No puede usar ese valor");
		}
		if(libro.getTitulo().length()<=3){
			errors.rejectValue("titulo", "tituloCorto", new Object[]{"'titulo'"}, "Debe contener mas de 3 caracteres");
		}
		if(libro.getAutor().length()<=3){
			errors.rejectValue("autor", "nombreautorCorto", new Object[]{"'autor'"}, "Debe contener mas de 3 caracteres");
		}
	}

}
