package Entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ManipulacaoArquivo {
    private File diretorio;
    public String caminhoDiretorio;

    public boolean criarDiretorio(String nome) {
        diretorio = new File("./src/Provas/" + nome);
        
        if(diretorio.exists())
            return false;

        return diretorio.mkdir();
    }

    public void escreverConteudo(String gabarito, String nomeAluno, String disciplina) {
        FileWriter fw;
        try {
            fw = new FileWriter("./src/Provas/" + disciplina + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(gabarito + " " + nomeAluno);
            bw.newLine();
            bw.close();
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(Exception e){
            System.out.println("Ops... aconteceu algum erro.");
        }
    }

    public String lerConteudo(String caminho){
        String resposta="";

        try {
            FileReader fr = new FileReader(caminho);
            BufferedReader br = new BufferedReader(fr);
            resposta = br.readLine();
            br.close();
            fr.close();
        } catch(IOException e) {
            System.out.println("Arquivo não encontrado!");
        }
        
        return resposta;
    }

    public ArrayList<String> lerProva(String disciplina){
        ArrayList<String> prova = new ArrayList<String>();

        try {
            FileReader fr = new FileReader("./src/Provas/" + disciplina + ".txt");
            BufferedReader br = new BufferedReader(fr);
            String linha = br.readLine();
            while(linha != null){
                prova.add(linha);
                linha = br.readLine();
            }
            br.close();
            fr.close();
        } catch(IOException e) {
            System.out.println("Arquivo não encontrado!");
        }

        return prova;
    }

    public boolean escreverConteudo(String arquivo, String conteudo){
        FileWriter fw;
        boolean retorno = false;
        try {
            fw = new FileWriter("./src/Provas/" + arquivo + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(conteudo);
            bw.newLine();
            bw.close();
            fw.close();
            retorno = true;
        } catch(IOException e) {
            e.printStackTrace();
        }

        return retorno;
    }

}
