/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import view.model.StatusType;

/**
 *
 * @author Admin
 */
public class TableStatus extends JLabel {

    private StatusType type;

    public StatusType getType() {
        return type;
    }

    public TableStatus() {
        setForeground(Color.WHITE);
    }

    public void setType(StatusType type) {
        this.type = type;
        setText(type == StatusType.DANGHOATDONG ? "Đang hoạt động" : "Không hoạt động");
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        if (type != null) {
            Graphics2D g2 = (Graphics2D) graphics;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint g;
            if (type == StatusType.DANGHOATDONG) {
                g = new GradientPaint(0, 0, new Color(160, 250, 167), 0, getHeight(), new Color(70, 250, 84));
            } else {
                g = new GradientPaint(0, 0, new Color(241, 208, 62), 0, getHeight(), new Color(242, 75, 75));
            }
            g2.setPaint(g);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 1, 1);
        }
        super.paintComponent(graphics);
    }

}
