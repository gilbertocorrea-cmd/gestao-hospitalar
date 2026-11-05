package src.model;

// enum representa um conjunto fechado de opções.
// Isso impede que um paciente receba um nível de emergência inexistente.
public enum NivelEmergencia {
    LEVE,
    MODERADO,
    URGENTE,
    CRITICO;

    // Converte a opção numérica digitada pelo usuário em um valor do enum.
    public static NivelEmergencia apartirEmergencia(int opcao) {
        // A Switch Expression devolve diretamente o nível correspondente.
        return switch (opcao) {
            case 1 -> LEVE;
            case 2 -> MODERADO;
            case 3 -> URGENTE;
            case 4 -> CRITICO;
            // Uma opção fora do intervalo interrompe a conversão com uma mensagem.
            default -> throw new IllegalArgumentException("Opção de emergencia invalida.");
        };
    }
}
