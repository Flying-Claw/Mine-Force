package de.flyverse.mineforce.items;

import cpw.mods.fml.client.FMLClientHandler;

public class Machine_Gun extends Shooter {

	public Machine_Gun(int id, String name, int max_ammo, int reserve_ammo,	int shoots, int reload_time, long timebetweenshoots, float rueckstoss, float prezi, int bt, float damage) {
		super(id, name, max_ammo, reserve_ammo, shoots, reload_time, timebetweenshoots,	rueckstoss, prezi, bt, damage, false);
	}

	@Override
	public void shoot() {
		
	}

	@Override
	public void onReloadStart() {
		FMLClientHandler.instance().getClient().thePlayer.isSwingInProgress = true;
		FMLClientHandler.instance().getClient().thePlayer.swingProgressInt = -5;
	}

	@Override
	public void onReloadEnd() {
		this.fillAmmo();
		FMLClientHandler.instance().getClient().thePlayer.isSwingInProgress = false;
		FMLClientHandler.instance().getClient().thePlayer.swingItem();
	}

	

}
