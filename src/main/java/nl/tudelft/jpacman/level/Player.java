package nl.tudelft.jpacman.level;

import java.util.Map;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.sprite.AnimatedSprite;
import nl.tudelft.jpacman.sprite.Sprite;

/**
 * A player operated unit in our game.
 *
 * @author Jeroen Roosen 
 */
public class Player extends Unit {

    /**
     * The amount of points accumulated by this player.
     */
    private int score;

    /**
     * The animations for every direction.
     */
    private final Map<Direction, Sprite> sprites;

    /**
     * The animation that is to be played when Pac-Man dies.
     */
    private final AnimatedSprite deathSprite;

    /**
     * {@link Unit} iff this player died by collision, <code>null</code> otherwise.
     */
    private Unit killer;

    /**
     * The number of lives of a player.
     */
    private int lives;

    /**
     * Creates a new player with a score of 0 points.
     *
     * @param spriteMap
     *            A map containing a sprite for this player for every direction.
     * @param deathAnimation
     *            The sprite to be shown when this player dies.
     */
    protected Player(Map<Direction, Sprite> spriteMap, AnimatedSprite deathAnimation, int lives) {
        this.score = 0;
        this.lives = lives;
        this.sprites = spriteMap;
        this.deathSprite = deathAnimation;
        deathSprite.setAnimating(false);
    }

    /**
     * Returns whether this player is alive or not.
     *
     * @return <code>true</code> iff the player is alive.
     */
    public boolean isAlive() {
        return getLives() > 0;
    }

    /**
     * Sets whether this player is alive or not.
     *
     * If the player comes back alive, the {@link killer} will be reset.
     *
     */
    public void loseLife() {
        decrementLives();
        if (isAlive()) {
            deathSprite.setAnimating(false);
            this.killer = null;
        }
        else {
            deathSprite.restart();
        }
    }

    /**
     * Returns the unit that caused the death of Pac-Man.
     *
     * @return <code>Unit</code> iff the player died by collision, otherwise <code>null</code>.
     */
    public Unit getKiller() {
        return killer;
    }

    /**
     * Sets the cause of death.
     *
     * @param killer is set if collision with ghost happens.
     */
    public void setKiller(Unit killer) {
        this.killer =  killer;
    }

    /**
     * Returns the amount of points accumulated by this player.
     *
     * @return The amount of points accumulated by this player.
     */
    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    private void decrementLives() {
        if (getLives() > 0)
            this.lives--;
    }

    @Override
    public Sprite getSprite() {
        if (isAlive()) {
            return sprites.get(getDirection());
        }
        return deathSprite;
    }

    /**
     * Adds points to the score of this player.
     *
     * @param points
     *            The amount of points to add to the points this player already
     *            has.
     */
    public void addPoints(int points) {
        score += points;
    }
}
