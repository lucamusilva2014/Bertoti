# Engenharia de Software
## 1. Comentar com suas palavras o primeiro trecho do livro Software Engineering at Google, Oreilly.
Diferentemente de programação que seria apenas a criação do código em si, a Engenharia de software é um pouco mais rigorosa onde devemos utilizar práticas e métodos baseados em conhecimentos teóricos para criar um processo mais confiável e preciso para a programação do software.

## 2. Comentar com suas palavras o segundo trecho do livro Software Engineering at Google, Oreilly

Engenharia de Software pode ser considerado como programação integrada ao tempo, utilizando práticas para fazer o código suntentavel, tornando o  adaptavel através do seu ciclo de vida e sua escalavel através da necessidade de  evolução do programa. Dessa forma a empresa pode decidir o custo e a trocas necessárias para a criação desse software.


## 3. Listar e explicar 3 exemplos de tradeoffs

- O SQLite melhora o desempenho ao manter dados locais, mas, ao longo do tempo, o código se torna mais complexo e difícil de atualizar, especialmente quando a base de dados cresce.
-  Startups que utilizam o MVP que exige com poucos testes e validações, priorizando o custo e o tempo de desenvolvimento, mas isso aumenta o risco de bugs e falhas na qualidade.
-  O Twitter começou com uma arquitetura monolítica. Com o crescimento, precisaram migrar para uma arquitetura de microsserviços. Isso permitiu maior escalabilidade, mas aumentou a complexidade na gestão e no desenvolvimento de novos recursos.



## 4. Classes UML

![UML_Cinema drawio](https://github.com/user-attachments/assets/47e09e50-0d12-4eef-8d26-c47756a6bef1)


## 5 JAVA
### Classe Cinema


![Cinema](https://github.com/user-attachments/assets/b891af4d-af35-4d86-b37b-354c70af7aa1)

### Classe Filme
![Filme](https://github.com/user-attachments/assets/d1563138-093a-437c-bf55-8eb1332b3799)

## 6 Teste Customizado

![Teste_1](https://github.com/user-attachments/assets/11809d96-e81a-4a87-9be0-ffcb15e97638)


## 7 SQLITE
### MAIN
```java
package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static Cinema cinema = new Cinema();

    public static void main(String[] args) {
        cinema.conectar();
        cinema.criarTabela();
        cinema.addFilme("Vingadores", "001");
        cinema.buscarTodos();
    }
}
```
### Cinema
```java
package org.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Cinema {
    private String url = "jdbc:sqlite:salacinema.db";

    public Cinema() {
    }

    public void conectar() {
        try (Connection conn = DriverManager.getConnection(this.url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS salacinema (" +
                "id TEXT PRIMARY KEY," +
                "filme TEXT NOT NULL);";

        try (
                Connection conn = DriverManager.getConnection(this.url);
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addFilme(String filme, String id) {
        String sql = "INSERT INTO salacinema(id, filme) VALUES('" + id + "', '" + filme + "')";
        System.out.println(sql);

        try (
                Connection conn = DriverManager.getConnection(this.url);
                Statement stmt = conn.createStatement()
        ) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void buscarTodos() {
        String sql = "SELECT * FROM salacinema";

        try (
                Connection conn = DriverManager.getConnection(this.url);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)
        ) {
            System.out.println(rs);

            while (rs.next()) {
                System.out.println(rs.getString("id"));
                System.out.println(rs.getString("filme"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

```
