package com.dh.integrador.controller;


import com.dh.integrador.entities.Odontologo;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    @Autowired
    private OdontologoService odontologoService;


//    @GetMapping("/index")
//    public String traerOdontologo(Model model, @RequestParam("id") Long id){
//        Optional<Odontologo> odontologo = odontologoService.buscar(id);
//        model.addAttribute("nombre",odontologo.get().getNombre());
//        model.addAttribute("apellido",odontologo.get().getApellido());
//        return "index";
//    }

    @PostMapping("/crear")
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo){
        logger.info("se creo un Odontologo");
        return odontologoService.guardar(odontologo);
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException{
        odontologoService.actualizar(odontologo);
        logger.info("se actualizó un Odontologo");
        return ResponseEntity.ok("Actualizado: "+ odontologo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(id);
        if (odontologoBuscado != null){
            return ResponseEntity.ok(odontologoBuscado.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
            odontologoService.eliminar(id);
            logger.info("se eliminó un Odontologo");
            return ResponseEntity.ok("Odontologo id: "+id+" eliminado");

    }

    @GetMapping
    public List<Odontologo> listarOdontologos(){
        return odontologoService.listarOdontologos();
    }
}
