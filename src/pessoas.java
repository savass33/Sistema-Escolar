import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class pessoas {

    ArrayList<pessoas> listaPessoas = new ArrayList<>();

    public String nome;
    public String ID;
    public String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setsenha(String senha){
        this.senha = senha;
    }

    public String getsenha(){
        return senha;
    }

    public void adicionarUser(pessoas users) {
        listaPessoas.add(users);
    }

    public void info(String ID, String senha, String nome){
        System.out.println("ID: " + ID + " Senha: " + senha + " Nome: " + nome);
        System.out.println();
    }

    // Método para salvar dados no arquivo CSV - Alunos
    public void salvarNoArquivo(String nome, String id, String senha) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("dadosContas.csv", true))) {
            writer.println(id + "," + senha + "," + nome);
            System.out.println("Dados salvos com sucesso no arquivo CSV.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados no arquivo CSV.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para salvar as notas e a frequência no CSV
    public void salvarNotasNoCSVFreq(String id, double nota1, double nota2, double nota3, double frequencia) {
        ArrayList<String> linhas = new ArrayList<>();
        boolean alunoEncontrado = false;

        // Ler o arquivo e buscar o aluno pelo ID
        try (BufferedReader reader = new BufferedReader(new FileReader("dadosContas.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");  // Separação padrão do CSV
                if (dados[0].equals(id)) {
                    // Adiciona as notas e a frequência ao final da linha do aluno
                    linha += "," + nota1 + "," + nota2 + "," + nota3 + "," + frequencia;
                    alunoEncontrado = true;
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }


        // Escrever novamente o arquivo com as informações atualizadas
        try (PrintWriter writer = new PrintWriter(new FileWriter("dadosContas.csv"))) {
            for (String linha : linhas) {
                writer.println(linha);
            }
            if (alunoEncontrado) {
                System.out.println("Notas e frequência adicionadas com sucesso ao aluno ID: " + id);
            } else {
                System.out.println("Aluno com ID: " + id + " não encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }

    // Método para ler as notas de um aluno do arquivo CSV
    public String lerNotasDoAluno(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader("dadosContas.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 5 && dados[0].equals(id)) { // Assumindo que temos ID, senha, nome e 3 notas
                    String notas = "Notas do aluno " + dados[2] + ":\n";
                    notas += "Nota 1: " + dados[3] + "\n";
                    notas += "Nota 2: " + dados[4] + "\n";
                    notas += "Nota 3: " + dados[5] + "\n";
                    return notas;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return "Aluno não encontrado ou notas não registradas.";
    }


    // Salvar no arquivo CSV - Professores (com campo adicional 'Cadeira')
    public void salvarNoArquivo(String nome, String id, String senha, String cadeira) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("dadosContas.csv", true))) {
            writer.println(id + "," + senha + "," + nome + "," + cadeira);
            System.out.println("Dados salvos com sucesso no arquivo CSV.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados no arquivo CSV.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Verificar login no arquivo CSV
    public static boolean verificarLogin(String ID, String senha) {
        try (BufferedReader reader = new BufferedReader(new FileReader("dadosContas.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 2 && dados[0].equals(ID) && dados[1].equals(senha)) {
                    return true; // Login bem-sucedido
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // ID ou senha nÃ£o encontrados
    }

    // Método para salvar as notas no CSV
    public void salvarNotasNoCSV(String id, double nota1, double nota2, double nota3) {
        ArrayList<String> linhas = new ArrayList<>();
        boolean alunoEncontrado = false;

        // Ler o arquivo e buscar o aluno pelo ID
        try (BufferedReader reader = new BufferedReader(new FileReader("dadosContas.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");  // Separação padrão do CSV
                if (dados[0].equals(id)) {
                    // Adiciona as notas ao final da linha do aluno
                    linha += "," + nota1 + "," + nota2 + "," + nota3;
                    alunoEncontrado = true;
                }
                linhas.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Escrever novamente o arquivo com as informações atualizadas
        try (PrintWriter writer = new PrintWriter(new FileWriter("dadosContas.csv"))) {
            for (String linha : linhas) {
                writer.println(linha);
            }
            if (alunoEncontrado) {
                System.out.println("Notas adicionadas com sucesso ao aluno ID: " + id);
            } else {
                System.out.println("Aluno com ID: " + id + " não encontrado.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao atualizar o arquivo: " + e.getMessage());
        }
    }

    // Método para editar as notas de um aluno e salvar no arquivo CSV
    public void editarNotas(String id, double nota1, double nota2, double nota3) {
        salvarNotasNoCSV(id, nota1, nota2, nota3);
    }

}