package atividade.gerenciabanco;

import java.util.List;
import java.util.ArrayList;

public class Usuario {
    final private String nome;
    final private String sobrenome;
    final private int idade;
    final private String cpf;
    private List<Conta> contas;
    
    public Usuario(String nome, String sobrenome, int idade, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.cpf = cpf;
        this.contas = new ArrayList<Conta>();
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCpf() {
        return cpf;
    }
    
    public List<Conta> getContas() {
        return contas;
    }
    
    public void printContas() {
        System.out.println("------ Você possui " + contas.size() + " contas! ------");
        int x = 1;
        for (Conta conta : contas) {
            System.out.print("[" + x + "]" + " - ");
            conta.getSaldo();
            System.out.println();
            x++;
        }
        System.out.println("\n-----------------------------------");
    }
    
    public List<Conta> addConta() {
        contas.add(new Conta(this));
        return contas;
    }
    
    public List<Conta> delConta(int idx) {
        contas.remove(idx);
        return contas;
    }
}
