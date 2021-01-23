package oop.im2020.ui;

import javax.swing.*;
import java.awt.*;

public class ThresholdUI extends JPanel {

    private final JSlider alphaSlider = new JSlider(0, 200);

    public ThresholdUI() {
        super(new BorderLayout());

        add(this.alphaSlider, BorderLayout.SOUTH);
    }

    public int getAlpha() {
        return this.alphaSlider.getValue();
    }
}
