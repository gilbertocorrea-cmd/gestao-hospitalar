package src;

import java.util.Scanner;

import src.Repositorio.RepositorioPaciente;
import src.Service.Hospital;
import src.UI.UiDeUsuario;
import src.UI.UiOperacoesHospital;

// A Main é o ponto de partida do programa.
// Ela apenas cria e conecta os objetos, deixando cada tarefa em sua própria classe.
public class Main {
    public static void main(String[] args) {
        // Um único Scanner é compartilhado pelas duas classes de interface.
        Scanner scanner = new Scanner(System.in);

        // O repositório guarda os pacientes em memória.
        RepositorioPaciente repositorio = new RepositorioPaciente();

        // O Hospital usa o repositório para acessar os pacientes.
        Hospital hospital = new Hospital(repositorio);

        // Esta parte da interface executa admissão, relatórios e buscas.
        UiOperacoesHospital operacoes = new UiOperacoesHospital(scanner, hospital);

        // Esta parte da interface controla o menu principal.
        UiDeUsuario ui = new UiDeUsuario(scanner, operacoes);

        // Inicia o menu e o mantém ativo até a opção de saída.
        ui.iniciar();
        
    }
}
