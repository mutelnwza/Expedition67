package com.Expedition67.ui;

import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.PlayerBrain;

import java.awt.*;

/**
 * Manages all UI elements related to a single unit, such as name, health, and status effects.
 */
public class UnitUIHandler {

    public static final int STATUS_DAMAGE = 0;
    public static final int STATUS_HEAL = 1;
    public static final int STATUS_SHIELD = 2;
    public static final int STATUS_STR = 3;
    public static final int STATUS_CRIT = 4;

    private static final int STATUS_SHOW_DELAY = 60;

    private final Unit unit;
    private GameText nameText, hpText, strText, critText, apText, shieldText, statusText;
    private int statusShowTimer;
    private boolean isShowStatus;

    /**
     * Constructs a new UnitUIHandler for a given unit.
     *
     * @param unit The game unit this UI handler is associated with.
     */
    public UnitUIHandler(Unit unit) {
        this.unit = unit;
        initTextComponents();
    }

    /**
     * Updates the unit's UI elements, called every frame.
     */
    public void update() {
        updateHpText();
        updateStrText();
        updateCritText();
        updateShieldText();
        updateApText();
        updateStatusTextVisibility();
    }

    /**
     * Renders all the UI components for this unit.
     *
     * @param g The Graphics object used for drawing.
     */
    public void render(Graphics g) {
        nameText.render(g);
        hpText.render(g);
        strText.render(g);
        critText.render(g);
        shieldText.render(g);
        if (apText != null) {
            apText.render(g);
        }
        statusText.render(g);
    }

    /**
     * Displays a temporary status message above the unit.
     *
     * @param amount     The value to display.
     * @param statusType The type of status, determining text and color.
     */
    public void showStatus(float amount, int statusType) {
        int intAmount = Math.round(amount) == 0 ? (int) Math.ceil(amount) : Math.round(amount);
        String text = "";
        Color color = Color.white;

        color = switch (statusType) {
            case STATUS_DAMAGE -> {
                text = String.format("- %d HP", intAmount);
                yield Color.red;
            }
            case STATUS_HEAL -> {
                text = String.format("+ %d HP", intAmount);
                yield Color.green;
            }
            case STATUS_SHIELD -> {
                text = String.format(intAmount < 0 ? "- %d DEF" : "+ %d DEF", Math.abs(intAmount));
                yield Color.cyan;
            }
            case STATUS_STR -> {
                text = String.format(intAmount < 0 ? "- %d STR" : "+ %d STR", Math.abs(intAmount));
                yield Color.orange;
            }
            case STATUS_CRIT -> {
                text = String.format(intAmount < 0 ? "- %d CRIT" : "+ %d CRIT", Math.abs(intAmount));
                yield Color.yellow;
            }
            default -> color;
        };

        statusText.setText(text);
        statusText.setColor(color);
        statusText.horizontallyCentering(unit.getX(), unit.getWidth());
        statusText.setVisible(true);

        isShowStatus = true;
        statusShowTimer = 0;
    }

    /**
     * Updates the position of UI elements if the unit's position changes.
     */
    public void updatePosition() {
        nameText.horizontallyCentering(unit.getX(), unit.getWidth());
        nameText.setY(unit.getY() - 70);

        hpText.horizontallyCentering(unit.getX(), unit.getWidth());
        hpText.setY(unit.getY() - 50);

        strText.setX(unit.getX() + 20);
        strText.setY(unit.getY() - 30);

        if (apText != null) {
            apText.horizontallyCentering(unit.getX(), unit.getWidth());
            apText.setY(unit.getY() + unit.getHeight() + 40);
        }
    }

    private void initTextComponents() {
        nameText = new GameText(unit.getName().toString(), 0, unit.getY() - 70, 18f, Color.white);
        nameText.horizontallyCentering(unit.getX(), unit.getWidth());

        hpText = new GameText("", 0, unit.getY() - 50, 18f, Color.white);
        hpText.horizontallyCentering(unit.getX(), unit.getWidth());

        strText = new GameText("", unit.getX() + 20, unit.getY() - 30, 18f, Color.orange);

        critText = new GameText("", 0, unit.getY() - 30, 18f, Color.yellow);

        shieldText = new GameText("0", 0, unit.getY() - 50, 18f, Color.cyan);
        shieldText.setVisible(false);

        if (unit.getBrain() instanceof PlayerBrain) {
            apText = new GameText("", 0, unit.getY() + unit.getHeight() + 40, 18f, Color.white);
            apText.horizontallyCentering(unit.getX(), unit.getWidth());
        }

        statusText = new GameText("0", 0, unit.getY() - 90, 18f, Color.red);
        statusText.setVisible(false);
    }

    private void updateHpText() {
        float hp = unit.getUnitStats().getHp();
        float maxHp = unit.getUnitStats().getMaxHp();
        int newHp = Math.round(hp) == 0 ? (int) Math.ceil(hp) : Math.round(hp);
        int newMaxHp = Math.round(maxHp) == 0 ? (int) Math.ceil(maxHp) : Math.round(maxHp);
        hpText.setText(String.format("HP: %d/%d", newHp, newMaxHp));
    }

    private void updateStrText() {
        float str = unit.getUnitStats().getStr();
        int newStr = Math.round(str) == 0 ? (int) Math.ceil(str) : Math.round(str);
        strText.setText(String.format("STR: %d", newStr));
    }

    private void updateCritText() {
        float crit = unit.getUnitStats().getCrit() * 100;
        int newCrit = Math.round(crit) == 0 ? (int) Math.ceil(crit) : Math.round(crit);
        critText.setText(String.format("CRIT: %d%%", newCrit));
        critText.setX(unit.getX() + unit.getWidth() - critText.getWidth() - 20);
    }

    private void updateShieldText() {
        float defense = unit.getUnitStats().getDef();
        int newDefense = Math.round(defense) == 0 ? (int) Math.ceil(defense) : Math.round(defense);
        if (newDefense > 0) {
            shieldText.setVisible(true);
            shieldText.setText(String.format("+ %d", newDefense));
            shieldText.setX(hpText.getX() + hpText.getWidth() + 10);
        } else {
            shieldText.setVisible(false);
        }
    }

    private void updateApText() {
        if (unit.getBrain() instanceof PlayerBrain pb) {
            apText.setText(String.format("AP: %d", pb.getAP()));
        }
    }

    private void updateStatusTextVisibility() {
        if (isShowStatus) {
            statusShowTimer++;
            if (statusShowTimer >= STATUS_SHOW_DELAY) {
                isShowStatus = false;
                statusText.setVisible(false);
            }
        }
    }
}
