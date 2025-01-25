package net.ensah.events;




public abstract class BaseEvent<T> {

   private final T id;

    public BaseEvent(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
