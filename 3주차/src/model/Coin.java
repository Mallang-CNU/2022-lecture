package model;

/**
 * Created by ShinD on 2022/03/17.
 */
public class Coin {

    private static final int DEFAULT_VALUE = 0;

    private int _value;

    /**
     * DEFAULT_VALUE 값을 가진 Coin 객체를 생성한다.
     */
    public Coin() {
        this._value = DEFAULT_VALUE;
    }

    /**
     * givenValue 값을 가진 Coin 객체를 생성한다.
     */
    public Coin(int givenValue) {
        this._value = givenValue;
    }

    /**
     * Coin 의 값을 반환한다
     * @return 코인의 value
     */
    public int value() {
        return _value;
    }

    /**
     * Coin 의 값을 설정한다
     * @param newValue 설정할 값
     */
    public void setValue(int newValue) {
        this._value = newValue;
    }


    /**
     * 현재 코인의 금액이 otherCoin과 같은지 확인한다
     * @param otherCoin 비교할 대상 Coin
     * @return 같으면 true, 다르다면 false
     */
    @Override
    public boolean equals(Object otherCoin) {
        if (otherCoin.getClass() != Coin.class){
            return false;
        }else {
            return (this.value() == ((Coin)otherCoin)._value);
        }
    }

}
