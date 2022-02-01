//DO NOT MODIFY THIS FILE

package doNotModify.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import toBeCompleted.stage1.Dice;
import java.util.ArrayList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DiceTest {

    @Test
    public void t000_testId() {
        ArrayList<Dice> dice = new ArrayList<Dice>();
        int i;
        for(i=1; i<=20; i++) {
            dice.add(new Dice());
        }
        i=1;
        for(Dice die: dice) {
            assertEquals(i, die.getId());
            i++;
        }
    }

    @Test
    public void t001_testSix() {
        Dice die = new Dice();
        assertEquals(6, die.getFaces());
        for(int i=1; i<100; i++) {
                die.roll();
                assertTrue(die.getValue() >= 1);
                assertTrue(die.getValue() <= 6);
        }
    }

    @Test
    public void t002_testTwenty() {
        Dice die = new Dice(20);
        assertEquals(20, die.getFaces());
        for(int i=1; i<100; i++) {
            die.roll();
            assertTrue(die.getValue() >= 1);
            assertTrue(die.getValue() <= 20);
        }
    }

    @Test
    public void t003_testToString() {
        Dice die = new Dice(20);
        for(int i=1; i<100; i++) {
            die.roll();
            String s = die.getId()+":"+die.getValue()+"/"+die.getFaces();
            assertEquals(s, die.toString());
        }
    }

    @Test
    public void t004_testCompareTo() {
        ArrayList<Dice> dice = new ArrayList<Dice>();
        int i, j;
        for(i=1; i<=20; i++) {
            dice.add(new Dice(12));
        }
        for(Dice die: dice) {
            die.roll();
        }
        for(i=1; i<20; i++) {
            for(j=1; j<20; j++) {
                if(dice.get(i).getValue() < dice.get(j).getValue()) {
                    assertEquals(dice.get(i).compareTo(dice.get(j)), -1);
                }
                if(dice.get(i).getValue() == dice.get(j).getValue()) {
                    assertEquals(dice.get(i).compareTo(dice.get(j)), 0);
                }
                if(dice.get(i).getValue() > dice.get(j).getValue()) {
                    assertEquals(dice.get(i).compareTo(dice.get(j)), 1);
                }
            }
        }
    }

}
