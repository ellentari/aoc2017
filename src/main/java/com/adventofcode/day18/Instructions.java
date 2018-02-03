package com.adventofcode.day18;

import java.util.Queue;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.Predicate;

public class Instructions {

    private Instructions() {
    }

    public static <T extends ExecutionContext> Instruction<T> set(char key, Value value) {
        return context -> {
            Registers registers = context.getRegisters();

            registers.set(key, value.get(registers));

            return 1;
        };
    }

    public static <T extends ExecutionContext> Instruction<T> mul(char key, Value value) {
        return context -> {
            Registers registers = context.getRegisters();

            registers.set(key, registers.get(key) * value.get(registers));

            return 1;
        };
    }

    public static <T extends ExecutionContext> Instruction<T> mod(char key, Value value) {
        return context -> {
            Registers registers = context.getRegisters();

            registers.set(key, registers.get(key) % value.get(registers));

            return 1;
        };
    }

    public static <T extends ExecutionContext> Instruction<T> inc(char key, Value value) {
        return context -> {
            Registers registers = context.getRegisters();

            registers.set(key, registers.get(key) + value.get(registers));

            return 1;
        };
    }

    public static <T extends ExecutionContext> Instruction<T> dec(char key, Value value) {
        return context -> {
            Registers registers = context.getRegisters();

            registers.set(key, registers.get(key) - value.get(registers));

            return 1;
        };
    }

    public static <T extends ExecutionContext> Instruction<T> jump(Value value, Value offset, LongPredicate jumpIfValue) {
        return context -> {
            Registers registers = context.getRegisters();

            if (jumpIfValue.test(value.get(registers))) {
                return (int) offset.get(registers);
            }

            return 1;
        };
    }

    public static <T extends ExecutionContext> Instruction<T> playSound(Value sound, LongConsumer audioOutput) {
        return context -> {
            Registers registers = context.getRegisters();

            audioOutput.accept(sound.get(registers));

            return 1;
        };
    }

    public static <T extends ExecutionContext> Instruction<T> recoverSound(LongSupplier sound, LongConsumer soundConsumer, Predicate<Registers> condition) {
        return context -> {
            Registers registers = context.getRegisters();

            if (condition.test(registers)) {
                soundConsumer.accept(sound.getAsLong());
            }

            return 1;
        };
    }

    public static <T extends MessageSendingExecutionContext> Instruction<T> send(Value value) {
        return context -> {
            Registers registers = context.getRegisters();

            context.send(value.get(registers));

            return 1;
        };
    }

    public static <T extends MessageQueueAwareExecutionContext> Instruction<T> receive(char toRegisterKey) {
        return new Instruction<T>() {
            @Override
            public int execute(T context) {
                Queue<Long> fromQueue = context.getMessageQueue();

                if (fromQueue.isEmpty()) {
                    return 0;
                }

                Registers registers = context.getRegisters();

                registers.set(toRegisterKey, fromQueue.poll());

                return 1;
            }

            @Override
            public boolean willBeExecuted(T context) {
                return !context.getMessageQueue().isEmpty();
            }
        };
    }
}
