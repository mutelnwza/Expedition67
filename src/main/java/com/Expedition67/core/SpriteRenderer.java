package com.Expedition67.core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.Expedition67.storage.AssetManager;
import com.Expedition67.unit.Unit;

public class SpriteRenderer {
    public void unitRender(Graphics2D g, Unit unit) {
        int row = unit.getAnimator().getRow();
        int index = unit.getAnimator().getIndex();
        BufferedImage image = AssetManager.Instance().getSprite(unit.getName(), row, index);
        if (image != null) {
            g.drawImage(image, unit.getX(), unit.getY(), unit.getWidth(), unit.getHeight(), null);
        }
    }

    public void cardRender() {
        //render cards in hand
    }
}
