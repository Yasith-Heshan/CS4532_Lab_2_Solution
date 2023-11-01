public class Bus implements Runnable {
    private Resources resources;

    public Bus(Resources resources) {
        this.resources = resources;
    }

    private void depart(int loadedPassengerCnt) {
        System.out.println("Loaded passenger count: " + loadedPassengerCnt + "\nWaiting passenger count: " + resources.waitingPassengerCnt);
        System.out.println("BUS DEPARTED !!! \n");
    }

    @Override
    public void run() {
        try {
            resources.passengerCntLock.acquire(); // Avoid new passengers coming to the bus stop after the bus arrived
            System.out.println("BUS ARRIVED !!! \n");
            System.out.println("Waiting passenger count: "+ resources.waitingPassengerCnt);
            int loadingPassengerCnt = Math.min(resources.waitingPassengerCnt,50);
            System.out.println("Loading passenger count : "+ loadingPassengerCnt );

            for (int i = 0; i < loadingPassengerCnt; i++) {
                resources.busArrival.release(); // Signal to the i_th passenger about the bus arrival
                resources.passengerBoarded.acquire(); // Wait on passenger boarding
            }

            resources.waitingPassengerCnt = Math.max(resources.waitingPassengerCnt -50 , 0);

            resources.passengerCntLock.release(); // Signal the capability of coming to the bus stop after departure of the bus
            depart(loadingPassengerCnt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
