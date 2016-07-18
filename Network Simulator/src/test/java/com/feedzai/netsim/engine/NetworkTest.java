package com.feedzai.netsim.engine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;

/**
 * Tests Network class.
 * 
 * @author  Rakesh Balan Lingakumar <rakesh.balan.career@gmail.com>
 * @version 1.0
 * @since   2016-07-18
 */
public class NetworkTest {
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
    public void testSendPacket() throws Exception {
        //TEST 1
        // Simulate sending a packet from "C" to "J"
        NetworkPath path = net.sendPacket("C", "J");

        // Check followed path
        String followedPath = path.toString();
        String expectedPath = "C -> E -> F -> J";
        assertEquals("Packet didn't follow expected path.", expectedPath, followedPath);

        // Check taken time
        int takenTime = path.getTime();
        int expectedTime = 6;
        assertEquals("Packet didn't take the expected time.", expectedTime, takenTime);
        
        
        //TEST 2
        path = net.sendPacket("J", "B");
        followedPath = path.toString();
        takenTime = path.getTime();
        assertEquals("Packet didn't follow expected path.", "J -> F -> D -> B", followedPath);
        assertEquals("Packet didn't take the expected time.", 4, takenTime);
        
        //TEST 3
        path = net.sendPacket("K", "B");
        followedPath = path.toString();
        takenTime = path.getTime();
        assertEquals("Packet didn't follow expected path.", "K -> H -> F -> D -> B", followedPath);
        assertEquals("Packet didn't take the expected time.", 18, takenTime);
        
        //TEST 4
        path = net.sendPacket("D", "K");
        followedPath = path.toString();
        takenTime = path.getTime();
        assertEquals("Packet didn't follow expected path.", "D -> F -> H -> K", followedPath);
        assertEquals("Packet didn't take the expected time.", 17, takenTime);
    }
    
    
    @Test
    public void testSendPacketNegativeScenarios() throws Exception {
        //TEST 1
        NetworkPath path = net.sendPacket("A", "M");

        // Check followed path
        String followedPath = path.toString();
        String expectedPath = "";
        assertEquals("Packet didn't follow expected path.", expectedPath, followedPath);

        // Check taken time
        int takenTime = path.getTime();
        int expectedTime = 0;
        assertEquals("Packet didn't take the expected time.", expectedTime, takenTime);
    }
}
