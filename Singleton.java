public class Singleton {
    private static final Singleton singleton = new Singleton();

    private Singleton () {}

    public Singleton getInstance () {
        return singleton;
    }
}

class Singleton2 {
    private static volatile Singleton2 singleton;

    private Singleton2 () {}

    public Singleton2 getInstance () {
        if (singleton == null) {
            synchronized (Singleton2.class) {
                if (singleton == null) {
                    singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }
}
