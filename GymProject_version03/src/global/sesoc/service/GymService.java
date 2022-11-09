package global.sesoc.service;

import java.util.List;

import global.sesoc.vo.GymVO;

//인터페이스가 먼저 완성되어야 service를 구현할 수 있다.
public interface GymService {
//CRUD
	//C-새 데이터 생성하기/DB에 집어넣기 , R-데이터 조회 U-데이터 수정 D-데이터 삭제
	public boolean insert(GymVO vo);//한사람의 정보를 받아서 arraylist 에 집어넣는다. //추가가 됐다 or 안됐다 2가지 경우로 나뉘니까-> boolean
	public GymVO selectOne(String id); //내가 찾으려고 하는 key값을 받아서 찾는다 //홍길동 회원을 찾고 싶다고 했을때, 동명이인이 있을 수 있기때문에 보통 id값을 통해 찾는다 
	public boolean update(GymVO vo); // 수정이 됐다 안됐다 2가지-> boolean //어떤 데이터를 어떻게 수정할지 모르니 한사람의 데이터정보를 전부 다 받는다
	public boolean delete(String id);//회원의 id값을 받아서 삭제 & 삭제가 됐다 안됐다 -> boolean
	public List<GymVO> selectAll();//전체데이터를 다 반환해야 하므로 List<GymVO>를 반환
}
