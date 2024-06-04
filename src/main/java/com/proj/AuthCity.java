package com.proj;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.proj.model.CityData;

import java.util.List;

public class AuthCity
{
  private CityData city = null;
  private Boolean cityExists = false;

  public CityData getCity() {
    return this.city;
  }

  public Boolean cityExists() {
    return this.cityExists;
  }

  public static AuthCity tryCity(String city) {
    AuthCity auth = new AuthCity();

    // Cria uma sessão
    Session session = HibernateUtil
      .getSessionFactory()
      .getCurrentSession();
    Transaction transaction = session.beginTransaction();
    
    Query query = session.createQuery(
      "from CityData where CityName = :city"
    );
    query.setParameter("city", city);
    List<CityData> citys = query.list();

    transaction.commit();
    if (citys.size() == 0)
      return auth;

    auth.cityExists = true;
    return auth;
  }
}