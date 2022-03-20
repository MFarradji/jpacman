package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.TooManyStaticImports"})
class PlayerTest {

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
