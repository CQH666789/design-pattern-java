public class Singleton {
    private static final Singleton SINGLETON = new Singleton();

    private Singleton () {}

    public static Singleton getInstance () {
        return SINGLETON;
    }
}

class Singleton2 {
    private static volatile Singleton2 singleton;

    private Singleton2 () {}

    public static Singleton2 getInstance () {
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
