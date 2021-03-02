package controller;

public class CarEvent {
    final private Command command;
    private final String description;
    private double amount;
    public CarEvent(String description,Command command, double amount){
        this.description = description;
        this.amount = amount;
        this.command = command;
    }
    public CarEvent(String description,Command command){
        this.description = description;
        this.amount = 1;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public double getAmount() {
        return amount;
    }

    public enum Command {
        GAS,
        GAS_SPEED,
        BRAKE,
        TURBO_ON,
        TURBO_OFF,
        LOWER_BED,
        LIFT_BED
    }


}
