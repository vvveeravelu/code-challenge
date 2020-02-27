package com.vcorp.codechallenge.helper;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vcorp.codechallenge.vo.CityVo;

/**
 * @author VVEERAVELU
 *
 */
public class CityPathHelper {
	 private CityPathHelper() { }
	 
	 private static final String YES="yes";
	 private static final String NO="no";
	 private static final Log LOG = LogFactory.getLog(CityPathHelper.class);

	
	    /**
	     * @param source
	     * @param destination
	     * @return
	     * Find if destination city is reachable from origin. Will visit all the cities
	     * on the bucket list which is built by collecting all the neighbours of a visited place
	     */
	    public static String checkPath(CityVo source, CityVo destination) {



	        LOG.info("Origin: " + source.getName() + ", destination: " + destination.getName());

	        if (source.equals(destination)) return YES;

	        if (source.getNearby().contains(destination)) return YES;

	        /*
	         * The origin city was already visited since we have started from it
	         */
	        Set<CityVo> visited = new HashSet<>(Collections.singleton(source));

	        /*
	         * Put all the neighboring cities into a bucket list
	         */
	        Deque<CityVo> bucketlist = new ArrayDeque<>(source.getNearby());


	        while (!bucketlist.isEmpty()) {


	        	CityVo city = bucketlist.getLast();

	            if (city.equals(destination)) return YES;

	            // remove the city from the bucket list

	            // first time visit?
	            if (!visited.contains(city)) {

	                visited.add(city);

	                // add neighbours to the bucket list and
	                // remove already visited cities from the list
	                bucketlist.addAll(city.getNearby());
	                bucketlist.removeAll(visited);

	                LOG.info("Visiting: ["
	                        + city.getName()
	                        + "] , neighbours: ["
	                        + (city.prettyPrint())
	                        + "], bucketlist: ["
	                        + bucketlist.toString()
	                        + "]");
	            } else {
	                // the city has been visited, so remove it from the bucket list
	                bucketlist.removeAll(Collections.singleton(city));
	            }
	        }

	        return NO;
	    }
}
