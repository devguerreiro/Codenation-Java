package com.challenge.testes;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;

import java.math.BigDecimal;

public class ClasseTeste {
    @Somar @Subtrair
    private BigDecimal valor1;

    @Somar @Subtrair
    private BigDecimal valor2;

    @Somar @Subtrair
    private BigDecimal valor3;

    public ClasseTeste(BigDecimal valor1, BigDecimal valor2, BigDecimal valor3) {
        this.valor1 = valor1;
        this.valor2 = valor2;
        this.valor3 = valor3;
    }


    public BigDecimal getValor1() {
        return valor1;
    }

    public void setValor1(BigDecimal valor1) {
        this.valor1 = valor1;
    }

    public BigDecimal getValor2() {
        return valor2;
    }

    public void setValor2(BigDecimal valor2) {
        this.valor2 = valor2;
    }

    public BigDecimal getValor3() {
        return valor3;
    }

    public void setValor3(BigDecimal valor3) {
        this.valor3 = valor3;
    }
}
