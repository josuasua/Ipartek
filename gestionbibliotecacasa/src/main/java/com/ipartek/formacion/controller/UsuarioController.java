package com.ipartek.formacion.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.ipartek.formacion.dao.persistence.Usuario;
import com.ipartek.formacion.service.interfaces.UsuarioService;


@RequestMapping("/usuarios")
@Controller
public class UsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService as;
	private ModelAndView mav;
	
	@Autowired
	@Qualifier("usuarioValidator")
	private Validator validator;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {

		mav = new ModelAndView("usuarios/listado");
		List<Usuario> usuarios = as.getAll();
		mav.addObject("listado-usuarios", usuarios);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ModelAndView getById(@PathVariable("id") int id) {
		mav = new ModelAndView("/usuarios/usuario");
		mav.addObject("usuario", as.getByID(id));
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		as.delete(id);
		return "redirect:/usuarios";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/addUsuario")
	public ModelAndView createUsuario() {
		mav = new ModelAndView("/usuarios/usuario");
		mav.addObject("usuario", new Usuario());
		return mav;
	}

	@RequestMapping(value="/save")
	public String saveUsuario(@ModelAttribute("usuario") @Validated(Usuario.class) Usuario usuario, BindingResult bindingResult){
		//el objeto del model attribute se llama igual que el commandName del formulario, ser� lo que recibir� encapsulado
		//@Validated se usa para obligar a validar los datos a guardar
		String destino ="";
		
		if(bindingResult.hasErrors()){
			logger.info("El usuario tiene errores");
			destino = "usuarios/usuario"; 
			//como tiene errores, lo manda otra vez a la pagina de alumno nuevo.
		}else{
			destino = "redirect:/usuarios";
			if(usuario.getId()>0){
				as.update(usuario);
			}else{
				as.create(usuario);
			}
		}
		
		return destino; // ofuscacion de URL
	}


	@RequestMapping(value="/restclients", method=RequestMethod.GET)
	public String sendToRestGetAll(){
		return "/usuarios/listado_rest";
	}
	
}
