package model;

/**
 * Created by ShinD on 2022/04/13.
 */
public class MeasuredResult {

    //Instance variables
    private int _size;
    private long _durationForAdd;
    private long _durationForMax;


    // Getter/Setter
    public int size() {
        return _size;
    }
    public void setSize(int newSize) {
        this._size = newSize;
    }
    public long durationForAdd() {
        return _durationForAdd;
    }
    public void setDurationForAdd(long newDurationForAdd) {
        this._durationForAdd = newDurationForAdd;
    }
    public long durationForMax() {
        return _durationForMax;
    }
    public void setDurationForMax(long newDurationForMax) {
        this._durationForMax = newDurationForMax;
    }


    //Constructors
    public MeasuredResult(){
        this(0,0,0);
    }
    public MeasuredResult(int givenSize, long givenDurationForAdd, long givenDurationForMax) {
        this.setSize(givenSize) ;
        this.setDurationForAdd (givenDurationForAdd) ;
        this.setDurationForMax (givenDurationForMax); ;

    }

}
