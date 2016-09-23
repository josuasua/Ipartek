package com.ipartek.formacion.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.ipartek.formacion.dao.persistence.Libro;
import com.ipartek.formacion.service.LibroServiceImp;

@Controller
@RequestMapping(value="/libros")
public class LibroController {
	
	private static final Logger logger = LoggerFactory.getLogger(LibroController.class);
	@Autowired
	private LibroServiceImp libImp = null;
	private ModelAndView mav = null;
	
	@Autowired
	@Qualifier("libroValidator")
	private Validator validator;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder){
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll() {

		mav = new ModelAndView("libros/listado");
		List<Libro> libros = libImp.getAll();
		mav.addObject("listado-libros", libros);
		return mav;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getByID(@PathVariable("id") int id) {
		mav = new ModelAndView("libros/libro");
		Libro libro = libImp.getByID(id);
		mav.addObject("libro", libro);
		return mav;
	}
	
	@RequestMapping(value="/addLibro", method=RequestMethod.GET)
	public String addLibro(Model model){
		model.addAttribute("libro", new Libro());
		return "libros/libro";
	}
	
	@RequestMapping(value = "deleteLibro/{id}",method = RequestMethod.GET)
	public String delete(@PathVariable("id") int id) {	
		System.out.println(id);
		libImp.delete(id);	
		return "redirect:/libros";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest req, HttpServletResponse res) {
		mav = new ModelAndView("cursos/listado");
		Libro libro = parseLibro(req);
		libImp.update(libro);
		mav.addObject("listado-libros", libro);
		return mav;
	}
	
	private Libro parseLibro(HttpServletRequest req) {
		
		Libro libro = null;
		int codigo = Integer.parseInt(req.getParameter("codigo"));
		libro.setId(codigo);
		String titulo = req.getParameter("titulo");
		libro.setTitulo(titulo);
		String autor = req.getParameter("autor");
		libro.setAutor(autor);
		String isbn = req.getParameter("isbn");
		libro.setIsbn(isbn);

		return libro;
	}

	@RequestMapping(value="/save")
	public String saveLibro(@ModelAttribute("libro") @Validated(Libro.class) Libro libro, BindingResult bindingResult){
		//el objeto del model attribute se llama igual que el commandName del formulario, ser� lo que recibir� encapsulado
		//@Validated se usa para obligar a validar los datos a guardar
		String destino ="";
		System.out.println(libro.getId());
		if(bindingResult.hasErrors()){
			System.out.println("El libro tiene errores");
			destino = "libros/libro"; 
			//como tiene errores, lo manda otra vez a la pagina de alumno nuevo.
		}else{
			System.out.println(String.valueOf(libro.getId())+libro.getAutor());
			destino = "redirect:/libros";
			if(libro.getId()>0){
				System.out.println(libro.getId());
				libImp.update(libro);
			}else{
				libImp.create(libro);
			}
		}
		return destino; // ofuscacion de URL
	}
	
	@RequestMapping(value="/restclients", method=RequestMethod.GET)
	public String sendToRestGetAll(){
		return "/libros/listado_rest";
	}
}
