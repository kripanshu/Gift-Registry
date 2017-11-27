package wpl.spring.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wpl.spring.entity.Registry;

@Repository
public class PublicRegistriesDaoImpl implements PublicRegistriesDao {

	@Autowired 
	private SessionFactory sessionFactory;
	
	public List<Registry> getPublicRegistries() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		String stringQuery = "from Registry where Share= "+1+"";
	    Query query = currentSession.createQuery(stringQuery);
	    @SuppressWarnings("unchecked")
		List<Registry> publicRegistries = query.getResultList();
	    System.out.println(publicRegistries.get(1).getRegistryName());
		return publicRegistries; 
	}

}
