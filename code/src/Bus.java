public class Bus implements Runnable {
    private BusStationController busStationController;

    public Bus(BusStationController busStationController) {
        this.busStationController = busStationController;
    }

    private void depart(int loadedPassengerCnt) {
        System.out.println("Loaded passenger count: " + loadedPassengerCnt + "\nWaiting passenger count: " + busStationController.waitingPassengerCnt);
        System.out.println("BUS DEPARTED !!! \n");
    }

    @Override
    public void run() {
        try {
            busStationController.passengerCntLock.acquire(); // Avoid new passengers coming to the bus stop after the bus arrived
            System.out.println("BUS ARRIVED !!! \n");
            System.out.println("Waiting passenger count: "+ busStationController.waitingPassengerCnt);
            int loadingPassengerCnt = Math.min(busStationController.waitingPassengerCnt,50);
            System.out.println("Loading passenger count : "+ loadingPassengerCnt );

            for (int i = 0; i < loadingPassengerCnt; i++) {
                busStationController.busArrival.release(); // Signal to the i_th passenger about the bus arrival
                busStationController.passengerBoarded.acquire(); // Wait on passenger boarding
            }

            busStationController.waitingPassengerCnt = Math.max(busStationController.waitingPassengerCnt -50 , 0);

            busStationController.passengerCntLock.release(); // Signal the capability of coming to the bus stop after departure of the bus
            depart(loadingPassengerCnt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
