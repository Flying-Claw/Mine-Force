package de.flyverse.mineforce.items;


import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.lwjgl.input.Mouse;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;

public abstract class Shooter extends Item {

	private int
	max_ammo,
	ammo,
	reserve_ammo,
	shoots,
	reload_time,
	bullet_type;
	
	private long
	timebetweenshoots,
	lastshoot = 0,
	lastreloadtick;
	private float
	dmg,
	recoil,
	prezision;
	
	private boolean
	canZoom,
	isZoom,
	reloading = false,
	shooted = false;
	
	public Shooter(int id, String name, int max_ammo, int reserve_ammo, int shoots, int reload_time, long timebetweenshoots, float rueckstoss, float prezi, int bt, float damage, boolean canZoom){
		super(id);
		setMaxStackSize(1);
		setUnlocalizedName("Shooter_" + name);
		LanguageRegistry.addName(this, name);
		this.max_ammo = max_ammo;
		this.ammo = max_ammo;
		this.reserve_ammo = reserve_ammo;
		this.shoots = shoots;
		this.reload_time = 2000;//reload_time;
		this.dmg = damage;
		this.canZoom = canZoom;
		bullet_type = bt;
		this.setMaxDamage(shoots);
		prezision = prezi;
		this.timebetweenshoots = timebetweenshoots;
		recoil = rueckstoss;
		lastshoot = 0;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack is, World w, EntityPlayer p) {
		
		if(System.currentTimeMillis() - lastshoot > timebetweenshoots){
			if(reloading){return is;}
		
		
		
			if(p.capabilities.isCreativeMode || consumeAmmo()){
			
				w.playSoundAtEntity(p, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			
				if (!w.isRemote){
				
					FMLClientHandler.instance().getClient().thePlayer.rotationPitch -= recoil;
					is.damageItem(1, p);
				
					shoot();
				}
			}
			lastshoot = System.currentTimeMillis();
		}
		
		return is;
	}
	
	
	public boolean consumeAmmo(){
		if(ammo > 0){
			ammo--;
			return true;
		}else{
			return false;
		}
	}

	
	public void reload(){
		if(reserve_ammo > 0 && ammo < max_ammo && !isReloading()){
			reloading = true;
			lastreloadtick = System.currentTimeMillis();
			onReloadStart();
		}
	}
	public boolean isReloading(){
		return reloading;
	}
	
	
	public abstract void shoot();
	public abstract void onReloadStart();
	public abstract void onReloadEnd();

	@Override
	public void onUpdate(ItemStack is, World w, Entity e, int par4, boolean par5) {
		EntityClientPlayerMP p = FMLClientHandler.instance().getClient().thePlayer;
		if(p.getHeldItem() == null){return;}
		if(p.getHeldItem().itemID == itemID){
			if(reloading){
				if(System.currentTimeMillis() - lastreloadtick >= reload_time){
					onReloadEnd();
					reloading = false;
				}
				return;
			}
			if(!(Mouse.isButtonDown(0) && System.currentTimeMillis() - lastshoot > timebetweenshoots)){
				return;
			}
			
			
			
			if(p.capabilities.isCreativeMode || consumeAmmo()){
				w.playSoundAtEntity(p, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			
				if (!w.isRemote){
					shooted = true;
					FMLClientHandler.instance().getClient().thePlayer.rotationPitch -= recoil;
					is.damageItem(1, p);
					shoot();
				}
			}
			lastshoot = System.currentTimeMillis();
			
		}
	}
	
	public int getAmmo(){
		return ammo;
	}
	public int getReserveAmmo(){
		return reserve_ammo;
	}

	
	public void fillAmmo(){
		int ammo_toget = max_ammo - ammo;
		if(reserve_ammo >= ammo_toget){
			ammo = max_ammo;
			reserve_ammo -= ammo_toget;
		}else if(reserve_ammo > 0){
			reserve_ammo = 0;
			ammo += reserve_ammo;
		}
	}
	
	@Override
	public boolean onEntitySwing(EntityLiving entityLiving, ItemStack stack) {
		return reloading;
	}
	
	

}
