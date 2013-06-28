package org.txxf.lang.java.net;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class AvailablePortTest {

	NetstatAvailablePort avaiPort;

	@Before
	public void setUp() {
		avaiPort = new NetstatAvailablePort();
	}

	@Test
	public void testPortAvailable() {
		Set<Integer> notAvaiPorts = avaiPort.notAvaiPorts();
		int port;
		do {
			port = randomInt(30000, 40000);
		} while (notAvaiPorts.contains(port));
		assertTrue(avaiPort.portAvailable(port));
	}

	@Test
	public void testPortNotAvailable() {
		Set<Integer> notAvaiPorts = avaiPort.notAvaiPorts();
		assertFalse(avaiPort.portAvailable(notAvaiPorts.iterator().next()));
	}

	private int randomInt(int lowBound, int upperBound) {
		Random random = new Random();
		return random.nextInt(upperBound - lowBound) + lowBound;
	}

}
