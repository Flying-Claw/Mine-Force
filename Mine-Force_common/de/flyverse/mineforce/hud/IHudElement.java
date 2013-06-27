package de.flyverse.mineforce.hud;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface IHudElement {

	public boolean shouldRender();

	public void render(float partialTicks);

	public void clientTick();
}