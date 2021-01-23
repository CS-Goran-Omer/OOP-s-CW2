package oop.im2020.operation;

/**
*
* @auther Goran
 */

public class OperationFactory {

    //use getOperation method to get object of type Operation
    public Operation getOperation(String operationType) {
        if (operationType == null) {
            return null;
        }
        if (operationType.equalsIgnoreCase("Grayscale")) {
            return new Grayscale();
        } else if (operationType.equalsIgnoreCase("Tint")) {
            return new Tint();
        } else if (operationType.equalsIgnoreCase("Chromakey")) {
            return new ChromaKey();
        } else if (operationType.equalsIgnoreCase("Negative")) {
            return new Negative();
        } else if (operationType.equalsIgnoreCase("Threshold")) {
            return new Threshold();
        } else if (operationType.equalsIgnoreCase("Blend")) {
            return new Blend();
        }
        return null;
    }
}

