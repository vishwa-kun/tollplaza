package com.toll;

import com.toll.cli.LaneCli;
import com.toll.cli.PlazaCli;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Toll Plaza System ---");
            System.out.println("1. Plaza");
            System.out.println("2. Lane");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    new PlazaCli().run();
                    break;

                case 2:
                    new LaneCli().showLaneDetails(sc);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}