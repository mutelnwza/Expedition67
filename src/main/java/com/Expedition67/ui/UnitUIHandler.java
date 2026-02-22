package com.Expedition67.ui;

import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.PlayerBrain;

import java.awt.Color;
import java.awt.Graphics;

public class UnitUIHandler {

    private final Unit unit;
    private GameText nameText;
    private GameText hpText;
    private GameText apText;
    private GameText damageText;

    private int damageShowTimer;
    private boolean isTakeDamage;

    public UnitUIHandler(Unit unit) {
        this.unit = unit;
        initNameText();
        initHpText();
        initApText();
        initDamageText();
    }

    private void initNameText() {
        nameText = new GameText(unit.getName().toString(), 0, unit.getY() - 50, 18f, Color.white);
        nameText.horizontallyCentering(unit.getX(), unit.getWidth());
    }

    private void initHpText() {
        String hpStr = String.format("HP: %.2f/%.2f", unit.getUnitStats().getHp(), unit.getUnitStats().getMaxHp());
        hpText = new GameText(hpStr, 0, unit.getY() - 30, 18f, Color.white);
        hpText.horizontallyCentering(unit.getX(), unit.getWidth());
    }

    private void initApText() {
        if (unit.getBrain() instanceof PlayerBrain pb) {
            String apStr = String.format("AP: %d", pb.getAP());
            apText = new GameText(apStr, 0, unit.getY() + unit.getHeight() + 40, 18f, Color.white);
            apText.horizontallyCentering(unit.getX(), unit.getWidth());
        }
    }

    private void initDamageText() {
        damageText = new GameText("0", 0, unit.getY() - 70, 18f, Color.red);
        damageText.setVisible(false);
    }

    public void showDamage(float amount) {
        damageText.setText("- " + amount);
        damageText.horizontallyCentering(unit.getX(), unit.getWidth());
        isTakeDamage = true;
        damageShowTimer = 0;
    }

    public void update() {
        hpText.setText(String.format("HP: %.2f/%.2f", unit.getUnitStats().getHp(), unit.getUnitStats().getMaxHp()));
        if (unit.getBrain() instanceof PlayerBrain pb) {
            apText.setText(String.format("AP: %d", pb.getAP()));
        }
        if (isTakeDamage) {
            damageText.setVisible(true);
            damageShowTimer++;
            int delay = 60;
            if (damageShowTimer >= delay) {
                isTakeDamage = false;
                damageText.setVisible(false);
            }
        }
    }

    public void render(Graphics g) {
        nameText.render(g);
        hpText.render(g);
        if (apText != null) {
            apText.render(g);
        }
        damageText.render(g);
    }

    public void updatePosition() {
        nameText.horizontallyCentering(unit.getX(), unit.getWidth());
        hpText.horizontallyCentering(unit.getX(), unit.getWidth());
        if (apText != null) {
            apText.horizontallyCentering(unit.getX(), unit.getWidth());
        }
    }
}
