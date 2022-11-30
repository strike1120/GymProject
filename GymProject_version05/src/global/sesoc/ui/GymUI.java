package global.sesoc.ui;

import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import global.sesoc.service.GymService;
import global.sesoc.service.GymServiceImpl;
import global.sesoc.vo.GymVO;

public class GymUI {
	Scanner keyin = new Scanner(System.in);
	GymService service = new GymServiceImpl(); //has-a관계 :클래스안에 다른 클래스가 포함되어있는 관계 짐유아이가 서비스를 가지고 있다. / 
	
	//생성자
	public GymUI() { //생성자는 여러번 호출되지 않는다. Main에서 딱 한번만 호출된다.
		service.loadFile(); //데이터를 읽어서 ArrayList에 가져다 넣는다.
		String choice;
		
		while(true) { //무한히 돌면서 메뉴를 띄워야함
			menu();
			choice = keyin.next();
			
			switch(choice) {
			case "1" :
				regist(); //회원 등록
				break;
			case "2" :
				findById(); //회원 조회
				break;
			case "3" :
				update();
				break;
			case "4" :
				delete();
				break;
			case "5" :
				findAll();
				break;
			case "0" : //프로그램이 종료될때는 service.saveFile();
				service.saveFile(); //기능이 좋아졌지만 사용자는 눈으로 확인 불가
				System.out.println("프로그램 종료합니다");
				System.exit(0);
			default :
				System.out.println("선택오류");
			} //end switch
		} //end while
	}
	


	//회원 탈퇴
	private void delete() {
		String usrid, answer;
		
		System.out.println("* id: ");
		usrid = keyin.next();

		GymVO temp = service.selectOne(usrid);
		if(temp == null) {
			System.out.println("** 아이디가 존재하지 않습니다");
			return;
		}
		temp.output();
		
		System.out.println("정말로 탈퇴하시겠습니까?");
		answer = keyin.next();
		
		if(answer.equals("Y")) {
			service.delete(usrid);
			System.out.println("** 탈퇴 완료");
		} else System.out.println("** 탈퇴 취소");
	}




	//회원의 몸무게와 키를 수정하는 메소드
	private void update() {
		String usrid;
		double height, weight;
		
		System.out.println("* id: ");
		usrid = keyin.next();

		GymVO temp = service.selectOne(usrid);
		if(temp == null) {
			System.out.println("** 아이디가 존재하지 않습니다");
			return;
		}
		
		temp.output();
		
		System.out.println("* height(cm): ");
		height = keyin.nextDouble();
		
		System.out.println("* weight(kg): ");
		weight = keyin.nextDouble();
		
		GymVO vo = new GymVO(usrid, null, height, weight); //usrid업데이트가 필요없으니 null값을 넘겨준다
		boolean result = service.update(vo);
		
		if(result) {
			System.out.println("** 수정 완료");
		} else {
			System.out.println("** 수정 오류");
		}
		
	}


	//회원가입
	private void regist() {
		String usrid, usrname; //지역변수는 옆으로적어준다
		double weight, height;
		
		System.out.println("* id: ");
		usrid = keyin.next();
		//temp = service.selectOne(usrid); //넘겨받은 id가 있으면 GymVo객체가 튀어나오므로 GymVO타입 변수에 담는다
		if(service.selectOne(usrid) != null) {
			System.out.println("동일한 아이디가 존재합니다");
			return;
		}
		
		System.out.println("* name: ");
		usrname = keyin.next();
		try {
			System.out.println("* height(cm): ");
			height = keyin.nextDouble();
			
			System.out.println("* weight(kg): ");
			weight = keyin.nextDouble();
		} catch(InputMismatchException e) {
			System.out.println("실수를 입력하세요");
			keyin.nextLine();
			return;
		}
		GymVO vo = new GymVO(usrid, usrname, height, weight); //한사람의 정보를 만들었으니 서비스에 있는 arraylist에 넘겨줘야한다
		boolean result = service.insert(vo);
		
		if(result) {
			System.out.println("가입 완료");
		}else {
			System.out.println("가입 실패");
		}
	}


	private void findById() {
		String usrid;
		
		System.out.println("아이디: ");
		usrid = keyin.next();
		
		GymVO vo = service.selectOne(usrid); //한사람의 정보를 검색및 조회하는함수 
		if(vo == null) {
			System.out.println("** 해당 아이디의 회원은 없습니다");
			return; // 다시 메뉴로 돌아간다
		}
		
		vo.output();
	}

	//전체 회원정보 출력
	private void findAll() {
	 List<GymVO> list = service.selectAll();
	 if(list.size() == 0) {
		 System.out.println("** 회원이 없습니다.");
		 return;
	 }
	 Iterator<GymVO> iter = list.iterator(); 
	 while(iter.hasNext()) {
		 iter.next().output();
	 }
	}

	//메뉴화면 - 외부에서 접근할 필요없음
	private void menu() {
		System.out.println("[ 피트니스클럽 회원관리 ]");
		System.out.println("     1.등 록");
		System.out.println("     2.조 회");
		System.out.println("     3.정보수정");
		System.out.println("     4.탈 퇴");
		System.out.println("     5.전체회원정보 조회");
		System.out.println("     0.종 료");
		System.out.println("==========================");
		System.out.print(">>>>>>>선택: ");
	}
	
	//입력, 삭제, 수정, 조회, 전체출력
}
