package com.Expedition67.core.graphics;

import com.Expedition67.storage.AssetManager;
import com.Expedition67.unit.Unit;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer {

    public void unitRender(Graphics2D g, Unit unit) {
        int row = unit.getAnimator().getRow();
        int index = unit.getAnimator().getIndex();
        BufferedImage image = AssetManager.Instance().getSprite(unit.getName(), row, index);
        if (image != null) {
            g.drawImage(image, unit.getX(), unit.getY(), unit.getWidth(), unit.getHeight(), null);
            if (unit.getFlashFrame() > 0 && unit.getFlashFrame() % 2 == 0) {
                g.setComposite(AlphaComposite.SrcAtop);
                g.setColor(new Color(255, 0, 0, 150));
                g.fillRect(unit.getX(), unit.getY(), unit.getWidth(), unit.getHeight());
            }
        }
    }
}
