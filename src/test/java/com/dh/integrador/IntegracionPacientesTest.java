package com.dh.integrador;

import com.dh.integrador.entities.Domicilio;
import com.dh.integrador.entities.Odontologo;
import com.dh.integrador.entities.Paciente;
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
public class IntegracionPacientesTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void crearPaciente() throws Exception {
        Paciente paciente = new Paciente("Wainstein","Martin", "mw@gmail.com", 44555999, LocalDate.of(2022,01,01), new Domicilio("libertador", 1234, "CABA", "BS AS"));
        String respuesta = "{\"id\":5,\"apellido\":\"Wainstein\",\"nombre\":\"Martin\",\"email\":\"mw@gmail.com\",\"dni\":44555999,\"fechaIngreso\":\"2022-01-01\",\"domicilio\":{\"id\":5,\"calle\":\"libertador\",\"numero\":1234,\"localidad\":\"CABA\",\"provincia\":\"BS AS\"}}";

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                .findAndRegisterModules()
                .writer();

        String odontologoJSON = writer.writeValueAsString(paciente);

        MvcResult resultado = this.mockMvc.perform(MockMvcRequestBuilders.post("/pacientes/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(resultado.getResponse().getContentAsString().isEmpty());
        Assert.assertEquals(respuesta,resultado.getResponse().getContentAsString());
    }


}
