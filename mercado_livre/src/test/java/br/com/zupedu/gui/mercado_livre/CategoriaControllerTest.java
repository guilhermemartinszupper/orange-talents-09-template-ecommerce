package br.com.zupedu.gui.mercado_livre;


import br.com.zupedu.gui.mercado_livre.categoria.Categoria;
import br.com.zupedu.gui.mercado_livre.categoria.CategoriaRepository;
import br.com.zupedu.gui.mercado_livre.categoria.NovaCategoriaRequest;
import br.com.zupedu.gui.mercado_livre.usuario.NovoUsuarioRequest;
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
import org.springframework.test.web.servlet.MvcResult;
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
public class CategoriaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @PersistenceContext
    EntityManager entityManager;

    @ParameterizedTest
    @CsvSource(value = {
            "null,null","'' ,null"},nullValues = {"null"},emptyValue = "")
    void deveRetornarBadRequestCasoTenhaDadosInvalidos(String nome, Long idMae) throws Exception {
        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest(nome,idMae);
        String request = mapper.writeValueAsString(novaCategoriaRequest);
        String URI = "/categorias";
        MockHttpServletRequestBuilder consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                        status().isBadRequest()
                );
    }

    @Test
    @Transactional
    void deveRetornarOkSemEnviarCategoriaMae() throws Exception {
        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest("Teste",null);
        String request = mapper.writeValueAsString(novaCategoriaRequest);
        String URI = "/categorias";
        MockHttpServletRequestBuilder consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                        status().isOk()
                );
        deletarPorNome("Teste");
    }

    @Test
    @Transactional
    void deveRetornarOkAoEnviarCategoriaMae() throws Exception {
        String nomeMae = "TesteMae";
        String nomeFilha = "TesteFilha";

        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest(nomeMae,null);
        String request = mapper.writeValueAsString(novaCategoriaRequest);
        String URI = "/categorias";
        MockHttpServletRequestBuilder consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        MvcResult result = mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                        status().isOk()
                )
                .andReturn();
        Long idMae = Long.valueOf(String.valueOf(result.getResponse().getContentAsString().charAt(13)));
        NovaCategoriaRequest novaCategoriaRequest2 = new NovaCategoriaRequest(nomeFilha,idMae);
        request = mapper.writeValueAsString(novaCategoriaRequest2);
        consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                        status().isOk()
                )
                .andReturn();
        deletarPorNome(nomeFilha);
        deletarPorNome(nomeMae);
    }
    @Test
    @Transactional
    void deveRetornarBadRequestCasoIdMaeNaoExista() throws Exception {
        String nomeFilha = "TesteFilha";
        Long idMae = Long.MAX_VALUE;
        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest(nomeFilha,idMae);
        String request = mapper.writeValueAsString(novaCategoriaRequest);
        String URI = "/categorias";
        MockHttpServletRequestBuilder consultaRequest = post(URI).contentType(MediaType.APPLICATION_JSON).content(request);
        MvcResult result = mockMvc.perform(consultaRequest)
                .andDo(print())
                .andExpect(
                        status().isBadRequest()
                )
                .andReturn();
        deletarPorNome(nomeFilha);
    }
    @Test
    @Transactional
    void deveRetornarBadRequestCasoNomeJaExista() throws Exception {
        NovaCategoriaRequest novaCategoriaRequest = new NovaCategoriaRequest("Teste",null);
        String request = mapper.writeValueAsString(novaCategoriaRequest);
        String URI = "/categorias";
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
        deletarPorNome("Teste");
    }



    void deletarPorNome(String nome){
        entityManager.createQuery("delete from Categoria where nome = :pnome").setParameter("pnome",nome).executeUpdate();
    }
}
