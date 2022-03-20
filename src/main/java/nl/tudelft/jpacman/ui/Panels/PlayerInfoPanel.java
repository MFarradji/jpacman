package nl.tudelft.jpacman.ui.Panels;

import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.ui.Panels.InfoPanel;

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

    private static final long serialVersionUID = 1L;

    private final InfoPanel scorePanel;

    private final InfoPanel livesPanel;

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
