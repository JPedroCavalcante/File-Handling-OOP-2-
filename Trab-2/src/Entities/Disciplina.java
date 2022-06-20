package Entities;

import java.util.ArrayList;

public abstract class Disciplina {
    protected static ArrayList<String> disciplinas = new ArrayList<String>();

    public boolean adicionarDisciplina(String disciplina){
        for(int i=0; i < disciplinas.size(); i++)
            if(disciplinas.get(i) == disciplina)
                return false;
        
        return disciplinas.add(disciplina);
    }

    public ArrayList<String> getDisciplinas(){
        return disciplinas;
    }
}