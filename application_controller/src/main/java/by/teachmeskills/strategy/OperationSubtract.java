package by.teachmeskills.strategy;

public class OperationSubtract implements Strategy {
    @Override
    public int operation(int a, int b) {
        return a - b;
    }
}
