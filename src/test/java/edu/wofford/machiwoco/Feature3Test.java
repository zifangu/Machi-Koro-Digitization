package edu.wofford.machiwoco;

import io.cucumber.java.jv.Lan;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class Feature3Test  {
    Feature3 feature3;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void before() {
        feature3 = new Feature3();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @Test
    public void testPlayerNum() {
        assertThat(feature3.getPlayers().length, is(2));
    }

//    @Test
//    public void testLogic() {
//        InputStream sysInBackup = System.in; // backup System.in to restore it later
//        ByteArrayInputStream in = new ByteArrayInputStream("99".getBytes());
//        System.setIn(in);
//
//        // with or without this, we've tried many ways.
//        feature3.sc = new Scanner(in);
//        feature3.playGame();
//
////        feature3.handleInput("99");
//        assertThat(outContent.toString(), containsString("chose not to make improvements."));
//    }



    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

}
