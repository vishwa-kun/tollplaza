package com.toll.cli;

import java.util.Scanner;
import com.toll.models.*;
import com.toll.repository.*;
import com.toll.service.*;

public class TollCli {
    public void run() {
        Scanner sc = new Scanner(System.in);
        VehicleRepository vr = new VehicleRepository();
        TransactionRepository tr = new TransactionRepository();
        VehicleService vs = new VehicleService(vr);
        TollProcessingService ps = new TollProcessingService(vr, tr);
        int choice;
        do {
            System.out.println("\\n1.Register vehicle/tag 2.Process crossing 3.Show transactions 0.Exit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                System.out.print("Registration: ");
                String reg = sc.nextLine();
                System.out.print("Registered type: ");
                String type = sc.nextLine();
                System.out.print("Tag ID: ");
                String tag = sc.nextLine();
                System.out.print("Balance: ");
                double bal = Double.parseDouble(sc.nextLine());
                System.out.print("Tag active? (yes/no): ");
                boolean active = sc.nextLine().equalsIgnoreCase("yes");
                System.out.print("Blacklisted? (yes/no): ");
                boolean black = sc.nextLine().equalsIgnoreCase("yes");
                System.out.println(vs.register(new Vehicle(reg, type, tag, bal, active, black)) ? "Vehicle added."
                        : "Duplicate or invalid tag.");
            } else if (choice == 2) {
                System.out.print("Tag ID: ");
                String tag = sc.nextLine();
                System.out.print("Observed type: ");
                String type = sc.nextLine();
                System.out.print("Plaza: ");
                String plaza = sc.nextLine();
                System.out.print("Lane: ");
                String lane = sc.nextLine();
                System.out.print("Normal toll: ");
                double toll = Double.parseDouble(sc.nextLine());
                System.out.print("Emergency vehicle? (yes/no): ");
                boolean emergency = sc.nextLine().equalsIgnoreCase("yes");
                System.out.print("Cash paid? (yes/no): ");
                String cash = sc.nextLine().equalsIgnoreCase("yes") ? "cash" : "no";
                System.out.println(ps.process(tag, type, plaza, lane, toll, emergency, cash));
            } else if (choice == 3)
                for (TollTransaction t : tr.all())
                    System.out.println(t);
        } while (choice != 0);
    }
}
