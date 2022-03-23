package main.controller;


import main.Board;
import main.CellLocation;
import main.OrderValidity;
import main.model.MagicSquare;
import main.view.AppView;



/**
 * Created by ShinD on 2022-03-03.
 */
public class AppController {

    //Define Order Range (Min = 3, Max = 99)
    public static final int MIN_ORDER = 3;
    public static final int MAX_ORDER = 99;

    private MagicSquare _magicSquare;

    public AppController() {
        this._magicSquare = new MagicSquare(AppController.MAX_ORDER);
    }

    public void run() {
        AppView.outputLine ("<<< 마방진풀이를시작합니다>>>") ;
        AppView.outputLine ("") ;

        int currentOrder = AppView.inputOrder() ; // 메시지를내보내고차수를입력받음
        OrderValidity currentValidity = OrderValidity.validityOf(currentOrder);


        while (currentValidity != OrderValidity.EndOfRun) { // 차수가음수이면프로그램종료
            if (currentValidity == OrderValidity.Valid ) { // 차수가유효한지검사
                AppView.outputTitleWithOrder (currentOrder) ;
                Board solvedBoard= this._magicSquare.solve (currentOrder) ;// _magicSquare 객체에게주어진차수의마방진을풀도록시킨다.

                // 결과로마방진판을얻는다
                this.showBoard (solvedBoard) ; // 마방진을화면에보여준다
            }
            else {
                this.showOrderValidityErrorMessage (currentValidity) ;
            }
            currentOrder = AppView.inputOrder() ; // 다음마방진을위해차수를입력받음
            currentValidity = OrderValidity.validityOf(currentOrder);
        } // end while
        AppView.outputLine ("");
        AppView.outputLine ("<<< 마방진풀이를종료합니다>>>") ;




    }


    private boolean isInvalid(OrderValidity currentValidity) {
        return currentValidity != OrderValidity.Valid;
    }


    private boolean isNegative(OrderValidity currentValidity) {
        return currentValidity == OrderValidity.EndOfRun;
    }

    private void showBoard(Board board) {

        CellLocation currentLoc = new CellLocation() ;

        this.showTitleForColumnIndexes (board.order()) ;

        for ( int row = 0 ; row < board.order(); row++ ) {
            // board.order()를 조건문에 넣으면, 매 반복때마다 board.order을 수행하여 성능이 안좋아지기에(매우 매우 낮은 차이), 변수를 따로 빼서 해주었습니다.
            AppView.outputRowNumber (row) ;
            for ( int col = 0 ; col < board.order(); col++ ) {
                currentLoc.setRow (row) ;
                currentLoc.setCol (col) ;
                AppView.outputCellValue (board.cellValue(currentLoc)) ;
            }
            AppView.outputLine("") ;
        }

    }


    private void showTitleForColumnIndexes(int order) {
        AppView.output("      ") ; // 빈칸6 개
        for (int col = 0 ; col < order ; col++ ) {
            AppView.output( String.format(" [%3d]", col) ) ;
        }
        AppView.outputLine("") ;
    }


    private void showOrderValidityErrorMessage(OrderValidity orderValidity) {
        switch ( orderValidity ) {
            case TooSmall:
                AppView.outputLine ("[오류]차수가 너무 작습니다. " + AppController.MIN_ORDER + " 보다 크거나 같아야 합니다.") ;
                break ;
            case TooLarge:
                AppView.outputLine ("[오류] 차수가 너무 큽니다. " + AppController.MAX_ORDER + " 보다 작거나 같아야 합니다.") ;
                break ;
            case NotOddNumber:
                AppView.outputLine ("[오류]차수가 짝수입니다. 홀수이어야 합니다.") ;
                break ;
            default:
                break ;
        }
    }
}
