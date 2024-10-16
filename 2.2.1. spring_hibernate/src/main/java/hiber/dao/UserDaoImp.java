package hiber.dao;

import hiber.model.User;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
	
	private final SessionFactory sessionFactory;
	
	@Autowired
	public UserDaoImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void add(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> listUsers() {
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
		return query.getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getUserByCarModelAndSeries(String model, int series) {
		try {
			
			TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series", User.class);
			query.setParameter("model", model);
			query.setParameter("series", series);
			
			return (User) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			// ... обработка нескольких результатов
			return null;
		}
	}
}
