package com.cognizant.jitender;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.cognizant.jitender.dto.UserDetails;

public class HibernateTest {
	
	public static void main(String[] args) {
		/*UserDetails user = new UserDetails();
	    user.setUserName("Test User");
		//user.setUserId("1");
		*/
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i=1;i<20;i++){
			UserDetails user = new UserDetails();
			user.setUserName("User" + i);
			session.save(user);
		}
		/*session.save(user);
		
		user.setUserName("Updated User");
		user.setUserName("Updated User1");
		user.setUserName("Updated User2");
		user.setUserName("Updated User3");*/
		
//		Query query = session.createQuery("from UserDetails");
//		List<UserDetails> users =(List<UserDetails>) query.list();
		Criteria criteria = session.createCriteria(UserDetails.class);
		criteria.
				add(Restrictions.between("userId", 5, 15));
		
		
		List<UserDetails> users = (List<UserDetails>)criteria.list();
		session.getTransaction().commit();
		session.close();
		
		for (UserDetails user: users)
			System.out.println(user.getUserName());
		
		//System.out.println("Retrieved users are: "+ users.size());
		
	}

}
