package com.dh.integrador;

import com.dh.integrador.entities.Domicilio;
import com.dh.integrador.entities.Odontologo;
import com.dh.integrador.entities.Paciente;
import com.dh.integrador.entities.Turno;
import com.dh.integrador.service.DomicilioService;
import com.dh.integrador.service.OdontologoService;
import com.dh.integrador.service.PacienteService;
import com.dh.integrador.service.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {

    @Autowired
    TurnoService turnoService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    DomicilioService domicilioService;

    @Autowired
    private MockMvc mockMvc;

    public void cargarDatosEnBD(){
        //cargar turno
        //odontologo y paciente con una fecha
        domicilioService.registrarDomicilio(new Domicilio("calle falsa 123", 1234, "pilar", "BS AS"));
        odontologoService.guardar(new Odontologo(123, "Juan", "Perez"));
        pacienteService.guardar(new Paciente("Wainstein", "Martin", "mw@gmail.com", 44555888, LocalDate.of(2022, 04, 27), domicilioService.buscar(1L).get()));
        turnoService.registrarTurno(new Turno(LocalDate.of(2022, 04, 27), pacienteService.buscar(1L).get(), odontologoService.buscar(1L).get()));
    }

    @Test
    public void listarTurnos() throws Exception {
//        cargarDatosEnBD();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        Assert.assertFalse(result.getResponse().getContentAsString().isEmpty());
    }


}
