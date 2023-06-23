package by.teachmeskills.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

@WebServlet("/calc")
public class CalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        try (PrintWriter writer = response.getWriter()) {
            double operand1 = Double.parseDouble(request.getParameter("operand1"));
            double operand2 = Double.parseDouble(request.getParameter("operand2"));
            String operation = request.getParameter("operation");

            double result = switch (operation) {
                case "+" -> CalculatorUtil.sum(operand1, operand2);
                case "-" -> CalculatorUtil.difference(operand1, operand2);
                case "*" -> CalculatorUtil.multiply(operand1, operand2);
                case "/" -> CalculatorUtil.divide(operand1, operand2);
                default -> 0.0;
            };
            writer.print("Result : " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

