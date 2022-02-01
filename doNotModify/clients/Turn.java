//DO NOT MODIFY THIS FILE

// Class representing the activity of a single turn
package doNotModify.clients;

import java.util.ArrayList;

public class Turn {

	private ArrayList<Integer> thrown, aside;
	private int total, sign;

	public Turn(ArrayList<Integer> d, ArrayList<Integer> a, int t, int s) {
		thrown = d; aside = a;
		total = t; sign = s;
	}

	public ArrayList<Integer> getThrown() {
		return thrown;
	}

	public ArrayList<Integer> getAside() {
		return aside;
	}

	public int getTotal() {
		return total;
	}

	public int getSign() {
		return sign;
	}
}
