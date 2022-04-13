package main.model;


import main.Board;
import main.CellLocation;
import main.OrderValidity;

/**
 * Created by ShinD on 2022-03-03.
 */
public class MagicSquare {

    private static final int DEFAULT_MAX_ORDER = 9;
    private int _maxOrder;



    public int maxOrder() {
        return _maxOrder;
    }

    private void setMaxOrder(int newMaxOrder) {
        this._maxOrder = newMaxOrder;
    }

    public MagicSquare() {
        this.setMaxOrder(MagicSquare.DEFAULT_MAX_ORDER);
    }

    public MagicSquare(int givenMaxOrder){
        this.setMaxOrder (givenMaxOrder);
    }

    public Board solve(int anOrder) {//차수를 받아온다.
        if ( OrderValidity.validityOf(anOrder) != OrderValidity.Valid ) {//차수(Order)가 유효하지 않다면 계산하지 않는다.
            return null ;
        }

        else {
            Board board = new Board (anOrder) ;// 차수와 함께 Board 객체 생성자를 call 하여, Board 객체를 생성한다.

            CellLocation currentLoc = new CellLocation (0, anOrder/2) ;// 출발위치(보드의 맨윗줄 한가운데)를 현재의 위치로 설정한다.
            CellLocation nextLoc = new CellLocation();// 다음위치를 설정할 CellLocation 객체를 생성해둔다.

            board.setCellValue(currentLoc, 1) ;// 보드의<출발위치>에1 을채운다.

            int lastValue = anOrder * anOrder ;//차수 * 차수 = 마방진의 전체 칸의 수 = lastValue

            for ( int cellValue = 2 ; cellValue <= lastValue ; cellValue++ ) {

                // 단계1: <현재위치>로부터<다음위치>인“오른쪽위” 위치를계산한다
                final int currentRow = currentLoc.row();//현재 Row를 받아온다
                final int currentCol = currentLoc.col();//현재 Col을 받아온다

                /*
                  row 는 위칸으로 갈수록 인덱스가 작아진다.
                  즉 위로 한 칸 이동하기 위해서는 row - 1을 해주어야 한다.
                  currentRow - 1 이 -1인 경우에는, 범위를 초과하였으므로 마방진의 맨 아래 (anOrder - 1)로 이동해야 한다.
                 */
                int nextRow = (currentRow - 1 == -1) ? anOrder -1 : currentRow - 1;

                /*
                  col 은 오른쪽으로 이동하기 위해서는 인덱스를 키워주어야 한다.
                  즉 오른쪽으로 한 칸 이동하기 위해서는 col + 1을 해주어야 한다.
                  currentCol + 1 이 anOrder 인 경우에는, 범위를 초과하였으므로 마방진의 맨 왼쪽(0)으로 이동해야 한다.
                 */
                int nextCol = (currentCol + 1 == anOrder) ? 0 : currentCol + 1;


                nextLoc.setRow(nextRow);//<다음위치>의 Row 를 설정한다.
                nextLoc.setCol(nextCol);//<다음위치>의 Col 을 설정한다.




                // 단계2: <다음위치> 가채워져있으면
                // <다음위치>를 <현재위치>의 바로 한 줄 아래칸 위치로 수정한다.
                if( ! board.cellIsEmpty (nextLoc) ) {


                    /*
                      row 는 아래칸으로 갈수록 인덱스가 커진다.
                      즉 아래로 한 칸 이동하기 위해서는 row + 1을 해주어야 한다.
                      currentRow + 1 이 anOrder 인 경우에는, 범위를 초과하였으므로 마방진의 맨 위래(0)로 이동해야 한다.
                    */
                    nextRow = (currentRow + 1 == anOrder) ? 0 : currentRow + 1;


                    /*
                    바로 한 줄 아래칸 위치이므로 Col의 변화는 없다.
                     */
                    nextCol = currentCol;



                    nextLoc.setRow(nextRow);//<다음위치>의 Row 를 설정한다.
                    nextLoc.setCol(nextCol);//<다음위치>의 Col 을 설정한다.
                }


                // 단계3: <다음위치>를새로운<현재위치>로한다.
                currentLoc.setRow (nextLoc.row()) ;
                currentLoc.setCol (nextLoc.col()) ;


                // 단계4: 새로운<현재위치>에 cellValue 값을넣는다.
                board.setCellValue (currentLoc, cellValue) ;
            }
            return board ;
        }
    }
}
