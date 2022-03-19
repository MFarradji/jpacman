package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.level.Player;

import javax.swing.*;
import java.awt.*;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective scores underneath.
 *
 * @author Jeroen Roosen 
 *
 */
public class PlayerInfoPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    private final InfoPanel scorePanel;

    private final InfoPanel livesPanel;

    /**
     * Creates a new score panel with a column for each player.
     *
     */
    public PlayerInfoPanel(Player player, int index) {
        super();
        assert player != null;

        setLayout(new GridLayout(2, 1));

        add(new JLabel("Player " + index, JLabel.CENTER));
        JPanel infoPanels = new JPanel();
        scorePanel = new InfoPanel(player, new nl.tudelft.jpacman.ui.InfoFormatters.ScoreFormatter());
        livesPanel = new InfoPanel(player, new nl.tudelft.jpacman.ui.InfoFormatters.LivesFormatter());
        infoPanels.add(scorePanel);
        infoPanels.add(livesPanel);
        add(infoPanels);
    }

    public void refresh() {
        scorePanel.refresh();
        livesPanel.refresh();
    }
}
