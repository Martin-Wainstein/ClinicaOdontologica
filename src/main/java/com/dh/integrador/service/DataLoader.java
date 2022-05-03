package com.dh.integrador.service;

import com.dh.integrador.entities.*;
import com.dh.integrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository repository;

    @Autowired
    OdontologoRepository odontologoRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    DomicilioRepository domicilioRepository;
    @Autowired
    TurnoRepository turnoRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordUser = passwordEncoder.encode("passwordUser");
        repository.save(new AppUser("Martin", "Martin", "user@gmail.com", passwordUser, AppUsuarioRoles.ROLE_USER));

        String passwordAdmin = passwordEncoder.encode("passwordAdmin");
        repository.save(new AppUser("Martin", "Martin", "admin@gmail.com", passwordAdmin, AppUsuarioRoles.ROLE_ADMIN));


        Odontologo odontologo1 = odontologoRepository.save(new Odontologo(1111, "Martin", "Wainstein"));
        Odontologo odontologo2 = odontologoRepository.save(new Odontologo(2222, "Juan", "Perez"));
        Odontologo odontologo3 = odontologoRepository.save(new Odontologo(3333, "Pedro", "Rodriguez"));
        Odontologo odontologo4 = odontologoRepository.save(new Odontologo(4444, "Esteban", "Quito"));

        Paciente paciente1 = pacienteRepository.save(new Paciente("Garcia", "Mateo", "mg@gmail.com", 44555666, LocalDate.of(2022, 1, 5), new Domicilio("libertador", 1234, "CABA", "BS AS")));
        Paciente paciente2 = pacienteRepository.save(new Paciente("Fernandez", "Tobias", "tf@gmail.com", 99555111, LocalDate.of(2021, 8, 20), new Domicilio("olazabal", 9876, "CABA", "BS AS")));
        Paciente paciente3 = pacienteRepository.save(new Paciente("Lopez", "Gustavo", "gl@gmail.com", 77333666, LocalDate.of(2019, 6, 6), new Domicilio("pampa", 7654, "CABA", "BS AS")));
        Paciente paciente4 = pacienteRepository.save(new Paciente("Moreno", "Alberto", "am@gmail.com", 11444222, LocalDate.of(2022, 5, 22), new Domicilio("mendoza", 5678, "CABA", "BS AS")));

        turnoRepository.save(new Turno(LocalDate.of(2022,8,20), paciente1, odontologo1));
        turnoRepository.save(new Turno(LocalDate.of(2022,7,19), paciente2, odontologo2));
        turnoRepository.save(new Turno(LocalDate.of(2022,6,18), paciente3, odontologo3));
        turnoRepository.save(new Turno(LocalDate.of(2022,5,17), paciente4, odontologo4));

    }
}
