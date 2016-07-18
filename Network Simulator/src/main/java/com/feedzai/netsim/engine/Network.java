package com.feedzai.netsim.engine;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import com.feedzai.netsim.bean.Vertex;

/**
 * This class represents the overall computer network.
 * 
 * @author  Rakesh Balan Lingakumar <rakesh.balan.career@gmail.com>
 * @version 1.0
 * @since   2016-07-18
 */
public class Network {
	protected static Map<String, Vertex> networkGraph = new HashMap<String, Vertex>();
	public int defaultLatency;

	
   /**
   * This method is used to create a Network object with the default latency
   * @param latency This is the default latency
   * @return Network This returns Network object with the default latency
   */
	public static Network createWithLatency(int latency) {
		// TODO: Implement factory method for creating a new network with a
		// default latency

		Network networkWithDefaultLatency = new Network();
		networkWithDefaultLatency.defaultLatency = latency;
		return networkWithDefaultLatency;
	}

   /**
   * This method is used to connect two vertex with a default latency
   * @param idA This is Vertex A
   * @param idB This is Vertex B
   */
	public void connect(String idA, String idB) {
		// TODO: Implement adding a network connection between to nodes --
		// default latency is used
		// You may (or may not) need to throw exceptions... change signature if
		// needed

		this.connect(idA, idB, defaultLatency);
	}

   /**
   * This method is used to connect two vertex with a given latency
   * @param idA This is Vertex A
   * @param idB This is Vertex A
   * @param latency This is the given latency
   */
	public void connect(String idA, String idB, int latency) {
		// TODO: Implement adding a network connection between two nodes with a
		// specified latency
		// You may (or may not) need to throw exceptions... change signature if
		// needed

		if (!networkGraph.containsKey(idA)) {
			networkGraph.put(idA, new Vertex(idA));
		}

		if (!networkGraph.containsKey(idB)) {
			networkGraph.put(idB, new Vertex(idB));
		}

		// Given newtwork is an Undirected graph. So bi-directional
		networkGraph.get(idA).getNeighbours().put(networkGraph.get(idB), latency);
		networkGraph.get(idB).getNeighbours().put(networkGraph.get(idA), latency);
	}
	
   /**
   * This method is used to send packet from source to destination
   * @param idA This is the Source Vertex
   * @param idB This is the destination Vertex
   * @return NetworkPath This returns NetworkPath object with the destination Vertex
   */
	public NetworkPath sendPacket(String idA, String idB) {
		// TODO: Simulates sending a packet from node idA to node idB. Returns
		// the route that the packet followed
		// You may (or may not) need to throw exceptions... change signature if
		// needed

		dijkstraAlgorithm(idA);
		NetworkPath path = new NetworkPath(idB);
		return path;
	}

   /**
   * This is the implementation of Dijkstra's algorithm using binary heap
   * @param startVertex This is the Source Vertex
   */
	public void dijkstraAlgorithm(String startVertex) {
		Vertex uVertex, vVertex;
		if (!networkGraph.containsKey(startVertex)) {
			System.err.printf("Network doesn't contain the given start vertex {%s}\n", startVertex);
			return;
			// System.exit(0);
		}

		final Vertex sourceVertex = networkGraph.get(startVertex);
		NavigableSet<Vertex> navigableSet = new TreeSet<Vertex>();

		for (Vertex vertex : networkGraph.values()) {
			if (vertex == sourceVertex) {
				vertex.previous = sourceVertex;
				vertex.latency = 0;
			} else {
				vertex.previous = null;
				vertex.latency = Integer.MAX_VALUE;
			}
			navigableSet.add(vertex);
		}

		while (!navigableSet.isEmpty()) {
			uVertex = navigableSet.pollFirst();
			if (uVertex.latency == Integer.MAX_VALUE)
				break;
			for (Map.Entry<Vertex, Integer> neighbourEntry : uVertex.getNeighbours().entrySet()) {
				vVertex = neighbourEntry.getKey();
				final int alternateLatency = uVertex.latency + neighbourEntry.getValue();
				if (alternateLatency < vVertex.latency) {
					navigableSet.remove(vVertex);
					vVertex.latency = alternateLatency;
					vVertex.previous = uVertex;
					navigableSet.add(vVertex);
				}
			}
		}
	}
}
