package nl.tudelft.jpacman.ui.InfoFormatters;

import nl.tudelft.jpacman.level.Player;

public class LivesFormatter implements InfoFormatter{
    @Override
    public String format(Player player) {
        return String.format("Lives: %3d left", player.getLives());
    }
}
