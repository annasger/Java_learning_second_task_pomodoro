package com.pomodoro;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Pomodoro {

    /*
     -w 2 -b 1 -count 2
     split()
     [-w 1 -b 1 -count 1]
    */
    static boolean test = false;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello pomodoro!");
        System.out.println("Please type command");
        String[] cmd = new Scanner(System.in).nextLine().split(" ");
        //Считываем пользовательский ввод
        System.out.println(Arrays.toString(cmd));

        //время работы
        int workMin = 25;
        //время отдыха
        int breakMin = 5;
        //количество повторов
        int count = 1;

        //Количество прогрессбара таймера
        int sizePrint = 30;

        boolean isCallHelp = false;

        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "-help" -> {
                    isCallHelp = true;
                    printHelpMsg();
                }
                case "-w" -> workMin = Integer.parseInt(cmd[++i]);
                case "-b" -> breakMin = Integer.parseInt(cmd[++i]);
                case "-count" -> count = Integer.parseInt(cmd[++i]);
            }
        }
        if (!isCallHelp) {
            System.out.printf("Work %d min, Break %d min, count %d", workMin, breakMin, count);
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < count; i++) {
                printProgress("This work time  ", workMin, 30);

                printProgress("This break time  ", breakMin, 30);
            }
            long endTime = System.currentTimeMillis();

            System.out.println("Pmodoro timer finished  " + (endTime - startTime) / (1000 * 60) + " min");


        }
    }

    private static void printHelpMsg() {
        System.out.println("this is help for program");
    }

    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60 * time / size;
        rep = 60 * time / length;
        int stretchb = size / (3 * time);
        for (int i = 1; i <= rep; i++) {
            double x = i;
            x = 1.0 / 3.0 * x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time * stretchb;
            double percent = (x / w) * 1000;
            x /= stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent + "% " + (" ").repeat(5 - (String.valueOf(percent).length())) + "[" + ("#").repeat(i) + ("-").repeat(rep - i) + "]    ( " + x + "min / " + time + "min )" + "\r");
            if (test == false) {
                TimeUnit.SECONDS.sleep(length);
            }
        }
        System.out.println();
    }
}





