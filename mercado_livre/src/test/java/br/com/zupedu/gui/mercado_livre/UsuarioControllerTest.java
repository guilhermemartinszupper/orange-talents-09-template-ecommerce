package br.com.zupedu.gui.mercado_livre;
import br.com.zupedu.gui.mercado_livre.usuario.NovoUsuarioRequest;
import br.com.zupedu.gui.mercado_livre.usuario.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    EntityManager entityManager;

    @ParameterizedTest
    @CsvSource(value = {
            "null,null","'' ,'' ","teste.teste,12345"
    },nullValues = {"null"},emptyValue = "")
    void deveRetornarBadRequestCasoTenhaDadosInvalidos(String usr, String pssw) throws Exception {
        NovoUsuarioRequest novoUsuarioRequest = new NovoUsuarioRequest(usr,pssw);
        String request = mapper.writeValueAsString(novoUsuarioRequest);
        String URI = "/usuarios";
        MockHttpServletRequestBuilder consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                     status().isBadRequest()
                );
    }
    @Test
    @Transactional
    void deveRetornarOk() throws Exception {
        deletarPorNome("teste@teste.com");
        NovoUsuarioRequest novoUsuarioRequest = new NovoUsuarioRequest("teste@teste.com","teste123");
        String request = mapper.writeValueAsString(novoUsuarioRequest);
        String URI = "/usuarios";
        MockHttpServletRequestBuilder consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                        status().isOk()
                );
       deletarPorNome("teste@teste.com");
    }

    @Test
    @Transactional
    void deveRetornarBadRequestSeEmailForDuplicado() throws Exception {
        deletarPorNome("teste@teste.com");
        NovoUsuarioRequest novoUsuarioRequest = new NovoUsuarioRequest("teste@teste.com","teste123");
        String request = mapper.writeValueAsString(novoUsuarioRequest);
        String URI = "/usuarios";
        MockHttpServletRequestBuilder consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                        status().isOk()
                );
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                        status().isBadRequest()
                );
        deletarPorNome("teste@teste.com");
    }

    void deletarPorNome(String nome){
        entityManager.createQuery("delete from Usuario where login = :pnome ").setParameter("pnome",nome).executeUpdate();
    }
}
