package global.sesoc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import global.sesoc.vo.GymVO;

public class GymServiceImpl implements GymService {
	List<GymVO> list = new ArrayList<>();
	private static final String FILENAME = "member.dat";
	
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

	@Override
	public void saveFile() {
		//oos로 저장, try-catch-finally 로 직접 내부에서 처리
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(FILENAME); //파일을 열고
			oos = new ObjectOutputStream(fos); //객체를 열고
			oos.writeObject(list); //쓰고 (저장하고)
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("쓰기 작업 종료"); //파일 저장 완료
		} finally {
			try {
				if(fos != null) fos.close(); //파일 닫고
				if(oos != null) oos.close(); //객체 닫고
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		
	}

	
	@Override
	public void loadFile() {  
		File file = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			file = new File(FILENAME);
			if(!file.exists()){ 
				return;
			}
			
			fis = new FileInputStream(FILENAME);
			ois = new ObjectInputStream(fis);
			list = (List<GymVO>) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)  fis.close();
				if (ois != null)  ois.close();
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}	
	
	}

}
