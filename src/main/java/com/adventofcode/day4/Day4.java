package com.adventofcode.day4;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import com.adventofcode.AdventOfCodePuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * --- Day 4: High-Entropy Passphrases ---
 *
 * A new system policy has been put in place that requires all accounts to use a passphrase instead of simply a password.
 * A passphrase consists of a series of words (lowercase letters) separated by spaces.
 *
 * To ensure security, a valid passphrase must contain no duplicate words.
 *
 * For example:
 *
 * aa bb cc dd ee is valid.
 * aa bb cc dd aa is not valid - the word aa appears more than once.
 * aa bb cc dd aaa is valid - aa and aaa count as different words.
 *
 * The system's full passphrase list is available as your puzzle input. How many passphrases are valid?
 *
 * --- Part Two ---
 *
 * For added security, yet another system policy has been put in place.
 * Now, a valid passphrase must contain no two words that are anagrams of each other - that is,
 * a passphrase is invalid if any word's letters can be rearranged to form any other word in the passphrase.
 *
 * For example:
 *
 * abcde fghij is a valid passphrase.
 * abcde xyz ecdab is not valid - the letters from the third word can be rearranged to form the first word.
 * a ab abc abd abf abj is a valid passphrase, because all letters need to be used when forming another word.
 * iiii oiii ooii oooi oooo is valid.
 * oiii ioii iioi iiio is not valid - any of these words can be rearranged to form any other word.
 *
 * Under this new system policy, how many passphrases are valid?
 */
public class Day4 implements AdventOfCodePuzzle<Integer, Integer> {

    private final List<String> passPhrases;

    Day4(List<String> passPhrases) {
        this.passPhrases = passPhrases;
    }

    @Override
    public Integer solvePartOne() {
        return countValidPassPhrases(Day4::allWordsAreDistinct);
    }

    @Override
    public Integer solvePartTwo() {
        return countValidPassPhrases(Day4::containsNoAnagrams);
    }

    private int countValidPassPhrases(Predicate<String> isValidPassphrase) {
        return (int) passPhrases.stream()
                .filter(isValidPassphrase)
                .count();
    }

    private static boolean allWordsAreDistinct(String passPhrase) {
        return areAllDistinct(splitIntoWords(passPhrase));
    }

    private static boolean containsNoAnagrams(String passPhrase) {
        return areAllDistinct(splitIntoWords(passPhrase).stream().map(Day4::sortLetters).collect(toList()));
    }

    private static String sortLetters(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private static List<String> splitIntoWords(String passPhrase) {
        return asList(passPhrase.split(" "));
    }

    private static boolean areAllDistinct(List<String> words) {
        Set<String> distinctWords = new HashSet<>(words);

        return words.size() == distinctWords.size();
    }
}
