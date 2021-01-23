package oop.im2020.operation;

import oop.im2020.ui.ColourComponent;
import oop.im2020.ui.OperationDialog;
import oop.im2020.ui.TintUI;
import oop.im2020.utils.OperationUtilities;

import java.awt.image.BufferedImage;

public class Tint  implements Operation{

    private final TintUI tintUI = new TintUI();


    @Override
    public BufferedImage doOperation(final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog( null, tintUI);
        dialog.setVisible( true);
        if (!dialog.wasCancelled()) {
            final ColourComponent band = tintUI.getChosenColor();
            final double alpha = tintUI.getAlpha() / 100.0;
            for (int x = 0; x < inputImage.getWidth(); x++) {
                for (int y = 0; y < inputImage.getHeight(); y++) {
                    final int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                    final int outputRGB = OperationUtilities.tint(inputRGB, alpha, band);
                    OperationUtilities.setRGB(x, y, outputRGB, inputImage);
                }
            }
        }
        return inputImage;
    }
}
