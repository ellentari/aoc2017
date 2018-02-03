package com.adventofcode.day18;

@FunctionalInterface
public interface Instruction<T extends ExecutionContext> {

    int execute(T executionContext);

    default boolean willBeExecuted(T executionContext) {
        return true;
    }

    default Instruction<T> onBefore(Runnable onBefore) {
        return executionContext -> {
            onBefore.run();
            return execute(executionContext);
        };
    }
}
