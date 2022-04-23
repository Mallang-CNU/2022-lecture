package model;

/**
 * Created by ShinD on 2022/04/22.
 */
public class Ban extends UnsortedArrayList <Student> {

    /**
     * 점수를 학점으로 변환한다.
     * @param aScore 점수
     * @return 학점
     */
    private static char scoreToGrade(int aScore) {
        if (aScore >= 90) {
            return 'A';
        }
        else if(aScore >= 80) {
            return 'B';
        }
        else if(aScore >= 70) {
            return 'C';
        }
        else if(aScore >= 60) {
            return 'D';
        }
        else return 'F';
    }

    //Constructor
    public Ban() {
        super();
    }
    public Ban(int givenCapacity) {
        super(givenCapacity);
    }

    /**
     * 점수가 가장 낮은 학생을 찾아서 반환한다.
     * @return 점수가 가장 낮은 학생
     */
    public Student lowest() {
        if(this.isEmpty()) {
            return null;
        }
        else {
            return this.lowestRecursively(0, this.size()-1);
        }
    }

    /**
     * 점수가 가장 낮은 학생을 재귀적으로 찾는다.
     * @param left 가장 왼쪽 범위
     * @param right 가장 오른쪽 범위
     * @return 성적이 가장 낮은 학생
     */
    private Student lowestRecursively(int left, int right) {
        if(left == right) return this.elementAt(left);
        else {
            Student lowestFromRights = lowestRecursively(left +1, right);
            if(lowestFromRights.compareTo(this.elementAt(left)) <= 0) {
                return lowestFromRights;
            }
            else {
                return this.elementAt(left);
            }
        }
    }


    /**
     * 점수가 가장 높은 학생을 찾아서 반환한다.
     * @return 점수가 가장 높은 학생
     */
    public Student highest() {
        if(this.isEmpty()) {
            return null;
        }
        else {
            return this.highestRecursively(0, this.size()-1);
        }
    }

    /**
     * 점수가 가장 높은 학생을 재귀적으로 찾아서 반환한다.
     * @param left 가장 왼쪽 범위
     * @param right 가장 오른쪽 범위
     * @return 성적이 가장 낮은 학생
     */
    private Student highestRecursively(int left, int right) {
        if(left == right) return this.elementAt(left);
        else {
            Student highestFromRights = highestRecursively(left +1, right);
            if(highestFromRights.compareTo(this.elementAt(left)) >= 0) {
                return highestFromRights;
            }
            else {
                return this.elementAt(left);
            }
        }
    }

    /**
     * 성적의 합계를 반환한다.
     * @return 성적의 합계
     */
    public int sum() {
        if(this.isEmpty()) {
            return 0;
        }
        else {
            return this.sumOfScoresRecursively(0, this.size() -1);
        }
    }


    /**
     * 성적의 합계를 재귀적으로 반환한다.
     * @param left 성적의 합계를 구할 왼쪽 구간
     * @param right 성적의 합계를 구할 오른쪽 구간
     * @return 주어진 구간의 성적의 합계
     */
    private int sumOfScoresRecursively(int left, int right) {
        int mid = (left + right) / 2;
        if(left == right) return this.elementAt(left).score();
        else {
            int leftSum = this.sumOfScoresRecursively(left, mid);
            int rightSum = this.sumOfScoresRecursively(mid +1, right);
            return (leftSum + rightSum);
        }
    }

    /**
     * 점수의 평균을 반환한다.
     * @return 평균 점수
     */
    public double average() {
        if(this.isEmpty()) return 0;
        else return (((double) this.sum()) / ((double)this.size()));
    }

    /**
     * 점수가 평균 이상인 학생의 수를 출력한다.
     * @return 평균 이상의 점수를 가진 학생 수
     */
    public int numberOfStudentsAboveAverage() {
        double average = this.average();
        int numberOfStudentsAboveAverage = 0;
        Iterator<Student> iterator = this.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if(student.score() >= average) {
                numberOfStudentsAboveAverage++;
            }
        }
        return numberOfStudentsAboveAverage;
    }


    /**
     * 두 순서의 값을 바꾼다
     * @param p 첫번째 순서
     * @param q 두번째 순서
     */
    private void swap(int p, int q) {
        Student temp = this.elementAt(p);
        this.setElementAt(p, this.elementAt(q));
        this.setElementAt(q, temp);
    }


    /**
     * Quick Sort 를 위해 pivot 을 기준으로 구간을 분할한다.
     * @param left
     * @param right
     * @return pivot의 위치
     */
    private int partition(int left, int right) {
        int pivot = left;
        int toRight = left;
        int toLeft = right+1;
        do {
            do {toRight++;} while(this.elementAt(toRight).score() < this.elementAt(pivot).score());
            do {toLeft--;} while(this.elementAt(toLeft).score() > this.elementAt(pivot).score());
            if(toRight < toLeft) {
                this.swap(toRight, toLeft);
            }
        }while(toRight < toLeft);
        this.swap(left, toLeft);
        return toLeft;
    }

    /**
     * 퀵 정렬을 수행한다.
     * @param left 왼쪽 시작 구간
     * @param right 오른쪽 끝 구간
     */
    private void quicksortRecursively(int left, int right) {
        if(left < right) {
            int mid = this.partition(left, right);
            this.quicksortRecursively(left, mid-1);
            this.quicksortRecursively(mid+1, right);
        }
    }

    /**
     * 성적 순으로 정렬한다.
     */
    public void sortByScore() {
        if(this.size() > 1) {
            int maxLoc = 0;
            for(int i = 1; i < this.size(); i++) {
                if(this.elementAt(i).score() > this.elementAt(maxLoc).score()) {
                    maxLoc = i;
                }
            }
            this.swap(maxLoc, this.size()-1);
            this.quicksortRecursively(0, this.size()-2);
        }

    }

    /**
     * 각 학점에 해당하는 학생들을 센다.
     * @return 학생들의 수를 담고있는 GradeCounter 객체
     */
    public GradeCounter countGrades() {
        GradeCounter gradeCounter = new GradeCounter();
        Iterator<Student> iterator = this.iterator();
        while( iterator.hasNext()){
            Student student = iterator.next();
            char grade = Ban.scoreToGrade(student.score());
            gradeCounter.count(grade);
        }
        return gradeCounter;
    }

}