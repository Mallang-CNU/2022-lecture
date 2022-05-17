package model;

import java.util.Scanner;
/**
 * Created by ShinD on 2022/05/03.
 */
public class PostfixCalculator {
	
	private static final int DEFAULT_MAX_EXPRESSION_LENGTH = 20;
	
	private int _maxExpressionLength;
	private Stack<Integer> _valueStack;
	
	public int maxExpressionLength() {
		return this._maxExpressionLength;
	}
	public void setMaxExpressionLength(int newMaxExpressionLength)
	{
		this._maxExpressionLength = newMaxExpressionLength;
	}

	private Stack<Integer> valueStack(){
		return this._valueStack;
	}
	private void setValueStack (Stack<Integer> newValueStack)
	{
		this._valueStack= newValueStack;
	}
	
	public PostfixCalculator()
	{
		this (PostfixCalculator.DEFAULT_MAX_EXPRESSION_LENGTH);
	}
	
	public PostfixCalculator (int givenMaxExpressionLength)
	{
		this.setMaxExpressionLength(givenMaxExpressionLength);
		this.setValueStack(new ArrayList<Integer>(this.maxExpressionLength()));
	}

	/**
	 * Stack 을 이용하여 postfix 수식을 계산하여 그 결과를 반환한다.
	 * @param aPostfixExpression postfix 수식
	 * @return 계산 결과
	 * @throws CalculatorException 표현이 없는 경우, 표현이 너무 긴 경우, 연산 과정에서 문제가 생긴 경우 등 발생
	 */
	public int evaluate(String aPostfixExpression) throws CalculatorException {
		if(aPostfixExpression == null || aPostfixExpression.length()==0) {
			throw new CalculatorException(CalculatorError.PostfixError_NoExpression);
		}

		this.valueStack().clear();//배열 초기화
		char token;//표기식의 한글자씩 저장할 변수
		for(int current =0; current < aPostfixExpression.length(); current++) {
			token = aPostfixExpression.charAt(current);

			if(Character.isDigit(token)) {//숫자인 경우
				int tokenValue = Character.getNumericValue(token);//정수로 반환
				if(this.valueStack().isFull()) {//스택이 가득 찬 경우 예외 발생
					throw new CalculatorException(CalculatorError.PostfixError_TooLongExpression);
				}
				else {//스택에 넣는다
					this.valueStack().push(Integer.valueOf(tokenValue));
				}
			}

			else {//숫자가 아닌 경우
				CalculatorError error = this.executeBinaryOperator(token);//연산자를 수행한 후 예외여부를 받는다.
				if(error != CalculatorError.PostfixError_None) {
					throw new CalculatorException(error);
				}
			}

			this.showTokenAndValueStack(token);
		}
		if(this.valueStack().size() == 1) {
			return (this.valueStack().pop().intValue());//쓸데없는 언박싱이다. 지워도 상관없다.
		}
		else {
			throw new CalculatorException(CalculatorError.PostfixError_TooManyValues);
		}
	}

	/**
	 * 받아온 수식과 스택의 값을 통해 연산을 수행하고, 중간에 예외가 발생하면 해당 예외를 반환한다.
	 * @param anOperator 연산자
	 * @return 발생한 예외
	 */
	private CalculatorError executeBinaryOperator(char anOperator) {
		if (this.valueStack().size() < 2) {
			return CalculatorError.PostfixError_TooFewValues;
		}

		int operand1 = this.valueStack().pop().intValue();//쓸데없는 언박싱이다. 지워도 상관없다.
		int operand2 = this.valueStack().pop().intValue();//쓸데없는 언박싱이다. 지워도 상관없다.
		int calculated = 0;

		switch (anOperator) {
			case '^':
				calculated = (int) Math.pow((double) operand2, (double) operand1);
				break;
			case '*':
				calculated = operand2 * operand1;
				break;
			case '/':
				if (operand1 == 0) {
					AppView.outputLineDebugMessage(
							anOperator + " : (DivideByZero) " + operand2 + " " + anOperator + " " + operand1);
					return CalculatorError.PostfixError_DivideByZero;
				} else {
					calculated = operand2 / operand1;
				}
				break;
			case '%':
				if (operand1 == 0) {
					AppView.outputLineDebugMessage(
							anOperator + " : (DivideByZero) " + operand2 + " " + anOperator + " " + operand1);
					return CalculatorError.PostfixError_DivideByZero;
				} else {
					calculated = operand2 % operand1;
				}
				break;
			case '+':
				calculated = operand2 + operand1;
				break;
			case '-':
				calculated = operand2 - operand1;
				break;
			default:
				return CalculatorError.PostfixError_UnknownOperator;
		}

		this.valueStack().push(Integer.valueOf(calculated));

		return CalculatorError.PostfixError_None;
	}
	
	private void showTokenAndValueStack(char aToken)
	{
		AppView.outputDebugMessage(aToken+": ValueStack<Bottom>");
		for(int i=0; i<this.valueStack().size(); i++) {
			AppView.outputDebugMessage(((ArrayList<Integer>)this.valueStack()).elementAt(i).intValue()+" ");
		}
		AppView.outputLineDebugMessage("<Top>");
	}

}


