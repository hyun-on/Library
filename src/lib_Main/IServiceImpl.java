package lib_Main;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lib_Database.Database;
import lib_Vo.Bk_rentVO;
import lib_Vo.BlackListVO;
import lib_Vo.BookVO;
import lib_Vo.GenreVO;
import lib_Vo.NoticeVO;
import lib_Vo.PlaceTotalVO;
import lib_Vo.Total_rentVO;
import lib_Vo.UserVO;

public class IServiceImpl implements IService{
	Database database = new Database();

	@Override
	public boolean insertUser(UserVO userVo) {
		// TODO Auto-generated method stub
		return database.insertUser(userVo);
	}
	//유저로그인
	@Override
	public UserVO userLogin(Map<String, String> userlogin) {
		// TODO Auto-generated method stub
		return database.userLogin(userlogin);
	}
	//관리자 로그인
	@Override
	public boolean adiminLogin(Map<String, String> adiminlogin) {
		// TODO Auto-generated method stub
		return database.adiminLogin(adiminlogin);
	}
	
	@Override
	public boolean checkId(String id) {
		return database.userIdCheck(id);
	}
	@Override
	public List<UserVO> selectAllUser() {
		return database.selectAllUser();
	}
	
	@Override
	public boolean userupdate(Map<String, Object> userinfor) {
		// TODO Auto-generated method stub
		return database.userupdate(userinfor);
	}
	@Override
	public BookVO bookcc(int seq) {
	      return database.bookcc(seq);
	   }
//	@Override
//	public int changePw(Map<String, Object> changepw) {
//		// TODO Auto-generated method stub
//		return database.userChangePw(changepw);
//	}
//
//	@Override
//	public int changeTel(Map<String, Object> changetel) {
//		// TODO Auto-generated method stub
//		return database.userChageTel(changetel);
//	}

	@Override
	public int userExit(String string) {
		// TODO Auto-generated method stub
		return database.userExit(string);
	}

	@Override
	public List<BlackListVO> blackList() {
		return database.blackList();
	}

	@Override
	public boolean createBlackList(String id) {
		// TODO Auto-generated method stub
		return database.createBlackList(id);
	}

	@Override
	public boolean deleteBlackList(String id) {
		// TODO Auto-generated method stub
		return database.deleteBlackList(id);
	}
	
	@Override
	public BookVO seletbookList(String book) {
		// TODO Auto-generated method stub
		return database.seletbookList(book);
	}
	@Override
	public GenreVO selectgenreList(String genre) {
		// TODO Auto-generated method stub
		return database.selectgenreList(genre);
	}

	@Override
	public List<BookVO> bookCheck() {
		// TODO Auto-generated method stub
		return database.bookCheck();
	}

	@Override
	public boolean bookRental(Bk_rentVO rent) {
		return database.bookRental(rent);
	}
	@Override
    public List<Bk_rentVO> mybookView(String id) {
      // TODO Auto-generated method stub
      return database.mybookView(id);
    }
	@Override
	public List<Bk_rentVO> myBook(String mybookrent) {
		// TODO Auto-generated method stub
		return database.myBook(mybookrent);
	}

	@Override
	public boolean mybookReturn(Map<String, String> bookreturn) {
		// TODO Auto-generated method stub
		return database.mybookReturn(bookreturn);
	}

//	@Override
//	public List<Bk_rentVO> bookRentalList(String id) {
//		// TODO Auto-generated method stub
//		return database.bookRentalList(id);
//	}

	@Override
	public boolean bookAdd(BookVO bookadd) {
		return database.bookAdd(bookadd);
	}

	@Override
	public boolean bookUpdate(Map<String, Object> bookupdate) {
		// TODO Auto-generated method stub
		return database.bookUpdate(bookupdate);
	}

	@Override
	public boolean bookDelete(int book_seq) {
		// TODO Auto-generated method stub
		return database.bookDelete(book_seq);
	}

	@Override
	//장소 등록
	public boolean cabinetPick(Map<String, Object> statusChange) {
		return database.cabinetPick(statusChange);
	}

	@Override
	public int useSview(Map<Integer, Integer> Suse) {
		// TODO Auto-generated method stub
		return database.useSview(Suse);
	}

	@Override
	public List<PlaceTotalVO> seatStatus(PlaceTotalVO placeTotalVO) {
		// TODO Auto-generated method stub
		return database.seatStatus(placeTotalVO);
	}

	@Override
	public List<Total_rentVO> seatInfromation(Total_rentVO total_rentVO) {
		// TODO Auto-generated method stub
		return database.seatInfromation(total_rentVO);
	}

	@Override
	public List<NoticeVO> useSview(NoticeVO noticeVO) {
		// TODO Auto-generated method stub
		return database.useSview(noticeVO);
	}

	@Override
	public List<NoticeVO> noticeContent(NoticeVO noticeVO) {
		// TODO Auto-generated method stub
		return database.noticeContent(noticeVO);
	}

	@Override
   public boolean notifyUpdate(Map<String, Object> noticechange) {
      return database.notifyUpdate(noticechange);
   }

   @Override
   public boolean notifyAdd(NoticeVO noticeadd) {
      return database.notifyAdd(noticeadd);
   }

   @Override
   public boolean notifyDelete(int notice_seq) {
      return database.notifyDelete(notice_seq);
   }


	@Override
	public List<BookVO> bookList() {
		// TODO Auto-generated method stub
		return database.bookList();
	}
	@Override
	public List<GenreVO> genreList() {
		// TODO Auto-generated method stub
		return database.genreList();
	}
	@Override
	//장소전체리스트
	public List<PlaceTotalVO> placelist() {
		return database.placelist();
	}
	//장소대여전체리스트
	public List<Total_rentVO> rentlist() {
		return database.rentlist();
	}
	//장소상태수정
	public boolean updatePlace(Map<String, Object> statusChange) {
		return database.updatePlace(statusChange);
	}
	// 유저 대여한 자리 사용중으로 변경
	public boolean 	updatePRent(Map<String, Object> statusChange) {
		return database.updatePRent(statusChange);
	}
	// 유저 사용중인 사물함 제거
	public boolean myPlaceDel(Map<String, Object> placedel) {
		return database.myPlaceDel(placedel);
	}
	@Override
	public boolean bookRental(BookVO bookinsert) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Bk_rentVO> bookRentalList(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean bookReturnView(int seq) {
		return database.bookReturnView(seq);
	}
	@Override
	public List<NoticeVO> noticeList() {
		return database.noticeList();
	}
	@Override
	public List<Bk_rentVO> bookRentList(String id) {
		return database.bookRentList(id);
	}
	
	@Override
	public List<Total_rentVO> totalList() {
		return database.totalList();
	}
	
	@Override
	public List<PlaceTotalVO> placeList() {
		return database.placeList();
	}
	@Override
	public List<Total_rentVO> totalRentList(String userid) {
		return database.totlaRentList(userid);
	}
	@Override
	public List<Total_rentVO> totalRentList() {
		// TODO Auto-generated method stub
		return null;
	}



	

	
	
}
