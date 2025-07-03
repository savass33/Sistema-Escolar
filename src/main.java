import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {

        verificadorAux verificador = new verificadorAux(); // Interface1
        addAlunosAux gerenciador = new addAlunosAux(); // Interface2
        pessoas pessoascoringa = new pessoas();

        boolean Loop = true; // Loop para as janelas aparecerem de maneira correta

        while (Loop) {
        	
            // JOptionPane do Menu 1
            String[] loginOpcao = { "Login", "Cadastro", "Sair" };
            int opcaoLogin = JOptionPane.showOptionDialog(null, "Escolha uma opção", "Bem-vindo à Lewis School",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, loginOpcao, loginOpcao[0]);

            // If para a opção "Sair" Menu 1
            if (opcaoLogin == 2 || opcaoLogin == -1) {
            System.out.println();
            System.out.println("Tchau piranha");
            break;
            }

            //Switch para opção escolhida no Menu 1
            switch (opcaoLogin) {
                case 0:
                    // Login

                    // Verificação para existência de Cadastro
                    if (!verificador.existeCadastroNoArquivo()) {
                    JOptionPane.showMessageDialog(null, "Nenhum cadastro encontrado. Realize um cadastro primeiro.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
                    }

                    // Variáveis para armazenar o login e senha
                    String IDLogin;
                    String SenhaLogin;

                    // Loop para pedir o ID até o usuário fornecer um valor ou cancelar
                    while (true) {  // O loop continua até o ID ser válido ou o usuário cancelar
                        IDLogin = JOptionPane.showInputDialog("Digite o seu ID:");

                        if (IDLogin == null) {
                        // Se o usuário clicou em 'Cancelar', volta ao menu principal
                        JOptionPane.showMessageDialog(null, "Você cancelou a entrada do ID.");
                        break;  // Sai do loop e volta para o menu principal
                        }

                        if (IDLogin.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "O ID não pode ser vazio.");
                        } else {
                        break;  // Se o ID for válido, sai do loop
                        }
                    }

                    if (IDLogin == null) break;  // Se o ID foi cancelado, volta ao menu

                    // Loop para pedir a senha até o usuário fornecer um valor ou cancelar
                    while (true) {  // O loop continua até a senha ser válida ou o usuário cancelar
                        SenhaLogin = JOptionPane.showInputDialog("Digite a sua senha:");
                        if (SenhaLogin == null) {
                        // Se o usuário clicou em 'Cancelar', volta ao menu principal
                        JOptionPane.showMessageDialog(null, "Você cancelou a entrada da senha.");
                        break;  // Sai do loop e volta para o menu principal
                        }

                        if (SenhaLogin.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "A senha não pode ser vazia.");
                        } else {
                            break;  // Se a senha for válida, sai do loop
                        }
                    }

                    if (SenhaLogin == null) break;

                    // Identificação do tipo de conta pelo número inicial do ID
                    char tipoConta = IDLogin.charAt(0);

                    //If para identificação de conta Aluno/Professor
                    // '0' para Aluno - '1' para Professor  <------- no ID.
                    if (tipoConta == '1') {
                    	
                        boolean loginValido = professor.verificarLogin(IDLogin, SenhaLogin);
                        
                        if (loginValido) {
                            JOptionPane.showMessageDialog(null, "Login do Professor bem-sucedido!", "Sucesso!",
                            JOptionPane.INFORMATION_MESSAGE);

                            // JOptionPane "Menu Professor" (Visualizar Turma, Editar Notas & Frequência, Sair)
                            Object[] SecaoProf = { "Turma", "Notas & Frequência", "Sair" };
                            int SecaoProfEscolha = JOptionPane.showOptionDialog(null,
                            "Selecione a ação que deseja realizar", "Bem-vindo ao Menu Professor!",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, SecaoProf, SecaoProf[0]);

                            // Enquanto o usuário não escolher "Sair"
                            while (SecaoProfEscolha != 2) {
                                //Switch de Escolhas: Turma ou Notas & Frequência
                                switch (SecaoProfEscolha) {
                                    case 0:
                                        // Turma

                                        String[] colunasTurma = { "Nome" , "ID"}; // Apenas a coluna "Nome"
                                        String[][] dadosTurma = new String[gerenciador.getListaAlunos().size()][2]; // Criação da Matriz, [a][b] -> a é a quantidade de linhas e b é a quantidade de colunas.

                                        // Preenchendo a matriz com os nomes dos alunos
                                        for (int i = 0; i < gerenciador.getListaAlunos().size(); i++) {
                                        aluno aluno = gerenciador.getListaAlunos().get(i);
                                        dadosTurma[i][0] = aluno.getNome();
                                        dadosTurma[i][1] = aluno.getID();// Preenche com o nome do aluno
                                        }

                                        // Criação do JTable com os dados
                                        DefaultTableModel modeloTabela = new DefaultTableModel(dadosTurma, colunasTurma);
                                        JTable table = new JTable(modeloTabela);

                                        // Adiciona o JTable em um JScrollPane para rolagem
                                        JScrollPane scrollPane = new JScrollPane(table);

                                        JFrame turmaFrame = new JFrame("Turma");
                                        turmaFrame.setSize(600, 400); // Tamanho ajustado para a tabela
                                        turmaFrame.setLocationRelativeTo(null); // Tela no centro.
                                        turmaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o comportamento padrão que vai acontecer quando o usuário fechar a janela turmaFrame
                                        turmaFrame.add(scrollPane, BorderLayout.CENTER); // Scroll no meio da tela.

                                        // Criação do botão "Sair"
                                        JButton sairTurmas = new JButton("Sair");
                                        sairTurmas.addActionListener(e -> turmaFrame.dispose()); // Fecha a janela de turma ao clicar, ActionListener é ouvinte de eventos ao botão e o Dispose é pra fechar. 
                                        turmaFrame.add(sairTurmas, BorderLayout.SOUTH); // Ficar botão embaixo.
                                        turmaFrame.setVisible(true);

                                        // Fica em loop até o usuário fechar a janela
                                        while (turmaFrame.isVisible()) {
                                        try {
                                        Thread.sleep(100); // Pausa de 100 ms para o usuário interagir, senão botar isso fica abrindo infinitamente.
                                        } catch (Exception e) {
                                        System.out.println("Erro:" + e.getMessage());
                                        }
                                        }

                                        // Mostra novamente o menu de ações após fechar a janela de notas
                                        SecaoProfEscolha = JOptionPane.showOptionDialog(null,
                                        "Selecione a ação que deseja realizar",
                                        "Bem-vindo ao Menu Professor!", JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE,
                                        null, SecaoProf, SecaoProf[0]);

                                        break;

                                    case 1:
                                        // Notas & Frequência

                                        //JOptionPane "Menu Edição de Alunos"
                                        String[] opcaoProfessor = { "Editar dados do aluno", "Sair" };
                                        int optionProfessor = JOptionPane.showOptionDialog(null, "Escolha sua ação",
                                        "Menu teacher",
                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                                        opcaoProfessor, opcaoProfessor[0]);

                                        while(optionProfessor != 1) {
                                            //Switch para escolha do Menu Edição de Alunos (Editar dados, Sair)
                                            switch (optionProfessor) {
                                                case 0:
                                                    // Editar dados do aluno

                                                    // Verifica se a lista de alunos está vazia
                                                    if (gerenciador.getListaAlunos().isEmpty()) {
                                                    gerenciador.exibirAlunos();
                                                    JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
                                                    return;
                                                    }

                                                    // Cria o array de nomes dos alunos
                                                    String[] arrayNotas = new String[gerenciador.getListaAlunos().size()];

                                                    // Preenche o array com os nomes dos alunos
                                                    for (int index = 0; index < gerenciador.getListaAlunos().size(); index++) {
                                                    arrayNotas[index] = gerenciador.getListaAlunos().get(index).getNome();
                                                    }

                                                    //JOptionPane com "Escolha de Alunos (Edição)"
                                                    String alunoSelecionadoNotas = (String) JOptionPane.showInputDialog(
                                                    null, "Escolha um aluno", "Selecione o aluno", JOptionPane.QUESTION_MESSAGE, null, arrayNotas, arrayNotas[0]
                                                    );

                                                    // Se o usuário cancelar a seleção de aluno, volta para o menu de edição de alunos
                                                    if (alunoSelecionadoNotas == null) {
                                                    JOptionPane.showMessageDialog(null, "Nenhum aluno foi selecionado. Voltando ao Menu Professor.");
                                                    // Volta para o Menu de Edição de Alunos
                                                    optionProfessor = 1; // Retorna ao loop de edição de alunos
                                                    } else {
                                                        System.out.println("Aluno selecionado: " + alunoSelecionadoNotas);



                                                        //JOptionPane "Menu Edição de Dados"
                                                        String[] NotaFreq = {"Editar Notas", "Editar Frequência"};
                                                        int FreqNota = JOptionPane.showOptionDialog(null, "Escolha sua ação",
                                                        "Menu teacher",
                                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                                                        null, NotaFreq, NotaFreq[0]);

                                                        
                                                        aluno alunoSelecionado;
                                                        
                                                        //Switch para Escolha de Edição de Dados
                                                        switch (FreqNota) {
                                                            case 0:
                                                            	
                                                            	// Inicializa a variável 'alunoSelecionado' como null, para garantir que, caso não encontre o aluno, ela não tenha valor anterior.
                                                            	alunoSelecionado = null;

                                                            	// Loop para percorrer a lista de alunos, procura no banco de Dados.
                                                            	for (aluno i : gerenciador.getListaAlunos()) {

                                                            	    // Verifica se o nome do aluno atual ('i.getNome()') é igual ao nome do aluno que o usuário selecionou para editar.
                                                            	    if (i.getNome().equals(alunoSelecionadoNotas)) {

                                                            	        // Se o nome do aluno for igual ao nome selecionado, armazena o objeto 'aluno' na variável 'alunoSelecionado'.
                                                            	        alunoSelecionado = i;

                                                            	        // Interrompe o loop, pois já encontrou o aluno desejado.
                                                            	        break;
                                                            	}

                                                                
                                                                }

                                                                if (alunoSelecionado != null) {
                                                                	
                                                                    // Pedir ao professor inserir as 3 notas
                                                                	
                                                                    while (true) {
                                                                        try {
                                                                        	
                                                                            String nota1Str = JOptionPane.showInputDialog("Digite o valor da Nota 1: ");
                                                                            
                                                                            // Se o usuário clicar em "Cancelar" ou "X", retorna para o menu de notas
                                                                            if (nota1Str == null) {
                                                                            JOptionPane.showMessageDialog(null, "Voltando ao menu de edição de notas.");
                                                                            break;  // Sai para o menu de edição de notas
                                                                            }

                                                                            if (nota1Str.trim().isEmpty()) {
                                                                            JOptionPane.showMessageDialog(null, "O campo não pode estar vazio. Tente novamente.");
                                                                            continue; // Volta para a entrada das notas
                                                                            }

                                                                            String nota2Str = JOptionPane.showInputDialog("Digite o valor da Nota 2: ");
                                                                            if (nota2Str == null) {
                                                                            JOptionPane.showMessageDialog(null, "Voltando ao menu de edição de notas.");
                                                                            break;  // Sai para o menu de edição de notas
                                                                            }

                                                                            if (nota2Str.trim().isEmpty()) {
                                                                            JOptionPane.showMessageDialog(null, "O campo não pode estar vazio. Tente novamente.");
                                                                            continue; // Volta para a entrada das notas
                                                                            }

                                                                            String nota3Str = JOptionPane.showInputDialog("Digite o valor da Nota 3: ");
                                                                            if (nota3Str == null) {
                                                                            JOptionPane.showMessageDialog(null, "Voltando ao menu de edição de notas.");
                                                                            break;  // Sai para o menu de edição de notas
                                                                            }

                                                                            if (nota3Str.trim().isEmpty()) {
                                                                            JOptionPane.showMessageDialog(null, "O campo não pode estar vazio. Tente novamente.");
                                                                            continue; // Volta para a entrada das notas
                                                                            }

                                                                            // Convertendo as notas de String para float
                                                                            float nota1 = Float.parseFloat(nota1Str);
                                                                            float nota2 = Float.parseFloat(nota2Str);
                                                                            float nota3 = Float.parseFloat(nota3Str);

                                                                            // Atualizar as notas do aluno
                                                                            alunoSelecionado.setNota1(nota1);  // Atualiza Nota 1
                                                                            alunoSelecionado.setNota2(nota2);  // Atualiza Nota 2
                                                                            alunoSelecionado.setNota3(nota3);  // Atualiza Nota 3

                                                                            // Salvar as notas no CSV
                                                                            pessoascoringa.salvarNotasNoCSV(alunoSelecionado.getID(), alunoSelecionado.getNota1(), alunoSelecionado.getNota2(), alunoSelecionado.getNota3());

                                                                            JOptionPane.showMessageDialog(null, "Notas atualizadas com sucesso!");
                                                                            break; // Sai do loop quando a entrada for válida
                                                                            
                                                                        } catch (Exception e) {
                                                                            JOptionPane.showMessageDialog(null, "Valor inválido para a nota. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                                                                            System.out.println("Erro em salvar as notas" + e.getMessage());
                                                                        }
                                                                    }
                                                                } else {
                                                                    JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                                                                }
                                                                break;


                                                            case 1:
                                                            	
                                                                // Editar frequência
                                                            	
                                                                while (true) {
                                                                    String FrequenciaAlunoStr = JOptionPane.showInputDialog("Digite a Frequência do Aluno:");

                                                                    // Se o usuário clicar em "Cancelar" ou "X", retorna para o menu de frequência
                                                                    if (FrequenciaAlunoStr == null) {
                                                                    JOptionPane.showMessageDialog(null, "Voltando ao menu de edição de frequência.");
                                                                    break;  // Sai para o menu de edição de frequência
                                                                    }

                                                                    if (FrequenciaAlunoStr.trim().isEmpty()) {
                                                                    JOptionPane.showMessageDialog(null, "O campo não pode estar vazio. Tente novamente.");
                                                                    continue; // Volta para a entrada de dados da frequência
                                                                    }

                                                                    try {
                                                                    	
                                                                        // Convertendo a frequência informada para double
                                                                        double FrequenciaAluno = Double.parseDouble(FrequenciaAlunoStr);

                                                                     // Inicializa a variável 'alunoSelecionado' como null. Isso garante que, caso o aluno não seja encontrado, a variável permanecerá vazia.
                                                                        alunoSelecionado = null;

                                                                        // Loop que percorre todos os alunos na lista de alunos do 'gerenciador'.
                                                                        for (aluno j : gerenciador.getListaAlunos()) {

                                                                            // Verifica se o nome do aluno atual ('j.getNome()') é igual ao nome do aluno que foi selecionado para edição ('alunoSelecionadoNotas').
                                                                            if (j.getNome().equals(alunoSelecionadoNotas)) {

                                                                                // Se o nome do aluno atual for igual ao nome selecionado, atribui o aluno encontrado à variável 'alunoSelecionado'.
                                                                                alunoSelecionado = j;

                                                                                // Interrompe o loop, pois já encontrou o aluno. Não é necessário continuar procurando.
                                                                                break;
                                                                            }
                                                                        }

                                                                        if (alunoSelecionado != null) {
                                                                            // Atualizar a frequência do aluno
                                                                            alunoSelecionado.setFreq((float) FrequenciaAluno);

                                                                            // Salvar as notas e a frequência no CSV
                                                                            pessoascoringa.salvarNotasNoCSVFreq(alunoSelecionado.getID(), alunoSelecionado.getNota1(),
                                                                            alunoSelecionado.getNota2(), alunoSelecionado.getNota3(), FrequenciaAluno);

                                                                            JOptionPane.showMessageDialog(null, "Frequência do aluno atualizada com sucesso!");
                                                                            break; // Sai do loop quando a entrada for válida
                                                                            
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(null, "Aluno não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                                                                        }
                                                                        
                                                                    } catch (Exception e) {
                                                                        JOptionPane.showMessageDialog(null, "Valor inválido para a frequência. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                }
                                                                break;

                                                            default:
                                                                break;
                                                        }
                                                    }
                                                    break;

                                                case 1: // Sair do Menu de Edição de Alunos
                                                    System.out.println("Saindo do Menu de Edição de Alunos");
                                                    optionProfessor = 1; // Define para sair do loop e voltar ao menu de professor
                                                    break;
                                            }
                                        }

                                        // Após sair do menu de edição de alunos, retorna ao menu principal
                                        SecaoProfEscolha = JOptionPane.showOptionDialog(null,
                                        "Selecione a ação que deseja realizar",
                                        "Bem-vindo ao Menu Professor!", JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE, null, SecaoProf, SecaoProf[0]);
                                        break;

                                    case 2: // "Sair" do Menu Professor
                                        System.out.println("Saindo do Menu Professor");
                                        break;

                                   default:
                                        SecaoProfEscolha = 2;
                                        break;
                                }
                            }

                            // Fim script professor

                        } else {
                            //Else do LoginValido
                            JOptionPane.showMessageDialog(null, "ID ou Senha incorretos para Professor.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    //Se o tipo de conta for Aluno
                    else if (tipoConta == '0') {
                        boolean loginValido = aluno.verificarLogin(IDLogin, SenhaLogin);

                        //If para LoginValido
                        if (loginValido) {
                            JOptionPane.showMessageDialog(null, "Login do Aluno bem-sucedido!", "Sucesso!",
                            JOptionPane.INFORMATION_MESSAGE);

                            // JOptionPane Seção Aluno: Ver notas e frequências | Sair
                            Object[] SecaoAluno = { "Notas & Frequência", "Sair" };
                            int SecaoAlunoEscolha = JOptionPane.showOptionDialog(null,
                            "Selecione a ação que deseja realizar", "Bem-vindo ao Menu Aluno!",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, SecaoAluno, SecaoAluno[0]);

                            // Enquanto o usuário não escolher 'Sair'
                            while (SecaoAlunoEscolha != 1) {

                                //Seção Aluno
                                switch (SecaoAlunoEscolha) {
                                    case 0:
                                        // Exibir Notas & Frequência do Aluno
                                       
                                        // Criação do frame para exibir a tabela
                                        JFrame tabelaFrame = new JFrame("Notas & Frequência do Aluno");
                                        tabelaFrame.setSize(600, 400);
                                        tabelaFrame.setLocationRelativeTo(null);
                                        tabelaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                                        // Colunas da tabela
                                        String[] colunas = {"Nome", "Nota 1", "Nota 2", "Nota 3", "Frequência"};

                                        // Criando uma lista para armazenar os dados lidos
                                        ArrayList<Object[]> dados = new ArrayList<>();

                                        // Ler o arquivo CSV e extrair os dados
                                        try (BufferedReader reader = new BufferedReader(new FileReader("dadosContas.csv"))) {
                                            String linha;
                                            while ((linha = reader.readLine()) != null) {
                                                String[] dadosLinha = linha.split(","); // Separar os dados por vírgula

                                                // Garantir que existem ao menos 7 campos (ID, senha, nome, 3 notas e a frequência)
                                                if (dadosLinha.length >= 7) {
                                                    String nome = dadosLinha[2]; // O nome está na terceira posição
                                                    String nota1 = dadosLinha[3]; // A primeira nota
                                                    String nota2 = dadosLinha[4]; // A segunda nota
                                                    String nota3 = dadosLinha[5]; // A terceira nota
                                                    String frequencia = dadosLinha[9]; // A frequência

                                                    // Adicionar os dados na lista
                                                    dados.add(new Object[]{nome, nota1, nota2, nota3, frequencia});
                                                }
                                            }
                                            
                                        } 
                                        catch (IOException e) {
                                            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
                                        }

                                        // Convertendo a lista de dados para um array 2D
                                        Object[][] dadosArray = new Object[dados.size()][5];
                                        dados.toArray(dadosArray);

                                        // Criando a tabela com os dados lidos
                                        JTable tabela = new JTable(dadosArray, colunas);
                                        tabela.setFillsViewportHeight(true);

                                        // Criando o JScrollPane para a tabela
                                        JScrollPane scrollPane = new JScrollPane(tabela);
                                        tabelaFrame.add(scrollPane, BorderLayout.CENTER);

                                        // Criando o botão "Sair"
                                        JButton sairButton = new JButton("Sair");
                                        sairButton.addActionListener(e -> tabelaFrame.dispose());
                                        tabelaFrame.add(sairButton, BorderLayout.SOUTH);

                                        // Tornando o frame visível
                                        tabelaFrame.setVisible(true);

                                        // Fica em loop até o usuário fechar a janela de notas ou clicar em "Sair"
                                        while (tabelaFrame.isVisible()) {
                                            try {
                                            Thread.sleep(100);  // Pausa de 100 ms para o usuário interagir, senão botar isso fica abrindo infinitamente.
                                            } catch (Exception e) {
                                            System.out.println("Erro: " + e.getMessage());;
                                            }
                                        }

                                        // Após a janela ser fechada, retorna ao menu SecaoAlunoEscolha
                                        SecaoAlunoEscolha = JOptionPane.showOptionDialog(null,
                                        "Selecione a ação que deseja realizar",
                                        "Bem-vindo ao Menu Aluno!", JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE, null, SecaoAluno, SecaoAluno[0]);

                                        break;

                                    default:
                                        System.out.println("Default aluno");
                                        break;
                                }
                            }

                        } else {
                            //Se o Login for invalidado
                            JOptionPane.showMessageDialog(null, "ID ou Senha incorretos para Aluno.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        //Else para caso o ID não seja Correto
                        JOptionPane.showMessageDialog(null, "Tipo de conta inválido.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                    }
                    break;


                case 1:
                    // Cadastro

                    //JOptionPane "Menu Cadastro"
                    String[] opcao = { "Aluno", "Professor" };
                    int escolha = JOptionPane.showOptionDialog(null, "Escolha sua opção", "Bem-Vindo à Lewis School",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);

                    // If para caso haja uma escolha
                    if (escolha >= 0) {
                        String cargoEscolhido = opcao[escolha];

                        // If para Escolha
                        if (cargoEscolhido.equals("Aluno")) {
                            System.out.println("Escolha: Aluno");

                            String nomeAluno = null;

                            // Loop para garantir que o nome não seja vazio e que o usuário não cancele
                            while (nomeAluno == null || nomeAluno.trim().isEmpty()) {
                                nomeAluno = JOptionPane.showInputDialog("Digite seu Nome: ");
                                if (nomeAluno == null) {
                                // Se o usuário clicar em "Cancelar", volta ao menu principal
                                JOptionPane.showMessageDialog(null, "Você cancelou a entrada do nome.");
                                break;  // Sai do loop e retorna ao menu principal
                                }
                                if (nomeAluno.trim().isEmpty()) {
                                // Se o nome for vazio ou contiver apenas espaços
                                JOptionPane.showMessageDialog(null, "O nome não pode ser vazio. Tente novamente.");
                                }
                            }

                            // Se o usuário cancelou a entrada, não prossegue com o cadastro
                            if (nomeAluno == null) {
                            break; // Sai do cadastro e volta para o menu principal
                            }

                            // Gera um número aleatório entre 0 e 1, depois multiplica por 100000 (Garante
                            // que temos um número de 5 dígitos)
                            int numeroAleatorioIDALUNO = (int) (Math.random() * 100000);
                            int numeroAleatorioSenhaALUNO = (int) (Math.random() * 100000);

                            // Concatena o '0' na frente e converte para String
                            String IDAluno = "0" + String.format("%05d", numeroAleatorioIDALUNO); // Formata para garantir 5 dígitos

                            // Verificar se o ID já existe no arquivo
                            if (gerenciador.idExistente(IDAluno)) {
                            JOptionPane.showMessageDialog(null, "Este ID já está cadastrado.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                            break;
                            }

                            String SenhaAluno = String.format("%05d", numeroAleatorioSenhaALUNO);

                            JOptionPane.showMessageDialog(null, "Seu ID é: " + IDAluno + "\nSua Senha é: " + SenhaAluno,
                                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);

                            // Teste
                            // Se mexer da merda, logo, nao mexe.
                            float nota = 10;
                            float freq = 90;
                            float nota1 = 0;
                            float nota2 = 0;
                            float nota3 = 0;

                            // Criação do objeto aluno
                            aluno aluno = new aluno(nomeAluno, IDAluno, SenhaAluno, nota, freq, nota1, nota2, nota3);
                            aluno.info(IDAluno, SenhaAluno, nomeAluno); // Mostra os dados do aluno

                            // Salvar dados no arquivo "dadosContas.txt"
                            aluno.salvarNoArquivo(nomeAluno, IDAluno, SenhaAluno);

                            gerenciador.lerAlunosDoArquivo();
                            // gerenciador.adicionarAluno(aluno);
                            gerenciador.exibirAlunos();

                            JOptionPane.showMessageDialog(null, "Cadastro concluído!", "Sucesso!",
                            JOptionPane.INFORMATION_MESSAGE);
                        }

                        if (cargoEscolhido.equals("Professor")) {
                            System.out.println("Escolha: Professor");

                            String nomeProf = null;

                            // Loop para garantir que o nome não seja vazio e que o usuário não cancele
                            while (nomeProf == null || nomeProf.trim().isEmpty()) {
                                nomeProf = JOptionPane.showInputDialog("Digite seu Nome: ");
                                if (nomeProf == null) {
                                // Se o usuário clicar em "Cancelar", volta ao menu principal
                                JOptionPane.showMessageDialog(null, "Você cancelou a entrada do nome.");
                                break;  // Sai do loop e retorna ao menu principal
                                }
                                if (nomeProf.trim().isEmpty()) {
                                // Se o nome for vazio ou contiver apenas espaços
                                JOptionPane.showMessageDialog(null, "O nome não pode ser vazio. Tente novamente.");
                                }
                            }

                            // Se o usuário cancelou a entrada, não prossegue com o cadastro
                            if (nomeProf == null) {
                            break; // Sai do cadastro e volta para o menu principal
                            }

                            String Cadeira;

                            // JOptionPane para Cadeira professor
                            String[] materias = { "Programação Orientada a Objetos", "Experimentação de Protótipos",
                            "Arquitetura de Computadores", "Álgebra e Geometria Comp." };

                            String materiaSelecionada = (String) JOptionPane.showInputDialog(null,
                            "Escolha uma Opção", "Selecione sua matéria", JOptionPane.QUESTION_MESSAGE, null,
                            materias, materias[0]);

                            // Seleciona as cadeiras
                            switch (materiaSelecionada) {
                                case "Programação Orientada a Objetos":
                                    JOptionPane.showMessageDialog(null, "Selecionado Professor de P.O.O",
                                    "Seleção de Cadeira", JOptionPane.INFORMATION_MESSAGE);
                                    Cadeira = "Programação Orientada a Objetos";
                                    break;

                                case "Experimentação de Protótipos":
                                    JOptionPane.showMessageDialog(null, "Selecionado Professor de Exp. de Protótipos",
                                    "Seleção de Cadeira", JOptionPane.INFORMATION_MESSAGE);
                                    Cadeira = "Experimentação de Protótipos";
                                    break;

                                case "Arquitetura de Computadores":
                                    JOptionPane.showMessageDialog(null, "Selecionado Professor de Arquitetura de Comp.",
                                    "Seleção de Cadeira", JOptionPane.INFORMATION_MESSAGE);
                                    Cadeira = "Arquitetura de Computadores";
                                    break;

                                case "Álgebra e Geometria Comp.":
                                    JOptionPane.showMessageDialog(null, "Selecionado Professor de Álgebra Linear",
                                    "Seleção de Cadeira", JOptionPane.INFORMATION_MESSAGE);
                                    Cadeira = "Álgebra e Geometria Comp.";
                                    break;

                                default:
                                    System.out.println("Cadeira não selecionada");
                                    Cadeira = "Sem cadeira";
                                    break;
                            }

                            // Gera um número aleatório entre 0 e 1, depois multiplica por 100000 (Garante
                            // que temos um número de 5 dígitos)
                            int numeroAleatorioIDPROF = (int) (Math.random() * 100000);
                            int numeroAleatorioSenhaPROF = (int) (Math.random() * 100000);

                            // Concatena o '1' na frente e converte para String
                            String IDProf = "1" + String.format("%05d", numeroAleatorioIDPROF); // Formata para garantir 5 dígitos
                            String SenhaProf = String.format("%05d", numeroAleatorioSenhaPROF);

                            // Exibe o ID
                            JOptionPane.showMessageDialog(null, "Seu ID é: " + IDProf + "\nSua Senha é: " + SenhaProf
                            + "\nSua Cadeira é: " + Cadeira, "Cadastro", JOptionPane.INFORMATION_MESSAGE);

                            pessoas professor = new professor(nomeProf, IDProf, SenhaProf, Cadeira); // Polimorfismo
                            professor.info(IDProf, SenhaProf, nomeProf);

                            professor.salvarNoArquivo(nomeProf, IDProf, SenhaProf, Cadeira);
                        }

                    }
                    break;

                default:
                    System.out.println("BREAK!");
                    break;
            }
        }
    }
}