package com.toll.cli;
import com.toll.models.Lane;
import java.util.Scanner;

public class LaneCli {
    public void showLaneDetails(Scanner sc) {
        System.out.print("Lane number: "); 
        int number = Integer.parseInt(sc.nextLine());
        System.out.print("Lane type: "); 
        String type = sc.nextLine();
        Lane lane = new Lane(number, type);
        System.out.println("Created: " + lane);
        System.out.print("Vehicle currently processing? (yes/no): ");
        lane.setProcessingVehicle(sc.nextLine().equalsIgnoreCase("yes"));
        System.out.println(lane.close() ? "Lane closed." : "Cannot close while a vehicle is being processed.");
    }
}
