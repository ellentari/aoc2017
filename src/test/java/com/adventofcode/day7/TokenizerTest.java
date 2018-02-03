package com.adventofcode.day7;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TokenizerTest {

    @Test
    public void name() {
        Tokenizer tokenizer = new Tokenizer("{{name}} ({{weight}})");
        tokenizer.bind("name", Tokenizer.string());
        tokenizer.bind("weight", Tokenizer.integer());

        List<Token> tokens = tokenizer.tokenize("testName (123)");

//        assertThat(tokens)
//                .as("Single token should have been tokenized")
//                .hasSize(1)
//                .containsExactly(new CompositeToken(
//                        asList(new Token("testName"),
//                                new Token("123")
//                        )
//                ));

    }

    private static class Tokenizer {

        private final String token;
        private Map<String, Function<String, ?>> parsers = new HashMap<>();

        public Tokenizer(String token) {
            this.token = token;
        }

        public static Function<String, String> string() {
            return Function.identity();
        }

        public void bind(String name, Function<String, ?> parser) {
            parsers.put(name, parser);
        }

        public static Function<String, Integer> integer() {
            return Integer::parseInt;
        }

        public List<Token> tokenize(String from) {
            return null;
        }
    }
}
