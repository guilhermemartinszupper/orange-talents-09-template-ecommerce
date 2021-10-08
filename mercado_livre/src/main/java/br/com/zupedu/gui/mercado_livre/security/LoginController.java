package br.com.zupedu.gui.mercado_livre.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ServiceToken serviceToken;

    @PostMapping
    public TokenDto logar(@RequestBody @Valid LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken loginCredentials = loginRequest.converter();
        Authentication authentication = authenticationManager.authenticate(loginCredentials);
        String token = serviceToken.gerarToken(authentication);
        return new TokenDto(token, "Bearer");
    }
}
