package global.sesoc.service;

import java.util.ArrayList;
import java.util.List;

import global.sesoc.vo.GymVO;

public class GymServiceImpl implements GymService {
	ArrayList<GymVO> list = new ArrayList<>();
	
	@Override
	public boolean insert(GymVO vo) {
		return list.add(vo); //usrid 유일하다!
	}

	@Override
	public GymVO selectOne(String id) {
		for(int i=0; i<list.size(); ++i) {
			GymVO vo = list.get(i); //GymVO의 한사람에 대한 정보가 튀어나온다
			//vo.getUsrid() == id;
			if(vo.getUsrid().equals(id)) {
				return list.get(i); //해당회원 리턴
			}
		}
		return null;  //찾는 회원이 없으면 -> null 리턴
	}

	@Override
	public boolean update(GymVO vo) {
		for(int i=0; i<list.size(); ++i) {
			if(list.get(i).getUsrid().equals(vo.getUsrid())) {
				double h = vo.getHeight(); //꺼내서
				list.get(i).setHeight(h); // 바꾼다
				// list.get(i).setHeight(vo.getHeight());
				 list.get(i).setWeight(vo.getWeight());
				 return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(String id) { //넘겨받은id값을 arraylist에서 찾아서, 있으면 지우고 없으면 
		for(int i=0; i<list.size(); ++i) {
			if(list.get(i).getUsrid().equals(id)) {
				list.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<GymVO> selectAll() {
//		for(int i=0; i<list.size(); ++i) {
//			GymVO vo = list.get(i);
//		}
		return list;
	}

}
