/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationjava;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author alessandro
 */
public class Scenary {

    private List<House> scenary = new ArrayList<House>();

    Scenary(String houses, String linkedhouses, String houseswithtrap, String houseswithfocus, String houseswithmosquito) {
        //criar construtores das casas e dos links
        String[] totalHouses = houses.split(",");
        String[] totalLinked = linkedhouses.split(",");
        String[] totalTrap = houseswithtrap.split(",");
        String[] totalFocus = houseswithfocus.split(",");
        String[] totalMosquito = houseswithmosquito.split(",");

        for (int create = 0; create < totalHouses.length; create++) {
            House newHouse = new House(totalHouses[create]);
            scenary.add(newHouse);
        }

        for (int create = 0; create < totalLinked.length; create++) {
            String link = totalLinked[create];
            String[] linkNow = link.split("-");
            House findone = scenary.get(0);
            House findtwo = scenary.get(0);
            for (int find = 0; find < totalHouses.length; find++) {
                if(scenary.get(find).getName().equals(linkNow[0]))
                {
                     findone=scenary.get(find);
                }
                if(scenary.get(find).getName().equals(linkNow[1]))
                {
                     findtwo=scenary.get(find);
                }
            }
            findone.addNeighborhood(findtwo);
            findtwo.addNeighborhood(findone);
        }

         for (int create = 0; create < totalTrap.length; create++) {
            String trap = totalTrap[create];
            for (int find = 0; find < totalHouses.length; find++) {
                if(scenary.get(find).getName().equals(trap))
                {
                    scenary.get(find).addTrap();
                }
            }
         }
         
            for (int create = 0; create < totalFocus.length; create++) {
            String focus = totalFocus[create];
            for (int find = 0; find < totalHouses.length; find++) {
                if(scenary.get(find).getName().equals(focus))
                {
                    scenary.get(find).addFocus();
                }
            }
         }
         
            for (int create = 0; create < totalMosquito.length; create++) {
            String mosquito = totalMosquito[create];
            House houseMosquito = scenary.get(0);
            for (int find = 0; find < totalHouses.length; find++) {
                if(scenary.get(find).getName().equals(mosquito))
                {
                    scenary.get(find).addMosquito();
                }
            }
         }
        // House c1 = new House();
        // House c2 = new House();
        // House c3 = new House();
        // scenary.add(c1);
        // scenary.add(c2);
        // scenary.add(c3);
/*
        c1.addNeighborhood(c2);
        c2.addNeighborhood(c1);
        c2.addNeighborhood(c3);
        c3.addNeighborhood(c2);

        c3.addTrap();

        c2.addFocus();

        c1.addMosquito();*/
    }

    public List<House> getScenary() {
        return scenary;
    }

    public void setScenary(List<House> scenary) {
        this.scenary = scenary;
    }

    void startSimulation(int days) {
        //inicia simulação - dias

        for (int today = 0; today < days; today++) {
            //regra que mosquito ganha (mosquito em todas as casas)
            fly();
            hatchEggs();
            layEggs();
            agent();
            clearDay(today);

            //verificando vencedor
            boolean hasMosquitos = false;
            boolean hasEggs = false;
            for (int verifyingHouses = 0; verifyingHouses < this.scenary.size(); verifyingHouses++) {
                House inHouse = this.scenary.get(verifyingHouses);
                List<Mosquito> mosquitosInHouse = inHouse.getMosquitos();
                List<Eggs> eggsInHouse = inHouse.getEggs();

                for (int verifyingMosquitos = mosquitosInHouse.size() - 1; verifyingMosquitos >= 0; verifyingMosquitos--) {
                    hasMosquitos = true;
                }
                for (int verifyingEggs = eggsInHouse.size() - 1; verifyingEggs >= 0; verifyingEggs--) {
                    hasEggs = true;
                }

            }
            if (hasMosquitos == false&&hasEggs==false) {
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("Simulation terminated with no Eggs/Mosquitos in "+today+" days.");
                today = days;
            }
            System.out.println(today);
        }
        
        verifySituation();

    }

    public int randomize(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    private void fly() {

        for (int verifyingHouses = 0; verifyingHouses < this.scenary.size(); verifyingHouses++) {
            House inHouse = this.scenary.get(verifyingHouses);
            List<Mosquito> mosquitosInHouse = inHouse.getMosquitos();
            for (int verifyingMosquitos = mosquitosInHouse.size() - 1; verifyingMosquitos >= 0; verifyingMosquitos--) {
                Mosquito mosquitoInHouse = mosquitosInHouse.get(verifyingMosquitos);
                if (mosquitoInHouse.getControl() == false) {
                    mosquitoInHouse.setControl(true);//controle do mosquito

                    List<House> neighborHouse = inHouse.getNeighbors();

                    int chooseHouse = randomize(0, neighborHouse.size() - 1);

                    House newHouse = neighborHouse.get(chooseHouse);

                    List<Mosquito> newMosquitoAdd = newHouse.getMosquitos();

                    newMosquitoAdd.add(mosquitoInHouse);

                    mosquitosInHouse.remove(verifyingMosquitos);

                    //System.out.println("Mosquito Flying");
                }
            }
        }
    }

    private void clearDay(int today) {
        for (int verifyingHouses = 0; verifyingHouses < this.scenary.size(); verifyingHouses++) {
            House inHouse = this.scenary.get(verifyingHouses);

            if (today % 15 == 0) {
                inHouse.setActivefocus(true);
                //System.out.println("Raining");
            }

            List<Mosquito> mosquitosInHouse = inHouse.getMosquitos();
            for (int verifyingMosquitos = mosquitosInHouse.size() - 1; verifyingMosquitos >= 0; verifyingMosquitos--) {
                Mosquito mosquitoInHouse = mosquitosInHouse.get(verifyingMosquitos);
                int dayMosquito = mosquitoInHouse.getDays();
                dayMosquito++;
                mosquitoInHouse.setDays(dayMosquito);
                mosquitoInHouse.setControl(false);
                if (dayMosquito == 38) {
                    mosquitosInHouse.remove(verifyingMosquitos);
                    //System.out.println("Mosquito has died");
                }
                //System.out.println("Mosquito new day");
            }

            List<Eggs> eggsInHouse = inHouse.getEggs();
            for (int verifyingEggs = eggsInHouse.size() - 1; verifyingEggs >= 0; verifyingEggs--) {
                Eggs eggInHouse = eggsInHouse.get(verifyingEggs);
                int dayEggs = eggInHouse.getDays();
                dayEggs++;
                eggInHouse.setDays(dayEggs);
                //System.out.println("Egg new day");
            }
        }
    }

    private void layEggs() {
        for (int verifyingHouses = 0; verifyingHouses < this.scenary.size(); verifyingHouses++) {
            House inHouse = this.scenary.get(verifyingHouses);
            List<Mosquito> mosquitosInHouse = inHouse.getMosquitos();
            for (int verifyingMosquitos = mosquitosInHouse.size() - 1; verifyingMosquitos >= 0; verifyingMosquitos--) {
                if (inHouse.isFocus() == true && inHouse.isActivefocus() == true) {
                    inHouse.addEggs();
                    //System.out.println("Laying Eggs");
                }
                if (inHouse.isTrap()) {
                    mosquitosInHouse.remove(verifyingMosquitos);
                    //System.out.println("Captured Mosquito");
                    inHouse.newAgent();
                    //System.out.println("Agent Called");
                }
            }
        }

    }

    private void agent() {
        for (int verifyingHouses = 0; verifyingHouses < this.scenary.size(); verifyingHouses++) {
            House inHouse = this.scenary.get(verifyingHouses);
            List<Agents> agentsInHouse = inHouse.getAgents();
            for (int verifyingAgents = agentsInHouse.size() - 1; verifyingAgents >= 0; verifyingAgents--) {
                Agents agent = agentsInHouse.get(verifyingAgents);
                if (agent.getControl() == 0) {
                    agentsInHouse.remove(verifyingAgents);
                    List<Eggs> eggsInHouse = inHouse.getEggs();
                    eggsInHouse.clear();
                    House houseant = inHouse;
                    for (int visited = 0; visited < 5; visited++) {
                        List<House> neighborHouse = houseant.getNeighbors();

                        int chooseHouse = randomize(0, neighborHouse.size() - 1);

                        House newHouse = neighborHouse.get(chooseHouse);

                        eggsInHouse = newHouse.getEggs();
                        eggsInHouse.clear();

                        List<Mosquito> mosquitoInHouse = newHouse.getMosquitos();
                        mosquitoInHouse.clear();
                        newHouse.setActivefocus(false);
                        houseant = newHouse;
                        //System.out.println("Visited house by agent");
                    }

                } else {
                    int agentControl = agent.getControl();
                    agentControl--;
                    agent.setControl(agentControl);
                    //System.out.println("Agent Control changed");
                }
            }
        }

    }

    private void hatchEggs() {
        for (int verifyingHouses = 0; verifyingHouses < this.scenary.size(); verifyingHouses++) {
            House inHouse = this.scenary.get(verifyingHouses);
            List<Eggs> eggsInHouse = inHouse.getEggs();
            for (int verifyingEggs = eggsInHouse.size() - 1; verifyingEggs >= 0; verifyingEggs--) {
                Eggs eggInHouse = eggsInHouse.get(verifyingEggs);
                if (eggInHouse.getDays() == 20) {
                    inHouse.addMosquito();
                    inHouse.addMosquito();
                    inHouse.addMosquito();
                    inHouse.addMosquito();
                    inHouse.addMosquito();
                    inHouse.addMosquito();
                    inHouse.addMosquito();
                    inHouse.addMosquito();
                    inHouse.addMosquito();
                    inHouse.addMosquito();
                    //System.out.println("Eggs Hatched");
                }
            }

        }
    }

    private void verifySituation() {
        int totalMosquitos = 0;
        int totalEggs = 0;
         for (int verifyingHouses = 0; verifyingHouses < this.scenary.size(); verifyingHouses++) {
         House verify = this.scenary.get(verifyingHouses);
         int mosquitosHere = verify.getMosquitos().size();
         int eggsHere= verify.getEggs().size();
         totalMosquitos=totalMosquitos+mosquitosHere;
         totalEggs=totalEggs+eggsHere;
         }
        System.out.println();
                System.out.println();
                System.out.println("Total: " + totalMosquitos + " mosquitos and "+ totalEggs + " eggs.");
         
    }

}
