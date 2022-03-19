package nl.tudelft.jpacman.ui.InfoFormatters;

import nl.tudelft.jpacman.level.Player;

public interface InfoFormatter {
    /**
     * Format the score of a given player.
     * @param player The player and its score
     * @return Formatted score.
     */
    String format(Player player);
}
