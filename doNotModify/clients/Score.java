//DO NOT MODIFY THIS FILE

// Class for a single score table entry
package doNotModify.clients;

import java.util.ArrayList;
import java.util.Collections;

public class Score {
	private String player;
	private int round, turn;
	private ArrayList<Integer> kept, aside;

	public Score(String p, int r, int t, ArrayList<Integer> k, ArrayList<Integer> a) {
		player = p;
		round = r; turn = t;
		kept = k; aside = a;
	}

	public int getTurn() {
		return turn;
	}

	public int getRound() {
		return round;
	}

	public String getPlayer() {
		return player;
	}

	public ArrayList<Integer> getKept() {
		Collections.sort(kept, Collections.reverseOrder()); 
		return kept;
	}

	public ArrayList<Integer> getAside() {
		Collections.sort(aside, Collections.reverseOrder()); 
		return aside;
	}

	public ArrayList<Integer> getThrown() {
		ArrayList<Integer> thrown = new ArrayList<Integer>();
		thrown.addAll(kept);
		thrown.addAll(aside);
		Collections.sort(thrown, Collections.reverseOrder()); 
		return thrown;
	}

}
