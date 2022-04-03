package controller;

import model.ArrayList;
import model.MainMenu;
import model.Student;
import view.AppView;

/**
 * Created by ShinD on 2022/04/01.
 */
public class AppController {

    //Constants
    private static final int LIST_CAPACITY =5;

    //Instance Variables
    private ArrayList<Student> _list;

    public ArrayList<Student> list() {
        return this._list;
    }

    public void setList(ArrayList<Student> newList) {
        this._list = newList;
    }
    public AppController(){
        this.setList(new ArrayList<Student>(AppController.LIST_CAPACITY));
    }



    private void showMenu(){
        AppView.outputLine("> 해야 할 작업의 번호를 선택해야 합니다: ");
        AppView.outputLine("  DoesContain=1, ElementAt=2, First=3, Last=4, OrderOf=5, ");
        AppView.outputLine("  AddTo=6, AddToFirst=7, AddToLast=8, Add=9,  ");
        AppView.outputLine("  RemoveFrom=10, RemoveFirst=11, RemoveLast-12, RemoveAny=13, ReplaceAt=14, EndOfRun=99");
        AppView.output("? 작업 번호를 입력하시오: ");
    }



    private MainMenu selectMenu(){
        AppView.outputLine("");
        this.showList();
        this.showMenu();
        try {
            int selectedMenuNumber = AppView.inputInteger();
            MainMenu selectMenuValue = MainMenu.value(selectedMenuNumber);

            if(selectMenuValue == MainMenu.Error) {
                AppView.outputLine("!오류: 작업 선택이 잘못되었습니다. (잘못된 메뉴 번호: " + selectedMenuNumber +")");
            }
            return selectMenuValue;
        }catch (NumberFormatException e){
            AppView.outputLine("!오류: 입력된 메뉴 번호가 정수 숫자가 아닙니다.");
            return MainMenu.Error;
        }
    }



    public void run(){
        AppView.outputLine("<<<< 리스트 기능 확인 프로그램을 시작합니다 >>>>");

        MainMenu selectedMenuValue = this.selectMenu();

        while (selectedMenuValue != MainMenu.EndOfRun) {
            switch (selectedMenuValue){
                case DoesContain :
                    this.doesContain();
                    break;
                case ElementAt :
                    this.elementAt();
                    break;
                case First :
                    this.first();
                    break;
                case Last :
                    this.last();
                    break;
                case OrderOf :
                    this.orderOf();
                    break;
                case AddTo :
                    this.addTo();
                    break;
                case AddToFirst:
                    this.addToFirst();
                    break;
                case AddToLast:
                    this.addToLast();
                    break;
                case Add:
                    this.add();
                    break;
                case RemoveFrom:
                    this.removeFrom();
                    break;
                case RemoveFirst:
                    this.removeFirst();
                    break;
                case RemoveLast:
                    this.removeLast();
                    break;
                case RemoveAny:
                    this.removeAny();
                    break;
                case ReplaceAt:
                    this.replaceAt();
                    break;
                default:
                    break;
            }
            selectedMenuValue = this.selectMenu();
        }
        this.showStatistics();

        AppView.outputLine("");
        AppView.outputLine("<<< 리스트 기능 확인 프로그램을 종료합니다 >>>");

    }


    private void doesContain() {
        AppView.outputLine("");
        AppView.outputLine("! DoesContain 작업을 실행합니다.");
    }

    private void elementAt() {
        AppView.outputLine("");
        AppView.outputLine("! elementAt 작업을 실행합니다.");
    }

    private void first() {
        AppView.outputLine("");
        AppView.outputLine("! first 작업을 실행합니다.");
    }

    private void last() {
        AppView.outputLine("");
        AppView.outputLine("! last 작업을 실행합니다.");
    }

    private void orderOf() {
        AppView.outputLine("");
        AppView.outputLine("! orderOf 작업을 실행합니다.");
    }

    private void addTo() {
        AppView.outputLine("");
        AppView.outputLine("! addTo 작업을 실행합니다.");
    }

    private void addToFirst() {
        AppView.outputLine("");
        AppView.outputLine("! addToFirst 작업을 실행합니다.");
    }

    private void addToLast() {
        AppView.outputLine("");
        AppView.outputLine("! addToLast 작업을 실행합니다.");
    }

    private void add() {
        AppView.outputLine("");
        AppView.outputLine("! add 작업을 실행합니다.");
    }

    private void removeFrom() {
        AppView.outputLine("");
        AppView.outputLine("! removeFrom 작업을 실행합니다.");
    }

    private void removeFirst() {
        AppView.outputLine("");
        AppView.outputLine("! removeFirst 작업을 실행합니다.");
    }

    private void removeLast() {
        AppView.outputLine("");
        AppView.outputLine("! removeLast 작업을 실행합니다.");
    }

    private void removeAny() {
        AppView.outputLine("");
        AppView.outputLine("! removeAny 작업을 실행합니다.");
    }


    private void replaceAt() {
        AppView.outputLine("");
        AppView.outputLine("! replaceAt 작업을 실행합니다.");
    }

    private void showStatistics() {
    }

    private void showList() {
    }


}
