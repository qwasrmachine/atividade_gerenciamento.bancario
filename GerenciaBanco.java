package atividade.gerenciabanco;

import java.util.Scanner;
import java.util.regex.Pattern;

public class GerenciaBanco {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("#===== Sistema Gerenciador de Contas Bancárias =====#\n" +
                            "Deseja cadastrar seu usuário?\n" +
                            "• [1] - Cadastrar usuário\n" + 
                            "• [2] - Sair do programa");
        char criarUsr = scan.next().charAt(0);
        scan.nextLine(); // Consome o caractere de escape
        
        Usuario usr = criarUsuario(criarUsr);
        
        if (usr != null) {
            System.out.println("#===== Seja bem vindo(a) " + usr.getNome() + "! =====#");
            boolean sair = false;
            while (!sair) {
                System.out.println("O que deseja fazer?");
                if (usr.getContas().isEmpty()) {
                    System.out.println("• [1] - Criar nova conta\n" + 
                                        "• [x] - Sair do programa");
                } else {
                    System.out.println("• [1] - Criar nova conta\n" + 
                                        "• [2] - Gerenciar contas ativas\n" + 
                                        "• [x] - Sair do programa");
                }
                
                char opcaoConta = ' ';
                
                boolean contaValid = false;
                while (!contaValid) {
                    try {
                        opcaoConta = scan.next().charAt(0);
                        contaValid = true;
                    } catch (Exception e) {
                        System.out.println("Formato de resposta inválido, insira um único digito correspondente à ação desejada");
                    }
                    scan.nextLine(); // Consome o caractere de escape
                }
                
                switch(opcaoConta) {
                    case '1' -> {
                        usr.addConta();
                        usr.printContas();
                    }
                    case '2' -> adminContas(usr);
                    case 'x' -> sair = true;
                }
            }
        }
        
        System.out.println("#===== Saindo do programa... =====#");
    }

    public static Usuario criarUsuario(char res) {
        Scanner scan = new Scanner(System.in);
        
        if (res == '1') {
            
            String nome = "";
            String sobrenome = "";
            int idade = 0;
            String cpf = "";
            
            System.out.println("------------------------------\n" +
                                "Por Favor, informe seu nome e sobrenome separados por um espaço, como no seguinte exemplo: \"José Ferreira\"");
            
            boolean nomeValid = false;
            while (!nomeValid) {
                try {
                    String[] nomeCompleto = scan.nextLine().split(" ");
                    nome = nomeCompleto[0];
                    sobrenome = nomeCompleto[1];
                    nomeValid = true;
                } catch (Exception e) {
                    System.out.println("Formato de nome incorreto, por favor, insira seu nome seguido de seu sobrenome, separados por um espaço, como no exemplo: \"Marcus Menezes\"");
                }
            }
            
            System.out.println("------------------------------\n" +
                                "Agora por favor insira sua idade em caracteres numéricos");
            
            boolean idadeValid = false;
            while (!idadeValid) {
                try {
                    idade = scan.nextInt();
                    idadeValid = true;
                } catch (Exception e) {
                    System.out.println("Formato de idade incorreto, por favor, insira caracteres numéricos somente");
                }
                scan.nextLine(); // Consome o caractere de escape
            }
            
            System.out.println("------------------------------\n" +
                                "Agora por favor insira seu CPF no formato \"XXX.XXX.XXX-XX\"");
            
            boolean cpfValid = false;
            while (!cpfValid) {
                cpf = scan.nextLine();
                Pattern cpfFormato = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
            
                if (cpfFormato.matcher(cpf).matches()) {
                    cpfValid = true;
                } else {
                    System.out.println("O CPF está incorreto, por favor, insira no formato \"XXX.XXX.XXX-XX\"");
                }
            }
            
            System.out.println("Usuário criado com sucesso!");
            return new Usuario(nome, sobrenome, idade, cpf);
        }
        System.out.println("------------------------------");
        return null;
    }
    
    public static void adminContas(Usuario usr) {
        Scanner scan = new Scanner(System.in);
        
        usr.printContas();
        System.out.println("Qual conta você deseja gerenciar?");
        
        int selec = 0;
        Conta conta = null;
        boolean contaValid = false;
        while (!contaValid) {
            try {
                selec = scan.nextInt();
                contaValid = true;
            } catch (Exception e) {
                System.out.println("Resposta inválida, insira um valor numérico correspondente à conta que deseja gerenciar");
            }
            if (selec <= 0 || selec > usr.getContas().size()) {
                System.out.println("Por favor, selecione um número entre 1 e " + usr.getContas().size());
                contaValid = false;
            }
            scan.nextLine(); // Consome o caractere de escape
            conta = usr.getContas().get(selec - 1);
        }
        
        boolean sair = false;
        while (!sair) {
            System.out.println("------------------------------");
            System.out.println("Conta de: " + usr.getNome() + " " + usr.getSobrenome());
            System.out.print("Saldo atual: ");
            double saldo = conta.getSaldo();
            System.out.println();
            
            System.out.println("O que deseja fazer?");
            if (saldo == 0.0) {
                System.out.println("• [1] - Depositar\n" + "• [x] - Retornar ao início");
            } else {
                System.out.println("• [1] - Depositar\n" + "• [2] - Sacar\n" + "• [3] - Transferir\n" + "• [x] - Retornar ao início");
            }
                
            char acao = ' ';
            boolean acaoValid = false;
            while (!acaoValid) {
                try {
                    acao = scan.next().charAt(0);
                    acaoValid = true;
                } catch (Exception e) {
                    System.out.println("Formato de resposta inválido, insira um único digito correspondente à ação desejada");
                }
                scan.nextLine(); // Consome o caractere de escape
            }

            switch(acao) {
                case '1' -> {
                    boolean depositoValid = false;
                    while (!depositoValid) {
                        try {
                            System.out.println("\nInsira o valor a ser depositado no formato \"X,0\": ");
                            conta.depositar(scan.nextDouble());
                            
                            System.out.print("Saldo atual: ");
                            conta.getSaldo();
                            System.out.println();
                            
                            depositoValid = true;
                        } catch (Exception e) {
                            System.out.println("Valor inválido, por favor, use o formato \"X,0\"");
                        }
                        scan.nextLine(); // Consome o caractere de escape
                    }
                }
                case '2' -> {
                    boolean saqueValid = false;
                    while (!saqueValid) {
                        try {
                            System.out.print("Saldo atual: ");
                            conta.getSaldo();
                            System.out.println();
                            
                            System.out.println("\nInsira o valor a ser sacado no formato \"X,0\": ");
                            conta.sacar(scan.nextDouble());
                            
                            System.out.print("Saldo atual: ");
                            conta.getSaldo();
                            System.out.println();
                            
                            saqueValid = true;
                        } catch (Exception e) {
                            System.out.println("Valor inválido, por favor, use o formato \"X,0\"");
                        }
                        scan.nextLine(); // Consome o caractere de escape
                    }
                }
                case '3' -> {
                    Conta contaAlvo = null;
                    boolean selecValid = false;
                    while (!selecValid) {
                        int alvo = 0;
                        try {
                            usr.printContas();
                            System.out.println("Insira o número da conta que receberá a transferência");
                            alvo = scan.nextInt();
                            selecValid = true;
                        } catch (Exception e) {
                            System.out.println("Resposta inválida, insira um valor numérico correspondente à conta que irá receber a transferência");
                        }
                        if (alvo <= 0 || alvo > usr.getContas().size()) {
                            System.out.println("Por favor, selecione um número entre 1 e " + usr.getContas().size());
                            contaValid = false;
                        }
                        scan.nextLine(); // Consome o caractere de escape
                        contaAlvo = usr.getContas().get(alvo - 1);
                    }
                    
                    boolean transferValid = false;
                    while (!transferValid) {
                        try {
                            System.out.print("Saldo atual: ");
                            conta.getSaldo();
                            System.out.println();
                            
                            System.out.println("Por favor insira o valor a ser transferido, use o formato \"X,0\"");
                            
                            double valor = scan.nextDouble();
                            
                            conta.sacar(valor);
                            contaAlvo.depositar(valor);
                            
                            System.out.print("Saldo atual (-): ");
                            conta.getSaldo();
                            System.out.println();
                            
                            System.out.print("Saldo atual (+): ");
                            contaAlvo.getSaldo();
                            System.out.println();
                            
                            transferValid = true;
                        } catch (Exception e) {
                            System.out.println("Valor inválido, por favor, use o formato \"X,0\"");
                        }
                        scan.nextLine(); // Consome o caractere de escape
                    }
                }
                case 'x' -> sair = true;
            }
        }
    }
}