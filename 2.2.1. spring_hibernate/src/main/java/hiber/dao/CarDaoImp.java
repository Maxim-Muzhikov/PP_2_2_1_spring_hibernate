package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {
	
	private final SessionFactory sessionFactory;
	
	@Autowired
	public CarDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(Car car) {
		sessionFactory.getCurrentSession().save(car);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Car> listCars() {
		TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
		return query.getResultList();
	}
}
