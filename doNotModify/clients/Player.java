//DO NOT MODIFY THIS FILE

package doNotModify.clients;

import toBeCompleted.stage1.Dice;
import java.util.ArrayList;

/**
 * Interface defining methods a Player
 * Needs to implement
 *
 */
public interface Player {

	public int chooseOne(ArrayList<Dice> dice);
	public int[] chooseTwo(ArrayList<Dice> dice);

}
