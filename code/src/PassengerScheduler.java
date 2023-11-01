import java.util.Random;

public class PassengerScheduler implements Runnable {
    public static Random random;
    private final Resources resources;

    public PassengerScheduler(Resources resources) {
        this.resources = resources;
        random = new Random();
    }

    @Override
    public void run() {
        int minTime = 100;
        int maxTime = 1000;
        int waitTime;
        while (true) {
            new Thread(new Passenger(resources)).start();
            try {
                waitTime = random.nextInt(maxTime - minTime) + minTime;
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
