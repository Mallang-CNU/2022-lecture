package model;
/**
 * Created by ShinD on 2022/05/03.
 */
public class CalculatorException extends Exception{


	private static final long serialVersionUID = 1L;
	private CalculatorError _error;

	public CalculatorError error() {
		return this._error;
	}
	public void setError (CalculatorError newError)
	{

		this._error = newError;
	}

	public CalculatorException() {
		this.setError(CalculatorError.Undefined);
	}
	public CalculatorException(CalculatorError givenError)
	{
		this.setError(givenError);
	}


	

}
