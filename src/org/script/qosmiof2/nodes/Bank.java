package org.script.qosmiof2.nodes;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;

public class Bank extends Node {

	public static int pieShellID = 2567;
	public static int strawberryID = 7307;
	public static int appleID = 2404;
	public static int rawSummerPieID = 11275;
	public static int watermellonID = 5982;

	public Bank(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return ctx.backpack.select().id(pieShellID).isEmpty()
				&& ctx.backpack.select().id(strawberryID).isEmpty()
				&& ctx.backpack.select().id(appleID).isEmpty()
				&& !ctx.backpack.select().id(rawSummerPieID).isEmpty()
				&& !ctx.bank.isOpen();
	}

	@Override
	public void execute() {

		if (ctx.movement.isReachable(ctx.players.local().getLocation(),
				ctx.bank.getNearest())) {
			
			ctx.bank.open();
			Timer openingBank = new Timer(3000);
			while(!ctx.bank.isOpen() && openingBank.isRunning()){
				sleep(500);
			}
			
			if(ctx.bank.isOpen()){
				ctx.bank.withdraw(appleID, 1);
				Timer wait = new Timer(2000);
				while(ctx.backpack.select().id(appleID).isEmpty() && wait.isRunning()){
					sleep(500);
				}
				
				ctx.bank.withdraw(pieShellID, 1);
				Timer wait1 = new Timer(2000);
				while(ctx.backpack.select().id(appleID).isEmpty() && wait1.isRunning()){
					sleep(500);
				}
				
				ctx.bank.withdraw(strawberryID, 1);
				Timer wait2 = new Timer(2000);
				while(ctx.backpack.select().id(appleID).isEmpty() && wait2.isRunning()){
					sleep(500);
				}
				
				if(!ctx.backpack.select().id(strawberryID).isEmpty()
						&& !ctx.backpack.select().id(appleID).isEmpty()
						&& !ctx.backpack.select().id(pieShellID).isEmpty()){
					
					ctx.bank.close();
					Timer closingBank = new Timer(2000);
					while(ctx.bank.isOpen() && closingBank.isRunning()){
						sleep(500);
					}
					
				}
			}

		} else {
			ctx.movement.stepTowards(ctx.bank.getNearest());
			
			Timer walking = new Timer(10000);
			while(ctx.players.local().isInMotion() && walking.isRunning()){
				sleep(500);
			}
		}

	}

}
