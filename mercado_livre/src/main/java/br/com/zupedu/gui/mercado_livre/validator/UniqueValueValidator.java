package br.com.zupedu.gui.mercado_livre.validator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    @PersistenceContext
    EntityManager entityManager;
    private String domainAttribute;
    private Class<?> klass;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        domainAttribute = constraintAnnotation.nomeCampo();
        klass = constraintAnnotation.domainClass();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        Query query = entityManager.createQuery("select 1 from " + klass.getName() +" where " + domainAttribute +"=:value");
        query.setParameter("value", s);
        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <= 1, "Foi encontrado mais de um" + klass + "com o atributo "+domainAttribute+" = " + s);
        return list.isEmpty();
    }
}
