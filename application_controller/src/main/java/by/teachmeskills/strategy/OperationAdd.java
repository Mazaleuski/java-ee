package by.teachmeskills.strategy;

public class OperationAdd implements Strategy {
    @Override
    public int operation(int a, int b) {
        return a + b;
    }
}
