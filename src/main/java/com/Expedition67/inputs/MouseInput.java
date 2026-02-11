package com.Expedition67.inputs;

import com.Expedition67.core.GameManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    // --- MouseListener Methods ---
    @Override
    public void mouseClicked(MouseEvent e) {
        GameManager.Instance().mouseClicked(e);
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
        GameManager.Instance().mouseMoved(e);
    }
}
