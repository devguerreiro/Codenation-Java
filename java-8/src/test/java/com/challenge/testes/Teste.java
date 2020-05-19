package com.challenge.testes;

import com.challenge.desafio.CalculadorDeClasses;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class Teste {
    ClasseTeste classeAtributosPositivos;
    ClasseTeste classeAtributosNegativos;
    CalculadorDeClasses calculadorDeClasses;

    @Before
    public void init() {
        classeAtributosPositivos = new ClasseTeste(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3));
        classeAtributosNegativos = new ClasseTeste(new BigDecimal(-1), new BigDecimal(-2), new BigDecimal(-3));
         calculadorDeClasses = new CalculadorDeClasses();
    }

    @Test
    public void testarSomaNumerosPositivos() {
        assertEquals(new BigDecimal(6), calculadorDeClasses.somar(classeAtributosPositivos));
    }

    @Test
    public void testarSomaNumerosNegativos() {
        assertEquals(new BigDecimal(-6), calculadorDeClasses.somar(classeAtributosNegativos));
    }

    @Test
    public void testarSubtracaoNumerosPositivos() {
        assertEquals(new BigDecimal(6), calculadorDeClasses.subtrair(classeAtributosPositivos));
    }

    @Test
    public void testarSubtracaoNumerosNegativos() {
        assertEquals(new BigDecimal(-6), calculadorDeClasses.subtrair(classeAtributosNegativos));
    }

    @Test
    public void testarTotalizador() {
        assertEquals(new BigDecimal(0), calculadorDeClasses.totalizar(classeAtributosPositivos));
        assertEquals(new BigDecimal(0), calculadorDeClasses.totalizar(classeAtributosNegativos));
    }
}
