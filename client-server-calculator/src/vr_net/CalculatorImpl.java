package vr_net;

import java.util.HashMap;
import java.util.function.BinaryOperator;

public class CalculatorImpl implements Calculator  {

	private static HashMap<String, BinaryOperator<Double>> operations;
	static {
		//construct static fields
		operations = new HashMap<>();
		operations.put("+", (operand1, operand2) -> operand1 + operand2);
		operations.put("-", (operand1, operand2) -> operand1 - operand2);
		operations.put("*", (operand1, operand2) -> operand1 * operand2);
		operations.put("/", (operand1, operand2) -> operand1 / operand2);
	}

	@Override
	public double calculate(String operator, double operand1, double operand2) {
		BinaryOperator<Double> method = operations.getOrDefault(operator,
				(a, b) -> {throw new IllegalArgumentException("unknown operator");});
		return method.apply(operand1,  operand2);
	}
}
