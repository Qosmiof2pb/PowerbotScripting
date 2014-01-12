package org.script.qosmiof2.nodes;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Item;

public class createPie extends Node {

	public createPie(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		return !ctx.backpack.select().id(Bank.pieShellID).isEmpty()
				&& !ctx.backpack.select().id(Bank.strawberryID).isEmpty()
				&& !ctx.backpack.select().id(Bank.appleID).isEmpty()
				&& !ctx.backpack.select().id(Bank.rawSummerPieID).isEmpty()
				&& !ctx.backpack.select().id(Bank.watermellonID).isEmpty()
				&& !ctx.bank.isOpen();
	}

	@Override
	public void execute() {

		for (Item pieShellID : ctx.backpack.select().id(Bank.pieShellID)
				.first()) {

			for (Item strawBerryID : ctx.backpack.select()
					.id(Bank.strawberryID).first()) {

				for (Item watermellonID : ctx.backpack.select()
						.id(Bank.watermellonID).first()) {

					for (Item appleID : ctx.backpack.select().id(Bank.appleID)
							.first()) {
						
						if (!ctx.backpack.select().id(Bank.strawberryID).isEmpty()) {
							pieShellID.interact("Use");
							strawBerryID.interact("Use");
							
							Timer wait = new Timer(1000);
							while(wait.isRunning() && !ctx.backpack.select().id(Bank.strawberryID).isEmpty()){
								sleep(500);
							}
						}
						
						if (!ctx.backpack.select().id(Bank.watermellonID).isEmpty()) {
							pieShellID.interact("Use");
							watermellonID.interact("Use");
							
							Timer wait1 = new Timer(1000);
							while(wait1.isRunning() && !ctx.backpack.select().id(Bank.watermellonID).isEmpty()){
								sleep(500);
							}
						}
						
						if (!ctx.backpack.select().id(Bank.appleID).isEmpty()) {
							pieShellID.interact("Use");
							appleID.interact("Use");
							
							Timer wait2 = new Timer(1000);
							while(wait2.isRunning() && !ctx.backpack.select().id(Bank.appleID).isEmpty()){
								sleep(500);
							}
						}

					}
				}

			}
		}
	}

}
