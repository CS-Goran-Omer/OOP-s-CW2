package oop.im2020.operation;

import oop.im2020.ui.OperationDialog;
import oop.im2020.utils.OperationUtilities;
import oop.im2020.ui.NegativeUI;

import java.awt.image.BufferedImage;

public class Negative implements Operation{

    private final NegativeUI negativeUI = new NegativeUI();

    @Override
    public BufferedImage doOperation(final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( null, negativeUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.negative(inputRGB);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }
}