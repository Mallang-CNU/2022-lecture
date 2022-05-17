package model;
/**
 * Created by ShinD on 2022/05/03.
 */
public enum CalculatorError {
	Undefined,
	InfixError_None,
	InfixError_NoExpression,
	InfixError_TooLongExpression,
	InfixError_MissingLeftParen,
	InfixError_MissingRightParen,
	InfixError_UnknownOperator,
	
	PostfixError_None,
	PostfixError_NoExpression,
	PostfixError_TooLongExpression,
	PostfixError_TooFewValues,
	PostfixError_TooManyValues,
	PostfixError_DivideByZero,
	PostfixError_UnknownOperator,
}
