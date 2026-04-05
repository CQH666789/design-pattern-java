import java.util.Deque;
import java.util.LinkedList;

public class ThreadPool {
    public static void main(String[] args) {
        InnerThreadPool pool = new InnerThreadPool(6);
        for (int i = 0; i < 15; i++) {
            final int temp = i;
            pool.addTask(() -> {
                System.out.println("第" + temp + "个执行");
            });
        }
    }
}

class InnerThreadPool {
    Deque<Runnable> list = new LinkedList();
    int capacity;
    Thread[] threads;
    public InnerThreadPool (int capacity) {
        this.capacity = capacity;
        threads = new Thread[capacity];

        for (int i = 0; i < capacity; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    synchronized (list) {
                        while (list.isEmpty()) {
                            try {
                                list.wait();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                        Runnable task = list.removeFirst();
                        task.run();
                    }   
                }
            });
            threads[i].start();
        }
    }

    public void addTask (Runnable task) {
        synchronized (list) {
            list.addLast(task);
            list.notifyAll();
        }
    }
}
