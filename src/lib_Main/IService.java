package lib_Main;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lib_Vo.Bk_rentVO;
import lib_Vo.BlackListVO;
import lib_Vo.BookVO;
import lib_Vo.GenreVO;
import lib_Vo.NoticeVO;
import lib_Vo.PlaceTotalVO;
import lib_Vo.Total_rentVO;
import lib_Vo.UserVO;

public interface IService {
	
	/**
	 * 회원가입 - 유저 정보 DB에 입력
	 * 
	 * @param - userVO
	 * @return 성공 시 true, 실패 시 false 반환
	 * @author 김현주
	 */
	boolean insertUser(UserVO userVo);
	
	/**
	 * id 중복 여부와 조건을 충분히 만족하는지 확인
	 * 
	 * @param - id
	 * @return 만족하면 true, 불만족하면 false 반환
	 * @author 김현주
	 */
	boolean checkId(String id);
	/**
	 * @param -  AdminVO
	 * @return - 성공하면 true, 실패하면 false 반환
	 * @author 김정원
	 */
	UserVO userLogin(Map<String, String> userlogin);
	/**
	 * @param -  UserVO
	 * @return - 성공하면 true, 실패하면 false 반환
	 * @author 김정원
	 */
	boolean adiminLogin(Map<String, String> adiminlogin);
	/**
	 * 회원정보수정 - 관리자메서드
	 * @param -  UserVO 
	 * 			<"id",id>,<"name",name>
	 * @return - 성공하면 true, 실패하면 false 반환
	 * @author 김현주
	 */
	boolean userupdate(Map<String, Object> userinfor);
	
	/**
	 * 회원정보가져오기 - 관리자메서드
	 * @param -  List UserVO 
	 * 			
	 * @author 김현주
	 */
	List<UserVO> selectAllUser();
	
//	/**
//	 * 회원비밀번호수정 - 관리자메서드
//	 * @param -  UserVO 
//	 * 			<"id",id>,<"pw",pw>
//	 * @return - 성공하면 true, 실패하면 false 반환
//	 * @author 김현주
//	 */
//	int changePw(Map<String, Object> changepw);
//	
//	/**
//	 * 회원전화번호수정 - 관리자메서드
//	 * @param -  UserVO 
//	 * 			<"id",id>,<"tel",tel>
//	 * @return - 성공하면 true, 실패하면 false 반환
//	 * @author 김현주
//	 */
//	int changeTel(Map<String, Object> changetel);
	
	/**
	 * 회원이름수정 - 관리자메서드
	 * @param -  UserVO 
	 * 			<"id",id>,<"activity",activity>
	 * @return - 성공하면 true, 실패하면 false 반환
	 * @author 김현주
	 */
	int userExit(String string);
	

	/**
	 * 블랙리스트 리스트 - 관리자 메서드
	 * @author 김정원 
	 */
	 List<BlackListVO> blackList(); //반환타입 List<BlackList>
	
	/**
	 * 블랙리스트 리스트 생성 메소드  - 관리자 메서드
	 * @param - id
	 * @return 블랙리스트의 리스트 내역
	 */
	boolean createBlackList(String id); // 설명
	
	/**
	 * 블랙리스트 리스트 삭제 메소드  - 관리자 메서드
	 * @param - id
	 * @return 삭제할 블랙리스트 회원 정보 
	 * @author 김정원 
	 */
	boolean deleteBlackList(String id); // list를 삭제 반환타입 확인
	
	/**
	  * 도서하나만출력 - 사용자메서드
	  * @param - selectbookList          
	  * @return - List<BookVO>
	  * @author 강남훈
	  */
	BookVO seletbookList(String book);
	/**
	 * 장르 하나만 출력 - 사용자메서드
	 * @param - selectgenreList          
	 * @return - List<GenreVO>
	 * @author 강남훈
	 */
	GenreVO selectgenreList(String genre);
	
	/**
	 * 도서리스트전체출력 - 사용자메서드
	 * @param - bookList          
	 * @return - List<BookVO>
	 * @author 강남훈
	 */
	List<BookVO> bookList();
	   
	 /**
	   * 도서리스트전체출력 - 사용자메서드
	   * @param - genreList          
	   * @return - List<BookVO>
	   * @author 강남훈
	   */
	List<GenreVO> genreList();
	   
	   
	/**
	 * 도서리스트 - 사용자메서드
	 * @param - booklist 			
	 * @return - List<BookVO>
	 * @author 강남훈
	 */
	List<BookVO> bookCheck();
	
	/**
	 * 책대여    - 사용자 메서드
	 * @param - bookinsert
	 * @return -성공하면 true, 실패하면 false 반환
	 * @author 강남훈
	 */
	boolean bookRental(BookVO bookinsert);
	
	/**
	 * 도서 대여 내역 - 사용자메서드
	 * @param - mybookrent 
	 * @return - List<Bk_rentVO>
	 * @author 강남훈
	 */
	List<Bk_rentVO> myBook(String mybookrent);
	
	/**
	 * 도서 반납  - 사용자메서드
	 * 
	 *@param -  BookVO 
	 * 			<"bookseq",bookseq>,<"book_state",book_state>
	 * @return -성공하면 true, 실패하면 false 반환
	 * @author 강남훈
	 */
	boolean mybookReturn(Map<String, String> bookreturn);
	
	
	
	/**
	 * 도서 대여 목록 리스트 출력 메서드  - 관리자 메서드
	 * @param - id 
	 * @return - List<Bk_rentVO>
	 * @author 강남훈
	 */
	List<Bk_rentVO> bookRentalList(String id); // 매개변수 사람id
	
	/**
	 * 도서  추가    - 관리자 메서드
	 * @param - bookadd
	 * @return -성공하면 true, 실패하면 false 반환
	 * @author 강남훈
	 */
	boolean bookAdd(BookVO bookadd);
	
	/**
	 * 도서 정보 수정  - 관리자 메서드
	 * @param - bookupdate
	 * 			<"book_seq", book_seq>,<"bookname", bookname>,<"genre_seq", genre_seq>,<"publiser", publiser>
	 * @return - boolean
	 * @author 강남훈
	 */
	boolean bookUpdate(Map<String, Object> bookupdate); //매개변수
	
	/**
	 * 도서 정보 제거  - 관리자 메서드
	 * @param - book_seq
	 * @return - 실패시 false 성공시  true반환
	 * @author 강남훈
	 */
	boolean bookDelete(int book_seq);
	
	/**
	 *열람실/사물함 자리선택  - 사용자메서드
	 *@param -  Total_rentVO 
	 * 			<"placeTotal_seq",placeTotal_seq>,<"userid",userid>,<"py",py>,<"pt_seq",pt_seq>
	 * 			
	 * @return -성공하면 true, 실패하면 false 반환
	 * @author 김정원 
	 * 
	 */
	boolean cabinetPick(Map<String, Object> statusChange);
	/**
	 *대여햇을경우 열람실/사물함 상태변경  - 사용자메서드
	 *@param -  Total_rentVO 
	 * 			<"placeTotal_seq",placeTotal_seq>,<"userid",userid>,<"py",py>,<"pt_seq",pt_seq>
	 * 			
	 * @return -성공하면 true, 실패하면 false 반환
	 * @author 김정원 
	 * 
	 */
	boolean updatePRent(Map<String, Object> statusChange);
	/**
	 *열람실/사물함 상태변경  - 관리자메서드
	 *@param -  Total_rentVO 
	 * 			<"placeTotal_seq",placeTotal_seq>,<"userid",userid>,<"py",py>,<"pt_seq",pt_seq>
	 * 			
	 * @return -성공하면 true, 실패하면 false 반환
	 * @author 김정원 
	 * 
	 */
	boolean updatePlace(Map<String, Object> statusChange);
	
	
	/**
	 * 사물함 대여내역 - 관리자 메서드
	 * @param - UserVO
	 * @return - List<Total_rentVO>
	 * @author 김정원 
	 */
	
	List<PlaceTotalVO> placelist();
	
	/**
	 * 사물함 상태 사용중으로 변경 - 관리자메서드
	 * @param -  Suse 
	 * 			<"placeTotal_seq",placeTotal_seq>,<"pt_status", pt_status>
	 * @return - int
	 * @author 김정원 
	 */
	int useSview(Map<Integer, Integer> Suse); //매개변수 사물함 번호, 상태코드
	
	/**
	 * 자리상태정보 - 관리자메서드
	 * @param - Total_rentVO
	 * @return - List<Total_rent>
	 * @author 김정원 
	 */
	//해당 자리에 정보를 리스트화 해서 출력한다.
	List<PlaceTotalVO> seatStatus(PlaceTotalVO placeTotalVO); //매개변수
	
	/**
	 * 자리상세정보 - 관리자메서드
	 * @param -  Total_rentVO
	 * @return - List<Total_rent>
	 * @author 김정원 
	 */
	//해당 자리에 대한 사용자의 이름, 사용시작 시간,끝에 대한 정보를 불러온다
	List<Total_rentVO> seatInfromation(Total_rentVO total_rentVO);

	/**
	    * 공지사항리스트 정보 - 관리자메서드
	    * @param -  NoticeVO 
	    * @return - List<NoticeVO>
	    * @author 강남훈
	    */
	List<NoticeVO> useSview(NoticeVO noticeVO); //매개변수
	   
	   
	   /**
	    * 공지사항 세부사항 - 관리자메서드
	    * @param -  NoticeVO          
	    * @return - List<NoticeVO>
	    * @author 강남훈
	    */
	   //공지사항 세부없음.
	List<NoticeVO> noticeContent(NoticeVO noticeVO);
	   
	   /**
	    * 공지사항수정 - 관리자메서드
	    * @param -  NoticeVO 
	    *          <"noticeVO _seq",noticeVO _seq>,<"title",title>
	    * @return - 성공하면 true, 실패하면 false 반환
	    * @author 강남훈
	    */
	boolean notifyUpdate(Map<String, Object> noticechange);
	   
	   /**
	    * 공지사항  추가    - 관리자 메서드
	    * @param - noticeadd
	    * @return -성공하면 true, 실패하면 false 반환
	    * @author 강남훈
	    */
	boolean notifyAdd(NoticeVO noticeadd);
	   
	   /**
	    * 공지사항  제거    - 관리자 메서드
	    * @param - noticedel
	    * @return -성공하면 true, 실패하면 false 반환
	    * @author 강남훈
	    */
	boolean notifyDelete(int notice_seq);
	
	

	BookVO bookcc(int seq);
	
	boolean bookRental(Bk_rentVO rent);

	List<Bk_rentVO> mybookView(String id);
	
	boolean bookReturnView(int seq);
	
	List<Bk_rentVO> bookRentList(String id);
	
	List<Total_rentVO> totalList();
	
	List<PlaceTotalVO> placeList();
	
	List<Total_rentVO> totalRentList();
	
	List<NoticeVO> noticeList();
//	/**
//	 * 상태 사용가능으로 변경 - 관리자메서드
//	 * @param -  Swait 
//	 * 			<"placeTotal_seq",placeTotal_seq>,<"px",px><"py",px>,<"pt_status", pt_status>
//	 * @return - int
//	 */
//	int waitSview(Map<Integer, Integer> Swait); //중복메서드
//	
//	/**
//	 * 상태 수리중으로 변경 - 관리자메서드
//	 * @param -  Srefuse 
//	 * 			<"placeTotal_seq",placeTotal_seq>,<"px",px><"py",px>,<"pt_status", pt_status>
//	 * @return - int
//	 */
//	int refuseSview(Map<Integer, Integer> Srefuse); //중복 메서드

	List<Total_rentVO> totalRentList(String userid);
	
//	/**
//	 * 열람실 대여내역 - 관리자 메서드
//	 * @param - UserVO
//	 * @return - List<Total_rentVO>
//	 */
//	List<Total_rentVO> readRoomRent(UserVO userVO); //사물함 여부 위 메서드와 같다
	
//	
//	/**
//	 * 사물함 상태확인 - 사용자메서드
//	 * @param - cabinetlist 
//	 * 			
//	 * @return - List<PlaceTotalVO>
//	 */
//	List<PlaceTotalVO> cabinetCheck(String cabinetlist);
	
//	/**
//	 * 열람실 상태확인 - 사용자메서드
//	 * @param - readRoomCheck 
//	 * 			
//	 * @return - List<PlaceTotalVO>
//	 */
//	List<PlaceTotalVO> readRoomCheck(String readRoomCheck);
	
//	/**
//	 * 열람실 자리선택  - 사용자메서드
//	 * 
//	 @param -  Total_rentVO 
//	 * 			<"placeTotal_seq",placeTotal_seq>,<"userid",userid>,<"py",py>,<"pt_seq",pt_seq>
//	 * 			
//	 * @return -성공하면 true, 실패하면 false 반환
//	 */
//	boolean readRoomPick(Map<String, Object> readroompick);
//	
//	/**
//	 * 열람실사용취소  - 사용자메서드
//	 * 
//	 @param -  Total_rentVO 
//	 * 			<"Total_rentseq",Total_rentseq><"pt_seq",pt_seq>
//	 * 			
//	 * @return -성공하면 true, 실패하면 false 반환
//	 */
//	boolean myReadroomcancel(Map<Integer, Integer> readroomcancel);
	
	
	
}
