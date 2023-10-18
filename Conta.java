package atividade.gerenciabanco;

import java.text.DecimalFormat;

public class Conta {
    private double saldo;
    private final Usuario usuario;

    public Conta(Usuario usuario) {
        this.saldo = 0.0;
        this.usuario = usuario;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$" + formatarValor(valor) + " realizado com sucesso.");
        } else {
            System.out.println("Valor inválido. O depósito deve ser maior que zero.");
        }
    }

    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$" + formatarValor(valor) + " realizado com sucesso.\n" +
                                "Saldo Atual: R$" + formatarValor(saldo));
        } else if (valor > saldo) {
            System.out.println("Saldo insuficiente para saque.");
        } else {
            System.out.println("Valor inválido. O saque deve ser maior que zero.");
        }
    }

    public double getSaldo() {
        System.out.print("R$" + formatarValor(saldo));
        return saldo;
    }

    private String formatarValor(double valor) {
        DecimalFormat formato = new DecimalFormat("0.00");
        return formato.format(valor);
    }
}
