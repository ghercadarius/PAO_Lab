package org.smartercalculator.calculatorResult;

import org.smartercalculator.CalculationRequest;

public abstract class CalculationResult {
    private final CalculationRequest request;

    public CalculationResult(CalculationRequest request) {
        this.request = request;
    }

    public CalculationRequest getRequest() {
        return request;
    }
    public abstract Object computeResult() throws InvalidOperationException;
}
