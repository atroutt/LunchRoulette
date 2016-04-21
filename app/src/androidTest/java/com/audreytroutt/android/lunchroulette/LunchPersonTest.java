package com.audreytroutt.android.lunchroulette;

import com.audreytroutt.android.lunchroulette.data.LunchPerson;

import static com.audreytroutt.android.lunchroulette.data.LunchPerson.LunchDay.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


/**
 * Created by audrey on 4/21/16.
 */
public class LunchPersonTest {

    LunchPerson testPerson;

    @Before
    public void setup() {
        testPerson = new LunchPerson();
    }

    @Test
    public void hasNoLunchDays() {
        assertTrue(testPerson.getLunchDays().isEmpty());
        assertEquals(0, testPerson.getLunchDays().size());
    }

    @Test
    public void addLunchDayWorks() {
        testPerson.addLunchDay(FRIDAY);
        assertEquals(1, testPerson.getLunchDays().size());
        assertEquals(FRIDAY, testPerson.getLunchDays().toArray()[0]);
    }

    @Test
    public void removeLunchDayWorks() {
        testPerson.addLunchDay(FRIDAY);
        assertEquals(1, testPerson.getLunchDays().size());
        assertEquals(testPerson.getLunchDays().toArray()[0], FRIDAY);
        testPerson.removeLunchDay(FRIDAY);
        assertEquals(0, testPerson.getLunchDays().size());
    }

}
