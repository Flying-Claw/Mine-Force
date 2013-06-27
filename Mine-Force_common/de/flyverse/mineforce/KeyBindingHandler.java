package de.flyverse.mineforce;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import de.flyverse.mineforce.items.Shooter;

public class KeyBindingHandler extends KeyHandler{

	public static KeyBinding reload = new KeyBinding("Reload Gun (Mine-Force)", Keyboard.KEY_R);

	public static KeyBinding[] arrayOfKeys = new KeyBinding[] {reload};
	public static boolean[] areRepeating = new boolean[] {false};



	public KeyBindingHandler() {
		super(arrayOfKeys, areRepeating);
	}
	
	@Override
	public String getLabel() {
		return "Mine-Force Key-Binding";
	}
	
	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if(tickEnd) {
			if (FMLClientHandler.instance().getClient().currentScreen == null) {
				if(kb.keyCode == reload.keyCode) {
					if(FMLClientHandler.instance().getClient().thePlayer == null){return;}
					if(FMLClientHandler.instance().getClient().thePlayer.getHeldItem() == null){return;}
					if(FMLClientHandler.instance().getClient().thePlayer.getHeldItem().getItem() instanceof Shooter){
						((Shooter)FMLClientHandler.instance().getClient().thePlayer.getHeldItem().getItem()).reload();
					}
				}
			}
		}
	}
	
	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}