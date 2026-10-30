package com.toll.cli;

import java.util.Scanner;
import com.toll.models.*;
import com.toll.repository.*;
import com.toll.service.*;

public class PaymentCli {
    public void run() {
        Scanner sc = new Scanner(System.in);
        PaymentRepository repo = new PaymentRepository();
        PaymentService service = new PaymentService(repo);
        int choice;
        do {
            System.out.println(
                    "\\n1.Set tag balance 2.Recharge 3.Record cash toll 4.Show payments 5.Show alerts 6.Daily plaza revenue 7.Record lane vehicle 8.Lane counts 0.Exit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                System.out.print("Tag ID: ");
                String tag = sc.nextLine();
                System.out.print("Current balance: ");
                double b = Double.parseDouble(sc.nextLine());
                service.setBalance(tag, b);
            } else if (choice == 2) {
                System.out.print("Tag ID: ");
                String tag = sc.nextLine();
                System.out.print("Amount: ");
                double amount = Double.parseDouble(sc.nextLine());
                System.out.print("Method (UPI/Card/Net Banking): ");
                String method = sc.nextLine();
                System.out.print("Status (Paid/Failed/Refunded): ");
                String status = sc.nextLine();
                System.out.println(service.recharge(tag, amount, method, status) ? "Recharge record saved."
                        : "Recharge rejected.");
            } else if (choice == 3) {
                System.out.print("Registration: ");
                String reg = sc.nextLine();
                System.out.print("Plaza: ");
                String plaza = sc.nextLine();
                System.out.print("Amount: ");
                double amount = Double.parseDouble(sc.nextLine());
                System.out.print("Lane operator: ");
                String op = sc.nextLine();
                System.out.println(service.recordCash(reg, plaza, amount, op) ? "Cash payment recorded."
                        : "Invalid cash payment.");
            } else if (choice == 4) {
                for (RechargeRecord r : repo.recharges())
                    System.out.println(r);
                for (CashPayment p : repo.cashPayments())
                    System.out.println(p);
            } else if (choice == 5)
                for (String a : service.alerts())
                    System.out.println(a);
            else if (choice == 6) {
                System.out.print("Plaza: ");
                String plaza = sc.nextLine();
                System.out.print("Date YYYY-MM-DD: ");
                java.time.LocalDate date = java.time.LocalDate.parse(sc.nextLine());
                System.out.println("Daily cash revenue: Rs." + service.dailyCashRevenue(plaza, date));
            } else if (choice == 7) {
                System.out.print("Plaza: ");
                String plaza = sc.nextLine();
                System.out.print("Lane: ");
                String lane = sc.nextLine();
                service.recordLaneVehicle(plaza, lane);
                System.out.println("Lane vehicle count updated.");
            } else if (choice == 8)
                for (java.util.Map.Entry<String, Integer> e : service.laneVehicleCounts().entrySet())
                    System.out.println(e.getKey() + " -> " + e.getValue() + " vehicles");
        } while (choice != 0);
    }
}
