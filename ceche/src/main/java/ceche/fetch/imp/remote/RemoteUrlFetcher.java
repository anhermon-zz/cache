package ceche.fetch.imp.remote;

import java.io.File;
import java.net.URL;

/**
 * Fetches data from service.
 * @author Angel
 *
 * @param <KEY>
 * @param <DATA>
 */
public abstract class RemoteUrlFetcher<KEY, DATA> implements RemoteFetcher<KEY, DATA>{
	
	@Override
	public DATA load(KEY key) {
		URL source = getSource(key);
		return read(source);
	}
	
	/**
	 * Generate {@link URL} instance based on key.
	 * @param key request key
	 * @return {@link URL}
	 */
	protected abstract URL getSource(KEY key);
	/**
	 * Retrieve data from {@link File}
	 * @param source
	 * @return data
	 */
	protected abstract DATA read(URL source);
}
