package com.dh.integrador.service;

import com.dh.integrador.entities.Paciente;
import com.dh.integrador.exceptions.ResourceNotFoundException;
import com.dh.integrador.repository.OdontologoRepository;
import com.dh.integrador.entities.Odontologo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService{

    @Autowired
    OdontologoRepository repository;



    public List<Odontologo> listarOdontologos() {
        return repository.findAll();
    }


    public Odontologo guardar(Odontologo odontologo) {
        return repository.save(odontologo);
    }


    public Odontologo actualizar(Odontologo odontologo) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = buscar(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            return repository.save(odontologo);
        }
        else {
            throw new ResourceNotFoundException("No existe paciente con id: "+odontologo.getId()+", no se puede actualizar");
        }
    }


    public void eliminar(Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = buscar(id);
        if (odontologoBuscado.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("No existe odontologo con id: "+id+", no se puede eliminar");
        }
    }


    public Optional<Odontologo> buscar(Long id) {
        return  repository.findById(id);
    }
}
