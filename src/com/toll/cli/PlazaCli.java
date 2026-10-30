package com.toll.cli;

import com.toll.models.*;
import com.toll.repository.*;
import com.toll.service.*;
import java.util.Scanner;

public class PlazaCli {
    public void run() {
        Scanner sc = new Scanner(System.in);
        TollPlazaRepository plazas = new TollPlazaRepository();
        TollRateRepository rates = new TollRateRepository();
        TollPlazaService plazaService = new TollPlazaService(plazas);
        TollRateService rateService = new TollRateService(rates);
        System.out.print("Admin access? (yes/no): ");
        boolean admin = sc.nextLine().trim().equalsIgnoreCase("yes");
        int choice;
        do {
            System.out.println("\n1.Create plaza \n 2.Add lane \n 3.Set rate \n 4.List plazas \n 5.Open plaza \n 0.Exit");
            choice = readInt(sc, "Choice: ");
            if (choice == 1) {
                int id = readInt(sc, "Plaza ID: ");
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Highway: ");
                String highway = sc.nextLine();
                System.out.print("Location: ");
                String location = sc.nextLine();
                System.out.println(
                        plazaService.createPlaza(admin, new TollPlaza(id, name, highway, location)) ? "Plaza created."
                                : "Could not create plaza.");
            } else if (choice == 2) {
                int id = readInt(sc, "Plaza ID: ");
                int number = readInt(sc, "Lane number: ");
                System.out.print("Type (FASTag/Cash/Emergency): ");
                String type = sc.nextLine();
                System.out.println(plazaService.addLane(admin, id, new Lane(number, type)) ? "Lane added."
                        : "Could not add lane.");
            } else if (choice == 3) {
                int id = readInt(sc, "Plaza ID: ");
                System.out.print("Vehicle type: ");
                String vehicle = sc.nextLine();
                System.out.print("Trip type (Single/Return): ");
                String trip = sc.nextLine();
                double amount = readDouble(sc, "Amount: ");
                System.out.print("Effective date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                TollRate rate = new TollRate(id, vehicle, trip, amount, java.time.LocalDate.parse(date));
                System.out.println(rateService.setRate(admin, rate) ? "Rate saved." : "Rate rejected.");
            } else if (choice == 4) {
                for (TollPlaza p : plazaService.getAll())
                    System.out.println(p);
            } else if (choice == 5) {
                int id = readInt(sc, "Plaza ID: ");
                System.out.println(plazaService.setPlazaOpen(id, true) ? "Plaza opened."
                        : "Plaza needs at least 2 lanes and rates for all vehicle types.");
            }
        } while (choice != 0);
    }

    private int readInt(Scanner sc, String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(sc.nextLine());
    }

    private double readDouble(Scanner sc, String prompt) {
        System.out.print(prompt);
        return Double.parseDouble(sc.nextLine());
    }
}
