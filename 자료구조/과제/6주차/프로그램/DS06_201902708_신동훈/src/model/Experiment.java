package model;

import java.util.Random;

/**
 * Created by ShinD on 2022/04/13.
 */
public class Experiment {
    // Constants
    private static final int DEFAULT_NUMBER_OF_ITERATION = 5 ;
    private static final int DEFAULT_FIRST_SIZE = 10000 ; // 첫 번째 실험 데이터 크기 private static final int DEFAULT_SIZE_INCREMENT = 10000 ; // 실험 데이터 크기 증가량
    private static final int DEFAULT_SIZE_INCREMENT = 10000 ; // 실험 데이터 크기 증가량


    // Private instances
    private int _numberOfIteration;
    private int _firstSize;
    private int _sizeIncrement;
    private Coin [] _data;
    private MeasuredResult [] _measuredResults ;


    // Getter/Setter
    public int numberOfIteration() {
        return _numberOfIteration;
    }
    public void setNumberOfIteration(int newNumberOfIteration) {
        this._numberOfIteration = newNumberOfIteration;
    }

    public int firstSize() {
        return _firstSize;
    }

    public void setFirstSize(int newFirstSize) {
        this._firstSize = newFirstSize;
    }

    public int sizeIncrement() {
        return _sizeIncrement;
    }

    public void setSizeIncrement(int newSizeIncrement) {
        this._sizeIncrement = newSizeIncrement;
    }

    public Coin[] data() {
        return _data;
    }

    public void setData(Coin[] newData) {
        this._data = newData;
    }
    public void setMeasuredResults(MeasuredResult[] newMeasuredResults) {
        this._measuredResults = newMeasuredResults;
    }


    //Constructor
    public Experiment(){
        this(DEFAULT_NUMBER_OF_ITERATION, DEFAULT_FIRST_SIZE, DEFAULT_SIZE_INCREMENT);
    }


    public Experiment(int givenNumberOfIteration, int givenFirstSize, int givenSizeIncrement){
        this.setNumberOfIteration(givenNumberOfIteration) ;
        this.setFirstSize(givenFirstSize) ;
        this.setSizeIncrement(givenSizeIncrement) ;
        this.setData (new Coin[this.maxSize()]) ; // 실험 데이터를 담을 배열 공간 확보
        this.setMeasuredResults (new MeasuredResult[this.numberOfIteration()]) ;// 실험 결과를 저장할 배열 공간 확보
    }






    /**
     * 실험 데이터의 최대 크기를 계산하여 돌려준다.
     * @return this.firstSize() + this.sizeIncrement() * (this.numberOfIteration() - 1)
     */
    public int maxSize(){
        return this.firstSize() + this.sizeIncrement() * (this.numberOfIteration() - 1);
    }



    /**
     * 성능 측정에 필요한 데이터를 생성한다.
     * 난수를 사용하며, 생성한 난수 값을 갖는 Coin 객체를 생성하여 저장한다.
     */
    public void generateData() {
        Random random = new Random() ;
        for (int i = 0 ; i < this.maxSize() ; i++ ) {
            int randomCoinValue = random.nextInt(this.maxSize()) ;
            this.data()[i] = new Coin(randomCoinValue);
        }
    }


    /**
     * 성능 측정 결과를 돌려준다.
     * @return 성능 측정 결과
     */
    public MeasuredResult[] measuredResults() {
        return _measuredResults;
    }



    /**
     * SortedArrayList 의 성능을 측정한다.
     * 이후 결과를 MeasuredResult[]에 담아 저장한다.
     */
    public void measureForSortedArrayList() {

        @SuppressWarnings("unused")
        Coin maxCoin ;//가장 값이 큰 코인을 저장할 변수

        long durationForAdd, durationForMax ;//add 와 max 에 소모된 시간을 저장할 객체
        long start, stop ;//시작 시간과 끝 시간을 저장할 객체

        int dataSize = this.firstSize() ;

        for ( int iteration = 0 ; iteration < this.numberOfIteration() ; iteration ++ ) {

            SortedArrayList<Coin>listOfCoins = new SortedArrayList<Coin>(dataSize) ;
            durationForAdd = 0 ;
            durationForMax = 0 ;

            for ( int i = 0 ; i < dataSize ; i++ ) {

                start = System.nanoTime() ;
                listOfCoins.add (this.data()[i]) ;
                stop = System.nanoTime() ;
                durationForAdd += (stop -start) ;


                start = System.nanoTime() ;
                maxCoin = listOfCoins.max() ;
                stop = System.nanoTime() ;
                durationForMax+= (stop -start) ;
            }
            this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax) ;
            dataSize += this.sizeIncrement() ;
        }
    }


    /**
     * UnsortedArrayList 의 성능을 측정한다.
     * 이후 결과를 MeasuredResult[]에 담아 저장한다.
     */
    public void measureForUnsortedArrayList() {

        Coin maxCoin ;

        long durationForAdd, durationForMax ;
        long start, stop ;

        int dataSize = this.firstSize() ;
        for ( int iteration = 0 ; iteration < this.numberOfIteration() ; iteration ++ ) {
            UnsortedArrayList<Coin>listOfCoins = new UnsortedArrayList<Coin>(dataSize) ;
            durationForAdd = 0 ;
            durationForMax = 0 ;
            for ( int i = 0 ; i < dataSize ; i++ ) {
                start = System.nanoTime() ;
                listOfCoins.add (this.data()[i]) ;
                stop = System.nanoTime() ;
                durationForAdd += (stop -start) ;


                start = System.nanoTime() ;
                maxCoin = listOfCoins.max() ;
                stop = System.nanoTime() ;
                durationForMax+= (stop -start) ;
            }
            this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax) ;
            dataSize += this.sizeIncrement() ;
        }
    }


    /**
     * UnsortedLinkedList 의 성능을 측정한다.
     * 이후 결과를 MeasuredResult[]에 담아 저장한다.
     */
    public void measureForUnsortedLinkedList() {
        Coin maxCoin ;
        long durationForAdd, durationForMax ;
        long start, stop ;
        int dataSize = this.firstSize() ;
        for ( int iteration = 0 ; iteration < this.numberOfIteration() ; iteration ++ ) {
            UnsortedLinkedList<Coin>listOfCoins = new UnsortedLinkedList<Coin>() ;
            durationForAdd = 0 ;
            durationForMax = 0 ;
            for ( int i = 0 ; i < dataSize ; i++ ) {
                start = System.nanoTime() ;
                listOfCoins.add (this.data()[i]) ;
                stop = System.nanoTime() ;
                durationForAdd += (stop -start) ;


                start = System.nanoTime() ;
                maxCoin = listOfCoins.max() ;
                stop = System.nanoTime() ;
                durationForMax+= (stop -start) ;
            }
            this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax) ;
            dataSize += this.sizeIncrement() ;
        }
    }

    /**
     * SortedLinkedList 의 성능을 측정한다.
     * 이후 결과를 MeasuredResult[]에 담아 저장한다.
     */
    public void measureForSortedLinkedList() {
        Coin maxCoin ;
        long durationForAdd, durationForMax ;
        long start, stop ;
        int dataSize = this.firstSize() ;
        for ( int iteration = 0 ; iteration < this.numberOfIteration() ; iteration ++ ) {
            SortedLinkedList<Coin>listOfCoins = new SortedLinkedList<Coin>() ;
            durationForAdd = 0 ;
            durationForMax = 0 ;
            for ( int i = 0 ; i < dataSize ; i++ ) {
                start = System.nanoTime() ;
                listOfCoins.add (this.data()[i]) ;
                stop = System.nanoTime() ;
                durationForAdd += (stop -start) ;


                start = System.nanoTime() ;
                maxCoin = listOfCoins.max() ;
                stop = System.nanoTime() ;
                durationForMax+= (stop -start) ;
            }
            this.measuredResults()[iteration] = new MeasuredResult(dataSize, durationForAdd, durationForMax) ;
            dataSize += this.sizeIncrement() ;
        }
    }
}
