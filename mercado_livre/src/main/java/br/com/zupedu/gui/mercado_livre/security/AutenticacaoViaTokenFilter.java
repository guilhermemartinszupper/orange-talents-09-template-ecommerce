package br.com.zupedu.gui.mercado_livre.security;

import br.com.zupedu.gui.mercado_livre.usuario.Usuario;
import br.com.zupedu.gui.mercado_livre.usuario.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private ServiceToken serviceToken;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(ServiceToken serviceToken, UsuarioRepository usuarioRepository){
        this.serviceToken = serviceToken;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        boolean valido = serviceToken.isTokenValido(token);
        if(valido){
            autenticarUsuario(token);
        }
        filterChain.doFilter(request,response);
    }

    private void autenticarUsuario(String token) {
        Long idUsuario = serviceToken.getIdUsuario(token);
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        if(usuario.isEmpty()){
            throw new EntityNotFoundException("Usuario Nao Existe");
        }
        //Faz o spring considerar que o usuario ja esta autenticado
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(usuario,null,usuario.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token =  request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
