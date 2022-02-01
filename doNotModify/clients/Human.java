//DO NOT MODIFY THIS FILE

package doNotModify.clients;

import toBeCompleted.stage1.Dice;

import java.util.ArrayList;
import java.util.Scanner;

public class Human implements Player {

	private Scanner keys;
	private String name;

	/**
	 * Construct a Human
	 * 
	 * @param name player's name
	 */
	public Human(String name) {
		this.name = name; 
		keys = new Scanner(System.in);
	}

	/**
	 * Present the user with a prompt
	 * and validate their response as
	 * a valid dice identifier
	 * 
	 * @param dice the list of dice to
	 * choose from
	 */
	public int chooseOne(ArrayList<Dice> dice) {
		boolean valid = false;
		int response = 0;
		System.out.print("Choose a single dice to set aside : ");
		while(!valid) {
		    if (keys.hasNextInt()) {
		        response = keys.nextInt();
		        if((response >= 1) && (response <= maxFaces(dice))) {
		        		valid = true;
		        		break;
		        }
		    }
		    if(!valid) {
		        System.out.println("Sorry, I couldn't understand you, please try again.");
		    }
		}
		return response;
	}

	/**
	 * Present the user two consecutive
	 * prompts and validate both responses
	 * as valid dice identifiers
	 * 
	 * @param dice the list of dice to
	 * choose from
	 */
	public int[] chooseTwo(ArrayList<Dice> dice) {
		int response = 0;
		int[] choice = new int[2];
		boolean valid = false;
		while(!valid) {
			System.out.print("Choose the first dice to set aside : ");
		    if (keys.hasNextInt()) {
		        response = keys.nextInt();
		        if((response >= 1) && (response <= maxFaces(dice))) {
		        		valid = true;
		        		break;
		        }
		    }
		    if(!valid) {
		        System.out.println("Sorry, I couldn't understand you, please try again.");
		    }
		}
		choice[0] = response;
		valid = false;
		while(!valid) {
			System.out.print("Choose the second dice to set aside : ");
		    if (keys.hasNextInt()) {
		        response = keys.nextInt();
		        if((response >= 1) && (response <= maxFaces(dice))) {
		        		valid = true;
		        		break;
		        }
		    }
		    if(!valid) {
		        System.out.println("Sorry, I couldn't understand you, please try again.");
		    }
		}
		choice[1] = response;
		return choice;
	}

	/**
	 * This method returns the maximum number of
	 * sides or faces in a dice
	 * Note that the equivalent method in
	 * class Computer needs to figure this out
	 * programmatically unlike the following code
	 * @param dice unused
	 * @return
	 */
	private int maxFaces(ArrayList<Dice> dice) {
		return 6;
	}

	public String toString() {
		return name;
	}

}
