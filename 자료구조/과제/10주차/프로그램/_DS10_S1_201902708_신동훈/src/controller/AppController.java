package controller;


import model.CircularArrayQueue;
import model.Iterator;
import model.Queue;
import view.AppView;

/**
 * Created by ShinD on 2022-05-13.
 */
public class AppController {
    private static final int QUEUE_CAPACITY = 10;

    private Queue<Character> _queue;

    private int _inputChars;//입력된 문자의 개수
    private int _addedChars;//삽입된 문자의 개수
    private int _ignoredChars;//무시된 문자의 개수


    private Queue<Character> queue() {
        return this._queue;
    }
    private void setQueue(Queue<Character> newQueue) {
        this._queue = newQueue;
    }

    private int inputChars() {
        return this._inputChars;
    }
    private void setInputChars(int newInputChars) {
        this._inputChars = newInputChars;
    }

    private int addedChars() {
        return this._addedChars;
    }
    private void setAddedChars(int newPushedChars) {
        this._addedChars = newPushedChars;
    }

    private int ignoredChars() {
        return this._ignoredChars;
    }
    private void setIgnoredChars(int newIgnoredChars) {
        this._ignoredChars = newIgnoredChars;
    }




    public AppController() {
        this.setQueue(new CircularArrayQueue<Character>(AppController.QUEUE_CAPACITY));
        this.setInputChars(0);
        this.setAddedChars(0);
        this.setIgnoredChars(0);
    }


    //== 횟수 계산 ==//
    private void countInputChar() {
        this.setInputChars(this.inputChars() + 1);
    }
    private void countIgnoredChar() {
        this.setIgnoredChars(this.ignoredChars() + 1);
    }
    private void countAddedChar() {
        this.setAddedChars(this.addedChars() + 1);
    }


    //== 큐 수행 관련 ==//
    private void addToQueue(Character anElement) {
        if ( this.queue().isFull() ) {
            AppView.outputLine("[EnQ.Empty] 큐이 꽉 차서, 더 이상 넣을 수 없습니다.") ;
        }

        if ( this.queue().enQueue(anElement) ) {
            AppView.outputLine("[Push] 삽입된 원소는 '" + anElement + "' 입니다.") ;
        }
        else {
            AppView.outputLine ("(오류) 큐에 넣는 동안에 오류가 발생하였습니다.") ;
        }
    }


    private void removeOne() {
        if (this.queue().isEmpty() ) {
            AppView.outputLine("[DeQ.Empty] 큐에 삭제할 원소가 없습니다.") ;
        }
        else {
            Character removedChar = this.queue().deQueue() ;
            if (removedChar == null) {
                AppView.outputLine("(오류) 큐에서 삭제하는 동안에 오류가 발생하였습니다.") ;
            }  else {
                AppView.outputLine("[DeQ] 삭제된 원소는 '" + removedChar.toString() + "' 입니다.") ;
            }
        }
    }

    private void removeN(int numberOfCharsToBeRemoved) {
        if (numberOfCharsToBeRemoved == 0) {
            AppView.outputLine("[DeQs] 삭제할 원소의 개수가 0 개 입니다.");
        }

        while (!this.queue().isEmpty() && numberOfCharsToBeRemoved >0){
            AppView.outputLine("[DeQs] 삭제된 원소는 '%c' 입니다".formatted(this.queue().deQueue()));
            numberOfCharsToBeRemoved --;
        }

        if(numberOfCharsToBeRemoved > 0 ){
            AppView.outputLine("[DeQs.Empty] 큐에 더 이상 삭제할 원소가 없습니다.");
        }
    }

    private void quitQueueProcessing() {
        AppView.outputLine("");
        AppView.outputLine("<큐을 비우고 사용을 종료합니다>");
        this.showAllFromFront();
        this.removeN(this.queue().size());
    }





    //== 출력 관련 ==//

    /**
     * 큐의 모든 원소를 Front 부터 Rear 까지 출력한다.
     * Iterator 를 사용한다.
     */
    private void showAllFromFront() {
        AppView.output ("[Queue] <Front> ") ;

        Iterator<Character> queueIterator = this.queue().iterator();
        while (queueIterator.hasNext()) {
            Character element=queueIterator.next();
            AppView.output (element.toString() + " " ) ;
        }
        AppView.outputLine ("<Rear>") ;
    }

    /**
     * 큐의 모든 원소를 Rear 부터 Front 까지 출력한다.
     * elementAt() 을 사용한다.
     */
    private void showAllFromRear() {
        AppView.output ("[Queue] <Rear> ") ;
        for (int order = this.queue().size()-1; order >= 0 ; order-- ) {
            AppView.output (this.queue().elementAt(order).toString() + " " ) ;
        }
        AppView.outputLine ("<Front>") ;
    }

    /**
     * Queue 객체의 front() 을 이용하여 Front 원소를 출력한다.
     * 큐가 비어 있으면 front 원소 대신 비어있다는 메세지를 출력한다.
     */
    private void showFrontElement(){
        if(this.queue().size() > 0) {
            AppView.outputLine("[Front] 큐의 맨 앞 원소는 '" + this.queue().front().toString() + "' 입니다.");
        } else {
            AppView.outputLine("[Front.Empty] 큐가 비어서 맨 앞 원소가 존재하지 않습니다.");
        }
    }

    /**
     * Queue 객체의 rear() 을 이용하여 rear 원소를 출력한다.
     * 큐가 비어 있으면 rear 원소 대신 비어있다는 메세지를 출력한다.
     */
    private void showRearElement(){
        if(this.queue().size() > 0) {
            AppView.outputLine("[Rear] 큐의 맨 뒤 원소는 '" + this.queue().rear().toString() + "' 입니다.");
        } else {
            AppView.outputLine("[Rear.Empty] 큐가 비어서 맨 뒤 원소가 존재하지 않습니다.");
        }
    }

    /**
     * Queue 객체의 size() 을 이용하여 원소의 개수를 출력한다.
     */
    private void showQueueSize() {
        AppView.outputLine("[Size] 큐에는 현재 " + this.queue().size() + "개의 원소가 있습니다.");
    }

    private void showStatistics() {
        AppView.outputLine("");
        AppView.outputLine("<큐 사용 통계>");
        AppView.outputLine("- 입력된 문자는 " + this.inputChars() + " 개 입니다.");
        AppView.outputLine("- 정상 처리된 문자는 " + (this.inputChars() - this.ignoredChars()) + " 개 입니다.");
        AppView.outputLine("- 무시된 문자는 " + this.ignoredChars() + " 개 입니다.");
        AppView.outputLine("- 삽입된 문자는 " + this.addedChars() + " 개 입니다.");
    }

    // 입력 관련
    private char inputChar() {
        AppView.output("? 문자를 입력하시오: ");
        return AppView.inputChar();
    }






    public void run(){
        AppView.outputLine("<<< 큐 기능 확인 프로그램을 시작합니다 >>>");
        AppView.outputLine();

        char input = this.inputChar();
        while(input != '!'){

            this.countInputChar();

            if(Character.isAlphabetic(input)){
                this.addToQueue(input);
                this.countAddedChar();
            }

            else if(Character.isDigit(input)){
                this.removeN(Character.getNumericValue(input));
            }

            else if(input == '-'){
                this.removeOne();
            }

            else if (input== '#'){
                this.showQueueSize();
            }

            else if (input == '/'){
                this.showAllFromFront();
            }

            else if (input == '\\'){
                this.showAllFromRear();
            }

            else if (input == '^'){
                this.showFrontElement();
            }

            else if (input == '>'){
                this.showRearElement();
            }

            else if (input == '<'){
                this.showFrontElement();
            }

            else{
                AppView.outputLine("[Ignore] 의미 없는 문자가 입력되었습니다.");
                this.countIgnoredChar();
            }

            input = this.inputChar();
        }



        this.quitQueueProcessing();
        this.showStatistics();

        AppView.outputLine("");
        AppView.outputLine("<<< 큐 기능 확인 프로그램을 종료합니다 >>>");
    }
}