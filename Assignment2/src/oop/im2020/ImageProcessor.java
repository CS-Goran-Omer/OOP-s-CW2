package oop.im2020;

import oop.im2020.operation.Operation;
import oop.im2020.operation.OperationFactory;
import oop.im2020.ui.ImagePanel;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
 * @author childm
 */
public class ImageProcessor extends JFrame {
    private static final long serialVersionUID = 1L;

    private final String GRAYSCALE = "GRAYSCALE";
    private final String TINT = "TINT";
    private final String CHROMAKEY = "CHROMAKEY";
    private final String NEGATIVE = "NEGATIVE";
    private final String THRESHOLD = "THRESHOLD";
    private final String BLEND = "BLEND";

    private final JFileChooser chooser = new JFileChooser();
    private final ImagePanel imagePanel = new ImagePanel();
    private BufferedImage image;

    private File loadedImage;

    private final JMenu opMenu = new JMenu("Operations");


    public ImageProcessor(OperationFactory operationFactory) {
        init(operationFactory);
    }

    private void init(OperationFactory operationFactory) {
        this.chooser.setMultiSelectionEnabled(false);
        this.chooser.setCurrentDirectory(new File(".")); // set current directory

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        final JMenuBar menuBar = new JMenuBar();
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(ev -> doOpenImage());
        fileMenu.add(openItem);

        final JMenuItem revertItem = new JMenuItem("Revert");
        revertItem.addActionListener(ev -> revert());
        fileMenu.add(revertItem);

        menuBar.add(fileMenu);
        menuBar.add(this.opMenu);
        setJMenuBar(menuBar);

        add(this.imagePanel, BorderLayout.CENTER);
        pack();

        addMenuOperation(GRAYSCALE, operationFactory);
        addMenuOperation(TINT, operationFactory);
        addMenuOperation(CHROMAKEY, operationFactory);
        addMenuOperation(NEGATIVE, operationFactory);
        addMenuOperation(THRESHOLD, operationFactory);
        addMenuOperation(BLEND, operationFactory);

        this.setVisible(true);
    }

    private void addMenuOperation(final String identifier, OperationFactory operationFactory) {
        final JMenuItem item = new JMenuItem(identifier);
        Operation operation = operationFactory.getOperation(identifier);
        item.addActionListener(ev -> setImage(operation.doOperation(this.image)));
        this.opMenu.add(item);
    }

    private File chooseImageFile() {
        if (this.chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return this.chooser.getSelectedFile();
        } else {
            return null;
        }
    }

    private void doOpenImage() {
        final File file = chooseImageFile();
        if (file != null) {
            loadImage(file);
        }
    }

    private void loadImage(final File file) {
        try {
            this.image = ImageIO.read(file);
            this.loadedImage = file;
            setImage(this.image);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setImage(final BufferedImage image) {
        this.image = image;
        this.imagePanel.setImage(image);
        pack();
    }

    private void revert() {
        if (this.loadedImage != null) {
            loadImage(this.loadedImage);
        }
    }

    public static void main(final String[] args) {
        OperationFactory operationFactory = new OperationFactory();
        SwingUtilities.invokeLater(() -> new ImageProcessor(operationFactory));
    }

}
