package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object classe){
        return somaGenerica(classe, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object classe) {
        return somaGenerica(classe, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object classe) {
        BigDecimal subtracao = subtrair(classe);
        BigDecimal adicao = somar(classe);

        return adicao.subtract(subtracao);
    }

    private BigDecimal somaGenerica(Object classe, Class<? extends Annotation> annotation) {
        Field[] fields = classe.getClass().getDeclaredFields();
        BigDecimal adicao = BigDecimal.ZERO;

        for (Field field : fields) {
            if (field.isAnnotationPresent(annotation)
                    && field.getType().equals(BigDecimal.class)) {
                try {
                    field.setAccessible(true);
                    adicao = adicao.add((BigDecimal) field.get(classe));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return adicao;
    }
}
