package com.Expedition67.ui;

import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.PlayerBrain;
import java.awt.*;

public class UnitUIHandler {

    public static final int STATUS_DAMAGE = 0;
    public static final int STATUS_HEAL = 1;
    public static final int STATUS_SHIELD = 2;
    public static final int STATUS_STR = 3;
    public static final int STATUS_CRIT = 4;

    private final Unit unit;
    private GameText nameText;
    private GameText hpText;
    private GameText apText;
    private GameText shieldText;
    private GameText statusText;

    private int statusShowTimer;
    private boolean isShowStatus;

    public UnitUIHandler(Unit unit) {
        this.unit = unit;
        initNameText();
        initHpText();
        initShieldText();
        initApText();
        initStatusText();
    }

    private void initNameText() {
        nameText = new GameText(unit.getName().toString(), 0, unit.getY() - 50, 18f, Color.white);
        nameText.horizontallyCentering(unit.getX(), unit.getWidth());
    }

    private void initHpText() {
        int hp = Math.round(unit.getUnitStats().getHp());
        int maxhp = Math.round(unit.getUnitStats().getMaxHp());

        String hpStr = String.format("HP: %d/%d", hp, maxhp);
        hpText = new GameText(hpStr, 0, unit.getY() - 30, 18f, Color.white);
        hpText.horizontallyCentering(unit.getX(), unit.getWidth());
    }

    private void initShieldText() {
        shieldText = new GameText("0", 0, unit.getY() - 30, 18f, Color.cyan);
        shieldText.setVisible(false);
    }

    private void initApText() {
        if (unit.getBrain() instanceof PlayerBrain pb) {
            String apStr = String.format("AP: %d", pb.getAP());
            apText = new GameText(apStr, 0, unit.getY() + unit.getHeight() + 40, 18f, Color.white);
            apText.horizontallyCentering(unit.getX(), unit.getWidth());
        }
    }

    private void initStatusText() {
        statusText = new GameText("0", 0, unit.getY() - 70, 18f, Color.red);
        statusText.setVisible(false);
    }

    public void showStatus(float _amount, int statusType) {
        int amount = Math.round(_amount);

        if (statusType == STATUS_DAMAGE) {
            statusText.setText(String.format("- %d HP", amount));
            statusText.setColor(Color.red);
        } else if (statusType == STATUS_HEAL) {
            statusText.setText(String.format("+ %d HP", amount));
            statusText.setColor(Color.green);
        } else if (statusType == STATUS_SHIELD) {
            if (amount < 0)
                statusText.setText(String.format("- %d DEF", amount));
            else
                statusText.setText(String.format("+ %d DEF", amount));
            statusText.setColor(Color.cyan);
        } else if (statusType == STATUS_STR) {
            if (amount < 0)
                statusText.setText(String.format("- %d STR", amount));
            else
                statusText.setText(String.format("+ %d STR", amount));
            statusText.setColor(Color.yellow);
        } else {
            if (amount < 0)
                statusText.setText(String.format("- %d CRIT", amount));
            else
                statusText.setText(String.format("+ %d CRIT", amount));
            statusText.setColor(Color.orange);
        }
        statusText.horizontallyCentering(unit.getX(), unit.getWidth());
        isShowStatus = true;
        statusShowTimer = 0;
    }

    public void update() {
        int hp = Math.round(unit.getUnitStats().getHp());
        int maxhp = Math.round(unit.getUnitStats().getMaxHp());
        int d = Math.round(unit.getUnitStats().getDef());

        hpText.setText(String.format("HP: %d/%d", hp, maxhp));

        if (d > 0) {
            shieldText.setVisible(true);
            shieldText.setText(String.format("+ %d", d));
            shieldText.setX(hpText.getX() + hpText.getWidth() + 35);
        } else
            shieldText.setVisible(false);

        if (unit.getBrain() instanceof PlayerBrain pb) {
            apText.setText(String.format("AP: %d", pb.getAP()));
        }

        if (isShowStatus) {
            statusText.setVisible(true);
            statusShowTimer++;
            int delay = 60;
            if (statusShowTimer >= delay) {
                isShowStatus = false;
                statusText.setVisible(false);
            }
        }
    }

    public void render(Graphics g) {
        nameText.render(g);
        hpText.render(g);
        shieldText.render(g);
        if (apText != null) {
            apText.render(g);
        }
        statusText.render(g);
    }

    public void updatePosition() {
        nameText.horizontallyCentering(unit.getX(), unit.getWidth());
        hpText.horizontallyCentering(unit.getX(), unit.getWidth());
        if (apText != null) {
            apText.horizontallyCentering(unit.getX(), unit.getWidth());
        }
    }
}
