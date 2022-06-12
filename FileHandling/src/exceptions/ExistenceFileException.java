package exceptions;

public class ExistenceFileException extends Exception {
    public String toString(String fileName){
        return "Arquivo: "+ fileName + " não encontrado!";
    }
}
