package src.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import src.Repositorio.RepositorioPaciente;
import src.model.NivelEmergencia;
import src.model.Paciente;

// Hospital contém as regras e consultas do sistema.
// Ele não guarda a lista diretamente: essa responsabilidade é do repositório.
public class Hospital {
    private final RepositorioPaciente repositorio;

    // Permite criar um Hospital diretamente, como solicitado no enunciado.
    public Hospital() {
        this(new RepositorioPaciente());
    }

    // O repositório é recebido no construtor para que o Hospital possa usá-lo.
    public Hospital(RepositorioPaciente repositorio) {
        this.repositorio = repositorio;
    }

    // Admitir um paciente significa enviá-lo para o repositório salvar.
    public void admitir(Paciente paciente) {
        repositorio.salvar(paciente);
    }

    // Retorna somente os pacientes que estão em situação urgente ou crítica.
    public List<Paciente> listarEmergenciais() {
        return repositorio.listarTodos()
                .stream()
                // O operador || significa "ou": basta uma condição ser verdadeira.
                .filter(paciente -> paciente.nivel() == NivelEmergencia.URGENTE
                        || paciente.nivel() == NivelEmergencia.CRITICO)
                // Ordena do paciente mais idoso para o mais jovem.
                .sorted(Comparator.comparingInt(Paciente::idade).reversed())
                // Converte o resultado do stream em uma nova lista.
                .toList();
    }

    // Mantém o nome usado anteriormente como uma função extra de compatibilidade.
    public List<Paciente> listarEmergencias() {
        return listarEmergenciais();
    }

    // Calcula a média de idade somente dos pacientes críticos.
    public OptionalDouble calcularMediaIdadeCriticos() {
        return repositorio.listarTodos()
                .stream()
                .filter(paciente -> paciente.nivel() == NivelEmergencia.CRITICO)
                // Transforma cada paciente em sua idade, que é um número inteiro.
                .mapToInt(Paciente::idade)
                // OptionalDouble fica vazio quando não existem pacientes críticos.
                .average();
    }

    // Compara as idades e devolve o paciente mais idoso, quando existir algum.
    public Optional<Paciente> buscarPacienteMaisIdoso() {
        return repositorio.listarTodos()
                .stream()
                .max(Comparator.comparingInt(Paciente::idade));
    }

    // Mantém o nome usado anteriormente como uma função extra de compatibilidade.
    public Optional<Paciente> buscarPacienteIdoso() {
        return buscarPacienteMaisIdoso();
    }

    // A busca já existe no repositório, então o Hospital encaminha o pedido.
    public Optional<Paciente> buscarPacientePorNome(String nome) {
        return repositorio.buscarPorNome(nome);
    }

    // Conta quantos pacientes possuem plano de saúde.
    public long contarSegurados() {
        return repositorio.listarTodos()
                .stream()
                // Mantém somente os pacientes cujo possuiPlano() é true.
                .filter(Paciente::possuiPlano)
                .count();
    }
}
