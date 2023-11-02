import java.util.concurrent.Semaphore;

public class BusStationController {
    public Semaphore passengerBoarded;
    public Semaphore passengerCntLock;
    public Semaphore busArrival;
    public int waitingPassengerCnt;

    public BusStationController() {
        this.waitingPassengerCnt = 0; // Count of the waiting passengers at the bus stop
        this.passengerCntLock = new Semaphore(1); // Avoid new passengers when bus at the bus stop
        this.busArrival = new Semaphore(0); // Signal when bus arrived
        this.passengerBoarded = new Semaphore(0); // Signal when passenger is boarded
    }

}
