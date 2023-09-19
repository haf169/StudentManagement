/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.ArrayList;
import view.Validation;
/**
 *
 * @author MINH TUAN
 */
public abstract class Menu<T> {

    protected String title;
    protected ArrayList<T> menuChoice;
    private boolean isStop;
     

    //------------------------------------------------------
    public Menu(String tT, String[] mC) {
        title = tT;
        menuChoice = new ArrayList<>();
        for (String s : mC) {
            menuChoice.add((T) s);
        }
    }

    //------------------------------------------------------
    public Menu() {
        this.isStop = false;
    }

    //------------------------------------------------------
    public void displayMenu() {
        System.out.println(title);
        System.out.println("-------------------------------");
        for (int i = 0; i < menuChoice.size(); i++) {
            System.out.println((i + 1) + "." + menuChoice.get(i));
        }
        System.out.println("-------------------------------");
    }

    //------------------------------------------------------
    public int getSelected() {
        displayMenu();
        return Validation.checkInt("Enter Selection: ", 1, this.menuChoice.size());
    }

    //------------------------------------------------------
    public abstract void execute(int choice) throws Exception;

    //------------------------------------------------------
    protected void stop() {
        this.isStop = true;
    }

    //------------------------------------------------------
    public void run() throws Exception {
        while (!this.isStop) {
            int choice = getSelected();
            execute(choice);
            if (choice > menuChoice.size()) {
                break;
            }

        }
    }

}

