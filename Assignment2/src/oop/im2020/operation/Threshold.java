package oop.im2020.operation;

import oop.im2020.ui.OperationDialog;
import oop.im2020.utils.OperationUtilities;
import oop.im2020.ui.ThresholdUI;

import java.awt.image.BufferedImage;

public class Threshold implements Operation{

    private final ThresholdUI thresholdUI = new ThresholdUI();

    @Override
    public BufferedImage doOperation(final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( null, thresholdUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            final int thresholdValue = thresholdUI.getAlpha();
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.threshold(inputRGB, thresholdValue);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }
}
