public class Passenger implements Runnable {
    private BusStationController busStationController;

    public Passenger(BusStationController busStationController) {
        this.busStationController = busStationController;
    }

    private void board() {
        System.out.println("PASSENGER : board to bus.");
    }

    @Override
    public void run() {
        try {

            busStationController.passengerCntLock.acquire(); // Lock the operations on waitingPassengerCnt variable until the current passenger added to the count
            busStationController.waitingPassengerCnt += 1;
            System.out.println("Passenger on waiting");
            busStationController.passengerCntLock.release(); // Release the lock on waitingPassengerCnt variable

            busStationController.busArrival.acquire(); // Wait until the bus arrive
            board();
            busStationController.passengerBoarded.release(); // Signal about the passenger boarding

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
