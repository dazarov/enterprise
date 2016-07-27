package com.daniel.algorithms.cache;

public class CacheManagerTestProgram {
	public CacheManagerTestProgram() {
	}

	public static void main(String[] args) {
		CacheManagerTestProgram cacheManagerTestProgram1 = new CacheManagerTestProgram();
		/*
		 * This is the object that we are going to cache. Admittedly this is a
		 * trivial object to cache. You might replace our alphabet with a list
		 * of every character in the world.
		 */
		String s = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		/*
		 * Create an instance of CachedObject, set the minutesToLive to 1
		 * minute. Give the object some unique identifier.
		 */
		CachedObject co = new CachedObject(s, new Long(1234), 1);
		/* Place the object into the cache! */
		CacheManager.putCache(co);
		/* Try to retrieve the object from the cache! */
		CachedObject o = (CachedObject) CacheManager.getCache(new Long(1234));
		/* Let's see if we got it! */
		if (o == null)
			System.out.println("CacheManagerTestProgram.Main:  FAILURE!  Object not Found.");
		else
			System.out.println("CacheManagerTestProgram.Main:  SUCCESS! " + ((String) o.object).toString());
	}
}
