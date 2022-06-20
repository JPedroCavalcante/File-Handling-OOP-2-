import java.util.ArrayList;
import java.util.Scanner;

import Entities.ManipulacaoArquivo;
import Exceptions.GabaritoInvalidoException;
import Services.Notas;

public class App {
    public static void main(String[] args) {
        String comando;
        int com;
        Notas notas = null;
        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("Bem vindo ao sistema de notas!");
            System.out.println("\t1. Adicionar disciplina");
            System.out.println("\t2. Adicionar aluno");
            System.out.println("\t3. Gerar resultado");
            System.out.println("\t4. Visualizar resultados");

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
                        try {
                            System.out.print("Informe seu gabarito: ");
                            gabarito = scan.next();
                            notas.setGabarito(gabarito);
                            System.out.print("Informe o nome do aluno: ");
                            scan.nextLine();
                            nomeAluno = scan.nextLine();
                            System.out.println("Disciplinas:");
                            for(int i=0; i < notas.getDisciplinas().size(); i++)
                                System.out.println("\t" + i + ". " + notas.getDisciplinas().get(i));
                            System.out.println("Qual disciplina (digite um número): ");
                            com = Integer.parseInt(scan.next());
                        
                            disciplina = notas.getDisciplinas().get(com);
                            if(notas.AdicionarAluno(gabarito, nomeAluno, disciplina))
                                System.out.println("Aluno adicionado!");
                        } catch(IndexOutOfBoundsException e){
                            System.out.println("Você escolheu uma disciplina que não existe.");
                        } catch(GabaritoInvalidoException e) {
                            System.out.println("Gabarito inválido! Seu gabarito deve conter 10 respostas de V ou F, por exemplo: VVVFVFVFVV.");
                        } catch(Exception e) {
                            System.out.println("Ops... Aconteceu algum erro inesperado na execução do programa.");
                        }
                    } else
                        System.out.println("Notas está vazio!");
                    break;
                case 3:
                    if(notas != null){
                        String gabarito, disciplina;
                        System.out.print("Informe o caminho do gabarito (Exemplo: ./src/Provas/GabaritoPOO.txt): ");
                        gabarito = scan.next();
                        System.out.println("Disciplinas:");
                        for(int i=0; i < notas.getDisciplinas().size(); i++)
                            System.out.println("\t" + i + ". " + notas.getDisciplinas().get(i));
                        System.out.println("Qual disciplina (digite um número): ");
                        try {
                            com = Integer.parseInt(scan.next());
                        
                            disciplina = notas.getDisciplinas().get(com);
                            notas.gerarResultado(gabarito, disciplina);
                        } catch(IndexOutOfBoundsException e){
                            System.out.println("Você escolheu uma disciplina que não existe.");
                        } catch(Exception e){
                            System.out.println("Ops... Aconteceu algum erro inesperado.");
                        }
                    } else
                        System.out.println("Notas está vazio!");
                    break;
                case 4:
                    if(notas != null) {
                        String disciplina;
                        ManipulacaoArquivo ma = new ManipulacaoArquivo();
                        ArrayList<String> prova = new ArrayList<>();
                        System.out.println("Disciplinas:");
                        for(int i=0; i < notas.getDisciplinas().size(); i++)
                            System.out.println("\t" + i + ". " + notas.getDisciplinas().get(i));
                        System.out.println("Qual disciplina (digite um número): ");
                        try {
                            com = Integer.parseInt(scan.next());
                        
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
                        } catch(Exception e){
                            System.out.println("Ops... Aconteceu algum erro inesperado.");
                        }
                    } else
                        System.out.println("Notas está vazio!");

                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            System.out.print("Deseja continuar? [nao/sim] ");
            comando = scan.next();
        } while(comando.toLowerCase().charAt(0) != 'n');
        System.out.println("Agradecemos o seu uso em nosso sistema de notas!");
        scan.close();
    }
}
