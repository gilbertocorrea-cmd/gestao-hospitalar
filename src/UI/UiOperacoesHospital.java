package src.UI;

import java.util.Scanner;

import src.Service.Hospital;
import src.model.NivelEmergencia;
import src.model.Paciente;

// Esta classe cuida da conversa com o usuário durante cada operação.
// Ela lê os dados, chama o Hospital e mostra o resultado na tela.
public class UiOperacoesHospital {
    private final Scanner scanner;
    private final Hospital hospital;

    // Usa o mesmo Scanner e o mesmo Hospital que foram criados na Main.
    public UiOperacoesHospital(Scanner scanner, Hospital hospital) {
        this.scanner = scanner;
        this.hospital = hospital;
    }

    // Solicita os dados necessários, cria um Paciente e pede sua admissão.
    public void executarAdmissao() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        int idade = lerInteiro("Idade: ");

        // Valida imediatamente para não continuar perguntando os outros dados.
        // O record Paciente mantém a mesma validação como uma segunda proteção.
        if (idade <= 0 || idade > 150) {
            throw new IllegalArgumentException("A idade deve estar entre 1 e 150 anos.");
        }

        int opcaoNivel = lerInteiro(
                "Nível: 1-Leve, 2-Moderado, 3-Urgente, 4-Crítico: ");

        NivelEmergencia nivel = NivelEmergencia.apartirEmergencia(opcaoNivel);

        int opcaoPlano = lerInteiro("Possui plano? 1-Sim, 2-Não: ");

        // A Switch Expression transforma 1 ou 2 diretamente em um boolean.
        boolean possuiPlano = switch (opcaoPlano) {
            case 1 -> true;
            case 2 -> false;
            // throw interrompe a operação quando a opção não é válida.
            default -> throw new IllegalArgumentException("Opção de plano inválida.");
        };

        // O construtor do record também valida os dados recebidos.
        Paciente paciente = new Paciente(nome, idade, nivel, possuiPlano);

        hospital.admitir(paciente);
        System.out.println("Paciente admitido.");
    }

    // forEach executa o println uma vez para cada paciente da lista.
    public void executarTriagem() {
        hospital.listarEmergenciais()
                .forEach(System.out::println);
    }

    // Mostra a média dos críticos e a quantidade de pacientes segurados.
    public void executarEstatisticas() {
        hospital.calcularMediaIdadeCriticos()
                // Executa a primeira ação se a média existir e a segunda se estiver vazia.
                .ifPresentOrElse(
                        media -> System.out.printf("Média dos casos críticos: %.2f%n", media),
                        () -> System.out.println("Não existem casos críticos."));

        System.out.println("Total de segurados: " + hospital.contarSegurados());
    }

    // Mostra o paciente mais idoso ou avisa que ainda não existem pacientes.
    public void executarBuscaPacienteIdoso() {
        hospital.buscarPacienteMaisIdoso()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("O hospital está vazio."));
    }

    // Lê um nome e mostra o paciente encontrado pelo Hospital.
    public void executarBuscaPorNome() {
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine();

        hospital.buscarPacientePorNome(nome)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Paciente não encontrado."));
    }

    // Centraliza a leitura de inteiros para evitar repetir o mesmo código.
    private int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        return Integer.parseInt(scanner.nextLine());
    }
}
