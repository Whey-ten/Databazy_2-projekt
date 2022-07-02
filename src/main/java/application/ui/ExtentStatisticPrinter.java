package application.ui;

import application.rdg.ExtentStatistic;
import ts.Exception1;

public class ExtentStatisticPrinter {
    private static final ExtentStatisticPrinter INSTANCE = new ExtentStatisticPrinter();
    public static ExtentStatisticPrinter getInstance(){ return INSTANCE;}
    private ExtentStatisticPrinter(){}

    public void print(ExtentStatistic extentStatistic) throws Exception1 {
        if(extentStatistic == null){
            throw new Exception1("there were no fights");
        }
        System.out.print("Player ID :     ");
        System.out.println(extentStatistic.getPlayer_id());
        System.out.print("Total kills :      ");
        System.out.println(extentStatistic.getNumber_of_kills());
        System.out.println();
    }
}
