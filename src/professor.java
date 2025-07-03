public class professor extends pessoas {
    public String Cadeira;

    public String getCadeira() {
        return Cadeira;
    }

    public void setCadeira(String Cadeira) {
        this.Cadeira = Cadeira;
    }

    public professor(String nome, String ID, String senha, String Cadeira) {
        super();
        this.ID = ID;
        this.nome = nome;
        this.senha = senha;
        this.Cadeira = Cadeira;
    }

    @Override

    public void info(String ID, String senha, String nome){
        System.out.println("Professor cadastrado! Dados:\n" + "ID: " + ID + " Senha: " + senha + " Nome: " + nome);
        System.out.println();
    }
}