package com.toll.cli;

import java.time.LocalDate;
import java.util.Scanner;
import com.toll.models.PaymentDispute;
import com.toll.repository.*;
import com.toll.service.*;

public class DisputeCli {
    public void run() {
        Scanner sc = new Scanner(System.in);
        PaymentService payments = new PaymentService(new PaymentRepository());
        DisputeRepository repo = new DisputeRepository();
        DisputeService service = new DisputeService(repo, payments);
        int choice;
        do {
            System.out
                    .println("\\n1.Set tag balance \n 2.File dispute \n 3.Admin decision \n 4.List disputes \n 0.Exit");
            System.out.print("Choice: ");
            choice = Integer.parseInt(sc.nextLine());
            if (choice == 1) {
                System.out.print("Tag ID: ");
                String tag = sc.nextLine();
                System.out.print("Balance: ");
                payments.setBalance(tag, Double.parseDouble(sc.nextLine()));
            } else if (choice == 2) {
                System.out.print("Transaction ID: ");
                String tx = sc.nextLine();
                System.out.print("Vehicle registration: ");
                String reg = sc.nextLine();
                System.out.print("Amount disputed: ");
                double amount = Double.parseDouble(sc.nextLine());
                System.out.print("Reason: ");
                String reason = sc.nextLine();
                System.out.print("Transaction date YYYY-MM-DD: ");
                LocalDate date = LocalDate.parse(sc.nextLine());
                System.out.println(service.fileDispute(tx, reg, amount, reason, date) ? "Dispute submitted."
                        : "Dispute must be within 7 days.");
            } else if (choice == 3) {
                System.out.print("Admin access? (yes/no): ");
                boolean admin = sc.nextLine().equalsIgnoreCase("yes");
                System.out.print("Transaction ID: ");
                String tx = sc.nextLine();
                System.out.print("Approve? (yes/no): ");
                boolean approve = sc.nextLine().equalsIgnoreCase("yes");
                System.out.print("Refund to tag ID: ");
                String tag = sc.nextLine();
                System.out.println(
                        service.decide(tx, approve, admin, tag) ? "Decision saved." : "Could not process decision.");
            } else if (choice == 4)
                for (PaymentDispute d : service.all())
                    System.out.println(d);
        } while (choice != 0);
    }
}
