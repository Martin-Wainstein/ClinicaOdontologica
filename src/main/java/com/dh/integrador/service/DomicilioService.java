package com.dh.integrador.service;

import com.dh.integrador.entities.Domicilio;
import com.dh.integrador.entities.Paciente;
import com.dh.integrador.entities.Turno;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService {

    @Autowired
    DomicilioRepository repository;

    public Domicilio registrarDomicilio(Domicilio domicilio){
        return repository.save(domicilio);
    }

    public List<Domicilio> listardomicilios(){
        return repository.findAll();
    }

    public Optional<Domicilio> buscar(Long id){
        return repository.findById(id);
    }

    public Domicilio actualiazar(Domicilio domicilio){
        if (buscar(domicilio.getId()).isPresent()) {
            return repository.save(domicilio);
        }
        else {
            return null;
        }
    }

    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Domicilio> domicilioBuscado = buscar(id);
        if (domicilioBuscado.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("No existe domicilio con id: "+id+", no se puede eliminar");
        }
    }

}
