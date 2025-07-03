import java.io.*;
import java.util.ArrayList;

public class addAlunosAux implements addAlunos {
    private ArrayList<aluno> listaAlunos = new ArrayList<>();  // Mantém como variável de instância

    @Override
    public void adicionarAluno(aluno aluno) {
        listaAlunos.add(aluno);
        System.out.println("Aluno adicionado: " + aluno.getNome());
    }

    @Override
    public void exibirAlunos() {
        System.out.println("Quantidade de alunos cadastrados: " + listaAlunos.size());
        System.out.println("Lista de Alunos Cadastrados:");
        for (aluno aluno : listaAlunos) {
            System.out.println(aluno.getNome());
        }
    }

    @Override
    public void lerAlunosDoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dadosContas.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");  // Usa vírgula como delimitador CSV
                if (dados.length >= 3) {
                    String id = dados[0].trim();    // Remove espaços extras
                    String senha = dados[1].trim();
                    String nome = dados[2].trim();

                    // Verifica se o ID começa com "0" e se o aluno ainda não está na lista
                    if (id.startsWith("0") && !idExistente(id)) {
                        aluno alunoLido = new aluno(id, senha, nome, 0, 0, 0, 0, 0);
                        listaAlunos.add(alunoLido);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
    }

    // Método para verificar se um aluno com o ID já existe na lista
    public boolean idExistente(String id) {
        for (aluno a : listaAlunos) {
            if (a.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<aluno> getListaAlunos() {
        return listaAlunos;
    }
}