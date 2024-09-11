package providers.iterator;

public interface PrintScriptIterator<T> {
    boolean hasNext();
    T next();
    T current();
}
