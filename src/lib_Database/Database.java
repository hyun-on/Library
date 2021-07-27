package lib_Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.TODO;

import lib_Vo.AdminVO;
import lib_Vo.Bk_rentVO;
import lib_Vo.BlackListVO;
import lib_Vo.BookVO;
import lib_Vo.GenreVO;
import lib_Vo.NoticeVO;
import lib_Vo.Total_rentVO;
import lib_Vo.PlaceTotalVO;
import lib_Vo.UserVO;

public class Database {
	public static int user_seq  = 10;
    public static int black_seq  = 5;
    public static int book_seq  = 10;
    public static int bookrent_seq  = 10;
    public static int genre_seq  = 10;
    public static int locker_seq  = 20;
    public static int lo_ren_seq  = 5;
    public static int notic_seq  = 10;
    
	private AdminVO admin = new AdminVO();	//관리자
	
	private List<UserVO> userList = new ArrayList<UserVO>();            	//회원목록
	
	private List<BlackListVO> blackList = new ArrayList<BlackListVO>(); 	//블랙리스트 목록
	
	private List<BookVO> booklist = new ArrayList<BookVO>();				//도서목록
	
	private List<Bk_rentVO> bk_lentList = new ArrayList<Bk_rentVO>();   	//도서대여목록
	
	private List<GenreVO> genrelist = new ArrayList<GenreVO>();         	//도서장르목록
	
	private List<PlaceTotalVO> lockerList = new ArrayList<PlaceTotalVO>();	//로커/사물함 목록
	
	private List<Total_rentVO> lo_rentList = new ArrayList<Total_rentVO>(); //로커/사물함 대여 목록
	
	private List<NoticeVO> noticeList = new ArrayList<NoticeVO>();			//공지사항 목록
	
	
	/**
	 * <code>insertUser</code> 메서드는 회원가입을 위한 메서드 입니다.
	 * 
	 * @param userVo
	 *            : 회원 정보를 저장 하는 변수
	 * @return userList에 정보추가
	 * @author 김현주
	 */
	public boolean insertUser(UserVO userVo) {
		return userList.add(userVo);
	}
	/*public boolean insertUser(UserVO userVo) {
		for(UserVO userCompare : userList){
			if(userCompare.getId().equals(userVo.getId())){
				System.out.println("회원등록에 성공하셧습니다");
				return userList.add(userVo);
			}
		}
		System.out.println("이미 존재하는 아이디입니다.");
		return false;
	}*/
	/**
	 * <code>userLogin</code> 메서드는 로그인을 위한 메서드 입니다.
	 * 
	 * @param Map<String, String> userlogin
	 *            : 회원 아이디와 비밀번호를 받아오는 변수
	 * @return user
	 * @author 김현주
	 */
	public UserVO userLogin(Map<String, String> userlogin) {
		String userid = userlogin.get("userid");
		String userpw = userlogin.get("userpw");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		for(UserVO user : userList){
			for(BlackListVO blackCheck : blackList){
				if(user.getId().equals(userid)&&user.getPw().equals(userpw)&&user.isActivity()==true) {
					if(!blackCheck.getUser_id().equals(userid)){
						System.out.println("로그인성공");
						return user;
					}else{
						//블랙리스트에 존재할경우
						System.out.println(userid +"블랙리스트 시작일"+df.format(blackCheck.getStart()));
						System.out.println(userid +"블랙리스트 종료일"+df.format(blackCheck.getEnd()));
						return null;
					}
				}
			}
		}
		return null;
	}
	/**
	 * <code>adiminLogin</code> 메서드는 관리자로그인을 위한 메서드 입니다.
	 * 
	 * @param Map<String, String> adiminlogin
	 *            : 관리자 아이디와 비밀번호를 받아오는 변수
	 * @return admin
	 * @author 김현주
	 */
	public boolean adiminLogin(Map<String, String> adiminlogin) {
		
		return admin.getId().equals(adiminlogin.get("userid"))&& admin.getPw().equals(adiminlogin.get("userpw"));
	}
	
	/**
	 * <code>userIdCheck</code> 메서드는 유저 아이디 중복검사를 위한 메서드 입니다.
	 * 
	 * @param userId
	 *            : 관리자 아이디와 비밀번호를 받아오는 변수
	 * @return : 유효성을 확인하기 위한 String 객체
	 * 			유일한 값이라면 true, 그렇지 않은 경우라면 false 반환
	 * @author 김현주
	 */
	public boolean userIdCheck(String userId) {
		if (admin.getId().equals(userId)) {
			return false;
		}
	return selectUser(userId) == null;
	}
	/**
	 * <code>selectAllUser</code> 메서드는 모든 유저의 정보를 가져오기 위한 메서드 입니다.
	 * 
	 * 
	 * @return : userList
	 * 			모든 유저의 정보가 true일때 정보를 가져옴
	 * @author 김현주
	 */
	public List<UserVO> selectAllUser() {
		List<UserVO> userList = new ArrayList<>();
		for (UserVO user : this.userList) {
			if (user.isActivity()) {
				userList.add(user);
			}
		}
		return userList;
	}
	


	/**
	 * <code>userupdate</code> 메서드는 유저의 정보를 변경하기 위한 메서드 입니다.
	 * 
	 * 
	 * @return : Map<String, Object> userupdate
	 * 			모든 유저의 정보가  변경되어 true일때 정보를 가져옴
	 * @author 김현주
	 */
	public boolean userupdate(Map<String, Object> userupdate) {
		UserVO user = selectUser((String)userupdate.get("user_id"));
		if((String)userupdate.get("username")!=null){
			user.setName((String)userupdate.get("username"));
			return true;
		}
		if((String)userupdate.get("userpw")!=null){
			user.setPw((String)userupdate.get("userpw"));
			return true;
		}
		if((String)userupdate.get("usertel")!=null){
			user.setTel((String)userupdate.get("usertel"));
			return true;
		}
		return false;
	}
	/**
	 * <code>userExit</code> 메서드는 사용자를 삭제하기 위한 메서드입니다.
	 * 
	 * @param user_id
	 *            : 사용자를 판별할 수 있는 id
	 * @return 삭제 성공 시 1, 실패 시 0 반환
	 *  @author 김현주
	 */
	 public int userExit(String user_id) {
         UserVO user = selectUser(user_id);
         if (user.isActivity()) {
            user.setActivity(false);
            return 1;
         }
         return 0;
      }
	 
	 /**
		 * <code>selectUser</code> 메서드는 선택한 유저의 정보를 불러오기 위한 메서드입니다.
		 * 
		 * @param id
		 *            : 유저의 id
		 * @return 사용자의 정보를 담고 있는 UserVO 객체
		 * @author 김현주
		 */
    public UserVO selectUser(String user_id) {
        for (UserVO user : userList) {
         if (user.getId().equals(user_id)) {
        	 return user;
            
            }
         }
         return null;
      }
    //블랙리스트 출력
	public List<BlackListVO> blackList() {
		return blackList;
	}
	public List<PlaceTotalVO> placelist() {
		return lockerList;
	}


	//블랙리스트 생성
     public boolean createBlackList(String blackid) {
        for(UserVO blackuser : userList){
           if(blackuser.getId().equals(blackid)){
              BlackListVO blk = new BlackListVO();
              blk.setUser_id(blackid);
              //블랙리스트 시작 날짜 
              Calendar cal = Calendar.getInstance();
              DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
              //현재의 날짜를 등록
              blk.setStart(cal.getTime());
              System.out.println("블랙리스트 등록일" + df.format(cal.getTime()));
              //현재에서 7일을더한 날짜를 등록 
              cal.add(Calendar.DATE, 7);
              blk.setEnd(cal.getTime());
              System.out.println("블랙리스트 종료일" + df.format(cal.getTime()));
              blackList.add(blk);
              return true;
           }
        }
        return false;
     }

     //블랙리스트제거
     public boolean deleteBlackList(String id) {
        for(BlackListVO blackuser : blackList){
           if(blackuser.getUser_id().equals(id)){
              //블랙리스트에 해당 아이디가 존재할경우 삭제
              System.out.println(id);
              return blackList.remove(blackuser);
           }
        }
        //실패하면 false반환
        return false;
     }
     
     //장르리스트출력
 	public List<GenreVO> genreList() {
		return genrelist;
	}
	public BookVO seletbookList(String book) {
		// TODO Auto-generated method stub
		return null;
	}
	public GenreVO selectgenreList(String genre) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<BookVO> bookCheck() {
		return booklist;
	}
	//수정
	 /**
  	 * <code>bookRental</code> 메서드는 책대여 상태를 구현하기 위한 메서드 입니다.
  	 * 
  	 * 
  	 * @return : booklist의 seq가 rent.seq(책 대여 번호)와 같을떄 true를반환 
  	 * @author 강남훈
  	 */
	 public boolean bookRental(Bk_rentVO rent) {
	  //    for(int i=0; i<booklist.size(); i++){
//	        }else if(bookrent.get().getSeq() == rent.getBook_seq()){
		 for(BookVO bookrent : booklist){
			if(bookrent.getSeq()==rent.getBook_seq()&&bookrent.isBook_state() == true){
				bookrent.setBook_state(false);
				bk_lentList.add(rent);
				return true;	
	         }
	      }  
	     return false;
	   }
/*   public boolean bookRental(Bk_rentVO rent) {//내가 빌리려는 책
	   for(Bk_rentVO bookrent : bk_lentList){
		   if(bookrent.getBook_seq()!=rent.getSeq()){//존재하는 책인지확인
			   for(BookVO books : booklist){ 
				   if(books.isBook_state()==true){// 대여가 가능한지 여부확인
					   bk_lentList.add(rent);
					   return true;
				   }
			   }
		   }
		  
	   }
	   return false;
   }*/
	public List<Bk_rentVO> myBook(String mybookrent) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean mybookReturn(Map<String, String> bookreturn) {
		// TODO Auto-generated method stub
		return false;
	}
	//대여 리스트 출력
    public List<Bk_rentVO> mybookView(String id){  
    List<Bk_rentVO> bo = new ArrayList<>();
	    for(int i = 0; i < bk_lentList.size(); i++ ){  //bk rentlist 의 유저아이디가 매개변수 id와 같다면 대여목록에 추가
	       if(bk_lentList.get(i).getUser_id().equals(id)){
	          bo.add(bk_lentList.get(i));
	          }
	       }
	       
	       return bo;
	    }

	// 리스트 이름만 출력 	
	public List<BookVO> bookList() {
		List<BookVO> boo = new ArrayList<>();
		for (BookVO bk : this.booklist) {
			if (bk.isBookActivate()) {
				boo.add(bk);
			}
		}
		return boo;
	}
	
	public BookVO bookcc(int seq) {
        for (BookVO bk :this.booklist) {
           if (bk.getSeq()==seq)
              return bk;
              
           }
        return null;
        
        
     }
//   public List<Bk_rentVO> bookRentalList(String id) {
// 		// TODO Auto-generated method stub
// 		return null;
// 	}
//	
//	public List<GenreVO> genreList() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	public List<GenreVO> genreCheck() {
		return genrelist;
	}		
		/**
		    * 
		    * 도서추가 
		    * @param bookadd
		    * @return
		    */
   public boolean bookAdd(BookVO bookadd) {
      return booklist.add(bookadd);
   }
   public boolean updatePRent(Map<String, Object> statusChange) {
		for(PlaceTotalVO placeUp : lockerList){
//			System.out.println((Integer)statusChange.get("parameter"));
			if((Integer)placeUp.getPlace() == (Integer)statusChange.get("parameter")){//사물함인지 열람실인지비교
				if((Integer)placeUp.getPx() == statusChange.get("bang")&&placeUp.getPy() == (Integer)statusChange.get("seat")){//방번호와 x 자리번호y가 둘다잇을경우비교
					if(placeUp.getPt_status() == 0){
						placeUp.setPt_status((Integer)statusChange.get("status"));//스테이터스를 변경해준다.
						return true;
					}
				}
			}
		}
		return false;
	}
   
   
   public boolean cabinetPick(Map<String, Object> statusChange) {
	   Total_rentVO tRent = new Total_rentVO();
	   tRent.setSeq(++Database.lo_ren_seq);//시퀀스
	   Calendar cal = Calendar.getInstance();
	   DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	   tRent.setStart(cal.getTime());//시작날짜
	   System.out.println("대여시작일:"+ df.format(cal.getTime()));
	   cal.add(Calendar.DATE,1);
	   System.out.println("대여 종료일:"+ df.format(cal.getTime()));
	   tRent.setEnd(cal.getTime());//종료날짜
	   tRent.setUserid((String)statusChange.get("userid"));
	   for(PlaceTotalVO find : lockerList){
		   if(find.getPx()==(int)statusChange.get("bang")&&(find.getPy())==(int)statusChange.get("seat")){
			   tRent.setPt_seq(find.getSeq());
		   }
	   }
		return lo_rentList.add(tRent);
	}
   
   //유저 장소대여 제거
   public boolean myPlaceDel(Map<String, Object> placedel) {
       for (Total_rentVO rentDelete : lo_rentList) {
           for (PlaceTotalVO changeTotal : lockerList) {
               if (rentDelete.getPt_seq() == changeTotal.getSeq() && rentDelete.getUserid().equals((String)placedel.get("userid")) && (int)placedel.get("parameter") == changeTotal.getPlace()) {
                   changeTotal.setPt_status(0);
                   return lo_rentList.remove(rentDelete);
               }
           }
       }
       System.out.println("대여하신내역이없습니다");
       return false;
   }
//   public boolean myPlaceDel(Map<String, Object> placedel) {
//       for (Total_rentVO rentDelete : lo_rentList) {
//           for (PlaceTotalVO changeTotal : lockerList) {
//               if (rentDelete.getUserid().equals((String)placedel.get("userid")) && (int)placedel.get("parameter") == changeTotal.getPlace()) {
//            	   if(rentDelete.getPt_seq() == changeTotal.getSeq()){
//	            	   changeTotal.setPt_status(0);
//	                   return lo_rentList.remove(rentDelete);
//            	   }
//               }
//           }
//       }
//       System.out.println("대여하신내역이없습니다");
//       return false;
//   }
  	// TODO Auto-generated method stub
//   	public boolean myPlaceDel(Map<String, Object> placedel) {
//   		for(Total_rentVO rentDelete : lo_rentList){//대여한 리스트 전체
//   			if(rentDelete.getUserid().equals(placedel.get((String)"userid"))){//대여한아이디와 로그인한 아이디를 비교해서 내대여목뽑는게목적
//   				System.out.println(rentDelete.getUserid());
//   				for(PlaceTotalVO changeTotal : lockerList){// 전체 리스트 사물함10(0)  열람실10(1)
//   					if(changeTotal.getSeq() == rentDelete.getPt_seq()){//전체 리스트에서 내가 빌린 seq와 같으면 
//   						changeTotal.setPt_status(0);//사용가능으로 변경
//   						System.out.println("대여하신 자리가  '사용가능'으로 변경되었습니다");
//   						return true;
//   					}
//   				}
//  				
//   			}
//   		}
//   		System.out.println("대여하신내역이없습니다");
//   		return false;
//   	}
//   	public boolean myPlaceDel(Map<String, Object> placedel) {
//   		for(Total_rentVO rentDelete : lo_rentList){
//   			if(rentDelete.getUserid().equals((String)placedel.get("userid"))&&rentDelete.getPt_seq()==(Integer)placedel.get("parameter")){
//   				//랜드한 목록에서  아이디와 장소의seq를 비교 해서 해당하는 목록을불러온다.
//   				for(PlaceTotalVO changeTotal : lockerList){
//   					if(changeTotal.getSeq() == rentDelete.getPt_seq()){
//   						changeTotal.setPt_status(0);
//   						lo_rentList.remove(rentDelete);
//   					}
//   						
//   				}
//   				
//   			}
//   		}
//   		
//   		return false;
//   	}
//   	&&rentDelete.getPt_seq()==(Integer)placedel.get("parameter")
   	//장소제거시 파라미터 사용가능으로 변경하기 
   	
   	
   	
   //도서  수정 구현
   public boolean bookUpdate(Map<String, Object> bookupdate) {
      for(BookVO bookinfo : booklist){
         if((Integer)bookinfo.getSeq()==bookupdate.get("bookid")){
            if(bookupdate.get("bookname")!=null){
               bookinfo.setBookname((String)bookupdate.get("bookname"));
               return true;
            }
            if(bookupdate.get("publisher")!=null){
               bookinfo.setPubliser((String)bookupdate.get("publisher"));
               return true;
            }
            if(bookupdate.get("genre")!=null){
               bookinfo.setGenre_seq((Integer)bookupdate.get("genre"));
               return true;
            }
            
         }
         
      }
      return false;
   }
   

   //도서삭제구현
   public boolean bookDelete(int book_seq) {
      for(BookVO bookid : booklist){
         if((Integer)bookid.getSeq()==(Integer)book_seq){
            //블랙리스트에 해당 아이디가 존재할경우 삭제
            System.out.println(book_seq);
            return booklist.remove(bookid);
         }
      }
      return false;
   }
   
//   public int mybookReturn(int seq) {      //TODO
//       BookVO bk = bookcc(seq)  ;
//       if (sta ) {
//          user.setActivity(false);
//          return 1;
//       }
//       return 0;
//    }
   
//   public BookVO bookcc(int seq) {
//       for (BookVO bk :this.booklist) {
//          if (bk.getSeq()==seq)
//             return bk;
//             
//          }
//       return null;
//   }
	//장소리스트
	public List<Total_rentVO> rentlist() {
		return lo_rentList;
	}
	//adimin장소업데이트
	public boolean updatePlace(Map<String, Object> statusChange) {
		for(PlaceTotalVO placeUp : lockerList){
//			System.out.println((Integer)statusChange.get("parameter"));
			if((Integer)placeUp.getPlace() == (Integer)statusChange.get("parameter")){//사물함인지 열람실인지비교
				if((Integer)placeUp.getPx() == statusChange.get("bang")&&placeUp.getPy() == (Integer)statusChange.get("seat")){//방번호와 x 자리번호y가 둘다잇을경우비교
					for(Total_rentVO total : lo_rentList){
						if(placeUp.getSeq() == total.getPt_seq()){
							placeUp.setPt_status((Integer)statusChange.get("status"));//스테이터스를 변경해준다.
							System.out.println("리스트도 수정됨");
							return lo_rentList.remove(total);	
						}
					}
				}
			}
		}
		return false;
	}

	
	
	public List<Total_rentVO> cabinetRent(PlaceTotalVO placeTotalVO) {
		// TODO Auto-generated method stub
		return null;
	}
	public int useSview(Map<Integer, Integer> suse) {
		// TODO Auto-generated method stub
		return 0;
	}
	public List<PlaceTotalVO> seatStatus(PlaceTotalVO placeTotalVO) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Total_rentVO> seatInfromation(Total_rentVO total_rentVO) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<NoticeVO> useSview(NoticeVO noticeVO) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<NoticeVO> noticeContent(NoticeVO noticeVO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//공지사항 등록
   public boolean notifyAdd(NoticeVO noticeadd) {
      return noticeList.add(noticeadd);
   }
   //공지사항 삭제
   public boolean notifyDelete(int notice_Seq) {
      for(NoticeVO noticeid : noticeList){
         if(noticeid.getSeq()==notice_Seq){
            //블랙리스트에 해당 아이디가 존재할경우 삭제
            return noticeList.remove(noticeid);
         }
      }
      return false;
   }
   
   //공지사항 수정
   public boolean notifyUpdate(Map<String, Object> noticechange) {
       for(NoticeVO noticeinfo : noticeList){
          if((Integer)noticeinfo.getSeq() == noticechange.get("noticenum")){
             if(noticechange.get("title")!=null){
                noticeinfo.setTitle((String)noticechange.get("title"));
                return true;
             }
             if(noticechange.get("content")!=null){
                noticeinfo.setContent((String)noticechange.get("content"));
                return true;
             }
             
          }
             
       }
       return false;
    }
	   
     public boolean bookReturnView(int seq) {
	       for(int i=0; i<bk_lentList.size(); i++){
	        if(bk_lentList.get(i).getSeq() == seq){
		          bk_lentList.remove(i).getSeq();
		          booklist.get(seq).setBook_state(true);
		          return true;
	        }
	     }         
	     return false;
	  }
	  
  
     /**
      * 공지사항 상세정보
      * @return
      */
     public List<NoticeVO> noticeList() {
    	
 		return noticeList;
 	}
     
    public List<Bk_rentVO> bookRentList(String id) {
 		for(Bk_rentVO bk : bk_lentList){
 			for(BookVO book : booklist){
 				if(bk.getUser_id().equals(id)){
 				}
 			}
 				return bk_lentList;
 		}	
 		return null;
 	}
	public List<Total_rentVO> totalList() {
		return lo_rentList;
	}
	
	public List<PlaceTotalVO> placeList() {
		return lockerList;
	}
	
	public List<Total_rentVO> totlaRentList(String userid) {
		for(Total_rentVO userRent : lo_rentList){
	
			return lo_rentList;
		}
		return null;
	}
	
	
	
	

	//관리자 초기화


	//회원목록초기화
	//회원목록초기화
	{	
		UserVO user1 = new UserVO();
		user1.setId("tkd753");
		user1.setName("김정원");
		user1.setPw("kjw12345");
		user1.setTel("010-2587-6180");
		user1.setActivity(true);
		userList.add(user1);

		UserVO user2 = new UserVO();
		user2.setId("gk753");
		user2.setName("홍길동");
		user2.setPw("kjw12345");
		user2.setTel("010-1111-2222");
		user2.setActivity(true);
		userList.add(user2);
		
		UserVO user3 = new UserVO();
		user3.setId("namhun753");
		user3.setName("강남훈");
		user3.setPw("knh12345");
		user3.setTel("010-2342-4673");
		user3.setActivity(true);
		userList.add(user3);
		
		UserVO user4 = new UserVO();
		user4.setId("hungu753");
		user4.setName("김현주");
		user4.setPw("khj12345");
		user4.setTel("010-2345-6323");
		user4.setActivity(true);
		userList.add(user4);
		
		UserVO user5 = new UserVO();
		user5.setId("qk753");
		user5.setName("김지훈");
		user5.setPw("kjw12345");
		user5.setTel("010-1341-5124");
		user5.setActivity(true);
		userList.add(user5);
		
		UserVO user6 = new UserVO();
		user6.setId("wk753");
		user6.setName("김미소");
		user6.setPw("kjw12345");
		user6.setTel("010-2345-2342");
		user6.setActivity(true);
		userList.add(user6);
		
		UserVO user7 = new UserVO();
		user7.setId("ek753");
		user7.setName("상의준");
		user7.setPw("kjw12345");
		user7.setTel("010-2352-5232");
		user7.setActivity(true);
		userList.add(user7);
		
		UserVO user8 = new UserVO();
		user8.setId("rk753");
		user8.setName("이재철");
		user8.setPw("kjw12345");
		user8.setTel("010-1235-3452");
		user8.setActivity(true);
		userList.add(user8);
		
		UserVO user9 = new UserVO();
		user9.setId("yk753");
		user9.setName("김민수");
		user9.setPw("kjw12345");
		user9.setTel("010-2347-2342");
		user9.setActivity(true);
		userList.add(user9);
		
		UserVO user10 = new UserVO();
		user10.setId("uk753");
		user10.setName("강진원");
		user10.setPw("kjw12345");
		user10.setTel("010-1242-6578");
		user10.setActivity(true);
		userList.add(user10);
			
	}
	//블랙리스트 목록  초기화
	{
		BlackListVO bl1 = new BlackListVO();
		Calendar a = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		a.set(2020, 0, 26);
		b.set(2020, 1, 2);
		bl1.setSeq(1);
		bl1.setUser_id("rk753");
		bl1.setStart(a.getTime());
		bl1.setEnd(b.getTime());
		blackList.add(bl1);
		
		
		BlackListVO bl2 = new BlackListVO();
		Calendar c = Calendar.getInstance();
		Calendar d = Calendar.getInstance();
		c.set(2020, 0, 26);
		d.set(2020, 1, 2);
		bl2.setSeq(2);
		bl2.setUser_id("ek753");
		bl2.setStart(c.getTime());
		bl2.setEnd(d.getTime());
		blackList.add(bl2);
		
		
		BlackListVO bl3 = new BlackListVO();
		Calendar e = Calendar.getInstance();
		Calendar f = Calendar.getInstance();
		e.set(2020, 0, 26);
		f.set(2020, 1, 2);
		bl3.setSeq(3);
		bl3.setUser_id("yk753");
		bl3.setStart(e.getTime());
		bl3.setEnd(f.getTime());
		blackList.add(bl3);
		
		
		BlackListVO bl4 = new BlackListVO();
		Calendar g = Calendar.getInstance();
		Calendar h = Calendar.getInstance();
		g.set(2020, 0, 26);
		h.set(2020, 1, 2);
		bl4.setSeq(4);
		bl4.setUser_id("uk753");
		bl4.setStart(g.getTime());
		bl4.setEnd(h.getTime());
		blackList.add(bl4);
		
		
		BlackListVO bl5 = new BlackListVO();
		Calendar i = Calendar.getInstance();
		Calendar j = Calendar.getInstance();
		i.set(2020, 0, 26);
		j.set(2020, 1, 2);
		bl5.setSeq(5);
		bl5.setUser_id("wk753");
		bl5.setStart(i.getTime());
		bl5.setEnd(j.getTime());
		blackList.add(bl5);
	}
	//도서목록초기화
	{
		BookVO bkv1 = new BookVO();
		bkv1.setSeq(1);
		bkv1.setBookname("어린왕자");
		bkv1.setPubliser("동화출판사");
		bkv1.setBook_state(false);
		bkv1.setBookActivate(true);
		bkv1.setGenre_seq(1);
		booklist.add(bkv1);
		
		BookVO bkv2 = new BookVO();
		bkv2.setSeq(2);
		bkv2.setBookname("파브로곤충기");
		bkv2.setPubliser("동화출판사");
		bkv2.setBook_state(true);
		bkv2.setBookActivate(true);
		bkv2.setGenre_seq(3);
		booklist.add(bkv2);
		
		BookVO bkv3 = new BookVO();
		bkv3.setSeq(3);
		bkv3.setBookname("1984");
		bkv3.setPubliser("문예출판사");
		bkv3.setBook_state(true);
		bkv3.setBookActivate(true);
		bkv3.setGenre_seq(3);
		booklist.add(bkv3);
		
		BookVO bkv4 = new BookVO();
		bkv4.setSeq(4);
		bkv4.setBookname("군주론");
		bkv4.setPubliser("서울대학교출판문화원");
		bkv4.setBook_state(true);
		bkv4.setBookActivate(true);
		bkv4.setGenre_seq(7);
		booklist.add(bkv4);
		
		BookVO bkv5 = new BookVO();
		bkv5.setSeq(5);
		bkv5.setBookname("긍정의배신");
		bkv5.setPubliser("북키");
		bkv5.setBook_state(true);
		bkv5.setBookActivate(true);
		bkv5.setGenre_seq(3);
		booklist.add(bkv5);
		
		BookVO bkv6 = new BookVO();
		bkv6.setSeq(6);
		bkv6.setBookname("논어");
		bkv6.setPubliser("글항아리");
		bkv6.setBook_state(true);
		bkv6.setBookActivate(true);
		bkv6.setGenre_seq(6);
		booklist.add(bkv6);
		
		BookVO bkv7 = new BookVO();
		bkv7.setSeq(7);
		bkv7.setBookname("대담:인문학과자연과학이만나다");
		bkv7.setPubliser("휴머니스트");
		bkv7.setBook_state(true);
		bkv7.setBookActivate(true);
		bkv7.setGenre_seq(3);
		booklist.add(bkv7);
		
		BookVO bkv8 = new BookVO();
		bkv8.setSeq(8);
		bkv8.setBookname("독일인의사랑");
		bkv8.setPubliser("지식의숲");
		bkv8.setBook_state(true);
		bkv8.setBookActivate(true);
		bkv8.setGenre_seq(1);
		booklist.add(bkv8);
		
		BookVO bkv9 = new BookVO();
		bkv9.setSeq(9);
		bkv9.setBookname("뒹구는돌은언재 잠깨는가");
		bkv9.setPubliser("문학과지성사");
		bkv9.setBook_state(true);
		bkv9.setBookActivate(true);
		bkv9.setGenre_seq(8);
		booklist.add(bkv9);
		
		BookVO bkv10 = new BookVO();
		bkv10.setSeq(10);
		bkv10.setBookname("무소유");
		bkv10.setPubliser("범우사");
		bkv10.setBook_state(true);
		bkv10.setBookActivate(true);
		bkv10.setGenre_seq(5);
		booklist.add(bkv10);
	}
		
	
/*	//도서대여목록초기화
	
	{	
		Bk_rentVO bkr1 = new Bk_rentVO();
		Calendar b1 = Calendar.getInstance();
		Calendar b2 = Calendar.getInstance();
		b1.set(2020,0,26);
		b2.set(2020,0,26);
		bkr1.setSeq(1);
		bkr1.setStart(b1.getTime());//대여일자
		bkr1.setEnd(b2.getTime());//반납일자
		bkr1.setBook_seq(1);
		bk_lentList.add(bkr1);
		
		Bk_rentVO bkr2 = new Bk_rentVO();
		Calendar b3 = Calendar.getInstance();
		Calendar b4 = Calendar.getInstance();
		b3.set(2020,0,26);
		b4.set(2020,0,26);
		bkr2.setSeq(1);
		bkr2.setStart(b3.getTime());//대여일자
		bkr2.setEnd(b4.getTime());//반납일자
		bkr2.setBook_seq(1);
		bk_lentList.add(bkr2);
		
		Bk_rentVO bkr3 = new Bk_rentVO();
		Calendar b5 = Calendar.getInstance();
		Calendar b6 = Calendar.getInstance();
		b5.set(2020,0,26);
		b6.set(2020,0,26);
		bkr3.setSeq(1);
		bkr3.setStart(b5.getTime());//대여일자
		bkr3.setEnd(b6.getTime());//반납일자
		bkr3.setBook_seq(1);
		bk_lentList.add(bkr3);
		
		Bk_rentVO bkr4 = new Bk_rentVO();
		Calendar b7 = Calendar.getInstance();
		Calendar b8 = Calendar.getInstance();
		b7.set(2020,0,26);
		b8.set(2020,0,26);
		bkr4.setSeq(1);
		bkr4.setStart(b7.getTime());//대여일자
		bkr4.setEnd(b8.getTime());//반납일자
		bkr4.setBook_seq(1);
		bk_lentList.add(bkr4);
		
		Bk_rentVO bkr5 = new Bk_rentVO();
		Calendar b9 = Calendar.getInstance();
		Calendar b10 = Calendar.getInstance();
		b9.set(2020,0,26);
		b10.set(2020,0,26);
		bkr5.setSeq(1);
		bkr5.setStart(b9.getTime());//대여일자
		bkr5.setEnd(b10.getTime());//반납일자
		bkr5.setBook_seq(1);
		bk_lentList.add(bkr5);
		
		Bk_rentVO bkr6 = new Bk_rentVO();
		Calendar b11 = Calendar.getInstance();
		Calendar b12 = Calendar.getInstance();
		b11.set(2020,0,26);
		b12.set(2020,0,26);
		bkr6.setSeq(1);
		bkr6.setStart(b11.getTime());//대여일자
		bkr6.setEnd(b12.getTime());//반납일자
		bkr6.setBook_seq(1);
		bk_lentList.add(bkr6);
		
		Bk_rentVO bkr7 = new Bk_rentVO();
		Calendar b13 = Calendar.getInstance();
		Calendar b14 = Calendar.getInstance();
		b13.set(2020,0,26);
		b14.set(2020,0,26);
		bkr7.setSeq(1);
		bkr7.setStart(b13.getTime());//대여일자
		bkr7.setEnd(b14.getTime());//반납일자
		bkr7.setBook_seq(1);
		bk_lentList.add(bkr7);
		
		Bk_rentVO bkr8 = new Bk_rentVO();
		Calendar b15 = Calendar.getInstance();
		Calendar b16 = Calendar.getInstance();
		b15.set(2020,0,26);
		b16.set(2020,0,26);
		bkr8.setSeq(1);
		bkr8.setStart(b15.getTime());//대여일자
		bkr8.setEnd(b16.getTime());//반납일자
		bkr8.setBook_seq(1);
		bk_lentList.add(bkr8);
		
		Bk_rentVO bkr9 = new Bk_rentVO();
		Calendar b17 = Calendar.getInstance();
		Calendar b18 = Calendar.getInstance();
		b17.set(2020,0,26);
		b18.set(2020,0,26);
		bkr9.setSeq(1);
		bkr9.setStart(b17.getTime());//대여일자
		bkr9.setEnd(b18.getTime());//반납일자
		bkr9.setBook_seq(1);
		bk_lentList.add(bkr9);
		
		
		Bk_rentVO bkr10 = new Bk_rentVO();
		Calendar b19 = Calendar.getInstance();
		Calendar b20 = Calendar.getInstance();
		b19.set(2020,0,26);
		b20.set(2020,0,26);
		bkr10.setSeq(1);
		bkr10.setStart(b19.getTime());//대여일자
		bkr10.setEnd(b20.getTime());//반납일자
		bkr10.setBook_seq(1);
		bk_lentList.add(bkr10);
		
		

		
	}*/
	//도서 장르 목록  초기화
	{
		GenreVO gv1 = new GenreVO();
		gv1.setSeq(1);
		gv1.setName("소설");
		genrelist.add(gv1);
		
		GenreVO gv2 = new GenreVO();
		gv2.setSeq(2);
		gv2.setName("시");
		genrelist.add(gv2);
		
		GenreVO gv3 = new GenreVO();
		gv3.setSeq(3);
		gv3.setName("에세이");
		genrelist.add(gv3);
		
		GenreVO gv4 = new GenreVO();
		gv4.setSeq(4);
		gv4.setName("경제");
		genrelist.add(gv4);
		
		GenreVO gv5 = new GenreVO();
		gv5.setSeq(5);
		gv5.setName("자기계발");
		genrelist.add(gv5);
		
		GenreVO gv6 = new GenreVO();
		gv6.setSeq(6);
		gv6.setName("역사");
		genrelist.add(gv6);
		
		GenreVO gv7 = new GenreVO();
		gv7.setSeq(7);
		gv7.setName("정치");
		genrelist.add(gv7);
		
		GenreVO gv8 = new GenreVO();
		gv8.setSeq(8);
		gv8.setName("예술");
		genrelist.add(gv8);
		
		GenreVO gv9 = new GenreVO();
		gv9.setSeq(9);
		gv9.setName("과학");
		genrelist.add(gv9);
		
		GenreVO gv10 = new GenreVO();
		gv10.setSeq(10);
		gv10.setName("컴퓨터");
		genrelist.add(gv10);
		
	}
	//로커 열람실 종합 목록 초기화
	{
		//사물함
		PlaceTotalVO pt1 = new PlaceTotalVO();
		pt1.setSeq(1);//자리 seq
		pt1.setPt_status(1);//사용중
		pt1.setPlace(0);//사물함
		pt1.setPx(1);//px
		pt1.setPy(1);//py
		lockerList.add(pt1);
		
		PlaceTotalVO pt2 = new PlaceTotalVO();
		pt2.setSeq(2);
		pt2.setPt_status(1); 
		pt2.setPlace(0);//사물함
		pt2.setPx(1);
		pt2.setPy(2);
		lockerList.add(pt2);
		
		PlaceTotalVO pt3 = new PlaceTotalVO();
		pt3.setSeq(3);
		pt3.setPt_status(1); 
		pt3.setPlace(0);//사물함
		pt3.setPx(1);
		pt3.setPy(3);
		lockerList.add(pt3);
		
		PlaceTotalVO pt4 = new PlaceTotalVO();
		pt4.setSeq(4);
		pt4.setPt_status(1); 
		pt4.setPlace(0);//사물함
		pt4.setPx(1);
		pt4.setPy(4);
		lockerList.add(pt4);
		
		PlaceTotalVO pt5 = new PlaceTotalVO();
		pt5.setSeq(5);
		pt5.setPt_status(1); 
		pt5.setPlace(0);//사물함
		pt5.setPx(1);
		pt5.setPy(5);
		lockerList.add(pt5);
		
		PlaceTotalVO pt6 = new PlaceTotalVO();
		pt6.setSeq(6);
		pt6.setPt_status(0); 
		pt6.setPlace(0);
		pt6.setPx(1);
		pt6.setPy(6);
		lockerList.add(pt6);
		
		PlaceTotalVO pt7 = new PlaceTotalVO();
		pt7.setSeq(7);
		pt7.setPt_status(0); 
		pt7.setPlace(0);
		pt7.setPx(1);
		pt7.setPy(7);
		lockerList.add(pt7);
		
		PlaceTotalVO pt8 = new PlaceTotalVO();
		pt8.setSeq(8);
		pt8.setPt_status(0); 
		pt8.setPlace(0);
		pt8.setPx(1);
		pt8.setPy(8);
		lockerList.add(pt8);

		PlaceTotalVO pt9 = new PlaceTotalVO();
		pt9.setSeq(9);
		pt9.setPt_status(0); 
		pt9.setPlace(0);
		pt9.setPx(1);
		pt9.setPy(9);
		lockerList.add(pt9);
		
		PlaceTotalVO pt10 = new PlaceTotalVO();
		pt10.setSeq(10);
		pt10.setPt_status(0); 
		pt10.setPlace(0);
		pt10.setPx(1);
		pt10.setPy(10);
		lockerList.add(pt10);
		
		//열람실공간
		PlaceTotalVO pt11 = new PlaceTotalVO();
		pt11.setSeq(11);//자리 seq
		pt11.setPt_status(0);//사용중
		pt11.setPlace(1);//사물함
		pt11.setPx(2);//px
		pt11.setPy(1);//py
		lockerList.add(pt11);
		
		PlaceTotalVO pt12 = new PlaceTotalVO();
		pt12.setSeq(12);
		pt12.setPt_status(0); 
		pt12.setPlace(1);//사물함
		pt12.setPx(2);
		pt12.setPy(2);
		lockerList.add(pt12);
		
		PlaceTotalVO pt13 = new PlaceTotalVO();
		pt13.setSeq(13);
		pt13.setPt_status(0); 
		pt13.setPlace(1);//사물함
		pt13.setPx(2);
		pt13.setPy(3);
		lockerList.add(pt13);
		
		PlaceTotalVO pt14 = new PlaceTotalVO();
		pt14.setSeq(14);
		pt14.setPt_status(0); 
		pt14.setPlace(1);//사물함
		pt14.setPx(2);
		pt14.setPy(4);
		lockerList.add(pt14);
		
		PlaceTotalVO pt15 = new PlaceTotalVO();
		pt15.setSeq(15);
		pt15.setPt_status(0); 
		pt15.setPlace(1);//사물함
		pt15.setPx(2);
		pt15.setPy(5);
		lockerList.add(pt15);
		
		PlaceTotalVO pt16 = new PlaceTotalVO();
		pt16.setSeq(16);
		pt16.setPt_status(0); 
		pt16.setPlace(1);
		pt16.setPx(2);
		pt16.setPy(6);
		lockerList.add(pt16);
		
		PlaceTotalVO pt17 = new PlaceTotalVO();
		pt17.setSeq(17);
		pt17.setPt_status(0); 
		pt17.setPlace(1);
		pt17.setPx(2);
		pt17.setPy(7);
		lockerList.add(pt17);
		
		PlaceTotalVO pt18 = new PlaceTotalVO();
		pt18.setSeq(18);
		pt18.setPt_status(0); 
		pt18.setPlace(1);
		pt18.setPx(2);
		pt18.setPy(8);
		lockerList.add(pt18);

		PlaceTotalVO pt19 = new PlaceTotalVO();
		pt19.setSeq(19);
		pt19.setPt_status(0); 
		pt19.setPlace(1);
		pt19.setPx(2);
		pt19.setPy(9);
		lockerList.add(pt19);
		
		PlaceTotalVO pt20 = new PlaceTotalVO();
		pt20.setSeq(20);
		pt20.setPt_status(0); 
		pt20.setPlace(1);
		pt20.setPx(2);
		pt20.setPy(10);
		lockerList.add(pt20);
	}
	//로커대여목록초기화
	
	{	
		Total_rentVO pr1 = new Total_rentVO();
		Calendar reng1 = Calendar.getInstance();//캘린더 생성
		Calendar reng2 = Calendar.getInstance();//캘린더 생성
		pr1.setSeq(1);
		reng1.set(2021,0,26);
		reng2.set(2021,1,2);
		pr1.setStart(reng1.getTime());
		pr1.setEnd(reng2.getTime());
		pr1.setPt_seq(1);
		pr1.setUserid("tkd753");
		lo_rentList.add(pr1);
		
		Total_rentVO pr2 = new Total_rentVO();
		Calendar reng3 = Calendar.getInstance();//캘린더 생성
		Calendar reng4 = Calendar.getInstance();//캘린더 생성
		pr2.setSeq(2);
		reng3.set(2021,0,27);
		reng4.set(2021,1,3);
		pr2.setStart(reng3.getTime());
		pr2.setEnd(reng4.getTime());
		pr2.setPt_seq(2);
		pr2.setUserid("gk753");
		lo_rentList.add(pr2);
		
		Total_rentVO pr3 = new Total_rentVO();
		Calendar reng5 = Calendar.getInstance();//캘린더 생성
		Calendar reng6 = Calendar.getInstance();//캘린더 생성
		pr3.setSeq(3);
		reng5.set(2021,0,30);
		reng6.set(2021,1,7);
		pr3.setStart(reng5.getTime());
		pr3.setEnd(reng6.getTime());
		pr3.setPt_seq(3);
		pr3.setUserid("namhun753");
		lo_rentList.add(pr3);
		
		Total_rentVO pr4 = new Total_rentVO();
		Calendar reng7 = Calendar.getInstance();//캘린더 생성
		Calendar reng8 = Calendar.getInstance();//캘린더 생성
		pr4.setSeq(4);
		reng7.set(2021,0,31);
		reng8.set(2021,1,8);
		pr4.setStart(reng7.getTime());
		pr4.setEnd(reng8.getTime());
		pr4.setPt_seq(4);
		pr4.setUserid("khj12345");
		lo_rentList.add(pr4);
		
		Total_rentVO pr5 = new Total_rentVO();
		pr5.setSeq(5);
		Calendar reng9 = Calendar.getInstance();//캘린더 생성
		Calendar reng10 = Calendar.getInstance();//캘린더 생성
		reng9.set(2021,2,2);
		reng10.set(2021,2,07);
		pr5.setStart(reng9.getTime());
		pr5.setEnd(reng10.getTime());
		pr5.setPt_seq(5);
		pr5.setUserid("wk753");
		lo_rentList.add(pr5);
		
		
	}	
	{
		NoticeVO nt1 = new NoticeVO();
		nt1.setSeq(1);
		nt1.setTitle("도서관은 이용관련 사항");
		nt1.setContent("도서관은 깨끗이 이용해주시기 바랍니다");
		Calendar ntds1 = Calendar.getInstance();//캘린더 생성
		ntds1.set(2021,0,28);
		nt1.setDate(ntds1.getTime());
		noticeList.add(nt1);
		
		NoticeVO nt2 = new NoticeVO();
		nt2.setSeq(2);
		nt2.setTitle("도서관은 이용관련 사항");
		nt2.setContent("도서관에서는 정숙해주세요");
		Calendar ntds2 = Calendar.getInstance();//캘린더 생성
		ntds2.set(2021,0,28);
		nt2.setDate(ntds1.getTime());
		noticeList.add(nt2);
		
		NoticeVO nt3 = new NoticeVO();
		nt3.setSeq(3);
		nt3.setTitle("도서관은 이용관련 사항");
		nt3.setContent("도서관을 이용한 후 쓰레기는 가지고 가주세요");
		Calendar ntds3 = Calendar.getInstance();//캘린더 생성
		ntds3.set(2021,0,28);
		nt3.setDate(ntds1.getTime());
		noticeList.add(nt3);
		
		NoticeVO nt4 = new NoticeVO();
		nt4.setSeq(4);
		nt4.setTitle("도서관은 이용관련 사항");
		nt4.setContent("도서관에서 뛰어다니지 마세요");
		Calendar ntds4 = Calendar.getInstance();//캘린더 생성
		ntds4.set(2021,0,28);
		nt4.setDate(ntds1.getTime());
		noticeList.add(nt4);
		
		NoticeVO nt5 = new NoticeVO();
		nt5.setSeq(5);
		nt5.setTitle("도서관은 이용관련 사항");
		nt5.setContent("도서관 문의사항은 사서에게 문의하시기 바랍니다");
		Calendar ntds5 = Calendar.getInstance();//캘린더 생성
		ntds5.set(2021,0,28);
		nt5.setDate(ntds1.getTime());
		noticeList.add(nt5);
		
		NoticeVO nt6 = new NoticeVO();
		nt6.setSeq(6);
		nt6.setTitle("도서관은 이용관련 사항");
		nt6.setContent("도서관 사물함 이용 후 깨끗이 정리해주시길 바랍니다");
		Calendar ntds6 = Calendar.getInstance();//캘린더 생성
		ntds6.set(2021,0,28);
		nt6.setDate(ntds1.getTime());
		noticeList.add(nt6);
		
		NoticeVO nt7 = new NoticeVO();
		nt7.setSeq(7);
		nt7.setTitle("도서관은 이용관련 사항");
		nt7.setContent("도서관 열람실 이용 후 깨끗이 정리해주시길 바랍니다");
		Calendar ntds7 = Calendar.getInstance();//캘린더 생성
		ntds7.set(2021,0,28);
		nt7.setDate(ntds1.getTime());
		noticeList.add(nt7);
		
		NoticeVO nt8 = new NoticeVO();
		nt8.setSeq(8);
		nt8.setTitle("도서관은 이용관련 사항");
		nt8.setContent("도서관 휴관날 안내");
		Calendar ntds8 = Calendar.getInstance();//캘린더 생성
		ntds8.set(2021,0,28);
		nt8.setDate(ntds1.getTime());
		noticeList.add(nt8);
		
		NoticeVO nt9 = new NoticeVO();
		nt9.setSeq(9);
		nt9.setTitle("도서관은 이용관련 사항");
		nt9.setContent("도서관은 모두가 이용하는 공공기관입니다");
		Calendar ntd9 = Calendar.getInstance();//캘린더 생성
		ntd9.set(2021,0,28);
		nt9.setDate(ntds1.getTime());
		noticeList.add(nt9);
		
		NoticeVO nt10 = new NoticeVO();
		nt10.setSeq(10);
		nt10.setTitle("도서관은 이용관련 사항");
		nt10.setContent("도서관은 조용히 이용해 주세요");
		Calendar ntds10 = Calendar.getInstance();//캘린더 생성
		ntds10.set(2021,0,28);
		nt10.setDate(ntds1.getTime());
		noticeList.add(nt10);
	}
	
	
}

