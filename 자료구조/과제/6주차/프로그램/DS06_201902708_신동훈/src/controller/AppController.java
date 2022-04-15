package controller;

import model.Experiment;
import model.MeasuredResult;
import view.AppView;

/**
 * Created by ShinD on 2022/04/13.
 */
public class AppController {

    private Experiment _experiment;


    // Getter/Setter
    private Experiment experiment() {
        return _experiment;
    }
    private void setExperiment(Experiment newExperiment) {
        this._experiment = newExperiment;
    }


    //Constructor
    public AppController() {
        this.setExperiment(new Experiment());
        this.experiment().generateData();
    }

    public void run() {
        AppView.outputLine ("<<<리스트 성능 측정 프로그램을 시작합니다.>>>") ;
        AppView.outputLine ("! 리스트의 구현에 따른 시간의 차이를 알아봅니다: (단위: Micro Second)") ;


        AppView.outputLine ("") ;
        AppView.outputLine ("<Unsorted Array List>") ;
        this.experiment().measureForUnsortedArrayList() ; //실험 객체에게 "SortedArrayList" 에 대한 성능측정을 실행하게한다.
        this.showExperimentResults() ;//실험 결과를 출력한다.

        AppView.outputLine ("") ;
        AppView.outputLine ("<Sorted Array List>") ;
        this.experiment().measureForSortedArrayList() ; //실험 객체에게 "SortedArrayList" 에 대한 성능측정을 실행하게한다.
        this.showExperimentResults() ;//실험 결과를 출력한다.

        AppView.outputLine ("") ;
        AppView.outputLine ("<Unsorted Linked List>") ;
        this.experiment().measureForUnsortedLinkedList() ; //실험 객체에게 "SortedArrayList" 에 대한 성능측정을 실행하게한다.
        this.showExperimentResults() ;//실험 결과를 출력한다.

        AppView.outputLine ("") ;
        AppView.outputLine ("<Sorted Linked List>") ;
        this.experiment().measureForSortedLinkedList() ; //실험 객체에게 "SortedArrayList" 에 대한 성능측정을 실행하게한다.
        this.showExperimentResults() ;//실험 결과를 출력한다.
        AppView.outputLine("<<<리스트 성능 측정 프로그램을 종료합니다.>>>") ;
    }

    private void showExperimentResults() {
        MeasuredResult[] results = this.experiment().measuredResults() ;
        for ( int i = 0 ; i < this.experiment().numberOfIteration () ; i++ ) {
            AppView.outputResults(
                    results[i].size(),
                    results[i].durationForAdd() / 1000, // Nano 를 Micro 로 변환
                    results[i].durationForMax() / 1000 // Nano 를 Micro 로 변환
            );

        }
    }
}
