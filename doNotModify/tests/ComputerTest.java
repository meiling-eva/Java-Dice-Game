//DO NOT MODIFY THIS FILE

package doNotModify.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import toBeCompleted.stage1.Dice;
import toBeCompleted.stage2.Computer;

import java.util.ArrayList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ComputerTest {

	/*
	 * Create a set of pre-determined
	 * dice rolls for testing purposes
	 * 
	 */
	private ArrayList<Dice> dice0;
	private ArrayList<Dice> dice1;
	private ArrayList<Dice> dice2;
	private ArrayList<Dice> dice3;
	private ArrayList<Dice> dice4;
	private ArrayList<Dice> dice5;

	public ArrayList<Dice> diceList(int count) {
		Dice.testReset(1, 0);
		ArrayList<Dice> dl = new ArrayList<Dice>();
		for(int i=0; i<count; i++) {
			dl.add(new Dice());
		}
		for(Dice d: dl) {
			d.roll();
		}
		return dl;
	}

	public ComputerTest() {
        dice0 = diceList(50);
        dice1 = diceList(1);
        dice2 = diceList(2);
        dice3 = diceList(3);
        dice4 = diceList(4);
        dice5 = diceList(5);
	}

	@Test
	public void t001_testName() {
		Computer computer = new Computer("Test Player");
		assertEquals("Test Player", computer.toString());
	}
	@Test
    public void t002_testMaxFaces() {
        Computer computer = new Computer("Test");
        assertEquals(6, computer.maxFaces(dice0));
    }

    @Test
    public void t003_testLowest() {
        Computer computer = new Computer("Test");
        assertEquals(15, computer.lowest(dice0));
    }

    @Test
    public void t004_testHighest() {
        Computer computer = new Computer("Test");
        assertEquals(20, computer.highest(dice0));
    }

    @Test
    public void t005_testSecond() {
        Computer computer = new Computer("Test");
        assertEquals(24, computer.secondHighest(dice0));
    }

    @Test
    public void t006_testChoose1Low() {
        Computer computer = new Computer("Test");
        assertEquals(1, computer.chooseOne(dice1));
        assertEquals(3, computer.chooseOne(dice3));
    }

    @Test
    public void t007_testChoose1High() {
        Computer computer = new Computer("Test");
        assertEquals(2, computer.chooseOne(dice2));
        assertEquals(2, computer.chooseOne(dice4));
    }

    @Test
    public void t008_testChoose2() {
        Computer computer = new Computer("Test");
        int[] results = computer.chooseTwo(dice5);
        assertEquals(2, results[0]);
        assertEquals(1, results[1]);
    }

}
