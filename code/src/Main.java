public class Main {

    private void startProgram() {
        System.out.println("-----Application started-----");

        BusStationController busStationController = new BusStationController();

        Thread busScheduler = new Thread(new BusScheduler(busStationController));
        Thread passengerScheduler = new Thread(new PassengerScheduler(busStationController));

        busScheduler.start();
        passengerScheduler.start();

        try {
            busScheduler.join();
            passengerScheduler.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Application terminated");
    }

    public static void main(String[] args) {
        new Main().startProgram();
    }
}
