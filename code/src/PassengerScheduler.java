import java.util.Random;

public class PassengerScheduler implements Runnable {
    public static Random random;
    private final BusStationController busStationController;

    public PassengerScheduler(BusStationController busStationController) {
        this.busStationController = busStationController;
        random = new Random();
    }

    @Override
    public void run() {
        double meanTime = 30 * 1000;
        while (true) {
            new Thread(new Passenger(busStationController)).start();
            try {
                Thread.sleep(Math.round(-Math.log(1 - random.nextFloat())* meanTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
