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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dao.persistence.*;
import com.ipartek.formacion.service.EjemplarServiceImp;
import com.ipartek.formacion.service.LibroServiceImp;
import com.ipartek.formacion.service.UsuarioServiceImp;
import com.ipartek.formacion.service.interfaces.*;

@Controller
@RequestMapping(value="/reservas")
public class ReservaController {
	/*no creo que sean necesarios, lo que tendré que hacer será usar las pojo 
	 * y crear un servicio de reservas con su DAO propia para hacer las consultas 
	 * con inner join especiales a las reservas. lo mismo con los ejemplares.
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(ReservaController.class);
	private ModelAndView mav = null;
	
	@Autowired
	private UsuarioServiceImp usu=null;
	
	@Autowired
	private LibroServiceImp lib=null;
	
	@Autowired
	private EjemplarServiceImp ejem=null;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView getAllEjemplaresDisponibles(@PathVariable("id") int id) {
		
		mav = new ModelAndView("reservas/listado");
		List<Ejemplar> ejemplares = null;
		Ejemplar ejemplar = null;
		Usuario usuario = null;
		ejemplar = ejem.getEjemplarReservado(id);
		if (ejemplar.getId()>0){
			ejemplar.setUsuario(usu.getByID(id));
			mav.addObject("ejemplar", ejemplar);
		} else{
			ejemplares = ejem.getAllDisponible();	
			mav.addObject("listado-ejemplares", ejemplares);
			usuario = usu.getByID(id);
			mav.addObject("usuario", usuario);
		}
		return mav;
	}
	
	@RequestMapping(value = "/reservarLibro/{idusuario}/{idejemplar}/", method = RequestMethod.GET)
	public ModelAndView EjemplarReservado(@PathVariable("idusuario") int idusuario,@PathVariable("{idejemplar}") int idejemplar){
		//llamada para realizar reserva en la base de datos
		System.out.println(idusuario);
		System.out.println(idejemplar);
		Ejemplar ejemplar = null;
		ejemplar.setUsuario(usu.getByID(idusuario));
		ejem.update(ejemplar);
		mav = new ModelAndView("usuarios/listado");
		List<Usuario> usuarios = usu.getAll();
		mav.addObject("listado-usuarios", usuarios);

		return mav;

	}
	
	@RequestMapping(value = "/devolverLibro/{idusuario}/{idejemplar}", method = RequestMethod.GET)
	public ModelAndView EjemplarDevuelto(@PathVariable("idusuario") int idusuario,@PathVariable("{idejemplar}") int idejemplar){
		//llamada para realizar reserva en la base de datos
		System.out.println(idusuario);
		System.out.println(idejemplar);
		Ejemplar ejemplar = null;
		ejemplar.setUsuario(usu.getByID(0));
		ejem.update(ejemplar);
		mav = new ModelAndView("usuarios/listado");
		List<Usuario> usuarios = usu.getAll();
		mav.addObject("listado-usuarios", usuarios);

		return mav;

	}
}
