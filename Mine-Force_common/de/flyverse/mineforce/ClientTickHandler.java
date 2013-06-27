package de.flyverse.mineforce;

import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.flyverse.mineforce.hud.HUD_CurrentGun_Info;
import de.flyverse.mineforce.hud.IHudElement;

@SideOnly(Side.CLIENT)
public class ClientTickHandler implements ITickHandler {

	public static final ClientTickHandler INSTANCE = new ClientTickHandler();

	public Collection<IHudElement> hud;
	
	public ClientTickHandler(){
		hud = new LinkedHashSet<IHudElement>();
		hud.add(new HUD_CurrentGun_Info());
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if(type.equals(EnumSet.of(TickType.CLIENT)))
			clientTick();
		else {
		renderTick((Float) tickData[0]);
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "FlyMod_MineForce";
	}

	public void clientTick() {
		for(IHudElement hudelement : hud){
			hudelement.clientTick();
		}
	}

	public void renderTick(float partialTicks) {
		for(IHudElement hudelement : hud){
			if(hudelement.shouldRender()){
				hudelement.render(partialTicks);
			}
		}
	}
}