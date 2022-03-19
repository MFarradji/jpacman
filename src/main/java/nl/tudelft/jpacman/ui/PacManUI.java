package nl.tudelft.jpacman.ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import nl.tudelft.jpacman.game.Game;
import nl.tudelft.jpacman.level.Player;

/**
 * The default JPacMan UI frame. The PacManUI consists of the following
 * elements:
 *
 * <ul>
 * <li>A score panel at the top, displaying the score of the player(s).
 * <li>A board panel, displaying the current level, i.e. the board and all units
 * on it.
 * <li>A button panel, containing all buttons provided upon creation.
 * </ul>
 *
 * @author Jeroen Roosen 
 *
 */
public class PacManUI extends JFrame {

    /**
     * Default serialisation UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The desired frame rate interval for the graphics in milliseconds, 40
     * being 25 fps.
     */
    private static final int FRAME_INTERVAL = 40;

    private final List<PlayerInfoPanel> playerInfoPanels;

    /**
     * The panel displaying the game.
     */
    private final BoardPanel boardPanel;

    /**
     * Creates a new UI for a JPacman game.
     *
     * @param game
     *            The game to play.
     * @param buttons
     *            The map of caption-to-action entries that will appear as
     *            buttons on the interface.
     * @param keyMappings
     *            The map of keyCode-to-action entries that will be added as key
     *            listeners to the interface.
     */
    public PacManUI(final Game game, final Map<String, Action> buttons,
                    final Map<Integer, Action> keyMappings) {
        super("JPacman");
        assert game != null;
        assert buttons != null;
        assert keyMappings != null;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PacKeyListener keys = new PacKeyListener(keyMappings);
        addKeyListener(keys);

        JPanel buttonPanel = new ButtonPanel(buttons, this);

        boardPanel = new BoardPanel(game);

        this.playerInfoPanels = new ArrayList<>();

        Container contentPanel = getContentPane();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        AddPlayerInfoPanels(contentPanel, game);
        contentPanel.add(boardPanel, BorderLayout.CENTER);

        pack();
    }

    private void AddPlayerInfoPanels(Container contentPanel, Game game) {
        List<Player> players = game.getPlayers();

        JPanel playerInfoContainer = new JPanel();
        playerInfoContainer.setLayout(new GridLayout(1, players.size()));

        int count = 1;
        for (Player player : players) {
            PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(player, count);
            playerInfoContainer.add(playerInfoPanel);
            playerInfoPanels.add(playerInfoPanel);
            count++;
        }
        contentPanel.add(playerInfoContainer, BorderLayout.NORTH);
    }

    /**
     * Starts the "engine", the thread that redraws the interface at set
     * intervals.
     */
    public void start() {
        setVisible(true);
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(this::nextFrame, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS);
    }

    /**
     * Draws the next frame, i.e. refreshes the scores and game.
     */
    private void nextFrame() {
        boardPanel.repaint();
        for (PlayerInfoPanel playerInfoPanels : playerInfoPanels) {
            playerInfoPanels.refresh();
        }
    }
}
