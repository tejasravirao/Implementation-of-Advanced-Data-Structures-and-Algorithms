/**SP7 - HASHING
 *
 * SP7 32 - MEMBERS: ESHA PUNJABI, NET ID: ehp170000
 *                   TEJAS RAVI RAO, NET ID: txr171830
 *
 * Please check readme.txt file
 *
 * Robin Hood Hashing
 *
 * *
 * */




package txr171830;

public class Timer {
    long startTime, endTime, elapsedTime, memAvailable, memUsed;

    public Timer() {
        startTime = System.currentTimeMillis();
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public Timer end() {
        endTime = System.currentTimeMillis();
        elapsedTime = endTime-startTime;
        memAvailable = Runtime.getRuntime().totalMemory();
        memUsed = memAvailable - Runtime.getRuntime().freeMemory();
        return this;
    }

    public String toString() {
        return "Time: " + elapsedTime + " msec.\n" + "Memory: " + (memUsed/1048576) + " MB / " + (memAvailable/1048576) + " MB.";
    }
}