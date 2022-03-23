package controller;

import model.ArrayBag;
import model.Coin;
import view.AppView;

/**
 * Created by ShinD on 2022/03/17.
 */
public class AppController {

    private static final int MENU_ADD = 1;
    private static final int MENU_REMOVE = 2;
    private static final int MENU_SEARCH = 3;
    private static final int MENU_FREQUENCY = 4;
    private static final int MENU_END_OF_RUN = 9;

    private ArrayBag<Coin> _coinBag;

    public ArrayBag<Coin> coinBag() {
        return _coinBag;
    }

    public void setCoinBag(ArrayBag<Coin> newCoinBag) {
        this._coinBag = newCoinBag;
    }

    public void run() {
        AppView.outputLine("<<< 동전 가방 프로그램을 시작합니다 >>>");
        AppView.outputLine("");

        int coinBagSize = AppView.inputCapacityOfCoinBag();
        this.setCoinBag(new ArrayBag<Coin>(coinBagSize));


        int menuNumber = AppView.inputMenuNumber();
        while (menuNumber != MENU_END_OF_RUN){
            switch ( menuNumber ){
                case MENU_ADD:
                    this.addCoin();
                    break;
                case MENU_REMOVE:
                    this.removeCoin();
                    break;
                case MENU_SEARCH:
                    this.searchCoin();
                    break;
                case MENU_FREQUENCY:
                    this.frequencyCoin();
                    break;
                default:
                    this.undefinedMenuNumber(menuNumber);
            }
            menuNumber = AppView.inputMenuNumber();
        }

        this.showStatistics();
        AppView.outputLine("<<< 동전 가방 프로그램을 종료합니다 >>>");

    }


    /**
     * 동전을 가방에 넣는 작업을 제어한다.
     */
    private void addCoin() {
        if( this.coinBag().isFull() ){
            AppView.outputLine("- 동전 가방이 꽉 차서 동전을 넣을 수 없습니다");
        }
        else {
            int codeValue = AppView.inputCoinValue();
            if(this.coinBag().add(new Coin(codeValue))){
                AppView.outputLine("- 주어진 값을 갖는 동전을 가방에 성공적으로 넣었습니다.");
            }else {
                AppView.outputLine("- 주어진 값을 갖는 동전을 가방에 넣는데 실패하였습니다.");
            }
        }
    }


    /**
     * 동전을 가방에서 제거하는 작업을 제어한다.
     */
    private void removeCoin() {
        int coinValue = AppView.inputCoinValue();
        if(! this.coinBag().remove(new Coin(coinValue))){
            AppView.outputLine("- 주어진 값을 갖는 동전은 가방 안에 존재하지 않습니다.");
        }else{
            AppView.outputLine("- 주어진 값을 갖는 동전 하나가 가방에서 정상적으로 삭제되었습니다.");
        }
    }

    /**
     * 동전을 가방에서 찾는 작업을 제어한다.
     */
    private void searchCoin() {
        int coinValue = AppView.inputCoinValue();
        if (this.coinBag().doesContain(new Coin(coinValue))){
            AppView.outputLine("- 주어진 값을 갖는 동전이 가방 안에 존재합니다.");
        }else {
            AppView.outputLine("- 주어진 값을 갖는 동전은 가방 안에 존재하지 않습니다.");
        }
    }

    /**
     * 동전의 빈도수를 구하는 작업을 제어한다
     */
    private void frequencyCoin() {
        int coinValue = AppView.inputCoinValue();
        int frequency = this.coinBag().frequencyOf(new Coin(coinValue));
        AppView.outputLine("- 주어진 값을 갖는 동전의 개수는 " + frequency + "개 입니다.");
    }

    /**
     * 잘못된 번호를 입력받았을 경우에 대한 처리를 담당한다.
     * @param menuNumber 입력받은 번호
     */
    private void undefinedMenuNumber(int menuNumber) {
        AppView.outputLine("- 선택된 메뉴 번호 " + menuNumber+ " 는 잘못된 번호입니다.");
    }


    /**
     * 가방 속 상태를 보여준다
     */
    private void showStatistics() {
        AppView.outputLine("가방에 들어 있는 동전의 개수: " + this.coinBag().size());
        AppView.outputLine("동전 중 가장 큰 값: " + this.maxCoinValue());
        AppView.outputLine("모든 동전 값의 합: " + this.sumOfCoinValue());
        AppView.outputLine("");
    }

    /**
     * 가방속 동전의 합을 구한다
     * @return 동전의 총 합
     */
    private int sumOfCoinValue() {
        int sum = 0;
        for (int i = 0; i < this.coinBag().size(); i++) {
            sum += this.coinBag().elementAt(i).value();
        }
        return sum;
    }

    /**
     * 가방속 가장 값이 큰 동전의 값을 구한다
     * @return 가장 큰 동전의 값
     */
    private int maxCoinValue() {
        int maxValue = 0;
        for (int i = 0; i < this.coinBag().size(); i++) {
            if(maxValue < this.coinBag().elementAt(i).value()){
                maxValue = this.coinBag().elementAt(i).value();
            }
        }

        return maxValue;
    }
}
