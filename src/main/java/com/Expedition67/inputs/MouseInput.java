package com.Expedition67.inputs;

import com.Expedition67.core.GameManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    private final GameManager gameManager;

    /**
     * Constructor: Connects this input handler to the main Game Manager.
     */
    public MouseInput(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    // --- MouseListener Methods ---
    @Override
    public void mouseClicked(MouseEvent e) {
        gameManager.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // --- MouseMotionListener Methods ---
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gameManager.mouseMoved(e);
    }
}
