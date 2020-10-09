package edu.wofford.machiwoco;

import io.cucumber.java.jv.Lan;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.is;


public class LandmarkTest {


    Landmark l;
    @Test
    public void testSomething() {
        assertThat(1, is(1));
    }

    @Before
    public void before() {
        l = new Landmark("Test", 1, Card.Color.BLUE, Card.Icon.FRUITO, "Test");
    }

    @Test
    public void testGenerateSignleline() {
        assertThat(l.generate_single_line("Train Station"),
                is("|     Train Station     |\n"));
    }

    @Test
    public void testGenerateBlocks() {
        String[] s = new String[2];
        s[0] = "You may roll 1 or 2";
        s[1] = "dice.";
        assertThat(l.generate_blocks("You may roll 1 or 2 "),
                is(s));
    }
}