package src.UI;

import java.util.Scanner;

// Esta classe controla apenas o menu principal.
// As operações escolhidas são encaminhadas para UiOperacoesHospital.
public class UiDeUsuario {
    private final Scanner scanner;
    private final UiOperacoesHospital operacoes;

    // Recebe objetos já criados na Main em vez de criar novas cópias deles.
    public UiDeUsuario(Scanner scanner, UiOperacoesHospital operacoes) {
        this.scanner = scanner;
        this.operacoes = operacoes;
    }

    // Mantém o menu funcionando enquanto continuar for true.
    public void iniciar() {
        boolean continuar = true;

        while (continuar) {
            // try tenta executar normalmente a leitura e a operação escolhida.
            try {
                exibirMenu();
                int op = lerInteiro("Escolha uma opção: ");

                // Uma Switch Expression devolve um resultado.
                // Neste caso, o resultado é guardado na variável continuar.
                continuar = switch (op) {
                    case 1 -> {
                        operacoes.executarAdmissao();
                        // yield devolve true para a Switch Expression.
                        // Com true, o while mostra o menu novamente.
                        yield true;
                    }
                    case 2 -> {
                        operacoes.executarTriagem();
                        yield true;
                    }
                    case 3 -> {
                        operacoes.executarEstatisticas();
                        yield true;
                    }
                    case 4 -> {
                        operacoes.executarBuscaPacienteIdoso();
                        yield true;
                    }
                    case 5 -> {
                        System.out.println("\nPrograma encerrado.");
                        // false encerra o while e, consequentemente, o programa.
                        yield false;
                    }
                    case 6 -> {
                        operacoes.executarBuscaPorNome();
                        yield true;
                    }
                    default -> {
                        System.out.println("Opção inválida.");
                        // Uma opção inválida não encerra o programa.
                        yield true;
                    }
                };
            // Este catch trata textos ou valores que não podem ser convertidos em int.
            } catch (NumberFormatException erro) {
                System.out.println("\nDigite um número válido.");
            // Este catch trata idade, nível, plano ou outro argumento inválido.
            } catch (IllegalArgumentException erro) {
                System.out.println("\nNão foi possível continuar: " + erro.getMessage());
            }

            // Cria um espaço entre o resultado de uma operação e o próximo menu.
            if (continuar) {
                System.out.println();
            }
        }
    }

    // O bloco de texto com três aspas permite escrever o menu em várias linhas.
    private void exibirMenu() {
        System.out.println("""

                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                1 - Admitir paciente
                2 - Relatório de triagem
                3 - Painel de estatísticas
                4 - Casos de risco
                5 - Sair
                6 - Buscar paciente por nome (função extra)
                -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-
                """);
    }

    // Lê a entrada como texto e depois a transforma em um número inteiro.
    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        return Integer.parseInt(scanner.nextLine());
    }
}
