package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.ui.InfoFormatters.InfoFormatter;

import javax.swing.*;
import java.awt.*;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective scores underneath.
 *
 * @author Jeroen Roosen 
 *
 */
public class InfoPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The map of players and the labels their scores are on.
     */
    private final JLabel label;

    private final Player player;

    private final InfoFormatter infoFormatter;

    /**
     * Creates a new score panel with a column for each player.
     *
     */
    public InfoPanel(Player player, InfoFormatter infoFormatter) {
        super();
        assert player != null;
        assert infoFormatter != null;

        this.player = player;
        this.infoFormatter = infoFormatter;

        setLayout(new GridLayout(1, 1));

        label = new JLabel("", JLabel.CENTER);
        add(label);
    }

    /**
     * Refreshes the scores of the players.
     */
    protected void refresh() {
        String score = infoFormatter.format(player);
        label.setText(score);
    }
}
