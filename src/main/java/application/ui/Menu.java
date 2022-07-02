package application.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Menu {
    private boolean end;

    public void start() throws IOException{
        end = false;

        BufferedReader vstup = new BufferedReader(new InputStreamReader(System.in));

        while (!end){
            System.out.println();
            print();
            System.out.println();

            String zo_vstupu = vstup.readLine();
            if(zo_vstupu == null){
                return;
            }
            System.out.println();

            spracuj(zo_vstupu);
        }
    }
    public void exit() {
        end = true;
    }

    public abstract void print();

    public abstract void spracuj(String option);
}
