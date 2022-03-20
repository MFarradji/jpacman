package nl.tudelft.jpacman.integration;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.game.Game;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An example test class that conducts integration tests.
 */
public class StartupSystemTest {

    private Launcher launcher;

    /**
     * Close the user interface.
     */
    @AfterEach
    public void after() {
        launcher.dispose();
    }

    /**
     * The simplest test that just starts the
     * game and checks it is indeed in progress.
     */
    @Test
    public void gameIsRunning() {
        launcher = new Launcher();

        launcher.launch();

        getGame().start();

        assertThat(getGame().isInProgress()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    public void livesCountIsCorrect(int lives) {
        launcher = new Launcher(lives);

        launcher.launch();

        getGame().start();

        assertThat(getGame().getPlayers().get(0).getLives()).isEqualTo(lives);
    }

    @Test
    public void livesCountByDefaultIsCorrect() {
        launcher = new Launcher();

        launcher.launch();

        getGame().start();

        assertThat(getGame().getPlayers().get(0).getLives()).isEqualTo(1);
    }

    private Game getGame() {
        return launcher.getGame();
    }
}
