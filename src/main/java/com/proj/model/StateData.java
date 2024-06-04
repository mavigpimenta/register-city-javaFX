package com.proj.model;

import javax.persistence.*;

@Entity
@Table(name = "StateData") // cria tabela
public class StateData {

    public StateData() { }

    public StateData(String stateName) {
        this.stateName = stateName;
    }
    
    @Id
    @GeneratedValue // gera o id sozinho 
    private Long IDState;
    
    public Long getIDState() {
        return IDState;
    }

    @Column(name = "StateName") // cria coluna
    private String stateName;

    public String getStateName() {
        return stateName;
    }
    
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
