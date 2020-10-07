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
    }

}