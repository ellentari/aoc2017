package com.adventofcode.day18;

import java.util.List;

public class SimpleInstructionsExecutor implements Program, ExecutionContext {

    private int currentInstruction;

    private final Registers registers;
    private final List<Instruction<ExecutionContext>> instructions;

    public SimpleInstructionsExecutor(Registers registers, List<Instruction<ExecutionContext>> instructions) {
        this.registers = registers;
        this.instructions = instructions;
    }

    @Override
    public boolean isTerminated() {
        return currentInstruction < 0 || instructions.size() <= currentInstruction;
    }

    @Override
    public void executeNext() {
        int result = getNextInstruction()
                .execute(this);

        currentInstruction += result;
    }

    private Instruction<ExecutionContext> getNextInstruction() {
        return instructions.get(currentInstruction);
    }

    @Override
    public Registers getRegisters() {
        return registers;
    }
}
