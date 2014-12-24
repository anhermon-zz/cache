package ceche.fetch.imp.file;

import java.io.File;

import ceche.fetch.Fetcher;

/**
 * Fetches data from file.
 * @author Angel
 *
 * @param <KEY>
 * @param <DATA>
 */
public abstract class AbstractFileFatcher<KEY, DATA> implements Fetcher<KEY, DATA> {
	
	@Override
	public DATA load(KEY key) {
		File source = getSource(key);
		if(!source.exists()) return null;
		return read(source);
	}
	
	/**
	 * Generate {@link File} instance based on key.
	 * @param key request key
	 * @return {@link File}
	 */
	protected abstract File getSource(KEY key);
	/**
	 * Retrieve data from {@link File}
	 * @param source
	 * @return data
	 */
	protected abstract DATA read(File source);
	
}
