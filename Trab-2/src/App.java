import java.util.ArrayList;
import java.util.Scanner;

import Entities.ManipulacaoArquivo;
import Services.Notas;

public class App {
    public static void main(String[] args) {
        String comando;
        int com;
        Notas notas = null;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Bem vindo ao sistema de notas!");
            System.out.println("1. Adicionar disciplina");
            System.out.println("2. Adicionar aluno");
            System.out.println("3. Gerar resultado");
            System.out.println("4. Visualizar resultados");

            com = Integer.parseInt(scan.next());

            switch(com){
                case 1:
                    System.out.print("Qual o nome da disciplina: ");
                    comando = scan.next();
                    notas = new Notas(comando);
                    if(!notas.adicionarDisciplina(comando))
                        notas = null;
                    else
                        System.out.println("Disciplina adicionada!");
                    break;
                case 2:
                    if(notas != null){
                        String gabarito, nomeAluno, disciplina;
                        System.out.print("Informe seu gabarito: ");
                        gabarito = scan.next();
                        System.out.print("Informe o nome do aluno: ");
                        scan.nextLine();
                        nomeAluno = scan.nextLine();
                        System.out.println("Disciplinas:");
                        for(int i=0; i < notas.getDisciplinas().size(); i++)
                            System.out.println("\t" + i + ". " + notas.getDisciplinas().get(i));
                        System.out.println("Qual disciplina (digite um número): ");
                        com = Integer.parseInt(scan.next());
                        try {
                            disciplina = notas.getDisciplinas().get(com);
                            if(notas.AdicionarAluno(gabarito, nomeAluno, disciplina))
                                System.out.println("Aluno adicionado!");
                        } catch(IndexOutOfBoundsException e){
                            System.out.println("Você escolheu uma disciplina que não existe.");
                        }
                    }
                    break;
                case 3:
                    if(notas != null){
                        String gabarito, disciplina;
                        System.out.print("Informe o caminho do gabarito (Exemplo: ./src/Provas/POO.txt): ");
                        gabarito = scan.next();
                        System.out.println("Disciplinas:");
                        for(int i=0; i < notas.getDisciplinas().size(); i++)
                            System.out.println("\t" + i + ". " + notas.getDisciplinas().get(i));
                        System.out.println("Qual disciplina (digite um número): ");
                        com = Integer.parseInt(scan.next());
                        try {
                            disciplina = notas.getDisciplinas().get(com);
                            notas.gerarResultado(gabarito, disciplina);
                        } catch(IndexOutOfBoundsException e){
                            System.out.println("Você escolheu uma disciplina que não existe.");
                        }
                    }
                    break;
                case 4:
                    String disciplina;
                    ManipulacaoArquivo ma = new ManipulacaoArquivo();
                    ArrayList<String> prova = new ArrayList<>();
                    System.out.println("Disciplinas:");
                    for(int i=0; i < notas.getDisciplinas().size(); i++)
                        System.out.println("\t" + i + ". " + notas.getDisciplinas().get(i));
                    System.out.println("Qual disciplina (digite um número): ");
                    com = Integer.parseInt(scan.next());
                    try {
                        disciplina = notas.getDisciplinas().get(com);
                        prova = ma.lerProva(disciplina + "ResultadoAlfabetico");

                        System.out.println("Notas em ordem alfabetica.");
                        for(int i=0; i < prova.size(); i++){
                            System.out.println(prova.get(i));
                        }

                        prova = ma.lerProva(disciplina + "ResultadoNotas");

                        System.out.println("Notas em ordem decrescente de nota.");
                        for(int i=0; i < prova.size(); i++){
                            System.out.println(prova.get(i));
                        }
                    } catch(IndexOutOfBoundsException e){
                        System.out.println("Você escolheu uma disciplina que não existe.");
                    }

                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            System.out.print("Deseja continuar? [nao/sim] ");
            comando = scan.next();
        } while(comando.toLowerCase() != "nao");
        scan.close();
    }
}
