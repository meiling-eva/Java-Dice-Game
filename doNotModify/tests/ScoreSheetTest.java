//DO NOT MODIFY THIS FILE

package doNotModify.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import doNotModify.clients.Player;
import doNotModify.clients.Score;
import doNotModify.clients.Turn;
import toBeCompleted.stage1.Dice;
import toBeCompleted.stage2.Computer;
import toBeCompleted.stage3.ScoreSheet;

import java.util.ArrayList;
import java.util.Collections;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScoreSheetTest {

	private ScoreSheet sheet;
    ArrayList<Player> players;
	/*
	 * Create a set of pre-determined
	 * dice rolls for testing purposes
	 * 
	 */
	private ArrayList<Dice> dice1, dice2, aside1, aside2;
	private Computer p1, p2;

	private ArrayList<Dice> diceList(int count) {
		ArrayList<Dice> dl = new ArrayList<Dice>();
		for(int i=0; i<count; i++) {
			dl.add(new Dice());
		}
		for(Dice d: dl) {
			d.roll();
		}
		return dl;
	}

	private void setAside(int id, ArrayList<Dice> dice, ArrayList<Dice> aside) {
		Dice d;
		int i=0;
		while(i<dice.size()) {
			d = dice.get(i);
			if(d.getId() == id) {
				aside.add(d);
				dice.remove(d);
				return;
			}
			i++;
		}
	}

	public ScoreSheetTest() {
		Dice.testReset(1, 0);
		sheet = new ScoreSheet();
        dice1 = diceList(5);
		aside1 = new ArrayList<Dice>();
        dice2 = diceList(5);
		aside2 = new ArrayList<Dice>();
        players = new ArrayList<Player>();
        p1 = new Computer("Player 1");
        players.add(p1);
        p2 = new Computer("Player 2");
        players.add(p2);
    	int[] id1 = new int[2];
    	int[] id2 = new int[2];
    	for(int r=1; r<=5; r++) {
    		id1 = p1.chooseTwo(dice1);
    		setAside(id1[0], dice1, aside1);
    		setAside(id1[1], dice1, aside1);
    		sheet.log(p1.toString(), r, 1, dice1, aside1);
    		id2 = p2.chooseTwo(dice2);
    		setAside(id2[0], dice2, aside2);
    		setAside(id2[1], dice2, aside2);
    		sheet.log(p2.toString(), r, 1, dice2, aside2);
    		for(int t=2; t<=4; t++) {
    			id1[0] = p1.chooseOne(dice1);
    			setAside(id1[0], dice1, aside1);
    			sheet.log(p1.toString(), r, t, dice1, aside1);
    			id2[0] = p2.chooseOne(dice2);
    			setAside(id2[0], dice2, aside2);
    			sheet.log(p2.toString(), r, t, dice2, aside2);
    		}
    		dice1.addAll(aside1);
    		dice2.addAll(aside2);
    		
    	}
	}

	@Test
    public void t001_testComma() {
        ArrayList<Integer> l1 = new ArrayList<Integer>();
        ArrayList<Integer> l2 = new ArrayList<Integer>();
        int i;
        l1.add(1);
        for(i=1; i<=5; i++) {
            l2.add(i);
        }
        assertEquals("1", ScoreSheet.commaSeparated(l1));
    	assertEquals("1,2,3,4,5", ScoreSheet.commaSeparated(l2));
    }

    @Test
    public void t002_testLog() {
    	Score s = sheet.scoreTable.get(2);
    	assertEquals(p1.toString(), s.getPlayer());
    	assertEquals(1, s.getRound());
    	assertEquals(2, s.getTurn());
    }

    @Test
    public void t003_testLog() {
    	Score s = sheet.scoreTable.get(8);
    	ArrayList<Integer> keptList;
    	keptList = new ArrayList<Integer>();
    	Collections.addAll(keptList, 4, 3, 2);
    	assertEquals(keptList, s.getKept());
    }

    @Test
    public void t004_testLog() {
    	
    	Score s = sheet.scoreTable.get(8);
    	ArrayList<Integer> asideList;
    	asideList = new ArrayList<Integer>();
    	Collections.addAll(asideList, 5, 4);
    	assertEquals(asideList, s.getAside());
    }

    @Test
    public void t005_testPlayerRound() {
    	ArrayList<Turn> l = sheet.playerRound(p1.toString(), 3);
    	assertEquals(1, l.get(0).getSign());
    	assertEquals(-1, l.get(1).getSign());
    }

    @Test
    public void t006_testPlayerRound() {
    	ArrayList<Turn> l = sheet.playerRound(p2.toString(), 2);
    	assertEquals(4, l.size());
    	assertEquals(1, l.get(2).getSign());
    }

    @Test
    public void t007_testTallyScore() {
    	
    	assertEquals(33, sheet.tallyScore(p1.toString()));
    	
    	assertEquals(29, sheet.tallyScore(p2.toString()));
    }
}
