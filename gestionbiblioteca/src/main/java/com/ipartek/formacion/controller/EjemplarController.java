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

import com.ipartek.formacion.dao.persistence.Ejemplar;
import com.ipartek.formacion.service.EjemplarServiceImp;

@Controller
@RequestMapping(value="/ejemplares")
public class EjemplarController {
	
	private static final Logger logger = LoggerFactory.getLogger(EjemplarController.class);
	@Autowired
	private EjemplarServiceImp ejemImp = null;
	private ModelAndView mav = null;
	
	@Autowired
	@Qualifier("ejemplarValidator")
	private Validator validator;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {

		mav = new ModelAndView("ejemplares/listado");
		List<Ejemplar> ejemplares = ejemImp.getAll();
		mav.addObject("listado-ejemplares", ejemplares);
		return mav;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getByID(@PathVariable("id") int id) {
		mav = new ModelAndView("usuarios/usuario");
		Ejemplar ejemplar = ejemImp.getByID(id);
		mav.addObject("ejemplar", ejemplar);
		return mav;
	}
	
	@RequestMapping(value="/addEjemplar", method=RequestMethod.GET)
	public String addEjemplar(Model model){
		model.addAttribute("ejemplar", new Ejemplar());
		return "ejemplares/ejemplar";
	}
	
	@RequestMapping(value = "deleteEjemplar/{id}")
	public String delete(@PathVariable("id") int id) {	
		ejemImp.delete(id);	
		return "redirect:/ejemplares";
	}

	@RequestMapping(value="/save")
	public String saveEjemplar(@ModelAttribute("ejemplar") @Validated(Ejemplar.class) Ejemplar ejemplar, BindingResult bindingResult){
		//el objeto del model attribute se llama igual que el commandName del formulario, ser� lo que recibir� encapsulado
		//@Validated se usa para obligar a validar los datos a guardar
		String destino ="";
		
		if(bindingResult.hasErrors()){
			logger.info("El ejemplar tiene errores");
			destino = "ejemplares/ejemplar"; 
			//como tiene errores, lo manda otra vez a la pagina de alumno nuevo.
		}else{
			destino = "redirect:/ejemplares";
			if(ejemplar.getId()>0){
				ejemImp.update(ejemplar);
			}else{
				ejemImp.create(ejemplar);
			}
		}
		
		return destino; // ofuscacion de URL
	}
	
	@RequestMapping(value="/restclients", method=RequestMethod.GET)
	public String sendToRestGetAll(){
		return "/ejemplares/listado_rest";
	}
}
