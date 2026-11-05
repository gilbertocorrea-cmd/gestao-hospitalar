package src.model;

// record é uma forma compacta de criar uma classe usada principalmente para dados.
// O Java cria automaticamente nome(), idade(), nivel() e possuiPlano().
public record Paciente(
        String nome,
        int idade,
        NivelEmergencia nivel,
        boolean possuiPlano
) {
    // Este é o construtor compacto do record.
    // Ele valida os dados antes que o paciente seja criado de verdade.
    public Paciente {
        // isBlank também considera inválido um nome formado somente por espaços.
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Campo com o nome do paciente esta vazio");
        }

        // A idade precisa estar dentro de uma faixa considerada possível.
        if (idade <= 0 || idade > 150) {
            throw new IllegalArgumentException("A idade deve ser entre 1 e 150 Anos");
        }

        // Objetos podem ser null, por isso o nível precisa ser validado.
        if (nivel == null) {
            throw new IllegalArgumentException("Nivel de emergencia obrigatório.");
        }

        // trim remove espaços desnecessários do começo e do final do nome.
        nome = nome.trim();
    }
}
