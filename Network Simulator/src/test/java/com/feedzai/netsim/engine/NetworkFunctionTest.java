package com.feedzai.netsim.engine;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests Network class functions.
 * 
 * @author  Rakesh Balan Lingakumar <rakesh.balan.career@gmail.com>
 * @version 1.0
 * @since   2016-07-18
 */
public class NetworkFunctionTest {
	public static Network net;
	
	@BeforeClass
	public static void beforeClass(){
		// Create a network with a default latency of 1 ms between nodes
        net = Network.createWithLatency(1);

        // Interconnect network elements
        net.connect("A", "D");                // Uses default network latency
        net.connect("B", "D");
        net.connect("C", "E");
        net.connect("I", "G");
        net.connect("J", "F");
        net.connect("K", "H", 10);            // Connect K computer to H router with a 10ms latency
        net.connect("D", "E", 3);             // D to E has a 3ms latency
        net.connect("D", "F", 2);             // D to F has a 2ms latency
        net.connect("E", "F", 4);             // E to F has a 4ms latency
        net.connect("E", "G", 5);             // E to G has a 5ms latency
        net.connect("G", "F", 3);             // G to F has a 3ms latency
        net.connect("F", "H", 5);             // F to H has a 5ms latency
	}
	
	@Test
	public void testSendPacket() {
		assertEquals("B", net.sendPacket("J", "B").endVertexName);
		assertEquals("D", net.sendPacket("J", "D").endVertexName);
	}
	
	@Test
	public void testCreateWithLatency() {
		assertEquals(2, net.createWithLatency(2).defaultLatency);
		assertEquals(1, net.createWithLatency(1).defaultLatency);
	}
	
	

}
