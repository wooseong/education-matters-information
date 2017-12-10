public class DBconf {
	public static final String DB = "./DB/temp.db";
	public static final String STUDENT_TABLE = "Student";
	public static final String LECTURE_TABLE = "Lecture";

	public static final int SQL_NO_DATA = 0;
	public static final int COURCE_ENROLL_SUCCESS = 1;
	public static final int COURCE_ENROLL_EXIST = 2;
	public static final int COURCE_ENROLL_FAIL_NO_MORE_SEAT = 3;
	public static final int COURCE_ENROLL_FAIL = 4;
	
	public static final String[] state = {
			"SQL_NO_DATA",
			"COURCE_ENROLL_SUCCESS",
			"COURCE_ENROLL_EXIST",
			"COURCE_ENROLL_FAIL_NO_MORE_SEAT",
			"COURCE_ENROLL_FAIL"};
}
