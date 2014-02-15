package se.tanke.exjobb.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class implement the behaviour for simple unary keys.
 * 
 * @author tobias
 * @param <T> The type of the key value
 */
public abstract class AbstractUnaryKey<T> implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private final T keyValue;
    
    protected AbstractUnaryKey(final T keyValue) {
        this.keyValue = keyValue;
    }
    
    public T getKeyValue() {
        return keyValue;
    }

    @Override
    public int hashCode() {
        return keyValue.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractUnaryKey<?> other = (AbstractUnaryKey<?>) obj;
        return Objects.equals(this.keyValue, other.keyValue);
    }
    
    @Override
    public String toString() {
        return keyValue.toString();
    }
}
