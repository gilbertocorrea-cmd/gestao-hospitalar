package src.Repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import src.model.Paciente;

// O repositório tem uma responsabilidade simples: guardar e recuperar pacientes.
public class RepositorioPaciente {
    // final impede que a variável passe a apontar para outra lista.
    // Os itens da lista ainda podem ser adicionados normalmente.
    private final List<Paciente> pacientes = new ArrayList<>();

    // Adiciona o paciente recebido à lista interna.
    public void salvar(Paciente paciente) {
        pacientes.add(paciente);
    }

    // Devolve uma cópia que não pode ser modificada por quem chamou o método.
    // Assim, somente o repositório controla a lista original.
    public List<Paciente> listarTodos() {
        return List.copyOf(pacientes);
    }

    // Procura o primeiro paciente cujo nome seja igual ao nome pesquisado.
    public Optional<Paciente> buscarPorNome(String nome) {
        return pacientes
                // stream permite examinar os pacientes como uma sequência de dados.
                .stream()
                // filter mantém somente quem atende à condição informada.
                // equalsIgnoreCase não diferencia letras maiúsculas e minúsculas.
                .filter(paciente -> paciente.nome().equalsIgnoreCase(nome.trim()))
                // Optional representa um resultado encontrado ou a ausência dele.
                .findFirst();
    }
}
