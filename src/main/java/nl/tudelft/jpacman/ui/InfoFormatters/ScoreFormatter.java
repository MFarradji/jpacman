package nl.tudelft.jpacman.ui.InfoFormatters;

import nl.tudelft.jpacman.level.Player;

public class ScoreFormatter implements InfoFormatter {
    @Override
    public String format(Player player) {
        String score = "Score: ";
        if (!player.isAlive()) {
            score = "You died. ";
        }
        else {
            score += String.valueOf(player.getScore());
        }
        return score;
    }
}
