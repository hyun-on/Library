package lib_Main;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.sun.xml.internal.bind.v2.TODO;

import lib_Database.Database;
import lib_Vo.Bk_rentVO;
import lib_Vo.BlackListVO;
import lib_Vo.BookVO;
import lib_Vo.GenreVO;
import lib_Vo.NoticeVO;
import lib_Vo.PlaceTotalVO;
import lib_Vo.Total_rentVO;
import lib_Vo.UserVO;

public class View {	
	private UserVO user = null;
	private final IServiceImpl iServiceImpl = new IServiceImpl();
	/**
	 * 문자열 입력 메서드
	 * @author 김정원
	 * @return String - 입력받은 문자열
	 */
	String sInput(){
		Scanner sc = new Scanner(System.in);
		return sc.next().trim();
	}
	/**
	 * 숫자 입력 메서드
	 * @author 김정원
	 * @return int - 입력받은 숫자
	 */
	int iInput(){
		int push;
		while (true){
			try{
				Scanner sc = new Scanner(System.in);
				push = sc.nextInt();
				break;
			} catch (Exception e){
				System.out.println("숫자만 입력하세요");
			}
				
		}
		return push;
	
	}
	/**
	 * 메인뷰 
	 * 
	 * @author 김정원
	 */
	
	void startMethod(){
		while(true){
			System.out.println("┌─---------------------------------------─┐");
			System.out.println("| ┌─────────────────────────────────────┐ |");
			System.out.println("| |  	※도서관에    오실건 환영합니다※			| |");
			System.out.println("| └─────────────────────────────────────┘ |");
			System.out.println("└─---------------------------------------─┘");
			System.out.println("[ 1 ] 로그인");
			System.out.println("[ 2 ] 회원가입");
			System.out.println("[ 0 ] 종료");
			switch (iInput()) {
			case 0:
				System.out.println("프로그램을 종료합니다");
				return;
			case 1:
				loginView();//유저페이지가기
				break;
			case 2:
				insertUserView();//회원가입
				break;
			default:
				break;
			}
		}
	}

	/**
	 * 로그인 뷰 - 관리자/사용자 메서드 -아이디와 비밀번호를  입력받아 database에서 비교\
	 * 
	 * @author 김정원
	 */
	private void loginView() {//로그인페이지
		System.out.println("---✔로그인페이지✔---");
		String userid = null;
		String userpw = null;
//		String usertel = null;
//		String username = null;
		while(true){
			if(userid == null){
				System.out.println("아이디를 입력해주세요");
				userid = sInput();
				continue;
			}else if(userpw == null){
				System.out.println("비밀번호를 입력해주세요");
				userpw = sInput();
				continue;
			}
			Map<String, String> loginGo = new HashMap<>();
			loginGo.put("userid", userid);
			loginGo.put("userpw", userpw);
			
			user = iServiceImpl.userLogin(loginGo);
			
			if(iServiceImpl.adiminLogin(loginGo)){
				System.out.println("관리자계정으로 접속하셨습니다");
				adminMain();
				return;
			}else if(user != null){
				userMainView();
				return;
			}
			System.out.println("아이디 비밀번호를 확인하세요");
			userid = null;
			userpw = null;
			
			
		}
	}
	/**
	 * 아이디 규칙성에 타당한지 확인 - 사용자 매서드
	 * 
	 * @author 김현주
	 * @return 아이디의 규칙이 맞는지 확인하고 입력받은 값 반환
	 */
	private String examId() {
		String input;
		String message = "";
		while (true) {
			System.out.println(">아이디 입력<");
			System.out.println(">6 ~ 15자리의 영문 또는 숫자 조합이 가능합니다<");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_id(input)) {
				break;
			}
			message = ">올바르지 않은 아이디 형식입니다<";
		}
		return input;
	}

	/**
	 * 이름 규칙성에 타당한지 확인 - 사용자 매서드
	 * 
	 * @author 김현주
	 * @return 이름이 규칙에 맞는지 확인 후 String 반환
	 */
	private String examName() {
		String input;
		String message = "";
		while (true) {
			System.out.println();
			System.out.println("→ 이름 입력 ←");
			System.out.println("2 ~ 17자의 한글만 입력해주세요. (※특수기호, 공백 사용 불가※ )");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_name(input)) {
				break;
			}
			message = "올바르지 않은 이름 형식입니다.";
		}
		return input;
	}

	/**
	 * 비밀번호 규칙성에 타당한지 확인 - 사용자 매서드
	 * 
	 * @author 김현주
	 * @return 비밀번호 규칙확인을 위해 String 반환
	 */
	private String examPw() {
		String message = "";
		String input;
		while (true) {
			System.out.println();
			System.out.println("→ 비밀번호 입력 ←");
			System.out.println(" 8 ~ 20자의 숫자 또는 문자를 입력해주세요");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_pw(input)) {
				break;
			}
			message = "X 올바르지 않은 비밀번호 형식입니다 X";
		}
		return input;
	}
	
	/**
	 * 전화번호 규칙성에 타당한지 확인 - 사용자 매서드
	 * 
	 * @author 김현주
	 * @return 비밀번호 규칙확인을 위해 String 반환
	 */
	private String examTel() {
		String message = "";
		String input;
		while (true) {
			System.out.println();
			System.out.println("→ 전화번호 입력 ←");
			System.out.println("※11자리의 숫자를 입력해 주세요 ※");
			System.out.println("￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣");
			if (!"".equals(message)) {
				System.out.println();
				System.out.println(message);
			}
			input = sInput();
			if (RegEx.checkUser_tel(input)) {
				break;
			}
			message = "X 올바르지 않은 전화번호 형식입니다 X";
		}
		return input;
	}
	
	
	/**
	 * 회원가입 - 사용자 매서드
	 * 
	 * 입력한 유저의 정보를  DB에 등록한다.
	 * 아이디는 PK로 중복이 허용되지 않음으로   checkId메서드를 활용하여 유효한ID인지 확인
	 * 
	 * @author 강남훈
	 */
	//회원가입  - 유저 정보 DB에 대입
	//
	//boolean insertUser(UserVo userVo);
	//return - 성공시 true 실패시 false 반환
	//param - UserVO 
	private void insertUserView() {
		System.out.println("---✔회원가입✔---");
		UserVO user = new UserVO(); 
		while (true) {
			if (user.getId() == null) {
				System.out.println("│ → 1. 아이디 설정			 │");
			} else if (user.getName() == null) {
				System.out.println("│ → 2. 이름 설정			 │");
			} else if (user.getPw() == null) {
				System.out.println("│ → 3. 비밀번호 설정			 │");
			} else if(user.getTel() == null){
				System.out.println("│ → 4. 전화번호 입력			 │");
			} 
			if (user.getId() == null) {
				user.setId(checkId());
				continue;
			} else if (user.getName() == null) {
				user.setName(examName());
				continue;
			} else if (user.getPw() == null) {
				user.setPw(examPw());
				continue;
			} else if (user.getTel() == null) {
				user.setTel(examTel());
				continue;
			}
			break;
		}
		if (iServiceImpl.insertUser(user)) {
			System.out.println("회원 등록 완료");
		}
	}
		
	
	

	/**
	 * 
	 * 아이디중복검사(아이디를 유일한지를 확인) - 사용자 매서드
	 * @author 강남훈
	 */
	//
	//아이디중복검사 - id 중복 여부와 조건을 만족하는지 검사
	//boolean checkid(String id);
	//return - 만족시 true, 불만족시 false 반환
	//param - id 
	private String checkId(){
		String id;
		while(true){
			id=examId();
			boolean idex = iServiceImpl.checkId(id);
			if(idex){
				break;
			}else {
				System.out.println("중복된 아이디입니다.");
			}
		}
		return id;
		
	}

	
	/**
	 * 관리자 메인 뷰 = 관리자 메서드
	 * 
	 * @author 강남훈 
	 */
	private void adminMain(){
		while (true) {
			System.out.println("---✔관리자 페이지✔---");
			System.out.println("[ 1 ] 회원 상세관리");
			System.out.println("[ 2 ]   도서관리   ");
			System.out.println("[ 3 ]  열람실 관리 ");
			System.out.println("[ 4 ]   공지사항   ");
			System.out.println("[ 0 ]   로그아웃   ");
			System.out.println();
			switch (iInput()) {
			case 0:
				// 뒤로가기
				return;
			case 1:
				// 회원 상세관리 메서드 호출
				userManageView();
				break;
			case 2:
				// 도서관리 메서드 호출
				bookManage();
				break;
			case 3:
				// 열람실 관리 메서드 호출
				placeSelect();
				break;
			case 4:
				// 공지사항 관리 메서드 호출
				notifyInfromation();
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
			}
		}
	}
	
	/**
	 * 회원상세관리 - 관리자 메서드 
	 * 
	 * @author 김정원 	 
	 */
	private void userManageView(){
		System.out.println("---✔회원관리페이지✔---");
		while (true) {
			
			System.out.println("[ 1 ] 대여내역");
			System.out.println("[ 2 ] 블랙리스트");
			System.out.println("[ 0 ] 뒤로가기");
			switch (iInput()) {
			case 0:
				// 뒤로가기
				return;
			case 1:
				// 도서내역 메서드 호출
				rentalListView();
				break;
			case 2:
				// 블랙리스트 메서드 호출
				blackListView();
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");	
			}
		}
	}
	
	/**
	 * 블랙리스트
	 * 
	 * @author 김정원
	 */
	//블랙리스트 출력 메서드 - DB
	//boolean blackList();성공시 true, 실패 시 false 반환
	
	//블랙리스트 추가 메서드 - DB
	//boolean createBlackList(String id);성공시 true, 실패 시 false 반환
	//param - BlackListVO 
	//return - BlackListVO 
	
	//블랙리스트 삭제 메서드 - DB
	//boolean deleteBlackList(String id);성공시 true, 실패 시 false 반환
	//return BlackListVO
	//
	private void blackListView(){
	      System.out.println("---✔블랙리스트✔---");
	      System.out.println("---✔블랙리스트✔---");
	      List<BlackListVO> black = iServiceImpl.blackList();
	      System.out.println("번호 \t시작일 \t       종료일         블랙아이디");
	      System.out.println("-----------------------------------");
	      for(BlackListVO blacklist : black){
	    	  System.out.println(blacklist);
	      }
	      while (true) {
	         
	         System.out.println("[ 0 ] 뒤로가기");
	         System.out.println("[ 1 ] 블랙리스트 추가");
	         System.out.println("[ 2 ] 블랙리스트 삭제");
	         switch (iInput()) {
	         case 0:
	            // 뒤로가기
	            return;
	         case 1:
	            blackListAdd();
	            
	            
	            break;
	         case 2:
	            blackListDelete();
	            break;
	         }
	      }
	   }
	   //블랙리스트 추가 메서드 - DB
	   //boolean createBlackList(String id);성공시 true, 실패 시 false 반환
	   //param - BlackListVO 
	   //return - BlackListVO
	   private void blackListAdd(){
	      String blackname;
	      System.out.println("---✔블랙리스트 등록✔---");
	      System.out.println("블랙리스트 할 아이디를 입력해주세요 ");
	      blackname = sInput();
	      if(iServiceImpl.createBlackList(blackname)){
	         System.out.println(blackname + "가 블랙리스트에 추가되었습니다");
	         return;
	      }else{
	         System.out.println("블랙리스트를 추가하지 못했습니다");
	         System.out.println(blackname + "이 우리의 회원이 맞는지 확인해 주세요");
	         return;
	         
	      }
	   }
	   //블랙리스트 삭제 메서드 - DB
	   private void blackListDelete() {
		   List<BlackListVO> black = iServiceImpl.blackList();
		   System.out.println("번호 \t시작일 \t      종료일         블랙아이디");
		   System.out.println("-----------------------------------");
		   for(BlackListVO blacklist : black){
			   System.out.println(blacklist);
		   }
	      String blackid;
	      System.out.println("---✔블랙리스트  제거✔---");
	      System.out.println("제거할 아이디를 입력하세요");
	      blackid = sInput();
	      if(iServiceImpl.deleteBlackList(blackid)){
	         System.out.println(blackid + "가 블랙리스트에 제거되었습니다");
	         return;
	      }else{
	         System.out.println(blackid+"가 블랙리스트에 존재하는지 확인해주세요");
	      }
	      
	   }
	/**
	 * 
	 * 대여 내역
	 * @author 강남훈
	 */
	private void rentalListView(){
		System.out.println("---✔전체대여리스트✔---");
		while (true) {
			System.out.println("[ 1 ] 도서대여 내역");
			System.out.println("[ 2 ] 사물함/열람실 대여내역");
			System.out.println("[ 0 ] 뒤로가기");
			switch (iInput()) {
			case 0:
				// 뒤로가기
				return;
			case 1:
				// 도서대여 내역 메서드 호출
				bookRentalView();
				break;
			case 2: 
				// 사물함 대여내역 메서드 호출
				cabinetRentView();
				break;		
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");		
			}
		}
	}
		
	/**
	 * 도서대여내역
	 *  @author 김현주 
	 */
	
	//도서대여내역 - DB
	//List<Bk_rentVo> bookRental(UserVO userVO);성공시 true, 실패 시 false 반환
	//param - UserVO 
	//return - UserVO
	private void bookRentalView(){
		System.out.println("---✔도서 대여내역 확인✔---");
		while (true) {		
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println("[ 1 ] 도서관리");
			switch (iInput()) {
			case 0:
				// 뒤로가기
				return;
			case 1:
				bookManage();
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");		
			}
		}
	}
	
	/**
	 * 사용자들이  누가   어디를  빌렷는지 리스트출력
	 * 사물함 대여내역
	 * @author 김정원
	 * 
	 */
	
	//사물함대여내역 - DB
	//List<Total_rentVO> cabinetRent(UserVO userVO);
	//param - UserVO 
	//return - UserVO
	private void cabinetRentView(){
		System.out.println("---✔사물함 대여내역 확인✔---");
		List<Total_rentVO> rentlist = iServiceImpl.totalList();
		SimpleDateFormat place = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("대여순서     시작날짜                종료날짜            자리번호       사용회원아이디");
		for(Total_rentVO rent : rentlist){
			System.out.print("  ");
			System.out.print(rent.getSeq()+"   ");
			System.out.print(place.format(rent.getStart())+"   ");
			System.out.print(place.format(rent.getEnd())+"      ");
			System.out.print(rent.getPt_seq()+"      ");
			System.out.println(rent.getUserid());
		}
		
		String choice;
		int parameter = 0;
		List<Total_rentVO> placeRentList = iServiceImpl.rentlist();
		System.out.println("사물함의  정보를 보시려면 '사물함'이라입력해주새요");
		System.out.println("열람실의  정보를 보시려면 '열람실'이라입력해주새요");
		while (true) {		
			choice = sInput();
			if("사물함".equals(choice)){
				parameter = 0;
				for(Total_rentVO rent : rentlist){
					System.out.print("  ");
					System.out.print(rent.getSeq()+"   ");
					System.out.print(place.format(rent.getStart())+"   ");
					System.out.print(place.format(rent.getEnd())+"      ");
					System.out.print(rent.getPt_seq()+"      ");
					System.out.println(rent.getUserid());
				}
				return;
			}else if ("열람실".equals(choice)) {
				parameter = 1;
				for(Total_rentVO rent : rentlist){
					System.out.print("  ");
					System.out.print(rent.getSeq()+"   ");
					System.out.print(place.format(rent.getStart())+"   ");
					System.out.print(place.format(rent.getEnd())+"      ");
					System.out.print(rent.getPt_seq()+"      ");
					System.out.println(rent.getUserid());
				}
				return;
			}else{
				System.out.println("'사물함' 혹은  '열람실'을 입력하셔야합니다");
				continue;
			}
		}
		
	} // locker로 바꿔야함
	

	/**
     * 여깁니다 여기 여기요`````````````````````````````````````````````````````````````````````````````````````````````
     * 이것은 책 리스트를 뽑아주는 것!
     * 
     */
   public void bookManage() {
       System.out.println("---✔도서관리✔---");
       List<BookVO> boo = iServiceImpl.bookList();
       bookList();
//       for(int i =0; i<boo.size(); i++){
//          System.out.println(boo.get(i) );
//       }
          System.out.println("[ 1 ] 도서등록 ");
          System.out.println("[ 2 ] 도서 상세정보");
          System.out.println("[ 0 ] 뒤로가기");
          switch (iInput()) {
          case 0:
             // 뒤로가기
             return;
          case 1:
             // 도서대여 내역 메서드 호출
             bookAdd();
             break;
          case 2:
             // 사물함 대여내역 메서드 호출
             bookInformation();
             break;   
          default:
             System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");      
          }
       }
    
//   +bk.get(i).getGenre_seq())
   public void bookList(){
       List<BookVO> bk = iServiceImpl.bookList();
       List<GenreVO> gn = iServiceImpl.genreList();
       System.out.println("---");
       for(BookVO list : bk){
          for(GenreVO glist : gn){
             if(list.getGenre_seq() == glist.getSeq()){
                System.out.print(list.getSeq()+"   ");
                System.out.print(list.getBookname()+"    ");
                System.out.print(list.getPubliser()+"    ");
                System.out.println(glist.getName());
             }
          }
       	}
       }
	
   	private void bookAdd(){
        System.out.println("---✔도서등록 및 추가✔---");
        
        System.out.println("추가할 도서이름을 입력해주세요");
        String bookname = sInput();
        System.out.println("도서의 출판사를 입력해주새요");
        String publiser = sInput();
        List<GenreVO> genrelist = iServiceImpl.genreList();
        System.out.println(genrelist);
        System.out.println("도서의 장르번호를  입력해주세요");
        int genre = iInput();
        
        BookVO bookadd = new BookVO();
        bookadd.setSeq(++Database.book_seq);
        bookadd.setBookname(bookname);
        bookadd.setPubliser(publiser);
        bookadd.setBook_state(true);
        bookadd.setBookActivate(true);
        bookadd.setGenre_seq(genre);
        
        
        
        if(iServiceImpl.bookAdd(bookadd)){
           System.out.println("도서추가에 성공하셨습니다");
        }else{
           System.out.println("도서추가에 실패하셨습니다");
        }
           
        
     }
     
     /**
      * 도서상세정보  - 관리자 메서드 
      * 
      * @author 김현주
      */
     
     private void bookInformation(){
        List<BookVO> booklist = iServiceImpl.bookCheck();
        System.out.println("============도서 목록=============");
        System.out.println("---✔도서 상세정보✔---");
        while (true) {
           bookList();
           System.out.println("[ 0 ] 뒤로가기");
           System.out.println("[ 1 ] 도서 수정");
           System.out.println("[ 2 ] 도서 삭제");
           switch (iInput()) {
           case 0:
              // 뒤로가기
              return;
           case 1 :
              bookUpdate();//도서 수정
              break;
           case 2 :
              bookDelete();//도서 삭제
              break;
           default:
              System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");      
           }
        }
     }
     
     /**
      * 
      * 도서 수정
      * @author 김현주
      */
     //도서수정 - DB
     //boolean bookUpdate(Map<String, Object> bookupdate);도서수정성공하면 true
     //param - bookupdate <"book_seq", book_seq>,<"bookname", bookname>,<"genre_seq", genre_seq>,<"publiser", publiser>
     //List<BookVO> selectAllSize();
     //return - boolea
     private void bookUpdate(){
        System.out.println("---✔도서 수정✔---");
        while(true){
           System.out.println("변경할 책번호를 입력하세요");
           Map<String,Object> bookchange = new HashMap<>();
           int bookid = 0;
           System.out.println("[1]책재목변경 [2]출판사변경 [3]장르변경 [0]뒤로가기");
           switch(iInput()) {
           case 1:
              System.out.println("책번호를 입력해주세요 : 숫자");
              bookid = iInput();            
              bookchange.put("bookid", bookid);
              System.out.println("책재목을 입력해주새요 ");
              String bookname = sInput();
              bookchange.put("bookname", bookname);
              break;
           case 2:
              System.out.println("책번호를 입력해주세요 : 숫자");
              bookid = iInput();            
              bookchange.put("bookid", bookid);
              System.out.println("출판사를 입력해주세요 : 문자");
              String publisher = sInput();
              bookchange.put("publisher", publisher);
              break;
           case 3:
              List<GenreVO> genrelist = iServiceImpl.genreList();
              System.out.println(genrelist);
              System.out.println("책번호를 입력해주세요 : 숫자");
              bookid = iInput();            
              bookchange.put("bookid", bookid);
              System.out.println("장르를 선택해주새요 : 숫자");
              int genre = iInput();
              bookchange.put("genre", genre);   
           case 0:
              return;
           default:
              System.out.println("다시입력해주새요");
              continue;
           }
           if(iServiceImpl.bookUpdate(bookchange)){
              System.out.println("도서정보가 수정되었습니다");
           }else{
              System.out.println("정보수정 실패");
           }
        }
     
     }

     /**
      * 관리자
      * 도서 삭제
      * @author 김정원
      */
     //도서삭제 - DB
     //boolean bookDelete(int book_seq);도서삭제성공하면 true
     //param - BookVO
     //return - boolean
     private void bookDelete(){
        int bookid;
        //도서리스트를 가공해서넣어줘야한다.**********
        //도서리스트미구현
        System.out.println("---✔도서 제거✔---");
        System.out.println("제거할 책의 번호를 입력하세요");
        bookid = iInput();
        if(iServiceImpl.bookDelete(bookid)){
           System.out.println(bookid + "가 도서리스트에 제거되었습니다");
           return;
        }else{
           System.out.println(bookid+"가 도서리스트에 존재하는지 확인해주세요");
        }
        
     }
	/**
	 * 
	 * 열람실,사물함 구분
	 * @author 강남훈
	 * 
	 */
	private void placeSelect(){
		System.out.println("---✔사물함 및 열람실 선택✔---");
		while (true) {	
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println("[ 1 ] 열람실/사물함 관리 페이지로 이동");

			switch (iInput()) {
			case 0:
				// 뒤로가기
				return;
			case 1 :
				seatInfromation();//자리상세정보 메서드 호출
				break;

			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");		
			}
		}
	}
	
	
	
	/**
	 * 
	 * 자리상세정보
	 * 현재 자리의 이미지가  출력되어야한다.
	 * @author 강남훈
	 */
	//자리상세정보 - DB
	//List<Total_rentVO> seatInfromation(String rent);
	//param - String rent
	//return - List<Total_rentVO>
	private void seatInfromation(){
		System.out.println("---✔자리 상세정보✔---");
		while (true) {	
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println("[ 1 ] 자리상태변경");
				
			switch (iInput()) {
			case 0:
				// 뒤로가기
				return;
			case 1 :
				seatStatus();//자리상세정보 메서드 호출
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");		
			}
		}
	}
	
	
	/**
	 * 자리상태변경
	 * @author 강남훈 
	 */
	//사물함  상태변경
	//List<Total_rentVO> seatStatus(String rent);
	//param - Total_rentVO
	//return - List<Total_rent>
	private void seatStatus(){
		System.out.println("---✔자리상태변경✔---");
		while (true) {	
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println("[ 1 ] 장소상태변경");
			
			switch (iInput()) {
			case 0 :
				// 뒤로가기
				return;
			case 1 :
				useSview();// 사용중 메서드 호출
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");		
			}
		}
	}
	

	
	
	
	/**
	 *상태  변경
	 * @author 김정원 
	 */
	//상태 사용중으로 변경 - DB
	//int useSview(Map<Integer, Integer> Suse);사용중으로 변경되면 0
	//param - Total_rentVO
	//return - int
	private void useSview(){
		System.out.println("---✔상태여부✔---");
		Map<String,Object> statusChange = new HashMap<String,Object>();
		 System.out.println("---✔사물함 대여내역 확인✔---");
		 System.out.println("=========================");
		 System.out.println("□ :사용가능 ■ :사용중 ▩  :수리중");
		 System.out.println("=========================");
		 System.out.println("            1 2 3 4 5 6 7 8 9 10");
		 List<PlaceTotalVO> placeList = iServiceImpl.placeList();
		 System.out.print("사물함상태[1]: ");
		 for(PlaceTotalVO placezari : placeList){
			 if(placezari.getPlace() == 0){
				 if(placezari.getPt_status() == 0){
					 System.out.print("□ ");
				 }else if(placezari.getPt_status() == 1){
					 System.out.print("■ ");
				 }else{
					 System.out.print("▩ ");
					 System.out.println();
				 }
			 }
		 }
		  System.out.println();
		  System.out.println("            1 2 3 4 5 6 7 8 9 10");
		  System.out.print("열람실상태[2]: ");
		  for(PlaceTotalVO placezari : placeList){
			  if(placezari.getPlace() == 1){
				  if(placezari.getPt_status() == 0){
					  System.out.print("□ ");
				  }else if(placezari.getPt_status() == 1){
					  System.out.print("■ ");
				  }else{
					  System.out.print("▩");
					  System.out.println();
				  }
			  }
		  }
		  	System.out.println();
			int parameter = 0;
			int bang = 0;
			int seat = 0;
			int status = 0;
			System.out.println("[ 1 ] 사물함");
			System.out.println("[ 2 ] 열람실");
			System.out.println("[ 0 ] 뒤로가기");
			while (true) {
				
				switch(iInput()) {
				case 0:
					return;
				case 1:
					parameter = 0;
					break;
				 case 2:
					 parameter = 1;
					 break;
				default:
				  System.out.println("다시");
				}
				statusChange.put("parameter", parameter);
				System.out.println("방번호를 입력해주새요 ");
				bang = iInput();
				statusChange.put("bang", bang);
				System.out.println("자리번호를 입력해주세요");
				seat = iInput();
				statusChange.put("seat", seat);
				System.out.println("변경할 사용상태를 입력해주세요");
				System.out.println("0)사용가능  1)사용중  2)수리중");
				status = iInput();
				statusChange.put("status", status);
				if(iServiceImpl.updatePlace(statusChange)){
					 System.out.println("장소정보가 수정되었습니다");
				}else{
					System.out.println("장소정보 수정  실패");
				}
				return;
				 
				 
			}
			
	}
	
	
	
	/**
	 * 공지사항정보
	 * @author 김정원
	 * 
	 */
	//공지사항 - DB
	//List<NoticeVO> useSview(NoticeVO noticeVO)
	//param - NoticeVO
	//return - List<NoticeVO>
	private void notifyInfromation(){
		System.out.println("---✔공지사항✔---");
		while (true) {
			
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println("[ 1 ] 추가");
			System.out.println("[ 2 ] 게시글상세보기");
			
			switch (iInput()) {
			case 0 :
				// 뒤로가기
				return;
			case 1 :
				notifyAdd();// 추가 메서드 호출
				break;
			case 2 :
				noticeContent();// 추가 메서드 호출
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");		
			}
		}
	}
	//공지사항 상세보기 - DB
	//List<NoticeVO> noticeContent(NoticeVO noticeVO)
	//param - NoticeVO
	//return - List<NoticeVO>
	private void noticeContent() {
		System.out.println("---✔상세내역✔---");
		List<NoticeVO> not = iServiceImpl.noticeList();
		for(NoticeVO no : not){
			System.out.print(no.getSeq()+"  ");
			System.out.println(no.getTitle()+"\t");
			System.out.println("-->"+no.getContent());
			System.out.println();
		}
		while (true) {	
			
			System.out.println("[ 0 ] 뒤로가기");
			System.out.println("[ 1 ] 수정");
			System.out.println("[ 2 ] 삭제");
			switch (iInput()) {
			case 0 :
				// 뒤로가기
				return;
			case 1 :
				notifyUpdate();// 수정 메서드 호출
				break;
			case 2 :
				notifyDelete();// 삭제 메서드 호출
				break;
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");		
			}
		}
	}
	
	/**
	    * 공지사항수정
	    * @author 김정원
	    */
	   // 공지사항수정 - DB
	   //boolean notifyUpdate(Map<String, String> notice);성공하면 true
	   //param - NoticeVO
	   //return - boolean
	   private void notifyUpdate(){
	      System.out.println("---✔공지사항이 수정✔---");
	      while(true){
	    	
	         System.out.println("변경할 사항을 선택하세요");
	         System.out.println("[ 1 ] title 수정");
	         System.out.println("[ 2 ] 공지내용 수정");
	         System.out.println("[ 0 ] 뒤로가기");
	         Map<String,Object> noticechange = new HashMap<>();
	         int noticenum = 0;
	         switch(iInput()) {
	         case 1:
	            System.out.println("공지번호 를 입력해주세요 : 숫자");
	            noticenum = iInput();            
	            noticechange.put("noticenum", noticenum);
	            System.out.println("title을 입력해주새요 ");
	            String title = sInput();
	            noticechange.put("title", title);
	            break;
	         case 2:
	            System.out.println("공지번호 를 입력해주세요 : 숫자");
	            noticenum = iInput();            
	            noticechange.put("noticenum", noticenum);
	            System.out.println("공지내용 를 입력해주세요 : 문자");
	            String content = sInput();
	            noticechange.put("content", content);
	            break;
	         case 0:
	            return;
	         default:
	            System.out.println("다시입력해주새요");
	            continue;
	         }
	         if(iServiceImpl.notifyUpdate(noticechange)){
	            System.out.println("공지사항이 수정되었습니다");
	         }else{
	            System.out.println("공지사항 수정  실패");
	         }
	      }
	   
	   }
	   
	   /**
	    * 공지사항추가 
	    * @author 김정원 
	    */
	   // 공지사항추가 - DB
	   //boolean notifyAdd(NoticeVO noticeadd);
	   //param - notice
	   //return - boolean
	   private void notifyAdd(){
	      System.out.println("---✔공지사항이 추가✔---");
	      System.out.println("title 을 입력해주세요");
	      String title = sInput();
	      System.out.println("도서의 공지내용을 입력해주새요");
	      String publiser = sInput();
	      
	      NoticeVO noticeadd = new NoticeVO();
	      noticeadd.setSeq(++Database.notic_seq);
	      noticeadd.setTitle(title);
	      noticeadd.setContent(publiser);

	      if(iServiceImpl.notifyAdd(noticeadd)){
	         System.out.println("공지사항에 추가되었습니다");
	      }else{
	         System.out.println("공지사항 등록에 실패했습니다");
	      }

	   }
	   
	   /**
	    * 공지사항제거 
	    * @author 김정원 
	    */
	   // 공지사항제거  - DB
	   //boolean notifyDelete(Map<String, String> notice);성공하면 true
	   //param - NoticeVO
	   //return - boolean
	   private void notifyDelete(){
	      int noticeid;
	      //도서리스트를 가공해서넣어줘야한다.**********
	      //도서리스트미구현
	      System.out.println("---✔공지사항이 삭제✔---");
	      System.out.println("제거할 공지사항 번호를 입력하세요");
	      noticeid = iInput();
	      if(iServiceImpl.notifyDelete(noticeid)){
	         System.out.println(noticeid + "가 공지사항에 제거되었습니다");
	         return;
	      }else{
	         System.out.println(noticeid+"기 공지사항에 존재하는지 확인해주세요");
	      }
	   }
	//_______________________________
	/** <회원main>
	 * 
	 * 공지사항  확인
	 * @author 김정원 
	 */
	private void userMainView(){
		System.out.println("---✔회원메인페이지✔---");
		while(true){
			if (user == null) {
				return;
			}
			System.out.println("[0]공지사항 확인");
			System.out.println("[1]대여(도서/열람실/사물함)");
			System.out.println("[2]마이페이지");
			System.out.println("[3]내정보수정");
			System.out.println("[4]로그아웃");
			switch (iInput()) {
			case 0:
				notifyCheck();
				break;
				
			case 1:
				rentView();
				break;
				
			case 2:
				myPage();
				break;
			
			case 3:
				modifyInformation();
				break;
			case 4:
				user = null;
				return;	
			default:
				break;
			}
			
		}
	}
		
	/** 
	 * 
	 * 공지사항  확인
	 * @author 김정원 
	 */
	//공지사항  확인
	//List<NoticeVO> notifyCheck(String notice);
	//param - NoticeVO
	//return - List<NoticeVO>
	private void notifyCheck(){
		System.out.println("---✔공지사항 확인✔---");
		List<NoticeVO> not = iServiceImpl.noticeList();
		for(NoticeVO no : not){
			System.out.print(no.getSeq()+"  ");
			System.out.println(no.getTitle()+"\t");
			System.out.println("-->"+no.getContent());
			System.out.println();
		}
		return;
	}
	/**
	 * 
	 * 대여View
	 * @author 김정원
	 */
	private void rentView(){
		System.out.println("---✔대여신청 목록✔---");
		while(true){
			if (user == null) {
				return;
			}
			System.out.println("[0]도서    대여");
			System.out.println("[1]사물함/열람실 대여");
			System.out.println("[2]이전");
			switch (iInput()) {
			case 0:
				bookCheck();
				break;
				//<>
			case 1:
				readRoomPick();
				break;
				
			case 2:
				return;
				
			default:
				break;
			}
			
		}
		
	}
	   /**
	    * 도서 
	    * @author 김정원
	    */   
	   
	   //DB도서리스트
	   //List<BookVO> bookCheck(String booklist);
	   //param - BookVO
	   //return - List<BookVO>
	   private void bookCheck(){
	      System.out.println("---✔도서 페이지✔---");
	      while(true){
	    	  if (user == null) {
					return;
				}
	         System.out.println("[0]이전페이지");
	         System.out.println("[1]도서대여");
	         System.out.println("[2]내 도서대여 리스트");
	         
	         switch (iInput()) {
	         case 0:
	            
	            return;
	            
	         case 1:
	            bookRental();
	            continue;
	         
	         case 2:
	            mybookView();
	            return;
	            
	         default:
	            break;
	         }
	         
	      }
	      
	   }
	   

	   private void bookRental(){
	      while(true){
	    	  if (user == null) {
					return;
				}
	         Bk_rentVO rent = new Bk_rentVO();
	         bookList();                   
	         System.out.println("책 번호를 입력해주세요");
	         int bookid = iInput();  //내가 원하는 책의 seq값     

	          
	         
	          //대여
	         Date start = Calendar.getInstance().getTime();
	         Date end = Calendar.getInstance().getTime();
	         Calendar ca1 = Calendar.getInstance();
	         ca1.add(Calendar.DATE,7);
	         rent.setBook_seq(bookid);
	         rent.setStart(start);
	         rent.setEnd(end);
	         rent.setUser_id(user.getId());
	         
	         if(iServiceImpl.bookRental(rent)){
	            System.out.println("성공");
	            
	   
	         }else{
	        	 System.out.println("이미 다른사람이 대여중입니다");
	            
	         }
	         System.out.println("---✔👀대여👀✔---");
	         return;
	         }
	   }
//	   private void mybookView(){
//		   List<Bk_rentVO> rt = iServiceImpl.mybookView(user.getId());
//		   BookVO bk = iServiceImpl.bookcc(rt.get(i).getBook_seq());
//	   }
	   
	   //대여 목록 확인
	// TODO Auto-generated method stub
	   private void mybookView(){
		   
	         List<Bk_rentVO> rt = iServiceImpl.mybookView(user.getId());
	          System.out.println("---");
	          for(int i=0; i<rt.size(); i++){
	             BookVO bk = iServiceImpl.bookcc(rt.get(i).getBook_seq());
	             System.out.println(bk.getBookname());
	             Calendar ca1 = Calendar.getInstance();
	             ca1.add(Calendar.DATE,7);
	             rt.get(i).setEnd(ca1.getTime());
	             System.out.println("아이디 : " + rt.get(i).getUser_id());   
	             System.out.println("대여일 : " + rt.get(i).getStart());   
	             System.out.println("반납예정일 : " + rt.get(i).getEnd());   
	      
	             
	          }
	      
	   }
     
	/**
	 * 열람실 자리선택
	 * @author 김정원 
	 */
	// TODO Auto-generated method stub
	//열람실 자리선택
	//int readRoomPick(Map<Integer, Integer> readroompick);사용중으로 변경되면 0
	//param - PlaceTotalVO
	//return - int
	private void readRoomPick(){
		System.out.println("=========================");
		 System.out.println("□ :사용가능 ■ :사용중 ▩  :수리중");
		 System.out.println("=========================");
		 System.out.println("            1 2 3 4 5 6 7 8 9 10");
		 List<PlaceTotalVO> placeList = iServiceImpl.placeList();
		 System.out.print("사물함상태[1]: ");
		 for(PlaceTotalVO placezari : placeList){
			 if(placezari.getPlace() == 0){
				 if(placezari.getPt_status() == 0){
					 System.out.print("□ ");
				 }else if(placezari.getPt_status() == 1){
					 System.out.print("■ ");
				 }else{
					 System.out.print("▩ ");
					 System.out.println();
				 }
			 }
		 }
		  System.out.println();
		  System.out.println("            1 2 3 4 5 6 7 8 9 10");
		  System.out.print("열람실상태[2]: ");
		  for(PlaceTotalVO placezari : placeList){
			  if(placezari.getPlace() == 1){
				  if(placezari.getPt_status() == 0){
					  System.out.print("□ ");
				  }else if(placezari.getPt_status() == 1){
					  System.out.print("■ ");
				  }else{
					  System.out.print("▩");
					  System.out.println();
				  }
			  }
		  }
		System.out.println("---✔열람실 자리 선택 완료✔---");
		Map<String,Object> statusChange = new HashMap<String,Object>();
		//사용상태 
		System.out.println();
		int parameter = 0;//0이면 사물함 1이면 열람실
		int bang = 0;//X값
		int seat = 0;//Y값
		//아이디, 장소seq
		// x값, y값 ,
		System.out.println("[ 0 ] 뒤로가기");
		System.out.println("[ 1 ] 열람실");
		System.out.println("[ 2 ] 사물함");
		while (true) {	
			if (user == null) {
				return;
			}
			switch(iInput()) {
				case 1:
					parameter = 0;
					break;
				case 2:
					parameter = 1;
					break;
				case 0:
					return;
				default:
				  System.out.println("다시");
			}
			//유저아이디 담기
			statusChange.put("userid", user.getId());
			//사물함이냐 열람실이냐 
			statusChange.put("parameter", parameter);
			//방번호 입력
			System.out.println("대여할 방번호를 입력해주세요");
			bang = iInput();
			statusChange.put("bang", bang);
			//자리번호입력
			System.out.println("대여할 자리번호를 입력해주새요");
			seat = iInput();
			statusChange.put("seat", seat);
			//상태 사용중으로 변경
			int status  =  1;
			statusChange.put("status", status);
						
			if(iServiceImpl.updatePRent(statusChange)){
				System.out.println("장소상태변경");
				if(iServiceImpl.cabinetPick(statusChange)){
					System.out.println("장소대여성공");
					System.out.println("뒤로가기[0]");
				}
			}else{
				System.out.println("그곳은 이미대여중입니다 다시입력해주세요");
			}
		}
		
	}
	
	
	/////////////////////////////////////////마이페이지영역
	/**
	 * 마이페이지
	 * @author 김정원 
	 * 
	 */
	private void myPage(){
		System.out.println("---✔마이페이지✔---");
		while(true){
			if (user == null) {
				return;
			}
			System.out.println("[0]대여도서");
			System.out.println("[1]대여사물함");
			System.out.println("[2]이전페이지");
			switch (iInput()) {
			case 0:
				myBook();
				break;
			case 1:
				myCabinet();
				break;
					
			case 2:
				
				return;
				

			default:
				break;
			}
			
		}
	}

	/**
	 * 나의 도서 대여
	 * @author 김현주
	 */
	//대여도서
	//List<Bk_rentVO> myBook(String mybookrent);
	//param - Bk_rentVO
	//return - List<Bk_rentVO>
	private void myBook(){
		System.out.println("---✔회원 도서✔---");
		while(true){
			if (user == null) {
				return;
			}
			System.out.println("[0]도서반납");
			System.out.println("[1]이전페이지");
			switch (iInput()) {
			case 0:
				mybookReturn();
				break;
				
			case 1:
				return;	
				

			default:
				break;
			}
			
		}
	}
		

	

	   /**
	    * 도서반납
	    * @author 김현주
	    */
	   //도서반납
	   //boolean mybookReturn(Map<String, String> bookreturn);//성공시 true
	   //param - BookVO 
	   //return - boolean
	   private void mybookReturn(){
	      System.out.println("---✔도서 반납✔---");
	      System.out.println("반납할 책의 번호를 입력하세요");
	      int input = iInput();
	      myReturnView(input);
	      return;
	   }
	   
   //      대여 항목에 있는 모든 책의 상태는 false 이므로  ->> true로 바꿔준다
   //대여 목록 확인            
   private void myReturnView(int seq){
      BookVO bk = new BookVO();
      if(iServiceImpl.bookReturnView(bk.getSeq())){
         System.out.println("성공");    //일단 state의 값이 true 가 나오게 하는게 우선 --- if문을 줘서 true면 remove시키기 
      }else{
         System.out.println("대여한 책이 없습니다");
         return;
      }
   }
		
		
	/**
	 * 사물함
	 * @author 강남훈
	 */
	//사물함
	// TODO Auto-generated method stub
	//해당 유저의 빌린 사물함 내역 가져오면됩니다 (비교는는 userid로 하시면되겠습니다)
	private void myCabinet(){
		System.out.println("---✔회원 사물함✔---");
		while(true){
			if (user == null) {
				return;
			}
			System.out.println("[1]사물함반납");
			System.out.println("[0]이전페이지");
			switch (iInput()) {

			case 0:
				return;
				
			case 1:
				myCabinetcancel();
				break;
				

			default:
				break;
			}
			
		}
		
	}
	

	
	/**
	 * 사물함 취소
	 * @author 강남훈
	 */
	// TODO Auto-generated method stub
	//boolean myCabinetcancel(Map<Integer, Integer> cabinetcancel);//성공시 true
	//param - cabinetcancel
	//return - boolean
	private void myCabinetcancel(){
		System.out.println("---✔사물함 취소/반납 완료✔---");
		System.out.println("대여순번   아이디       대여일                       대여종료일    자리번호");
		List<Total_rentVO> totalRent = iServiceImpl.totalRentList(user.getId());
		SimpleDateFormat place = new SimpleDateFormat("yyyy-MM-dd");
		for(Total_rentVO userTotal : totalRent){
			if(userTotal.getUserid().equals(user.getId())){
				System.out.print(userTotal.getSeq()+"\t");
				System.out.print(userTotal.getUserid()+"\t");
				System.out.print(place.format(userTotal.getStart())+"\t");
				System.out.print(place.format(userTotal.getEnd())+"\t");
				System.out.println(userTotal.getPt_seq());
				
			}
			
			
		}
		if(totalRent.size()==0){
			System.out.println("대여내역이 없습니다");
		}
		
		Map<String, Object> placedel = new HashMap<String, Object>();
		int parameter  = 0;//사물함이냐 열람실이냐
		
		System.out.println("[ 0 ] 뒤로가기");
		System.out.println("[ 1 ] 사물함");
		System.out.println("[ 2 ] 열람실");
		while(true){
			if (user == null) {
				return;
			}
			 switch (iInput()) {
			case 1:
				parameter = 0;
				break;
			case 2:
				parameter = 1;
				break;
			case 0:
				return;
			default:
				break;
			}
			placedel.put("parameter", parameter);
			placedel.put("userid", user.getId());
			if(iServiceImpl.myPlaceDel(placedel)){
				System.out.println("대여수정완료");
			}
			
			
		}
	}
	
	/**
	 * 열람실
	 * @author 김정원
	 */
	//사물함
	//List<Total_rentVO> myReadroom(String myreadroom);
	//param - myreadroom
	//return - List<Total_rentVO>
//	private void myReadroom(){
//		System.out.println("---✔회원 열람실✔---");
//		while(true){
//			System.out.println("[0]열람실취소");
//			System.out.println("[1]이전페이지");
//			switch (iInput()) {
//			case 0:
//				myReadroomcancel();
//				break;
//				
//			case 1:
//				return;
//			default:
//				return;
//			}
//			
//		}
//	}
//	
//	/**
//	 * 열람실 취소
//	 * @author 김정원
//	 */
//	//boolean myReadroomcancel(Map<Integer, Integer> readroomcancel);//성공시 true
//	//param - PlaceTotalVO
//	//return - boolean
//	private void myReadroomcancel(){
//		System.out.println("---✔열람실 취소 완료✔---");
//		return;
//	}
	//--------------------------------------
	/**
	 * 내정보수정
	 * @author 김현주
	 */
	private void modifyInformation(){
		System.out.println("---✔마이페이지✔---");
	while(true){
		 if (user == null) {
	         return;
	     }
	    System.out.println();
	    System.out.println("------------------------------");
	    System.out.println("아이디 : "+user.getId());
	    System.out.println("비밀번호 : "+user.getPw());
	    System.out.println("이름 : "+user.getName());
	    System.out.println("전화번호 : "+user.getTel());
		System.out.println("＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿");
		System.out.println("[ 1 ] 이름 변경");
		System.out.println("[ 2 ] 비밀번호 변경");
		System.out.println("[ 3 ] 전화번호 변경");
		System.out.println("[ 4 ] 회원 탈퇴");
		System.out.println("[ 0 ] 뒤로 가기");
		System.out.println("=============================");
		switch (iInput()) {
		case 1:
			changeName();
			break;
		case 2:
			changePw();
			break;
		case 3:
			changeTel();
			break;
		case 4:
			userExit();
			break;
		case 0:
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
		}
	}
		
	}
	
	
	/**
	 * 이름변경
	 * @author 김정원
	 */
	//boolean changeName(Map<String, String> changename);//성공시 true
	//param - UserVO
	//return - boolean
	private void changeName(){
		System.out.println("---✔이름변경✔---");
		 String newna = examName();
		 Map<String, Object> cn = new HashMap<>();
		 
		 cn.put("user_id", user.getId());
		 cn.put("username", newna);
		 
		 boolean na = iServiceImpl.userupdate(cn);
		 if(na){
			 System.out.println("===변경완료===");
			 System.out.println("변경된 이름"+user.getName());
			 return; 
		 }
		 System.out.println("===변경실패===");
	}
	
	
	/**
	 * 비밀번호변경
	 * @author 강남훈 
	 */
	//boolean changePw(Map<String, String> changepw);//성공시 true
	//param - UserVO
	//return - boolean
	private void changePw(){
		System.out.println("---✔비밀번호 변경✔---");
		String newpw = examPw();
		 Map<String, Object> cp = new HashMap<>();
		 
		 cp.put("user_id", user.getId());
		 cp.put("userpw", newpw);
		 boolean pw = iServiceImpl.userupdate(cp);
		 
		 if(pw){
			 System.out.println("===변경완료===");
			 System.out.println("변경된 비밀번호"+user.getPw());
			 return; 
		 }
			 System.out.println("===변경실패===");
			 return; 
		 
	}
	
	/**
	 * 전화번호 변경
	 * @author 김현주 
	 */
	//boolean changeTel(Map<String, String> changetel);//성공시 true
	//param - UserVO
	//return - boolean
	private void changeTel(){
		System.out.println("---✔전화번호 변경✔---");
		String newtel = examTel();
		 Map<String, Object> ct = new HashMap<>();
		 
		 ct.put("user_id", user.getId());
		 ct.put("usertel", newtel);
		 
		 
		 boolean tel = iServiceImpl.userupdate(ct);
		 if(tel){
			 System.out.println("===변경완료===");
			 System.out.println("변경된 번호"+user.getTel());
			 return; 
		 }
		 System.out.println("===변경실패===");
	}
	
	/**
	 * 회원탈퇴
	 * @author 김현주  //true flase값으로 회원과 탈퇴회원을 구분  
	 */
	//boolean userExit(Map<Integer, Integer> userexit);//성공시 true
	//param - UserVO
	//return - boolean
	private void userExit(){
	      while (true) {
	         System.out.println();
	         System.out.println("=========Warning=========");
	         System.out.println("탈퇴하시겠습니까?");
	         System.out.println("[ 1 ] Y");
	         System.out.println("[ 2 ] N");
	         String input = sInput();
	         if ("1".equals(input)) {
	            int result = iServiceImpl.userExit(user.getId());
	            if (result > 0) {
	               System.out.println("---✔회원탈퇴 성공✔---");
	               user = null;
	            } else {
	               System.out.println("회원 탈퇴에 실패하였습니다.");
	            }
	            return;
	         } else if ("2".equals(input)) {
	            return;
	         } else {
	        	 System.out.println("====올바르지 않은 입력입니다====");
	         }
	      }
	   
	   }

}
	
	
	


