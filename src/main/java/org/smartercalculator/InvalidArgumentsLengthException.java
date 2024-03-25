package org.smartercalculator;

import org.example.CalculatorRuntimeException;

public class InvalidArgumentsLengthException extends CalculatorRuntimeException{
    public InvalidArgumentsLengthException() {
        super("Invalid arguments length");
    }
}
