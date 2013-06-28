package org.txxf.lang.java.net;

import org.junit.Test;

public class PortSelectorTest {

	@Test
	public void testPortSelector() {
		PortSelector portSel = new PortSelector();
		portSel.callNetstat();
		System.out.println(portSel.getNetstat());
	}
	
}
