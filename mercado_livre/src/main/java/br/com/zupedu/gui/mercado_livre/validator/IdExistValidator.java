package br.com.zupedu.gui.mercado_livre.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IdExistValidator implements ConstraintValidator<IdExist, Object> {

    @PersistenceContext
    EntityManager entityManager;

    Class<?> klass;

    @Override
    public void initialize(IdExist constraintAnnotation) {
        klass = constraintAnnotation.domainClass();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null){
            return true;
        }
        Query query = entityManager.createQuery("Select 1 from " + klass.getName() + " where id = " + o);
        List<?> resultList = query.getResultList();
        Assert.isTrue(resultList.size() <= 1, "Foi encontrado mais de um" + klass + "com o id =" + o);
        return !resultList.isEmpty();
    }
}
