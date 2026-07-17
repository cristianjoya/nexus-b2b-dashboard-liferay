package fin.services.rest.client.function;

import jakarta.annotation.Generated;

/**
 * @author krism
 * @generated
 */
@FunctionalInterface
@Generated("")
public interface UnsafeSupplier<T, E extends Throwable> {

	public T get() throws E;

}