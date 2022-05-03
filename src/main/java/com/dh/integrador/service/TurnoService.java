package com.dh.integrador.service;

import com.dh.integrador.entities.Paciente;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.repository.TurnoRepository;
import com.dh.integrador.entities.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    TurnoRepository repository;


    public Turno registrarTurno(Turno turno){
        return repository.save(turno);
    }

    public List<Turno> listarTurno(){
        return repository.findAll();
    }

    public Optional<Turno> buscar(Long id){
        return repository.findById(id);
    }

    public Turno actualizar(Turno turno)throws ResourceNotFoundException{
        Optional<Turno> turnoBuscado = buscar(turno.getId());
        if (turnoBuscado.isPresent()) {
            return repository.save(turno);
        }
        else {
            throw new ResourceNotFoundException("No existe paciente con id: "+turno.getId()+", no se puede actualizar");
        }
    }

    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoBuscado= buscar(id);
        if (turnoBuscado.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("No existe turno con id: "+id+", no se puede eliminar");
        }
    }

}
