package br.com.codenation.calculadora;


public class CalculadoraSalario {

	private static final double SALARIO_MINIMO = 1039;
	private static final double PORCENTAGEM_DESCONTO_ATE_1500 = 1 - (8.0 / 100);
	private static final double PORCENTAGEM_DESCONTO_DE_1500_ATE_4000 = 1 - (9.0 / 100);
	private static final double PORCENTAGEM_DESCONTO_APOS_4000 = 1 - (11.0 / 100);
	private static final double PORCENTAGEM_DESCONTO_DE_3000_ATE_6000 = 1 - (7.5 / 100);
	private static final double PORCENTAGEM_DESCONTO_APOS_6000 = 1 - (15.0 / 100);

	public long calcularSalarioLiquido(double salarioBase) {

		if (salarioBase <= SALARIO_MINIMO) {
			return Math.round(0.0);
		}

		double salarioDescontoInss = calcularInss(salarioBase);
		return Math.round(calcularIrrf(salarioDescontoInss));
	}

	private double calcularInss(double salarioBase) {

		if (salarioBase <= 1500) {
			return salarioBase * PORCENTAGEM_DESCONTO_ATE_1500;
		} else if (salarioBase >= 1500.01 && salarioBase <= 4000) {
			return salarioBase * PORCENTAGEM_DESCONTO_DE_1500_ATE_4000;
		}
		return salarioBase * PORCENTAGEM_DESCONTO_APOS_4000;
	}

	private double calcularIrrf(double salarioBrutoInss) {

		if (salarioBrutoInss <= 3000) {
			return salarioBrutoInss;
		} else if (salarioBrutoInss >= 3000.01 && salarioBrutoInss <= 6000) {
			return salarioBrutoInss * PORCENTAGEM_DESCONTO_DE_3000_ATE_6000;
		}
		return salarioBrutoInss * PORCENTAGEM_DESCONTO_APOS_6000;
	}
}