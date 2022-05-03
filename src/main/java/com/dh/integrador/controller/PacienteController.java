package com.dh.integrador.controller;

import com.dh.integrador.entities.Paciente;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    private PacienteService pacienteService;



    @PostMapping("/crear")
    public Paciente registrarPaciente(@RequestBody Paciente paciente){
        logger.info("se creo un Paciente");
        return pacienteService.guardar(paciente);
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente)throws ResourceNotFoundException{
        pacienteService.actualizar(paciente);
        logger.info("se actualizó un Paciente");
        return ResponseEntity.ok("Actualizado: "+ paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(id);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
            pacienteService.eliminar(id);
        logger.info("se eliminó un Paciente");
            return ResponseEntity.ok("Paciente id: "+id+" eliminado");
    }

    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacienteService.listaPacientes();
    }
}