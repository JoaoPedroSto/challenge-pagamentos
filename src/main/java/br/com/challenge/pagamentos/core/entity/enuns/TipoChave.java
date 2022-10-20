package br.com.challenge.pagamentos.core.entity.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.regex.Pattern;
@Getter
@RequiredArgsConstructor
public enum TipoChave {
    CPF("CPF", 0, Pattern.compile("^[0-9]{11}$")),
    EMAIL("EMAIL", 1, Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")),
    TELEFONE("TELEFONE",2,Pattern.compile("^[0-9]{11}$")),
    ALEATORIA("ALEATORIA", 3, Pattern.compile("^[a-zA-Z0-9\\-]*$"));

    private final String description;
    private final Integer id;
    private final Pattern regex;
    public static TipoChave getTipoChave(String key) {
        return Arrays.stream(TipoChave.values())
                .filter(it -> key.matches(it.getRegex().pattern()))
                .findFirst()
                .orElse(null);
    }
}
