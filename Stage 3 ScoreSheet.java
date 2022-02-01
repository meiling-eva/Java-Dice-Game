package toBeCompleted.stage3;

import toBeCompleted.stage1.Dice;
import toBeCompleted.stage3.ScoreSheet;
import doNotModify.clients.Turn;
import doNotModify.clients.Score;

// Utilities for the module
import java.util.ArrayList;

public class ScoreSheet {

	public ArrayList<Score> scoreTable;

	/*
	 * Constructor for ScoreSheet
	 */
	public ScoreSheet() {
		scoreTable = new ArrayList<Score>();
	}

	// Format list l as a comma separated string
	/*
	 * @param l is an ArrayList<Integer> return the list as a comma separated
	 * string. eg. The list [4, 7, 9, 11, 3] should return "4,7,9,11,3"
	 */
	public static String commaSeparated(ArrayList<Integer> l) {
		String s = "";
		for (int i = 0; i < l.size(); i++) {
			s += l.get(i) + ",";
		}
		int n = s.length();
		s = s.substring(0, n - 1);
		return s;
	}

	/*
	 * Calculate the total score of a player
	 * 
	 * @param n is the name of the player
	 */
	public int tallyScore(String n) {
		int total = 0;
		
		ArrayList<Integer> aside = new ArrayList<Integer>();
		for (int i = 0; i < scoreTable.size(); i++) {
			int sign = 1;
			Score k = scoreTable.get(i);
			//System.out.println(aside);
			aside = k.getAside();
			if (k.getPlayer().equals(n)) {
				if (k.getTurn() % 2 == 1) {
					for (Integer item : aside) {
						//sign = sign * (-1);
						//total1 += item;
						total += item;
						//System.out.println(n + ", total:"+total);
						//System.out.println("total1:" + total1);
					}
				} else if (k.getTurn() % 2 == 0) {
					for (Integer item : aside) {
						sign = sign * (-1);
						//total2 += item * sign;
						total -= item;
						//System.out.println(n + ", total:"+total);
						//System.out.println("total2:"+total2);
					}
				}				
			}
			//System.out.println(n + ", " + total1 + " + " + total2 + " = " + (total1+total2));
		}
		//total = total1 + total2;
		//System.out.println("total:"+total);
		return total;
	}

	/*
	 * Return a list of Turns (see doNotModify.clients.Turn)
	 * 
	 * @param n is the name of the player
	 * 
	 * @param r is the round The list that is returned should contain a separate
	 * 'Turn' for each time the player rolled the dice and set aside one or more
	 * dice the face values of all the dice thrown as an ArrayList<Integer> the face
	 * values of the dice set aside as an ArrayList<Integer> the total score for the
	 * turn, and finally the sign of the turn. ie. is the score to be added or
	 * subtracted?
	 */
	public ArrayList<Turn> playerRound(String n, int r) {
		ArrayList<Integer> thrown = new ArrayList<Integer>();
		ArrayList<Integer> aside = new ArrayList<Integer>();
		ArrayList<Turn> turns = new ArrayList<Turn>();
		int sign, total = 0;
		int total1 = 0;
		int total2 = 0;
		for (int i = 0; i < scoreTable.size(); i++) {
			Score k = scoreTable.get(i);
			if (k.getPlayer() == n && k.getRound() == r) {
				for (Integer item : k.getAside()) {
					thrown.add(item);
				}
				for (Integer item : k.getKept()) {
					thrown.add(item);
				}
			}
		}
		for (int i = 0; i < scoreTable.size(); i++) {
			sign = 1;
			Score a = scoreTable.get(i);
			if (a.getPlayer() == n && a.getRound() == r) {
				aside = a.getAside();
				if (a.getTurn() % 2 == 1) {
					for (Integer item : aside) {
						total1 += item;
					}
				} else if (a.getTurn() % 2 == 0) {
					for (Integer item : aside) {
						sign = sign * (-1);
						total2 += item * sign;
					}
				}
				total = total1 + total2;
				Turn t = new Turn(thrown, aside, total, sign);
				turns.add(t);
			}
		}
		return turns;

	}

	/*
	 * Log a turn in the score table for a player
	 * 
	 * @param n is the name of the player
	 * 
	 * @param r is the 'round'. ie. a game consists of 5 rounds each player taking 4
	 * turns at throwing the dice before the next player has their go.
	 * 
	 * @param t is the turn. ie the dice throw within a round
	 * 
	 * @param k the list of dice to be kept for the next roll
	 * 
	 * @param a the list of dice that the player set aside
	 */
	public void log(String n, int r, int t, ArrayList<Dice> k, ArrayList<Dice> a) {
		//System.out.println(a);
		ArrayList<Integer> keep, aside;
		keep = new ArrayList<Integer>();
		aside = new ArrayList<Integer>();
		
		for (Dice item : k) {
			keep.add(item.getValue());
		}
		aside.add(a.get(a.size()-1).getValue());
		if (t == 1) {
				aside.add(a.get(a.size()-2).getValue());
			}
		Score a_socre = new Score(n, r, t, keep, aside);
		scoreTable.add(a_socre);
	    System.out.println(aside);
	}
}
