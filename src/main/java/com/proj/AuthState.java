package com.proj;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.proj.model.StateData;

import java.util.List;

public class AuthState
{
  private StateData state = null;
  private Boolean stateExists = false;

  public StateData getState() {
    return this.state;
  }

  public Boolean stateExists() {
    return this.stateExists;
  }

  public static AuthState tryState(String state) {
    AuthState auth = new AuthState();

    // Cria uma sessão
    Session session = HibernateUtil
      .getSessionFactory()
      .getCurrentSession();
    Transaction transaction = session.beginTransaction();
    
    Query query = session.createQuery(
      "from StateData where stateName = :state"
    );
    query.setParameter("state", state);
    List<StateData> states = query.list();

    transaction.commit();
    if (states.size() == 0)
      return auth;

    auth.stateExists = true;
    return auth;
  }
}
