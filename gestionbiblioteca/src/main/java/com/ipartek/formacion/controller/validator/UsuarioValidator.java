package com.ipartek.formacion.controller.validator;

import java.util.Calendar;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.ipartek.formacion.dao.persistence.Usuario;

public class UsuarioValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return Usuario.class.equals(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "nombreInvalido", "Nombre requerido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "apellidos", "apellidosInvalido", "Apellidos requeridos");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "emailInvalido", "Email requerido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "passwordInvalido", "Password requerido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fNacimiento", "fNacimientoInvalido", "Fecha nacimiento requerido");
		
		Usuario usuario = (Usuario) obj;
		System.out.println(usuario.getfNacimiento());
		comprobarFecha(usuario);
		
		if(usuario.getId()<-1){
			errors.rejectValue("id", "ValorNoValido", new Object[]{"'id'"}, "No puede usar ese valor");
		}
		if(usuario.getNombre().length()<=3){
			errors.rejectValue("nombre", "nombreCorto", new Object[]{"'nombre'"}, "Debe contener mas de 3 caracteres");
		}
		if(usuario.getApellidos().length()<=3){
			errors.rejectValue("apellidos", "apellidosCorto", new Object[]{"'apellidos'"}, "Debe contener mas de 3 caracteres");
		}
		if(usuario.getPassword().length()<6 || usuario.getPassword().length()>50){
			errors.rejectValue("password", "passwordCorto", new Object[]{"'password'"}, "Debe contener minimo 6 caracteres y 50 maximo");
		}else{
			String pass = usuario.getPassword();
			int op= 0;
			boolean numero = false;
			boolean letra = false;
		    for(int i = 0; i < pass.length(); ++i) {
		        char caracter = pass.charAt(i);
		        if(!Character.isLetterOrDigit(caracter)) {
		        	op=1;
		        }
		        if(Character.isDigit(caracter)) {
		        	numero = true;
		        }
		        if(Character.isLetter(caracter)) {
		        	letra = true;
		        }
		    }
		    System.out.println("numero: "+numero+". Letra: "+letra );
		    if (numero==false || letra==false){
		    	op=1;
		    }
		    if (op==1){
		    	System.out.println("Contraseña incorrecta");
				errors.rejectValue("password", "passwordcaracterincorrecto", new Object[]{"'password'"}, "Debe ser alfanumerica, debe contener numeros y letras. No introduzca simbolos. No use la Ñ");
			}
		
		}
		
	}
	
	public void comprobarFecha(Usuario usuario){
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		/*String day = request.getParameter(Constantes.PAR_DIA);
		String month = request.getParameter(Constantes.PAR_MES);
		String anyo = request.getParameter(Constantes.PAR_ANYO);
		int mes = Integer.parseInt(month);
		int dia = Integer.parseInt(day);
		int year = Integer.parseInt(anyo);
		calendar.set(Calendar.MONTH, mes - 1);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		Date date = calendar.getTime();*/

	}

}
