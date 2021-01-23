package oop.im2020.operation;

import oop.im2020.ui.OperationDialog;
import oop.im2020.utils.OperationUtilities;
import oop.im2020.ui.BlendUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Blend implements Operation {

    private final JFileChooser chooser = new JFileChooser();
    private final BlendUI blendUI = new BlendUI(chooser);

    @Override
    public BufferedImage doOperation(final BufferedImage inputImage) {
        final OperationDialog dialog = new OperationDialog(null, blendUI);
        dialog.setVisible(true);
        if (!dialog.wasCancelled()) {
            try {
                double sensitivity = blendUI.getSensitivity();
                BufferedImage otherImage = ImageIO.read(blendUI.getOtherImagePath());

                BufferedImage output = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), inputImage.getType());
                for (int x = 0; x < output.getWidth(); x++) {
                    for (int y = 0; y < output.getHeight(); y++) {
                        int inputRGB = OperationUtilities.getRGB(x, y, inputImage);
                        int otherRGB = OperationUtilities.getRGB(x, y, otherImage);
                        int outputRGB = OperationUtilities.blend(inputRGB, otherRGB, sensitivity);
                        OperationUtilities.setRGB(x, y, outputRGB, output);
                    }
                }
                return output;
            } catch (IOException ex) {
                ex.printStackTrace();
                return inputImage;
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(new JFrame(), "You have to choose an image to blend firstly");
                return inputImage;
            }
        } else {
            return inputImage;
        }
    }
}