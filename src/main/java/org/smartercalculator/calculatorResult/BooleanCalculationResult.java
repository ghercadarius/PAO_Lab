package org.smartercalculator.calculatorResult;

import org.smartercalculator.CalculationRequest;

public class BooleanCalculationResult extends CalculationResult {
    public BooleanCalculationResult(CalculationRequest request) {
        super(request);
    }

    @Override
    public Object computeResult() throws InvalidOperationException{
        CalculationRequest request = getRequest();
        Boolean leftOperand = (Boolean) request.getLeftOperand();
        Boolean rightOperand = (Boolean) request.getRightOperand();

        return switch (request.getOperation()) {
            case "&&" -> leftOperand && rightOperand;
            case "||" -> leftOperand || rightOperand;
            default -> throw new InvalidOperationException("Invalid operation: " + request.getOperation());
        };
    }
}
