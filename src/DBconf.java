/**
 * 데이터베이스 설정 가지고 있는 클래스
 * @author bang
 *
 */
public class DBconf {
	public static final String DB = "./DB/temp.db";					// 데이터베이스 파일
	public static final String STUDENT_TABLE = "student";			// 학생 테이블 명
	public static final String LECTURE_TABLE = "lecture";			// 수업 테이블 명

	public static final int SQL_NO_DATA = 0;						// 조회된 데이터 없음
	public static final int COURCE_ENROLL_SUCCESS = 1;				// 수강신청 성공
	public static final int COURCE_ENROLL_EXIST = 2;				// 이미 수강된 수업
	public static final int COURCE_ENROLL_FAIL_NO_MORE_SEAT = 3;	// 수강여석 없음
	public static final int COURCE_ENROLL_FAIL = 4;					// 수강 실패
	
	public static final String[] state = {
			"SQL_NO_DATA",
			"COURCE_ENROLL_SUCCESS",
			"COURCE_ENROLL_EXIST",
			"COURCE_ENROLL_FAIL_NO_MORE_SEAT",
			"COURCE_ENROLL_FAIL"};
}
