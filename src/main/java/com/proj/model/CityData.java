package com.proj.model;

import javax.persistence.*;

@Entity
@Table(name = "CityData") // cria tabela
public class CityData {

    public CityData() { }

    public CityData(String cityName, StateData state) {
        this.cityName = cityName;
        this.state = state;
    }
    
    @Id
    @GeneratedValue // gera o id sozinho 
    private Long IDCity;

    @OneToOne
    @JoinColumn(name = "FKState") // cria coluna
    private StateData state;
    
    public Long getIDCity() {
        return IDCity;
    }

    @Column(name = "CityName") // cria coluna
    private String cityName;

    public String getCityName() {
        return cityName;
    }
    
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public StateData getState() {
      return state;
    }
  
    public void setState(StateData fkstate) {
      state = fkstate;
    }
}

