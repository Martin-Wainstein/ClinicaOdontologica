package com.dh.integrador.controller;

import com.dh.integrador.entities.Odontologo;
import com.dh.integrador.entities.Paciente;
import com.dh.integrador.entities.Turno;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.service.OdontologoService;
import com.dh.integrador.service.PacienteService;
import com.dh.integrador.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private static final Logger logger = Logger.getLogger(PacienteController.class);

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.listarTurno());
    }

    @PostMapping("/crear")
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        Optional<Paciente> pacienteBuscado = pacienteService.buscar(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscar(turno.getOdontologo().getId());
        if (odontologoBuscado.isPresent() && pacienteBuscado.isPresent()){
            logger.info("se creo un Turno");
            respuesta = ResponseEntity.ok(turnoService.registrarTurno(turno));
        }
        else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
            turnoService.eliminar(id);
        logger.info("se eliminó un Turno");
            return ResponseEntity.status(HttpStatus.OK).body("Turno con ID: "+id+" eliminado");
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno)throws ResourceNotFoundException{
        turnoService.actualizar(turno);
        logger.info("se actualizó un Turno");
        return ResponseEntity.ok("Actualizado: "+ turno);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Turno>> buscarPorId(@PathVariable Long id){
        ResponseEntity<Optional<Turno>> respuesta;
        if (turnoService.buscar(id) != null) {
            respuesta = ResponseEntity.ok(turnoService.buscar(id));
        }
        else {
            respuesta = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return respuesta;
    }
}
