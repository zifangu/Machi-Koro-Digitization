package edu.wofford.machiwoco;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Feature4Test {
    Feature4 feature4;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        feature4 = new Feature4(3);
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    public void testPlayerNum() {
        assertThat(feature4.getPlayers().length, is(3));
    }

//    @Test
//    public void testAILogic() {
//        feature4.getPlayer2().setTurn(true);
//        feature4.aiLogic();
//        assertThat(outContent.toString(), Matchers.anyOf(Matchers.is(1),Matchers.is(2),Matchers.is(3),Matchers.is(4),Matchers.is(5),Matchers.is(6),Matchers.is(99)));
//    }

}
