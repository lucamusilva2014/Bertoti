package entity;

import entity.strategys.Emprestimo;
import entity.strategys.Permitido;

public class Cliente {

    private Emprestimo emprestimo;

    public void setEmprestimo(Emprestimo emprestimo){this.emprestimo =emprestimo;}

    public void chamarEmprestimo(){
        if(emprestimo.equals(new Permitido())){
            emprestimo.emprestar();
        }
        else {
            emprestimo.emprestar();
        }
    }

}
