package Entities;

public class Estudante extends Cliente {
    @Override
    public void emprestar(){
        System.out.println("Emprestimo Reprovado");
    }
}
