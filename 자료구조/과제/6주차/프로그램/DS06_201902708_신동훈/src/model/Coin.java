package model;

/**
 * Created by ShinD on 2022/04/13.
 */
public class Coin implements Comparable<Coin>{


    @Override
    public int compareTo(Coin aCoin) {
        if ( this.value() < aCoin.value() ) {
            return -1;
        }
        else if (this.value() > aCoin.value() ) {
                return +1 ;
        }
        else {
            return 0;
        }

    }


    private static final int DEFAULT_VALUE = 0;

    private int _value;

    public Coin() {
        this._value = DEFAULT_VALUE;
    }

    public Coin(int givenValue) {
        this._value = givenValue;
    }

    public int value() {
        return _value;
    }

    public void setValue(int newValue) {
        this._value = newValue;
    }


    @Override
    public boolean equals(Object otherCoin) {
        if (otherCoin.getClass() != Coin.class){
            return false;
        }else {
            return (this.value() == ((Coin)otherCoin)._value);
        }
    }
}
