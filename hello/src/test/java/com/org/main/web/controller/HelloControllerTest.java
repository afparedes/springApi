package com.org.main.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.main.domain.service.SaludoService;
import com.org.main.persistence.entity.Saludo;
import com.org.main.web.controller.HelloController;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class HelloControllerTest {
	
	@MockBean
	private SaludoService saludoService;

    @InjectMocks
	private HelloController controller;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	@Test
	public void contextLoads2() throws Exception {
		assertThat(mockMvc).isNotNull();
	}
	
	@Test
	public void testMockito() throws Exception {
		Mockito.when(saludoService.testMockito()).thenReturn("hola23");
        assertEquals(controller.testMockito(), "hola3");
		Mockito.verify(saludoService).testMockito();
	}
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/saludo/test34")).andDo(print())
											.andExpect(status().isOk())
											.andExpect(content().string("hola23"));
				
	}
	@Test
	public void formalShouldReturnJohn() throws Exception {
		ArrayList<Saludo> lista=new ArrayList<>();
		lista.add(new Saludo(1,"John","Johnson"));
		Optional<List> opt=Optional.of(lista);

		Mockito.doReturn(lista).when(saludoService).getAll();
		this.mockMvc.perform(get("/saludo/all")).andDo(print())
											.andExpect(status().isOk())
											.andExpect(content().json("[\n" +
													"    {\n" +
													"        \"id\": 1,\n" +
													"        \"nombre\": \"John\",\n" +
													"        \"apellido\": \"Johnson\",\n" +
													"        \"genero\": null,\n" +
													"        \"pronombre\": null\n" +
													"    }\n" +
													"]"));
		Mockito.verify(saludoService).getAll();
				
	}
//	@Test
//	public void formalShouldReturnDustin() throws Exception {
//
//		Mockito.when(saludoService.getSaludoFormal(Mockito.any(String.class))).thenReturn(new ResponseEntity<>("Saludos estimado Dustin",HttpStatus.OK));
//
//		this.mockMvc.perform(get("/saludo/formal")).andDo(print())
//											.andExpect(status().isOk())
//											.andExpect(content().string("Saludos estimado Dustin"));
//
//		Mockito.verify(saludoService).getSaludoFormal(Mockito.any(String.class));
//
//	}
//	@Test
//	public void allShouldReturnJohnAndMarlon() throws Exception {
//		List<Saludo> temp=Arrays.asList(new Saludo(1,"John","Johnson"),new Saludo(2,"Marlon","Gracia"));
//
//		Mockito.when(saludoService.getAll()).thenReturn((List<Saludo>) new ResponseEntity<>(temp,HttpStatus.ACCEPTED));
//		this.mockMvc.perform(get("/saludo/all")).andDo(print())
//											.andExpect(status().isAccepted())
//											.andExpect(jsonPath("$[0].nombre", is("John")))
//											.andExpect(jsonPath("$[1].nombre", is("Marlon")));
//
//		Mockito.verify(saludoService).getAll();
//
//	}
//	@Test
//	public void deleteShouldReturnNotFound() throws Exception {
//		Mockito.when(saludoService.getSaludo(Mockito.eq(2))).thenReturn(Optional.empty());
//		this.mockMvc.perform(delete("/saludo/delete/2")).andDo(print())
//											.andExpect(status().isNotFound());
//		Mockito.verify(saludoService).getSaludo(Mockito.eq(2));
//
//	}
//	@Test
//	public void saveShouldReturnSaludo() throws Exception {
//		Mockito.when(saludoService.saveSaludo(Mockito.any())).thenReturn(new ResponseEntity<>(new Saludo(1,"John","Johnson"),HttpStatus.OK));
//		 this.mockMvc.perform(post("/saludo/nuevo")
//				 .contentType(MediaType.APPLICATION_JSON)
//			        .content(new ObjectMapper()
//			        		.writeValueAsString(new Saludo(1,"John","Johnson"))))
//		 									.andDo(print())
//											.andExpect(status().isOk())
//											.andExpect(jsonPath("$.nombre", is("John")))
//											.andExpect(jsonPath("$.apellido", is("Johnson")));
//		 Mockito.verify(saludoService).saveSaludo(Mockito.any());
//
//	}
//	@Test
//	public void putShouldReturnSaludo() throws Exception {
//		OngoingStubbing<Optional<Saludo>> optionalOngoingStubbing = Mockito.when(saludoService.updateById(Mockito.eq(2), Mockito.any())).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//		this.mockMvc.perform(put("/saludo/actualizar/2").contentType(MediaType.APPLICATION_JSON)
//			        .content(new ObjectMapper().writeValueAsString(new Saludo(1,"John","Johnson"))))
//		 									.andDo(print())
//											.andExpect(status().isNotFound());
//		 Mockito.verify(saludoService).updateById(Mockito.eq(2),Mockito.any());
//
//	}

}
