package com.daniel.algorithms.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class CacheManager {
	/* This is the HashMap that contains all objects in the cache. */
	private static HashMap cacheHashMap = new HashMap();
	static int milliSecondSleepTime = 5000;
	/* This object acts as a semaphore, which protects the HashMap */
	/* RESERVED FOR FUTURE USE private static Object lock = new Object(); */
	static {
		try {
			/*
			 * Create background thread, which will be responsible for purging
			 * expired items.
			 */
			Thread threadCleanerUpper = new Thread(new Runnable() {
				/*
				 * The default time the thread should sleep between scans. The
				 * sleep method takes in a millisecond value so 5000 = 5
				 * Seconds.
				 */

				public void run() {
					try {
						/*
						 * Sets up an infinite loop. The thread will continue
						 * looping forever.
						 */
						while (true) {
							System.out.println("ThreadCleanerUpper Scanning For	Expired Objects...");
							/*
							 * Get the set of all keys that are in cache. These
							 * are the unique identifiers
							 */
							Set keySet;
							synchronized (cacheHashMap){
								keySet = cacheHashMap.keySet();
							}
							/* An iterator is used to move through the Keyset */
							Iterator keys = keySet.iterator();
							/*
							 * Sets up a loop that will iterate through each key
							 * in the KeySet
							 */
							while (keys.hasNext()) {
								/*
								 * Get the individual key. We need to hold on to
								 * this key in case it needs to be removed
								 */
								Object key = keys.next();
								/*
								 * Get the cacheable object associated with the
								 * key inside the cache
								 */
								Cacheable value;
								synchronized (cacheHashMap){
									value = (Cacheable) cacheHashMap.get(key);
								}
								/* Is the cacheable object expired? */
								if (value.isExpired()) {
									/*
									 * Yes it's expired! Remove it from the
									 * cache
									 */
									synchronized (cacheHashMap){
										cacheHashMap.remove(key);
									}
									System.out.println(
											"ThreadCleanerUpper Running.	Found an Expired Object in the Cache.");
								}
							}
							/*************************************************************************
							 ******* A LRU (least-recently used) or LFU
							 * (least-frequently used) ***** would best be
							 * inserted here *****
							 *************************************************************************/
							/*
							 * Puts the thread to sleep. Don't need to check it
							 * continuously
							 */
							Thread.sleep(milliSecondSleepTime);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return;
				}
			});

			// Sets the thread's priority to the minimum value.
			threadCleanerUpper.setPriority(Thread.MIN_PRIORITY);
			// Starts the thread.
			threadCleanerUpper.start();
		} catch (Exception e) {
			System.out.println("CacheManager.Static Block: " + e);
		}
	}

	public CacheManager() {
	}

	public static void putCache(Cacheable object) {
		// Remember if the HashMap previously contains a mapping for the key,
		// the old value
		// will be replaced. This is valid functioning.
		cacheHashMap.put(object.getIdentifier(), object);
	}

	public static Cacheable getCache(Object identifier) {
		Cacheable object = null;
		synchronized (cacheHashMap) // UNCOMMENT LINE XXX
		{ // UNCOMMENT LINE XXX
			object = (Cacheable) cacheHashMap.get(identifier);
			// The code to create the object would be placed here.
		} // UNCOMMENT LINE XXX
		if (object == null)
			return null;
		if (object.isExpired()) {
			synchronized (cacheHashMap){
				cacheHashMap.remove(identifier);
			}
			return null;
		} else {
			return object;
		}
	}
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
