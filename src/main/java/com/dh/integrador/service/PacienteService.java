package com.dh.integrador.service;

//import aj.org.objectweb.asm.Opcodes;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.repository.PacienteRepository;
import com.dh.integrador.entities.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService{

    @Autowired
    PacienteRepository repository;


    public List<Paciente> listaPacientes() {
        return repository.findAll();
    }




    public Paciente guardar(Paciente paciente) {
        return repository.save(paciente);
    }


    public Paciente actualizar(Paciente paciente) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = buscar(paciente.getId());
        if (pacienteBuscado.isPresent()) {
            return repository.save(paciente);
        }
        else {
            throw new ResourceNotFoundException("No existe paciente con id: "+paciente.getId()+", no se puede actualizar");
        }
    }

    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = buscar(id);
        if (pacienteBuscado.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("No existe paciente con id: "+id+", no se puede eliminar");
        }
    }


    public Optional<Paciente> buscar(Long id) {
        return repository.findById(id);
    }

}
