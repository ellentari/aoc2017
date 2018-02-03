package com.adventofcode.day18;

import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

import com.adventofcode.common.Parser;
import com.adventofcode.common.Parsers;

import java.util.Optional;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class InstructionsParsers {

    private static final String EITHER_VALUE = "val";
    private static final String EITHER_VALUE_2 = "val2";
    private static final String OR_REGISTER = "reg";
    private static final String OR_REGISTER_2 = "reg2";

    private static final String SIMPLE_VALUE = "value";
    private static final String SIMPLE_REGISTER = "register";

    private static final String VALUE_REGEXP = "(?<%s>-?\\d+)";
    private static final String REGISTER_KEY_REGEXP = "(?<%s>\\w)";

    private static final String VALUE = String.format(VALUE_REGEXP, SIMPLE_VALUE);
    private static final String REGISTER = String.format(REGISTER_KEY_REGEXP, SIMPLE_REGISTER);

    private static final String VALUE_OR_REGISTER = String.format("(%s|%s)",
            String.format(VALUE_REGEXP, EITHER_VALUE),
            String.format(REGISTER_KEY_REGEXP, OR_REGISTER)
    );

    private static final String VALUE_OR_REGISTER_2 = String.format("(%s|%s)",
            String.format(VALUE_REGEXP, EITHER_VALUE_2),
            String.format(REGISTER_KEY_REGEXP, OR_REGISTER_2)
    );

    private InstructionsParsers() {
    }

    public static <T extends ExecutionContext> Parser<Instruction<T>> playSound(LongConsumer audioOutput) {
        return Parsers.parserFor(instructionRegexp("snd", VALUE_OR_REGISTER), playSoundMatcher(audioOutput));
    }

    public static <T extends ExecutionContext> Parser<Instruction<T>> setRegister() {
        return Parsers.parserFor(instructionRegexp("set", REGISTER, VALUE_OR_REGISTER), setRegisterMatcher());
    }

    public static <T extends ExecutionContext> Parser<Instruction<T>> incRegister() {
        return Parsers.parserFor(instructionRegexp("add", REGISTER, VALUE_OR_REGISTER), incRegisterMatcher());
    }

    public static <T extends ExecutionContext> Parser<Instruction<T>> decRegister() {
        return Parsers.parserFor(instructionRegexp("sub", REGISTER, VALUE_OR_REGISTER), decRegisterMatcher());
    }

    public static <T extends ExecutionContext> Parser<Instruction<T>> mulRegister() {
        return Parsers.parserFor(instructionRegexp("mul", REGISTER, VALUE_OR_REGISTER), mulRegisterMatcher());
    }

    public static <T extends ExecutionContext> Parser<Instruction<T>> mulRegister(Runnable onBefore) {
        return Parsers.parserFor(instructionRegexp("mul", REGISTER, VALUE_OR_REGISTER), mulRegisterMatcher(onBefore));
    }

    public static <T extends ExecutionContext> Parser<Instruction<T>> modRegister() {
        return Parsers.parserFor(instructionRegexp("mod", REGISTER, VALUE_OR_REGISTER), modRegisterMatcher());
    }

    public static <T extends ExecutionContext> Parser<Instruction<T>> recoverSound(LongSupplier sound, LongConsumer soundConsumer) {
        return Parsers.parserFor(instructionRegexp("rcv", VALUE_OR_REGISTER), recoverSoundMatcher(sound, soundConsumer));
    }

    public static <T extends ExecutionContext> Parser<Instruction<T>> jump(String instruction, LongPredicate valuePredicate) {
        return Parsers.parserFor(instructionRegexp(instruction, VALUE_OR_REGISTER, VALUE_OR_REGISTER_2), jumpMatcher(valuePredicate));
    }

    public static <T extends MessageSendingExecutionContext> Parser<Instruction<T>> send() {
        return Parsers.parserFor(instructionRegexp("snd", VALUE_OR_REGISTER), sendMatcher());
    }

    public static <T extends MessageQueueAwareExecutionContext> Parser<Instruction<T>> receive() {
        return Parsers.parserFor(instructionRegexp("rcv", REGISTER), receiveMatcher());
    }

    private static <T extends ExecutionContext> Function<Matcher, Instruction<T>> playSoundMatcher(LongConsumer audioOutput) {
        return matcher -> Instructions.playSound(valueOrRegisterValueFrom(matcher), audioOutput);
    }

    private static <T extends ExecutionContext> Function<Matcher, Instruction<T>> setRegisterMatcher() {
        return matcher -> Instructions.set(registerFrom(matcher), valueOrRegisterValueFrom(matcher));
    }

    private static <T extends ExecutionContext> Function<Matcher, Instruction<T>> incRegisterMatcher() {
        return matcher -> Instructions.inc(registerFrom(matcher), valueOrRegisterValueFrom(matcher));
    }

    private static <T extends ExecutionContext> Function<Matcher, Instruction<T>> decRegisterMatcher() {
        return matcher -> Instructions.dec(registerFrom(matcher), valueOrRegisterValueFrom(matcher));
    }

    private static <T extends ExecutionContext> Function<Matcher, Instruction<T>> mulRegisterMatcher() {
        return matcher -> Instructions.mul(registerFrom(matcher), valueOrRegisterValueFrom(matcher));
    }

    private static <T extends ExecutionContext> Function<Matcher, Instruction<T>> mulRegisterMatcher(Runnable onBefore) {
        return matcher -> Instructions.<T>mul(registerFrom(matcher), valueOrRegisterValueFrom(matcher)).onBefore(onBefore);
    }

    private static <T extends ExecutionContext> Function<Matcher, Instruction<T>> modRegisterMatcher() {
        return matcher -> Instructions.mod(registerFrom(matcher), valueOrRegisterValueFrom(matcher));
    }

    private static <T extends ExecutionContext> Function<Matcher, Instruction<T>> recoverSoundMatcher(LongSupplier sound, LongConsumer soundConsumer) {
        return matcher -> Instructions.recoverSound(sound, soundConsumer, registers -> valueOrRegisterValueFrom(matcher).get(registers) != 0);
    }

    private static <T extends ExecutionContext> Function<Matcher, Instruction<T>> jumpMatcher(LongPredicate valuePredicate) {
        return matcher -> Instructions.jump(valueOrRegisterValueFrom(matcher), valueOrRegisterValueFrom2(matcher), valuePredicate);
    }

    private static <T extends MessageSendingExecutionContext> Function<Matcher, Instruction<T>> sendMatcher() {
        return matcher -> Instructions.send(valueOrRegisterValueFrom(matcher));
    }

    private static <T extends MessageQueueAwareExecutionContext> Function<Matcher, Instruction<T>> receiveMatcher() {
        return matcher -> Instructions.receive(registerFrom(matcher));
    }

    private static String instructionRegexp(String instruction, String... otherTokens) {
        return Stream.concat(Stream.of(instruction), stream(otherTokens)).collect(joining(" "));
    }

    private static Optional<Value> valueOf(String value) {
        return ofNullable(value)
                .map(Long::parseLong)
                .map(valueLong -> registers -> valueLong);
    }

    private static Value fromRegister(String regKey) {
        return registers -> registers.get(regKey.charAt(0));
    }

    private static Value valueOrRegisterValueFrom(Matcher matcher) {
        String value = eitherValueFrom(matcher);
        String regKey = orRegisterFrom(matcher);

        return numOrRegister(value, regKey);
    }

    private static Value valueOrRegisterValueFrom2(Matcher matcher) {
        String value = fromMatcher(matcher, EITHER_VALUE_2);
        String regKey = fromMatcher(matcher, OR_REGISTER_2);

        return numOrRegister(value, regKey);
    }

    private static String eitherValueFrom(Matcher matcher) {
        return fromMatcher(matcher, EITHER_VALUE);
    }

    private static String fromMatcher(Matcher matcher, String eitherValue) {
        return matcher.group(eitherValue);
    }

    private static String orRegisterFrom(Matcher matcher) {
        return fromMatcher(matcher, OR_REGISTER);
    }

    private static char registerFrom(Matcher matcher) {
        return matcher.group(SIMPLE_REGISTER).charAt(0);
    }

    private static Value numOrRegister(String value, String regKey) {
        return valueOf(value)
                .orElseGet(() -> fromRegister(regKey));
    }


}
