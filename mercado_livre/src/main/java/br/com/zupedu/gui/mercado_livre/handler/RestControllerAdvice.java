package br.com.zupedu.gui.mercado_livre.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {

    @Autowired
    MessageSource messageSource;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        List<ErroDTO> erros = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            ErroDTO erroDTO = new ErroDTO(fieldError.getField(),message);
            erros.add(erroDTO);
        });
         List<ObjectError> objectErrors = exception.getBindingResult().getGlobalErrors();
         objectErrors.forEach(objectError -> {
             String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
             ErroDTO erroDTO = new ErroDTO(objectError.getObjectName(),message);
             erros.add(erroDTO);
         });
         return erros;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ErroDTO handleEntityNotFound(EntityNotFoundException exception){
        return new ErroDTO("Id",exception.getMessage());
    }


    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ProdutoNaoPertenceAoUsuarioException.class)
    public ErroDTO handleEntityNotFound(ProdutoNaoPertenceAoUsuarioException exception){
        return new ErroDTO("autorização",exception.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(QuantidadeInsuficienteNoEstoqueException.class)
    public ErroDTO handleQuantidadeInsuficienteNoEstoque(QuantidadeInsuficienteNoEstoqueException exception){
        return new ErroDTO("Quantidade",exception.getMessage());
    }

}
