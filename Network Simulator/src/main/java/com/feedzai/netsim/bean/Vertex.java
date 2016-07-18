package com.feedzai.netsim.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a single component (vertex) of the computer network.
 * 
 * @author  Rakesh Balan Lingakumar <rakesh.balan.career@gmail.com>
 * @version 1.0
 * @since   2016-07-18
 */

public class Vertex implements Comparable<Vertex> {
	private final String vertexName;
	public int latency = Integer.MAX_VALUE;
	private final Map<Vertex, Integer> neighbours = new HashMap<Vertex, Integer>();
	public Vertex previous = null;

   /**
   * Vertex class constructor
   * @param vertexName This is the Vertex Name
   */
	public Vertex(String vertexName) {
		this.vertexName = vertexName;
	}

   /**
   * neighbours variable's getter function
   * @return Map This map contains all the neighbours of the given vertex
   */
	public Map<Vertex, Integer> getNeighbours() {
		return neighbours;
	}

   /**
   * This method is used to compare two vertex
   * @param other This is the other vertex for comparison
   * @return int  Returns the compared value -1, 0 ,1
   */
	@Override
	public int compareTo(Vertex other) {
		return Integer.compare(latency, other.latency);
	}

   /**
   * This method is used to back trace the path from the shortest path table created using Dijkstra's algorithm 
   * @return String  Returns the path
   */
	public String backTrace() {
		if (this == this.previous) {
			return this.vertexName;
		} else if (this.previous == null) {
			return this.vertexName + ": Can't be reached";
		} else {
			return this.previous.backTrace() + " -> " + this.vertexName;
		}
	}
}
