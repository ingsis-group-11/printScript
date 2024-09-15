package providers.iterator;

public interface PrintScriptIterator<T> {

  boolean hasNext();

  PrintScriptIterator<T> next();

  T current();

  T last();
}