package hiber.service;

import hiber.dao.CarDao;
import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CarServiceImp implements CarService{

	private final CarDao carDao;
	
	@Autowired
	public CarServiceImp(CarDao carDao) {
		this.carDao = carDao;
	}
	
	@Transactional
	@Override
	public void add(Car car) {
		carDao.add(car);
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Car> listCars() {
		return carDao.listCars();
	}
}
