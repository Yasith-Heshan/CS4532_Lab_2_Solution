import java.util.Random;

public class BusScheduler implements Runnable {
    public static Random random;
    private final BusStationController busStationController;

    public BusScheduler(BusStationController busStationController) {
        this.busStationController = busStationController;
        random = new Random();
    }

    @Override
    public void run() {
        double meanTime = 20 * 60 * 1000;
        while (true) {
            try {
                Thread.sleep(Math.round(-Math.log(1 - random.nextFloat()) * meanTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Bus(busStationController)).start();
        }
    }
}
