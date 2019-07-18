package string;

/**
 * Created by xu on 07/09/2017.
 */
//551. Student Attendance Record I
//A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent)
// or more than two CONTINUOUS 'L' (late).


public class StudentAttendanceRecord_1 {

    public boolean checkRecord(String s) {
        return !s.matches(".*LLL.*|.*A.*A.*");
    }

    public boolean checkRecord_2(String s) {
        if (s.contains("LLL") || s.indexOf("A") != s.lastIndexOf("A"))
            return false;
        return true;
    }

}
