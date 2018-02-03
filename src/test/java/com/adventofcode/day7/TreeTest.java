//package com.adventofcode.day7;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import org.junit.Test;
//
//import java.util.List;
//
//public class TreeTest {
//
//    @Test
//    public void simple() {
//        assertTree("pbga (66)", Tree.of(Node.of("pbga", 66)));
//    }
//
//    private void assertTree(String treeString, Tree expected) {
//        Tree tree = parse(treeString);
//
//        assertThat(tree).isEqualTo(expected);
//    }
//
//    private Tree parse(String treeString) {
//
//        Tokenizer tokenizer = new Tokenizer("{name} ({weight})", "\n");
//        tokenizer.bind("name", "\\w+");
//        tokenizer.bind("weight", "\\d+");
//
//
//        List<Token> tokens = tokenizer.tokenize(treeString);
//
//
//        return Tree.of(null);
//    }
//}
