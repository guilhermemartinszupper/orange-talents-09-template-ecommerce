package br.com.zupedu.gui.mercado_livre;

import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import br.com.zupedu.gui.mercado_livre.usuario.UsuarioRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @ParameterizedTest
    @CsvSource(value = {
            "null,null"," , ","teste.com.br,12345"
    },nullValues = {"null"})
    void deveRetornarBadRequestCasoTenhaDadosInvalidos(String usr, String pssw) throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest(usr,pssw);
        String request = mapper.writeValueAsString(usuarioRequest);
        String URI = "/usuarios";
        MockHttpServletRequestBuilder consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                     status().isBadRequest()
                );

    }
    @Test
    void deveRetornarOk() throws Exception {
        UsuarioRequest usuarioRequest = new UsuarioRequest("gui@zup.com","teste123");
        String request = mapper.writeValueAsString(usuarioRequest);
        String URI = "/usuarios";
        MockHttpServletRequestBuilder consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                        status().isOk()
                );
    }
}
