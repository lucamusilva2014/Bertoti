import entity.Cliente;
import entity.strategys.Permitido;
import entity.strategys.Proibido;

public class Main{
        public static void main(String[] args){

            Cliente lucas= new Cliente();

            lucas.setEmprestimo(new Permitido());
            lucas.chamarEmprestimo();

            lucas.setEmprestimo(new Proibido());
            lucas.chamarEmprestimo();

        }
    }


