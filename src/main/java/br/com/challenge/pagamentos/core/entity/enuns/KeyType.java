package br.com.challenge.pagamentos.core.entity.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.regex.Pattern;
@Getter
@RequiredArgsConstructor
public enum KeyType {
    CPF("CPF", 0, Pattern.compile("^[0-9]{11}$")),
    EMAIL("EMAIL", 1, Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$")),
    TELEFONE("TELEFONE",2,Pattern.compile("^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}[0-9]{4}")),
    ALEATORIA("ALEATORIA", 3, Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"));

    private final String description;
    private final Integer id;
    private final Pattern regex;
    public static KeyType getKeyType(String key) {
        return Arrays.stream(KeyType.values())
                .filter(it -> key.matches(it.getRegex().pattern()))
                .findFirst()
                .orElse(null);
    }
}
