package org.smartercalculator.calculatorResult;

import org.smartercalculator.CalculationRequest;
import org.smartercalculator.calculatorResult.InvalidOperationException;


public class IntegerCalculationResult extends CalculationResult {
    public IntegerCalculationResult(CalculationRequest request) {
        super(request);
    }

    @Override
    public Object computeResult() throws InvalidOperationException{
        CalculationRequest request = getRequest();
        Integer leftOperand = (Integer) request.getLeftOperand();
        Integer rightOperand = (Integer) request.getRightOperand();

        return switch (request.getOperation()) {
            case "+" -> leftOperand + rightOperand;
            case "-" -> leftOperand - rightOperand;
            case "*" -> leftOperand * rightOperand;
            case "/" -> leftOperand / rightOperand;
            default -> throw new InvalidOperationException("Invalid operation: " + request.getOperation());
        };
    }
}
