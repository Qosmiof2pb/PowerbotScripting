package org.script.qosmiof2.nodes;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;

public abstract class Node extends MethodProvider {

	public Node(MethodContext ctx) {
		super(ctx);
	}
	
	public abstract boolean activate();
	public abstract void execute();


}

