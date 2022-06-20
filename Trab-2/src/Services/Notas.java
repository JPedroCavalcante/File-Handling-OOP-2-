package Services;

import java.util.ArrayList;

import Entities.Disciplina;
import Entities.ManipulacaoArquivo;
import Exceptions.GabaritoInvalidoException;

public class Notas extends Disciplina {
    private String gabarito;
    private String nomeAluno;
    private ManipulacaoArquivo arquivos;

    public Notas(String disciplina){
        arquivos = new ManipulacaoArquivo();
    }

    public boolean AdicionarAluno(String gabarito, String nomeAluno, String disciplina){
        arquivos.escreverConteudo(gabarito, nomeAluno, disciplina);
        return false;
    }

    public boolean gerarResultado(String gabarito, String disciplina){
        double mediaNotas=0, somaNotas=0;
        ArrayList<String> prova = arquivos.lerProva(disciplina);
        ArrayList<String> notas = new ArrayList<>(); 
        for(int i=0, k=0; i < prova.size(); i++){
            for(int j=0, contV=0, contF=0; j < 10; j++){
                if(arquivos.lerConteudo(gabarito).charAt(j) == prova.get(i).charAt(j))
                    k++;
                
                if(prova.get(i).toLowerCase().charAt(j) == 'v')
                    contV++;

                if(prova.get(i).toLowerCase().charAt(j) == 'f')
                    contF++;

                if(contV == 10 || contF == 10)
                    k=0;
            }
            notas.add(k+"");
            k=0;
        }

        ArrayList<String> prova1 = prova, prova2 = prova, notas1 = notas, notas2 = notas;

        for(int j = 1; j < prova1.size(); j++){
            char key = prova1.get(j).toLowerCase().charAt(11);
            String chave = prova1.get(j);
            int chaveNota = Integer.parseInt(notas1.get(j));
            int i = j - 1;

            while(i >= 0 && prova1.get(i).toLowerCase().charAt(11) > key){
                prova1.set(i + 1, prova1.get(i));
                notas1.set(i + 1, notas1.get(i));
                i = i - 1;
            }

            prova1.set(i + 1, chave);
            notas1.set(i + 1, chaveNota+"");
        }

        for(int i=0; i < notas.size(); i++){
            somaNotas += Integer.parseInt(notas.get(i));
        }

        mediaNotas = somaNotas / notas.size();

        for(int i=0; i < notas1.size(); i++){
            arquivos.escreverConteudo(prova1.get(i),"\t" + notas1.get(i), disciplina + "ResultadoAlfabetico");
        }

        arquivos.escreverConteudo(disciplina + "ResultadoAlfabetico", "Média geral: " + mediaNotas);

        for(int j = 1; j < prova2.size(); j++){
            String chave = prova2.get(j);
            int chaveNota = Integer.parseInt(notas2.get(j));
            int i = j - 1;

            while(i >= 0 && Integer.parseInt(notas2.get(i)) < chaveNota){
                prova2.set(i + 1, prova2.get(i));
                notas2.set(i + 1, notas2.get(i));
                i = i - 1;
            }

            prova2.set(i + 1, chave);
            notas2.set(i + 1, chaveNota+"");
        }

        for(int i=0; i < notas2.size(); i++)
            arquivos.escreverConteudo(prova2.get(i),"\t" + notas2.get(i), disciplina + "ResultadoNotas");

        arquivos.escreverConteudo(disciplina + "ResultadoNotas", "Média geral: " + mediaNotas);

        return true;
    }

    public String getGabarito(){
        return gabarito;
    }

    public String getNomeAluno(){
        return nomeAluno;
    }

    public void setGabarito(String gabarito) throws GabaritoInvalidoException {
        if(gabarito.length() != 10)
            throw new GabaritoInvalidoException();

        this.gabarito = gabarito;
    }

    public void setNomeAluno(String nomeAluno){
        this.nomeAluno = nomeAluno;
    }
    
}
