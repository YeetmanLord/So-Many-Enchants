package net.minecraft.enchantment;

import com.yeetmanlord.somanyenchants.core.config.Config;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

public class ProtectionEnchantment extends Enchantment 
{
	public final ProtectionEnchantment.Type protectionType;

	   public ProtectionEnchantment(Enchantment.Rarity rarityIn, ProtectionEnchantment.Type protectionTypeIn, EquipmentSlotType... slots) {
	      super(rarityIn, protectionTypeIn == ProtectionEnchantment.Type.FALL ? EnchantmentType.ARMOR_FEET : EnchantmentType.ARMOR, slots);
	      this.protectionType = protectionTypeIn;
	   }

	   /**
	    * Returns the minimal value of enchantability needed on the enchantment level passed.
	    */
	   @Override
	   public int getMinEnchantability(int enchantmentLevel) {
	      return (this.protectionType.getMinimalEnchantability() + (enchantmentLevel - 1) * (this.protectionType.getEnchantIncreasePerLevel()));
	   }
	   @Override
	   public int getMaxEnchantability(int enchantmentLevel) {
	      return this.getMinEnchantability(enchantmentLevel) + this.protectionType.getEnchantIncreasePerLevel();
	   }

	   /**
	    * Returns the maximum level that the enchantment can have.
	    */
	   @Override
	   public int getMaxLevel() {
		   if(Config.pr.isEnabled.get() == false)
			 {
				 return 4;
			 }
			 else return Config.pr.maxLevel.get();
	   }

	   /**
	    * Calculates the damage protection of the enchantment based on level and damage source passed.
	    */
	   @Override
	   public int calcModifierDamage(int level, DamageSource source) {
	      if (source.canHarmInCreative()) {
	         return 0;
	      } else if (this.protectionType == ProtectionEnchantment.Type.ALL) 
	      {
	         if(level <= 4)
	         {
	        	 return level;
	         } else if(level <= 6) 
	         {
	        	 return (int) ((level - 4) * 0.5) + 4;
	         } else if(level <= 10)
	         {
	        	 return (int) ((int) ((level - 6) * 0.1875) + 5);
	         } else
	         {
	        	 return 6;
	         }
	      } else if (this.protectionType == ProtectionEnchantment.Type.FIRE && source.isFireDamage())
	      {
	    	  	if(level <= 4)
		         {
		        	 return (int) (level * 1.5);
		         } else if(level <= 8) 
		         {
		        	 return (int) ((level - 4) * .25) + 6;
		         } else if(level <= 10)
		         {
		        	 return (int) ((10 - 8) * .5) + 7;
		         } else
		         {
		        	 return 10;
		         }
	      } else if (this.protectionType == ProtectionEnchantment.Type.FALL && source == DamageSource.FALL)
	      {
	         return level * 2;
	      } else if (this.protectionType == ProtectionEnchantment.Type.EXPLOSION && source.isExplosion())
	      {
	    	  	if(level <= 4)
		        {
		        	 return (int) (level * 1.5);
		        } else if(level <= 8) 
		        {
		        	 return (int) ((level - 4) * .25) + 6;
		        } else if(level <= 10)
		        {
		        	 return (int) ((10 - 8) * .5) + 7;
		        } else
		        {
		        	 return 10;
		        }
	      } else if(this.protectionType == ProtectionEnchantment.Type.PROJECTILE && source.isProjectile())
	      {
	    	  	if(level <= 4)
		        {
		        	 return (int) (level * 1.5);
		        } else if(level <= 8) 
		        {
		        	 return (int) ((level - 4) * .25) + 6;
		        } else if(level <= 10)
		        {
		        	 return (int) ((10 - 8) * .5) + 7;
		        } else
		        {
		        	 return 10;
		        }
	      } else
	      {
	    	  return 0;
	      }
	   }

	   /**
	    * Determines if the enchantment passed can be applyied together with this enchantment.
	    */
	   @Override
	   public boolean canApplyTogether(Enchantment ench) {
	      if (ench instanceof ProtectionEnchantment) {
	         ProtectionEnchantment protectionenchantment = (ProtectionEnchantment)ench;
	         if (this.protectionType == protectionenchantment.protectionType) {
	            return false;
	         } else {
	            return this.protectionType == ProtectionEnchantment.Type.FALL || protectionenchantment.protectionType == ProtectionEnchantment.Type.FALL;
	         }
	      } else {
	         return super.canApplyTogether(ench);
	      }
	   }

	   /**
	    * Gets the amount of ticks an entity should be set fire, adjusted for fire protection.
	    */
	   public static int getFireTimeForEntity(LivingEntity livingEntity, int level) {
	      int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FIRE_PROTECTION, livingEntity);
	      if (i > 0) {
	         level -= MathHelper.floor((float)level * (float)i * 0.15F);
	      }

	      return level;
	   }

	   public static double getBlastDamageReduction(LivingEntity entityLivingBaseIn, double damage) {
	      int i = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.BLAST_PROTECTION, entityLivingBaseIn);
	      if (i > 0) {
	         damage -= (double)MathHelper.floor(damage * (double)((float)i * 0.15F));
	      }

	      return damage;
	   }

	   public static enum Type {
	      ALL("all", 1, 6),
	      FIRE("fire", 10, 5),
	      FALL("fall", 5, 6),
	      EXPLOSION("explosion", 5, 4),
	      PROJECTILE("projectile", 3, 5);

	      @SuppressWarnings("unused")
		private final String typeName;
	      private final int minEnchantability;
	      private final int levelCost;

	      private Type(String typeName, int minEnchantability, int levelCost) {
	         this.typeName = typeName;
	         this.minEnchantability = minEnchantability;
	         this.levelCost = levelCost;
	      }

	      public int getMinimalEnchantability() {
	         return this.minEnchantability;
	      }

	      public int getEnchantIncreasePerLevel() {
	         return this.levelCost;
	      }
	   }

}

