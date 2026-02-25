package entity.strategys;

public class Permitido implements Emprestimo {

    @Override
    public void emprestar() {
        System.out.println("Emprestimo Aprovado!");
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
