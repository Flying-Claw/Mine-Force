package de.flyverse.mineforce.hud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import cpw.mods.fml.client.FMLClientHandler;
import de.flyverse.mineforce.items.Shooter;

public class HUD_CurrentGun_Info implements IHudElement {

	@Override
	public boolean shouldRender() {
		if(!FMLClientHandler.instance().getClient().inGameHasFocus){return false;}
		if(FMLClientHandler.instance().getClient().thePlayer == null){return false;}
		if(FMLClientHandler.instance().getClient().thePlayer.getHeldItem() == null){return false;}
		if(FMLClientHandler.instance().getClient().thePlayer.getHeldItem().getItem() instanceof Shooter){
			return true;
		}
		return false;
	}

	@Override
	public void render(float partialTicks) {
		Minecraft mc = FMLClientHandler.instance().getClient();
		ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);

		String ammo = String.valueOf(((Shooter)mc.thePlayer.getHeldItem().getItem()).getAmmo());
		String rammo = String.valueOf(((Shooter)mc.thePlayer.getHeldItem().getItem()).getReserveAmmo());

		int width = res.getScaledWidth();
		int height = res.getScaledHeight();

		boolean unicode = mc.fontRenderer.getUnicodeFlag();
		mc.fontRenderer.setUnicodeFlag(true);
		int al 	= 18 + mc.fontRenderer.getStringWidth(ammo);
		int ral	= 18 + mc.fontRenderer.getStringWidth(rammo);

        //Hier wird die Schrift auf dem Bildschirm gerendert. Der Text, Die Positionen und der Hexal-Farbcode.
		mc.fontRenderer.drawStringWithShadow(ammo, width / 2 - al / 2 + 18, height - 81, 0x5078C0);
		mc.fontRenderer.drawStringWithShadow(rammo, width / 2 - ral / 2 + 18, height - 99, 0x5050B0);
		mc.fontRenderer.setUnicodeFlag(unicode);
	}

	@Override
	public void clientTick() {
		
	}

}
