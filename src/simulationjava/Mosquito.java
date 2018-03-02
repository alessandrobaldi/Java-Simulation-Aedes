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
public class Mosquito {
   private int days = 0;
   private boolean control = false;

    public int getDays() {
        return days;
    }

    public boolean getControl() {
        return control;
    }

    public boolean isControl() {
        return control;
    }

    public void setControl(boolean control) {
        this.control = control;
    }

    public void setDays(int days) {
        this.days = days;
    }
   
}
