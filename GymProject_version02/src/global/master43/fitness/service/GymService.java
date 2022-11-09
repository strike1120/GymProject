package global.master43.fitness.service; //현재 이 클래스가 어느 위치에 있는지를 나타내준다
import java.util.Scanner;
import global.master43.fitness.vo.GymVO;

public class GymService {

		Scanner scan = new Scanner(System.in);
		GymVO vo; //회원클래스의 vo회원 객체
		
		//생성자
		public GymService() {
			String choice; //메뉴를 담을 변수선언
			
			while(true) {
				menu(); // 메뉴 보여주기
				choice = scan.next(); //메뉴 선택하기
				
				switch(choice) {
				case "1" :
					join();
					break;
				case "2" :
					update();
					break;
				case "3" :
					delete(); //참조하던 객체를 참조하지 않도록한다(=객체에 null을 집어넣는다)
 					break;
				case "4" :
					retrieve();
					break;
				case "0" :
					System.out.println("프로그램을 종료합니다");
					return; //return or exit으로 프로그램종료 가능
				default :
					System.out.println("메뉴선택을 다시 해주세요");
				}
			}
			
		}
		

		


		//회원가입
		private void join() {
			System.out.println("=====[회원가입]=====");
			if(vo != null) {
				System.out.println("**가입을 할 수 없습니다");
				return;
			}

			int id;
			String name;
			double height, weight;

			System.out.print("> 아이디:");
			id = scan.nextInt();
			System.out.print("> 이름: ");
			name = scan.next();
			System.out.print("> 키(cm): ");
			height = scan.nextDouble();
			System.out.print("> 몸무게(kg)");
			weight = scan.nextDouble();

			vo = new GymVO(id,name,height,weight);
			System.out.println("회원가입이 완료되었습니다");
			return;

		}
		
		//수정
		private void update() {
			System.out.println("=====[수 정]=====");
			double height, weight;
			String answer; //수정을 할건지 물어보기 위해
			
			if(vo == null) {
				System.out.println("** 수정할 수 없습니다");
				return;
			}
			System.out.print("> 키(cm): ");
			height = scan.nextDouble();
			System.out.print("> 몸무게(kg)");
			weight = scan.nextDouble();
			
			System.out.println("> 수정하시겠습니까?(y/n) : " );
			answer = scan.next();
			if(!(answer.equals("Y")||answer.equals("y"))) {
				System.out.println("작업이 취소되었습니다");
				return;
			}
			vo.setWeight(weight); //실제 수정하는 작업. **vo가 null인상태(객체를 생성하지 않은 상태)에서는 작업 불가능 -> vo가 참조하는 값이 없으니까.
			vo.setHeight(height); //실제 수정하는 작업.
			System.out.println("**수정이 완료되었습니다.");
		}
		
		//회원가입이 되어있는지 아닌지 조회 - 객체의 참조값이 null이면 아직 가입x. vo(객체) == null
		private void retrieve() {
			System.out.println("[ 조 회 ]");
			if(vo == null) {
				System.out.println("조회할 수 없습니다");
				return;
			}
			vo.output();
			
		}
		
		//탈퇴 - 참조하던 객체를 참조하지 않도록한다(=객체(vo)에 null을 집어넣는다)
		private void delete() {
			System.out.println(" [탈 퇴] ");
			String answer;
			
			if(vo == null) {
				System.out.println("탈퇴할 수 없습니다");
				return;
			}
			
			System.out.print("> 탈퇴하시겠습니까?(y/n) : ");
			answer = scan.next();
			//answer == "y" //이런 식으로 비교하면 참조값(주소값)을 비교하는 꼴. 객체는 ==으로 비교하지않는다
			// ↓ answer에 들어간 데이터가 y이거나 Y가 아니면 탈퇴 취소!
			if(!(answer.equals("y") || answer.equals("Y"))) {//객체는 equals메소드로 비교
				System.out.println("** 작업이 취소되었습니다.");
				return;
			}
			
			vo = null;
			System.out.println("탈퇴완료");
			
		}

		
		public void menu() {
			System.out.println("=========[1인 피트니스 클럽]=========");
			System.out.println("            1.가 입");
			System.out.println("            2.수 정");
			System.out.println("            3.탈 퇴");
			System.out.println("            4.조 회");
			System.out.println("            0.종 료");
			System.out.println("=================================");
			System.out.print("            >선택 : ");
		}
}
