package by.teachmeskills.strategy;

public class OperationDivision implements Strategy {
    @Override
    public int operation(int a, int b) throws ArithmeticException {
        return a / b;
    }
}
