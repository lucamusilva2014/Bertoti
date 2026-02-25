package entity.strategys;

public class Proibido implements Emprestimo {

    @Override
    public void emprestar() {
        System.out.println("Emprestimo Negado!");
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
