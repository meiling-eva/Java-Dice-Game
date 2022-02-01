//To be completed

package toBeCompleted.stage2;

import toBeCompleted.stage1.Dice;
import doNotModify.clients.Player;

import java.util.ArrayList;
import java.util.Comparator;

public class Computer implements Player {

	private String name;

	/*
	 * Constructor for computer player
	 * 
	 * @param name player's name
	 */
	public Computer(String name) {
		this.name = name; 
	}

	/*
	 * This is an auxiliary method that determines
	 * the highest number that can be rolled on
	 * any individual dice in an array.
	 * 
	 * (This class should really be private
	 * But, we need to make it public in order
	 * to run a Unit test on it with JUnit)
	 * 
	 * @param dice
	 */
	public int maxFaces(ArrayList<Dice> dice) {
		// We could have just 'hard coded' 6 into our
		// 'lowest' function below but by using
		// this function we can ensure that our code
		// still works if we change the game to use
		// different dice with more (or less) faces.
		int faces=0;
		for(Dice d: dice) {
			if(d.getFaces() > faces) {
				faces = d.getFaces();
			}
		}
		return faces;
	}

	/*
	 * This is an auxiliary method that determines
	 * the lowest number rolled in a set of dice
	 * in an array.
	 * 
	 * @param dice
	 */	
	public int lowest(ArrayList<Dice> dice) {
		// In order to find the lowest number rolled
		// We start with the highest possible face value + 1
		// We then scan the list of dice and if we find
		// a lower face value than the lowest we've seen so far
		// we update our lowest to the current value
		int low = maxFaces(dice)+1;
		int id=0;
		for(Dice d: dice) {
			if(d.getValue() < low) {
				low = d.getValue();
				id = d.getId();
			}
		}
		return id;
	}

	/*
	 * This is an auxiliary method that determines
	 * the highest number rolled in a set of dice
	 * in an array.
	 * 
	 * @param dice
	 */	
	public int highest(ArrayList<Dice> dice) {
		// Essentially the same as 'lowest' above
		// Except we start with the lowest possible value - 1
		// Which is always zero.
		int high=0;
		int id=0;
		for(Dice d: dice) {
			if(d.getValue() > high) {
				high = d.getValue();
				id = d.getId();
			}
		}
		return id;
	}

	/*
	 * This is an auxiliary method that determines
	 * the second highest number rolled in a set of dice
	 * in an array.
	 * 
	 * @param dice
	 */
	public int secondHighest(ArrayList<Dice> dice) {
		// Although we want to return the Id of the 2nd highest
		// We need to keep track of both the highest and 2nd highest
		// So that we can determine if the dice currently being
		// examined is the new highest or the new 2nd highest
		// We only return the 2nd highest though.
		int first=0;
		int second=0;
		int id1=0;
		int id2=0;
		for(Dice d: dice) {
			if(d.getValue() > first) {
				second = first;
				id2 = id1;
				first = d.getValue();
				id1 = d.getId();
			} else if(d.getValue() > second) {
				second = d.getValue();
				id2 = d.getId();
			}
		}
		return id2;
	}

	/*
	 * This method is called by the game's main loop
	 * It's purpose is to choose which single dice
	 * to set aside from the array.
	 * 
	 * @param dice
	 */	
	public int chooseOne(ArrayList<Dice> dice) {
			// If the number of dice remaining is an odd
			// number we will subtract the dice we set
			// aside therefore we choose the lowest face
			// value so as to maximize our final score
			// Note that we do not need special code to
			// accommodate the first turn (5 by default)
			// as chooseTwo will be called in that case
			//
			// Otherwise we choose the highest face value
			//
		// One line version:
		// If you don't known what '? :' does search online
		// for 'ternary operator'
		return ((dice.size() %2) == 1) ? lowest(dice) : highest(dice);
		// More readable version is 4 lines (excluding curly braces)
		// if((dice.size() % 2) == 1) {
		//     return lowest(dice);
		// }
		// else {
		//     return highest(dice);
		// }
	}

	/*
	 * This method is called by the game's main loop
	 * It's purpose is to choose two dice to set
	 * aside from the array.
	 * 
	 * @param dice
	 */	
	public int[] chooseTwo(ArrayList<Dice> dice) {
		// We only set aside two dice on our first throw
		// Since we are going to add the scores for the two dice
		// We want to choose the two highest face values
		// So as to maximize our final score
		//
		// One line version:
		return new int[]{highest(dice), secondHighest(dice)};
		// More readable version is 4 lines
		// int[] both = new int[2];
		// both[0] = highest(dice);
		// both[1] = secondHighest(dice);
		// return both;
	}

	/*
	 * Return the name of the player
	 * as the string representation
	 * of the object.
	 */
	public String toString() {
		return name;
	}

}
