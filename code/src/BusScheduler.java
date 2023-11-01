import java.util.Random;

public class BusScheduler implements Runnable {
    public static Random random;
    private final Resources resources;

    public BusScheduler(Resources resources) {
        this.resources = resources;
        random = new Random();
    }

    @Override
    public void run() {
        int minTime = 10000;
        int maxTime = 100000;
        int waitTime;
        while (true) {
            try {
                waitTime = random.nextInt(maxTime - minTime) + minTime;
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Bus(resources)).start();
        }
    }
}
