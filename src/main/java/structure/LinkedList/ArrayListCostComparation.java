package structure.LinkedList;

import structure.StackAndQueue.ArrayQueue;
import structure.StackAndQueue.LoopQueue;
import structure.StackAndQueue.MyQueue;

import java.util.Random;

public class ArrayListCostComparation {

    private static final int OPT_COUNT = 10000;

    public static double timeCompare(MyQueue<Integer> queue, int optCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < optCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < optCount; i++) {
            queue.dequeue();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.printf("ArrayQueue Cost Time: %f s\n", timeCompare(arrayQueue, OPT_COUNT));

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        System.out.printf("LoopQueue Cost Time: %f s\n", timeCompare(loopQueue, OPT_COUNT));

        LinkedListQueueWithTail<Integer> linkedListQueue = new LinkedListQueueWithTail<>();
        double time3 = timeCompare(linkedListQueue, OPT_COUNT);
        System.out.println("LinkedListQueue, time: " + time3 + " s");    }
}
