package org.example;

import org.smartercalculator.CalculationRequest;
import org.smartercalculator.SmarterCalculator;
import org.smartercalculator.calculatorResult.CalculationResult;
import org.smartercalculator.calculatorResult.InvalidOperationException;

import java.util.List;

class JustMyFirstException extends RuntimeException {
    public JustMyFirstException() {
        super("Oopsie! This is my first exception!");
    }
}


public class Main {
    public static void foo() {
        try {
            System.out.println("1");
            throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("2");
        }

        System.out.println("3");
    }


    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
        try {
            throw new JustMyFirstException();
        } catch (JustMyFirstException e) {
            System.out.println(e.getMessage());
        }
        args = new String[]{
                "1", "+", "2",
                "2", "*", "5",
                "1", "+", "5.0",
                "1.0", "-", "2",
                "10.0", "/", "1",
        };
        foo();
    }

    private static void runSmarterCalculator(String[] args) {
        List<CalculationResult> calculationResults =  SmarterCalculator.calculate(args);

        for (CalculationResult result : calculationResults) {
            CalculationRequest request = result.getRequest();
            try {
                System.out.println("Operation " + request + " has result " + result.computeResult());
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}