package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests various aspects of level.
 *
 * @author Jeroen Roosen 
 */
// The four suppress warnings ignore the same rule, which results in 4 same string literals
@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.TooManyStaticImports"})
class PlayerTest {

    /**
     * System under tests
     */
    private Player player;

    @BeforeEach
    void setUp() {
        player = new PlayerFactory(new PacManSprites()).createPacMan(3);
    }

    @Test
    void shouldDecrementByOneLifeWhenLoseLife() {
        player.loseLife();

        assertThat(player.getLives()).isEqualTo(2);
    }

    @Test
    void shouldNotBeAliveWhenNoMoreLivesLeft() {
        player.loseLife();
        player.loseLife();
        player.loseLife();

        assertThat(player.isAlive()).isFalse();
    }

    @Test
    void shouldBeAliveWhenLivesAreStillLeft() {
        player.loseLife();

        assertThat(player.isAlive()).isTrue();
    }

    @Test
    void shouldNotHaveNegativeLives() {
        player.loseLife();
        player.loseLife();
        player.loseLife();
        player.loseLife();

        assertThat(player.getLives()).isGreaterThanOrEqualTo(0);
    }
}
