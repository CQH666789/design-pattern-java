import java.util.LinkedList;
import java.util.List;

public class Listener {
    public static void main(String[] args) {
        Observable observable = new Observable();
        Observer observer1 = new Observer() {
            public void method () {
                System.out.println("第1个监听到了");
            }
        };
        Observer observer2 = new Observer() {
            public void method () {
                System.out.println("第2个监听到了");
            }
        };
        Observer observer3 = new Observer() {
            public void method () {
                System.out.println("第3个监听到了");
            }
        };
        observable.register(observer1);
        observable.register(observer2);
        observable.register(observer3);
        observable.publish();
    }
}

interface Observer {
    void method();
}

class Observable {
    List<Observer> list;

    public Observable () {
        list = new LinkedList<>();
    }

    public void register(Observer observer) {
        list.add(observer);
    }

    public void unRegister(Observer observer) {
        list.remove(observer);
    }

    public void publish() {
        for (Observer observer: list) {
            observer.method();
        }
    }
}



