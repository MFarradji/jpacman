package nl.tudelft.jpacman.ui.Panels;

import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.ui.InfoFormatters.InfoFormatter;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private final JLabel label;

    private final Player player;

    private final InfoFormatter infoFormatter;

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

    protected void refresh() {
        String score = infoFormatter.format(player);
        label.setText(score);
    }
}
