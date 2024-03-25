package org.smartercalculator.calculatorResult;

import org.example.CalculatorException;

public class InvalidOperationException extends CalculatorException{
    public InvalidOperationException(String message) {
        super(message);
    }
}
