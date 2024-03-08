package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   @Transactional
   public List<User> getUserByCarModelandSeries(String model, int series) {
      return sessionFactory.getCurrentSession().createQuery("SELECT user FROM User user JOIN user.car car WHERE car.model = :model AND car.series = :series", User.class)
              .setParameter("model", model).setParameter("series", series).getResultList();
   }

}
