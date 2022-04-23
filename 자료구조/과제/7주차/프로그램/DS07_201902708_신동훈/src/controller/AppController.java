package controller;

import model.Ban;
import model.GradeCounter;
import model.Iterator;
import model.Student;
import view.AppView;

/**
 * Created by ShinD on 2022/04/22.
 */
public class AppController {
    //== 상수 ==//
    private static final int VALID_MAX_SCORE = 100;
    private static final int VALID_MIN_SCORE = 0;

    private static final int BAN_CAPACITY = 10;

    //== 비공개 인스턴스 변수들 ==//
    private Ban _ban;
    private GradeCounter _gradeCounter;


    /**
     * 입력한 점수가 범위를 벗어나지 않는지 확인
     * @param aSocre 입력받은 점수
     * @return 범위를 벗어났으면 false
     */
    private static boolean scoreIsValid(int aSocre) {
        return ( aSocre >= AppController.VALID_MIN_SCORE &&
                aSocre <= AppController.VALID_MAX_SCORE);
    }

    /**
     * 점수를 입력받는다
     * @return 입력받은 점수를 가진 Student 객체
     */
    private static Student inputStudent() {
        int score = AppView.inputScore();
        while(! AppController.scoreIsValid(score)) {
            AppView.outputLine("[오류] " +
                    AppController.VALID_MIN_SCORE + " 보다 작거나 " +
                    AppController.VALID_MAX_SCORE + " 보다 커서, 정상적인 점수가 아닙니다.");
            score = AppView.inputScore();
        }
        Student student = new Student();
        student.setScore(score);
        return student;
    }


    //== Getter/Setter ==//
    private Ban ban() {
        return this._ban;
    }
    private void setBan (Ban newBan) {
        this._ban = newBan;
    }
    private GradeCounter gradeCounter() {
        return this._gradeCounter;
    }
    private void setGradeCounter (GradeCounter newGradeCounter) {
        this._gradeCounter = newGradeCounter;
    }

    //== Constructor ==//
    public AppController() {
    }


    /**
     * 점수를 ban 객체에 저장한다.
     */
    private void inputAndStoreStudent() {
        AppView.outputLine("");
        boolean storingAStudentWasSuccessful = true;
        while(storingAStudentWasSuccessful && AppView.doesContinueToInputStudent()) {
            Student student = AppController.inputStudent();
            if(! this.ban().add(student)) {
                AppView.outputLine("(경고) 입력에 오류가 있습니다. 학급에 더이상 학생을 넣을 공간이 없습니다.");
                storingAStudentWasSuccessful = false;
            }
        }
        AppView.outputLine("! 성적 입력을 마칩니다.");
    }

    /**
     * 성적 통계를 출력한다.
     * 학생수 , 최고 점수, 최저 점수, 평균 점수. 평균이상인 학생수
     */
    private void showStatistics() {
        AppView.outputLine("");
        AppView.outputLine("[학급 성적 통계]");

        AppView.outputNumberOfStudents(this.ban().size());
        AppView.outputHighestScore(this.ban().highest().score());
        AppView.outputLowestScore(this.ban().lowest().score());
        AppView.outputAverageScore(this.ban().average());
        AppView.outputNumberOfStudentsAboveAverage(this.ban().numberOfStudentsAboveAverage());
    }


    /**
     * 학점별 학생수 출력
     */
    private void showGradeCounts() {
        AppView.outputLine("");
        AppView.outputLine("[학점별 학생수]");

        this.setGradeCounter(this.ban().countGrades());
        AppView.outputNumberOfStudentsForGrade('A', this.gradeCounter().numberOfA());
        AppView.outputNumberOfStudentsForGrade('B', this.gradeCounter().numberOfB());
        AppView.outputNumberOfStudentsForGrade('C', this.gradeCounter().numberOfC());
        AppView.outputNumberOfStudentsForGrade('D', this.gradeCounter().numberOfD());
        AppView.outputNumberOfStudentsForGrade('F', this.gradeCounter().numberOfF());
    }


    /**
     * 성적순 목록 출력
     */
    private void showStudentsSortedByScore() {
        AppView.outputLine("");
        AppView.outputLine("[학생들의 성적순 목록]");

        this.ban().sortByScore();

        Iterator<Student> iterator = this.ban().iterator();
        Student student = null;
        while (iterator.hasNext()) {
            student = iterator.next();
            AppView.outputScore(student.score());
        }
    }



    /**
     * 프로그램을 동작시킨다.
     */
    public void run() {
        AppView.outputLine("");
        AppView.outputLine("<<<학급 성적 처리를 시작합니다 >>>");

        this.setBan(new Ban(AppController.BAN_CAPACITY));
        this.inputAndStoreStudent();
        if(this.ban().isEmpty()) {
            AppView.outputLine("");
            AppView.outputLine("(경고) 입력된 성적이 없습니다.");
        }
        else {
            this.showStatistics();
            this.showGradeCounts();
            this.showStudentsSortedByScore();
        }
        AppView.outputLine("");
        AppView.outputLine("<<< 학급 성적 처리를 종료합니다. >>>");

    }

}

