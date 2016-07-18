package com.feedzai.netsim.engine;

/**
 * This class represents the path followed by a packet in the network.
 * 
 * @author  Rakesh Balan Lingakumar <rakesh.balan.career@gmail.com>
 * @version 1.0
 * @since   2016-07-18
 */
public class NetworkPath {

	public String endVertexName;
	public int latency;

   /**
   * This method is the constructor of NetworkPath
   * @param endVertexName This is the end Vertex
   */
	public NetworkPath(String endVertexName) {
		this.endVertexName = endVertexName;
	}
	
	
	/**
	 * Time token to follow this path.
	 * 
	 * @return The time (ms)
	 */
	public int getTime() {
		// TODO: Change this so that it corresponds to the time taken to follow
		// the path
		int time = 0;
		try {
			time = Network.networkGraph.get(endVertexName).latency;
		} catch (NullPointerException e) {
			
		}
		return time;
	}

	/**
	 * To convert to string.
	 * 
	 * @return String  	converted string
	 */
	@Override
	public String toString() {
		return printPath(endVertexName);
	}

	
   /**
   * This method is used to print the path
   * @param endVertex This is the end vertex
   * @return String  Returns the path
   */
	public String printPath(String endVertex) {
		if (!Network.networkGraph.containsKey(endVertex)) {
			System.err.printf("Network doesn't contain the given end vertex {%s}\n", endVertex);
			return "";
		}
		return Network.networkGraph.get(endVertex).backTrace();
	}
}
