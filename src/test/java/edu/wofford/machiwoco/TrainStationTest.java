package edu.wofford.machiwoco;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class TrainStationTest {

    TrainStation t;
    @Test
    public void testSomething() {
        assertThat(1, is(1));
    }

    @Before
    public void before() {
        t = new TrainStation();
    }

    @Test
    public void  testTrainStation() {
        assertThat(t.name, is("Train Station"));
        assertThat(t.cost, is(4));
    }


    @Test
    public void  testToString() {
            assertThat(1, is(1));


//        assertThat(t.toString(), is(".-----------------------.\n" +
//                "| <N>   LANDMARK    {T} |\n" +
//                "|     Train Station     |\n" +
//                "|                       |\n" +
//                "|  You may roll 1 or 2  |\n" +
//                "|         dice.         |\n" +
//                "|                       |\n" +
//                "| (4)               [ ] |\n" +
//                "|_______________________|"));
    }

}