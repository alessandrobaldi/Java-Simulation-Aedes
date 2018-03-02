/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulationjava;

/**
 *
 * @author alessandro
 */
public class SimulationJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scenary ufes = new Scenary("c0,c1,c2,c3,c4,c5,c6,c7,c8,c9","c0-c1,c1-c2,c2-c3,c3-c4,c4-c5,c5-c6,c6-c7,c7-c8,c8-c9","","c0,c1,c2,c3,c4,c5,c6,c7,c8,c9","c0");
        //Scenary = declaração de casas, declaração de ligações entre casas, declaração de casas com armadilha, declaração de casas com foco, declaração de casas com mosquito
        long start = System.currentTimeMillis();

        ufes.startSimulation(90);
        
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("Time:"+elapsed);
        //iniciar simulação por 30 dias.
        System.out.println("Finished Simulation");
    }
    
}
