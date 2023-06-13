package address;

import java.util.ArrayList;

public class AddrBookDAO {
	// 자료를 저장할 ArrayList 생성
	private ArrayList<AddrBook> addrList = new ArrayList<>();
	
	// 자료 삽입
	public void add(AddrBook addrBook) {
		addrList.add(addrBook);
	}
	
	// 자료 목록 조회(출력)
	public ArrayList<AddrBook> getList() {
		return addrList;
	}
	
	// 주소 상세보기
	public AddrBook getAddrBook(String username) {
		AddrBook addrBook = null;
		for(int i=0; i<addrList.size(); i++) {
			String dbUsername = addrList.get(i).getusername();
			if(dbUsername.equals(username)) { // 외부 입력된 이름과 일치하면
				addrBook = addrList.get(i);
				break;
			}
		}
		return addrBook;
	}
	
	// 주소 삭제하기
	public void deleteAddrBook(String username) {
		AddrBook addrBook = null;
		for(int i=0; i<addrList.size(); i++) {
			String dbUsername = addrList.get(i).getusername();
			if(dbUsername.equals(username)) { // 외부 입력된 이름과 일치하면
				addrBook = addrList.get(i);
				addrList.remove(addrBook);
				break;
			}
		}
	}
	
}
