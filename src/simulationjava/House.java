/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationjava;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alessandro
 */
public class House {
    private List<House> neighbors = new ArrayList<House>();
    private List<Mosquito> mosquitos = new ArrayList<Mosquito>();
    private List<Eggs> eggs = new ArrayList<Eggs>();
    private List<Agents> agents= new ArrayList<Agents>();       
    private boolean focus = false;
    private boolean trap = false;
    private boolean activefocus = false;
    private String name = "";

    House(String nameHouse) {
        name=nameHouse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivefocus() {
        return activefocus;
    }

    public void setActivefocus(boolean activefocus) {
        this.activefocus = activefocus;
    }
    
    
    void addEggs()
    {
        Eggs egg = new Eggs();
        eggs.add(egg);
    }
    
    
    void addNeighborhood(House house) {
        this.neighbors.add(house);
    }

    void addTrap() {
    this.trap=true;
    }

    void addFocus() {
    this.focus=true;
    this.activefocus=true;
    }

    void addMosquito() {
        Mosquito mosquito = new Mosquito();
        mosquitos.add(mosquito);
    }

    public List<House> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<House> neighbors) {
        this.neighbors = neighbors;
    }

    public List<Mosquito> getMosquitos() {
        return mosquitos;
    }

    public void setMosquitos(List<Mosquito> mosquitos) {
        this.mosquitos = mosquitos;
    }

    public List<Eggs> getEggs() {
        return eggs;
    }

    public void setEggs(List<Eggs> eggs) {
        this.eggs = eggs;
    }

    public List<Agents> getAgents() {
        return agents;
    }

    public void setAgents(List<Agents> agents) {
        this.agents = agents;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public boolean isTrap() {
        return trap;
    }

    public void setTrap(boolean trap) {
        this.trap = trap;
    }

    void newAgent() {
        Agents agent = new Agents();
        this.agents.add(agent);
    }

    
}
