public class aluno extends pessoas {
    private float nota;
    private float nota1;
    private float nota2;
    private float nota3;
    private float freq;

    public aluno(String ID, String senha, String nome, float nota, float freq, float nota1, float nota2, float nota3) {
        super();  // Chama o construtor da classe pai
        this.nome = nome;
        this.ID = ID;
        this.senha = senha;
        this.nota = nota; // Inicializa os atributos específicos da classe aluno
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;
        this.freq = freq;
    }

    // Sobrescrevendo o toString para garantir que o nome será impresso
    @Override
    public String toString() {
        return getNome();  // Retorna apenas o nome do aluno
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getNota3() {
        return nota3;
    }

    public void setNota3(float nota3) {
        this.nota3 = nota3;
    }

    public float getFreq() {
        return freq;
    }

    public void setFreq(float freq) {
        this.freq = freq;
    }

    @Override
    public void info(String ID, String senha, String nome) {
        System.out.println("Aluno cadastrado! Dados:\n" + "ID: " + ID + " Senha: " + senha + " Nome: " + nome);
        System.out.println();
    }
}