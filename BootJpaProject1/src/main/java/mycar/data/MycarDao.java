package mycar.data;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MycarDao {
	@Autowired
	MyCarDaoInter carInter;
	
	//insert
	public void insertCar(MycarDto dto)
	{
		carInter.save(dto); //id유무에 따라서 자동으로 insert or update
	}
	
	//전체출력
	public List<MycarDto> getAllDatas()
	{
		return carInter.findAll();
	}
	
	
	//삭제
	public void deleteCar(Long num)
	{
		carInter.deleteById(num);
	}
	
	//num에 대한 dto 반환
	public MycarDto getData(Long num)
	{
		return carInter.getReferenceById(num);
	}
	
	//수정
	public void updateCar(MycarDto dto) 
	{
		carInter.save(dto);
	}
}
