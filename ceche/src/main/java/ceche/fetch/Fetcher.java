package ceche.fetch;

/**
 * Retrieves data from based on key.
 * @author Angel
 *
 * @param <KEY> request key type
 * @param <DATA> response type
 */
public interface Fetcher<KEY,DATA> {
	
	/**
	 * Load data
	 * @param key request key
	 * @return
	 */
	public DATA load(KEY key);
}
