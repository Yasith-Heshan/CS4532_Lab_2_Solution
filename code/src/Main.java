public class Main {

    private void startProgram() {
        System.out.println("-----Application started-----");

        Resources resources = new Resources();

        Thread busScheduler = new Thread(new BusScheduler(resources));
        Thread passengerScheduler = new Thread(new PassengerScheduler(resources));

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
