package oop.im2020.operation;

import oop.im2020.ui.GrayscaleUI;
import oop.im2020.ui.OperationDialog;
import oop.im2020.utils.OperationUtilities;

import java.awt.image.BufferedImage;

public class Grayscale implements Operation{

    private final GrayscaleUI grayscaleUI = new GrayscaleUI();

    @Override
    public BufferedImage doOperation(final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( null, grayscaleUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.grayscale(inputRGB);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }
}
