package de.flyverse.mineforce;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import de.flyverse.mineforce.blocks.Blocks;
import de.flyverse.mineforce.items.Items;
import de.flyverse.mineforce.items.Machine_Gun;
import de.flyverse.mineforce.lib.Reference;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class MineForce {
	
	
	@PreInit
	public void preInit(FMLPreInitializationEvent e){
		Blocks.initBlocks();
		Items.initItems();
		Item spider36 = new Machine_Gun(3333, "Spider 36", 30, 125, 500, 1000, 100L, 5F, 0.5F, Types.bullet_gun, 20);
    	GameRegistry.registerItem(spider36, "Spider 36");
    	GameRegistry.addShapelessRecipe(new ItemStack(spider36, 1), Block.dirt);
    	TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
    	KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
	}
	
	
	
	
	
	
	
	
	
	
	
	@PostInit public void postInit(FMLPostInitializationEvent e){}
	@Init public void init(FMLInitializationEvent e){}
	

}
