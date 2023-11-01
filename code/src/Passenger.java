public class Passenger implements Runnable {
    private Resources resources;

    public Passenger(Resources resources) {
        this.resources = resources;
    }

    private void board() {
        System.out.println("PASSENGER : board to bus.");
    }

    @Override
    public void run() {
        try {

            resources.passengerCntLock.acquire(); // Lock the operations on waitingPassengerCnt variable until the current passenger added to the count
            resources.waitingPassengerCnt += 1;
            System.out.println("Passenger on waiting");
            resources.passengerCntLock.release(); // Release the lock on waitingPassengerCnt variable

            resources.busArrival.acquire(); // Wait until the bus arrive
            board();
            resources.passengerBoarded.release(); // Signal about the passenger boarding

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
