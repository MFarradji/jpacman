package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.level.Player;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A panel consisting of a column for each player, with the numbered players on
 * top and their respective lives underneath.
 *
 * @author Jeroen Roosen 
 *
 */
public class LivesPanel extends JPanel {

    /**
     * Default serialisation ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The map of players and the labels their lives are on.
     */
    private final Map<Player, JLabel> livesLabels;

    /**
     * The default way in which the lives are shown.
     */
    public static final LivesFormatter DEFAULT_LIVES_FORMATTER =
        (Player player) -> String.format("Lives: %3d left", player.getLives());

    /**
     * The way to format the lives information.
     */
    private LivesFormatter livesFormatter = DEFAULT_LIVES_FORMATTER;

    /**
     * Creates a new lives panel with a column for each player.
     *
     * @param players
     *            The players to display the lives of.
     */
    public LivesPanel(List<Player> players) {
        super();
        assert players != null;

        setLayout(new GridLayout(2, players.size()));

        for (int i = 1; i <= players.size(); i++) {
            add(new JLabel("Player " + i, JLabel.CENTER));
        }
        livesLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel livesLabel = new JLabel("0", JLabel.CENTER);
            livesLabels.put(player, livesLabel);
            add(livesLabel);
        }
    }

    /**
     * Refreshes the lives of the players.
     */
    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : livesLabels.entrySet()) {
            Player player = entry.getKey();
            String lives = livesFormatter.format(player);
            entry.getValue().setText(lives);
        }
    }

    /**
     * Provide means to format the lives for a given player.
     */
    public interface LivesFormatter {

        /**
         * Format the lives of a given player.
         * @param player The player and its lives
         * @return Formatted lives.
         */
        String format(Player player);
    }

    /**
     * Let the lives panel use a dedicated lives formatter.
     * @param livesFormatter Lives formatter to be used.
     */
    public void setLivesFormatter(LivesFormatter livesFormatter) {
        assert livesFormatter != null;
        this.livesFormatter = livesFormatter;
    }
}
