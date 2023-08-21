package by.teachmeskills.strategy;

public class Application {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("7 + 12 = " + context.executeStrategy(7, 12));

        context = new Context(new OperationSubtract());
        System.out.println("3 - 1 = " + context.executeStrategy(3, 1));

        context = new Context(new OperationMultiply());
        System.out.println("3 * 4 = " + context.executeStrategy(3, 4));

        try {
            context = new Context(new OperationDivision());
            System.out.println("7 / 0 = " + context.executeStrategy(7, 0));
        } catch (ArithmeticException e) {
            System.out.println("Don't divide by zero!");
        }
    }
}
