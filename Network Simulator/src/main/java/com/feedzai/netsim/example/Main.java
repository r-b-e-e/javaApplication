package com.feedzai.netsim.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.feedzai.netsim.engine.*;

/**
 * Main function for the network simulator.
 *
 * 
 * @author  Rakesh Balan Lingakumar <rakesh.balan.career@gmail.com>
 * @version 1.0
 * @since   2016-07-18
 */

public class Main {
	
	   /**
	   * This method main function where the Graph is constructed and threading is implemented 
	   */
	public static void main(String[] args) {
		// Create a network with a default latency of 1 ms between nodes
		Network net = Network.createWithLatency(1);

		// Interconnect network elements
		net.connect("A", "D"); // Uses default network latency
		net.connect("B", "D");
		net.connect("C", "E");
		net.connect("I", "G");
		net.connect("J", "F");
		net.connect("K", "H", 10); // Connect K computer to H router with a 10ms
									// latency
		net.connect("D", "E", 3); // D to E has a 3ms latency
		net.connect("D", "F", 2); // D to F has a 2ms latency
		net.connect("E", "F", 4); // E to F has a 4ms latency
		net.connect("E", "G", 5); // E to G has a 5ms latency
		net.connect("G", "F", 3); // G to F has a 3ms latency
		net.connect("F", "H", 5); // F to H has a 5ms latency

		ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		pool.submit(new Runnable() {
			public void run() {
				NetworkPath path = net.sendPacket("C", "J");
				System.out.println(path);
				System.out.println(path.getTime());
			}
		});

		pool.submit(new Runnable() {
			public void run() {
				NetworkPath path = net.sendPacket("D", "J");
				System.out.println(path);
				System.out.println(path.getTime());
			}
		});

		pool.shutdown();
		try {
			if (!pool.awaitTermination(1, TimeUnit.MINUTES)) {
				pool.shutdownNow();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		if (pool.isShutdown() && pool.isTerminated()) {
			System.out.println("ThreadPoolExecutor Shutdown");
		}

	}
}
