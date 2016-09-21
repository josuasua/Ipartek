package com.ipartek.formacion.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.ipartek.formacion.service.UsuarioServiceImp;


@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	@Autowired
	private UsuarioServiceImp usuImp = null;
	private ModelAndView mav = null;
	
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
		List<Usuario> usuarios = usuImp.getAll();
		mav.addObject("listado-usuarios", usuarios);
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getByID(@PathVariable("id") int id) {
		mav = new ModelAndView("usuarios/usuario");
		Usuario usuario = usuImp.getByID(id);
		mav.addObject("usuario", usuario);
		return mav;
	}

	@RequestMapping(value="/addUsuario", method=RequestMethod.GET)
	public String addUsuario(Model model){
		model.addAttribute("usuario", new Usuario());
		return "usuarios/usuario";
	}
	
	@RequestMapping(value = "deleteUsuario/{id}")
	public String delete(@PathVariable("id") int id) {	
		usuImp.delete(id);	
		return "redirect:/usuarios";
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
				usuImp.update(usuario);
			}else{
				usuImp.create(usuario);
			}
		}
		
		return destino; // ofuscacion de URL
	}
	
	@RequestMapping(value="/restclients", method=RequestMethod.GET)
	public String sendToRestGetAll(){
		return "/usuarios/listado_rest";
	}

}
