import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class verificadorAux implements verificador {

    //Transformar isso numa classe
    public boolean existeCadastroNoArquivo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dadosContas.csv"))) {
            String linha;
            // Lê o arquivo e verifica se há pelo menos uma linha
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    return true; // Se houver qualquer linha não vazia, retorna true
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return false; // Se o arquivo estiver vazio ou não for lido corretamente, retorna false
    }
}
