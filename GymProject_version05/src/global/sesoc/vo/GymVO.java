package global.sesoc.vo;

import java.io.Serializable;

public class GymVO implements Serializable{
	private static final long serialVersionUID = 5163707290209729384L;
	//회원 클래스
	private String usrid; //primary key (유일한 값이어야하고 null이 아니어야 한다. -> unique, not null
	private String usrname;
	private double height; 
	private double weight;
	private double bmi;
	private String result;
	
	//기본생성자 (무적권 필요)
	public GymVO() {}
	
	//오버로딩된 생성자
	public GymVO(String usrid, String usrname, double height, double weight) {
		this.usrid = usrid;
		this.usrname= usrname;
		this.height = height;
		this.weight = weight;
		calcBMI();
	}
	public void calcBMI() {
		this.bmi = this.weight / ((height*0.01)*(height*0.01));
		calcResult();
	}
	public void calcResult() {
		if(bmi >= 35)      result = "고도비만";
		else if(bmi >= 30) result = "중도비만";
		else if(bmi >= 25) result = "경도비만";
		else if(bmi >= 23) result = "과체중";
		else if(bmi >= 18.5) result = "정상";
		else  			   result = "저체중";
	}
	// setter
	public void setUsrid(String usrid) {
		this.usrid = usrid;
	}
	public void setUsrame(String usrname) {
		this.usrname = usrname;
	}	
	public void setHeight(double height) { //생성자에 의해 생성된 객체의 참조값을 바꾸려면 setter를 사용해야함!
		this.height = height;
		calcBMI();
	}
	public void setWeight(double weight) {
		this.weight = weight;
		calcBMI();
	}
	// getter
	public String getUsrid() {
		return usrid;
	}
	public String getUsrname() {
		return usrname;
	}

	public double getWeight() {
		return weight;
	}
	public double getHeight() {
		return height;
	}
	public double getBMI() {
		return bmi;
	}
	public String getResult() {
		return result;
	}
	public void output() {
		System.out.println("* 아이디: " + usrid);
		System.out.println("* 이름: " + usrname);
		System.out.printf("* 몸무게: %.2f(kg)%n", weight);
		System.out.printf("* 키: %.2f(cm)%n", height);
		System.out.println("* BMI 측정결과: "+ bmi + ", " + result);
		
		System.out.printf("당신의 BMI는 %.2f이고 '%s'상태입니다.%n", bmi, result);
	}
}