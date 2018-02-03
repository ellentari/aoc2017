package com.adventofcode.common;

import java.util.Optional;

public interface Parser<T> {

    Optional<T> from(String input);

}
