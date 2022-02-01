//DO NOT MODIFY THIS FILE

package doNotModify.clients;

import toBeCompleted.stage1.Dice;
import toBeCompleted.stage2.Computer;
import toBeCompleted.stage3.ScoreSheet;

// Utilities for the module
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Game {

	// Instance variables to hold
	// game players
	private ArrayList<Player> players;
	private Player currentPlayer;

	// Dice lists
	private static final int NUM_DICE = 5;
	private ArrayList<Dice> dice;
	private ArrayList<Dice> aside;

	// Game rules
	private static final int NUM_ROUNDS = 5;
    private int round;
    private static final int NUM_TURNS = 4;
    private int turn;

    private ScoreSheet scores;

	public Game() {
		// Setup two players
		// 1 Human, and
		// 1 Computer
		this(new ScoreSheet(), new ArrayList<Player>() {{
			add(new Human("Player 1"));
			// comment out the above line and
			// uncomment the line below for two computer players
			//add(new Computer("Player 1"));
			add(new Computer("Player 2"));
		}});
	}

	public Game(ScoreSheet s, ArrayList<Player> p) {
		scores = s;
		players = p;
		// Create a list of dice for the game
		// Also, create a second list to "hold"
		// the dice whilst being set aside.
		dice = new ArrayList<Dice>();
		aside = new ArrayList<Dice>();
		for(int i=1; i<=NUM_DICE; i++) {
			dice.add(new Dice());
		}
	}

	// Select the next player to take a turn
	private Player nextPlayer() {
		int i = players.indexOf(currentPlayer);
		currentPlayer = players.get((i + 1) % players.size());
		return currentPlayer;
	}

	// Place the selected dice into
	// the "aside" ArrayList
	private void setAside(int id) {
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

	// Put all the dice back in the main set
	// ready for the next round
	private void resetDice() {
		dice.addAll(aside);
		aside.clear();
		Collections.sort(dice, new SortById());		
	}

	// Display the values of the rolled dice
	private void showRoll() {
		System.out.print("Dice   : ");
		for(Dice d: dice) {
			System.out.print("  " + d.getId());
		}
		System.out.println();
		System.out.print("Values : ");
		for(Dice d: dice) {
			System.out.print("  " + d.getValue());
		}
		System.out.println();
	}

	private void showFinalScores() {
		String name;
		ArrayList<Integer> finalScores = new ArrayList<Integer>();
		ArrayList<Integer> winners = new ArrayList<Integer>();
		System.out.println();
		System.out.println("Final scores");
		for(int i=0; i<players.size(); i++) {
			name = players.get(i).toString();
			finalScores.add(scores.tallyScore(name));
			System.out.println(name + ": " + finalScores.get(i));
		}
		for(int i=0; i<finalScores.size(); i++) {
			if(finalScores.get(i) == Collections.max(finalScores)) {
				winners.add(i);
			}
		}
		System.out.println();
		if(winners.size() == 1) {
			System.out.println("The winner is " + players.get(winners.get(0)).toString());
		}
		else {
			System.out.println("With a score of " + Collections.max(finalScores));
			System.out.println("We have a tie between the following players:");
			for(Integer w: winners) {
				System.out.println(players.get(w).toString());
			}
		}
		System.out.println("Thankyou for playing!");		
	}

	// The main loop of the game
	public void play() {
		Player player;
		String name;
		int[] id = new int[2];
		int tot=0;

		for(round=1; round <= NUM_ROUNDS; round++) {
			System.out.println();
			System.out.println("Round " + round);
			player = nextPlayer();
			name = player.toString();
			resetDice();
			for(turn=1; turn <= NUM_TURNS; turn++) {
				System.out.println();
				System.out.println("Rolling...");
				for(Dice d: dice) {
					d.roll();
				}
				showRoll();
				if(turn == 1) {
					id = player.chooseTwo(dice);
					System.out.println(name + " chose " + id[0] + " & " + id[1]);
					setAside(id[0]);
					setAside(id[1]);
				}
				else {
					id[0] = player.chooseOne(dice);
					System.out.println(name + " chose " + id[0]);
					setAside(id[0]);
				}
				scores.log(player+"", round, turn, dice, aside);
			}
			showHeader(round);
			for(Turn t: scores.playerRound(player+"",  round)) {
				showTurn(t);
				tot = t.getTotal();
			}
			showFooter(tot);
		}
		showFinalScores();
	}

	private int col;
	private static final int COL1 = 1, COL2 = 17, COL3 = 30, COL4 = 35;

	private void showHeader(int r) {
		printNewline();
		printAt(COL1, "Round " + r);
		printNewline();
		printAt(COL1, "Dice Rolled");
		printAt(COL2, "Dice Set Aside");
		printAt(COL4, "Total");
		printNewline();
	}

	private void showTurn(Turn t) {
		printAt(COL1, t.getThrown());
		printAt(COL2+2, (t.getSign() == 1) ? "+" : "-");
		printAt(COL2+4, t.getAside());
		printAt((t.getTotal() > 9) ? COL4+3 : COL4+4, t.getTotal());
		printNewline();		
	}

	private void showFooter(int t) {
		printAt(COL3, "Score:");
		printAt((t > 9) ? COL4+3 : COL4+4, t);
		printNewline();
	}

	public void printNewline() {
		System.out.println();
		col=1;
	}

	public void moveToCol(int c) {
		for(int i=col; i<c; i++) {
			System.out.print(" ");
		}
		col=c;
	}

	public void printAt(int c, String f) {
		moveToCol(c);
		System.out.print(f);
		col += f.length();
	}

	// Print list l at column 'c'
	public void printAt(int c, ArrayList<Integer> l) {
		printAt(c, ScoreSheet.commaSeparated(l));
	}

	// Print integer v at column 'c'
	public void printAt(int c, int v) {
		moveToCol(c);
		printAt(c, "" + v);
	}

}

// Private class to sort in ascending order of
// dice ID
class SortById implements Comparator<Dice>
{
    public int compare(Dice a, Dice b)
    {
        return a.getId() - b.getId();
    }
}
