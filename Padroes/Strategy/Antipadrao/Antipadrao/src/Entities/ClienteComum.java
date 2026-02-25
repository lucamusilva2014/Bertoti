package Entities;

public class ClienteComum extends Cliente {
    @Override
    public void emprestar(){
        System.out.println("Emprestimo Reprovado");
    }
}
