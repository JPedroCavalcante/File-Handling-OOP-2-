package Exceptions;

public class GabaritoInvalidoException extends Exception {
    public String toString(){
        return "Gabarito inválido! É necessário informar 10 respostas.";
    }
}
