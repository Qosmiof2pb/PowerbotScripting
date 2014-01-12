package org.script.qosmiof2;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import org.powerbot.event.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;
import org.script.qosmiof2.nodes.*;

public class RawSummerPie extends PollingScript{

	private final ArrayList<Node> nodes = new ArrayList<>();

	public RawSummerPie(){
		Collections.addAll(nodes, new Bank(ctx), new createPie(ctx));
	}
	@Override
	public int poll() {
		for (final Node node : nodes) {
			if (node.activate()) {
				node.execute();
				return Random.nextInt(500, 1500);
			}
		}
		return 0;
	}
}
