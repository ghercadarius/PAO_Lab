package org.smartercalculator;

import org.example.CalculatorException;

public class UnknownOperandTypeException extends CalculatorException {
    public UnknownOperandTypeException() {
        super("Unknown operand type");
    }
}

