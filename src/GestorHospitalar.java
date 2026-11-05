package src;

import java.util.Scanner;

import src.Repositorio.RepositorioPaciente;
import src.Service.Hospital;
import src.UI.UiDeUsuario;
import src.UI.UiOperacoesHospital;

// Esta é a classe de console solicitada no enunciado.
// As duas classes de UI continuam separadas para manter o projeto organizado.
public class GestorHospitalar {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RepositorioPaciente repositorio = new RepositorioPaciente();
        Hospital hospital = new Hospital(repositorio);
        UiOperacoesHospital operacoes = new UiOperacoesHospital(scanner, hospital);
        UiDeUsuario ui = new UiDeUsuario(scanner, operacoes);

        ui.iniciar();
    }
}
