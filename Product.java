import java.util.Deque;
import java.util.LinkedList;

public class Product {
    public static void main (String[] args) {
        Deque<Integer> list = new LinkedList<>();
        int CAPACITY = 5;

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (list) {
                    while (list.size() == CAPACITY) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    list.addLast(i);
                    System.out.println("生产了:" + i);
                    list.notifyAll();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized(list) {
                    while (list.isEmpty()) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    int cur = list.removeFirst();
                    list.notifyAll();
                    System.out.println("消费了:" + cur);
                }
            }
        }).start();
    }
}
