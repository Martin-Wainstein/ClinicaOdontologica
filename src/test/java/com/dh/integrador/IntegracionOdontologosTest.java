package com.dh.integrador;



import com.dh.integrador.entities.Odontologo;
import com.dh.integrador.service.OdontologoService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionOdontologosTest {

    @Autowired
    OdontologoService odontologoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void crearOdontologo() throws Exception {
        Odontologo odontologo = new Odontologo(123,"pepe","perez");
        String respuesta = "{\"id\":5,\"matricula\":123,\"nombre\":\"pepe\",\"apellido\":\"perez\"}";

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                .writer();

        String odontologoJSON = writer.writeValueAsString(odontologo);

        MvcResult resultado = this.mockMvc.perform(MockMvcRequestBuilders.post("/odontologos/crear")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(resultado.getResponse().getContentAsString().isEmpty());
        Assert.assertEquals(respuesta,resultado.getResponse().getContentAsString());
    }

    @Test
    public void borrar() throws Exception {
        String respuesta = "Odontologo id: 5 eliminado";
        MvcResult resultado = mockMvc.perform(MockMvcRequestBuilders.delete(
                        "/odontologos/{id}","5").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertEquals(respuesta,resultado.getResponse().getContentAsString());
    }

    @Test
    public void actualizar() throws Exception {
        Odontologo odontologo = new Odontologo(123,"pepe","perez");
        String respuesta = "Actualizado: {\"id\":4,\"matricula\":123,\"nombre\":\"pepe\",\"apellido\":\"perez\"}";

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE,false)
                .writer();

        String odontologoJSON = writer.writeValueAsString(odontologo);

        MvcResult resultado = this.mockMvc.perform(MockMvcRequestBuilders.put("/odontologos/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assert.assertFalse(resultado.getResponse().getContentAsString().isEmpty());
        Assert.assertEquals(respuesta,resultado.getResponse().getContentAsString());
    }


}
