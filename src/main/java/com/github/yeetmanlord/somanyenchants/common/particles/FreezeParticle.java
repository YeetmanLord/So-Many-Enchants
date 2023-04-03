package com.github.yeetmanlord.somanyenchants.common.particles;


import javax.annotation.Nullable;

import net.minecraft.client.particle.IAnimatedSprite;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteTexturedParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FreezeParticle extends SpriteTexturedParticle {
   private FreezeParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
      super(world, x, y, z, 0.0D, 0.0D, 0.0D);
      this.xd *= (double)0.1F;
      this.yd *= (double)0.1F;
      this.zd *= (double)0.1F;
      this.xd += motionX * 0.4D;
      this.yd += motionY * 0.4D;
      this.zd += motionZ * 0.4D;
      float f = (float)(Math.random() * (double)0.3F + (double)0.6F);
      this.rCol = f;
      this.gCol = f;
      this.bCol = f;
      this.quadSize *= 0.75F;
      this.lifetime = Math.max((int)(6.0D / (Math.random() * 0.8D + 0.6D)), 1);
      this.hasPhysics = false;
      this.tick();
   }

   @Override
public float getQuadSize(float scaleFactor) {
      return this.quadSize * MathHelper.clamp(((float)this.age + scaleFactor) / (float)this.lifetime * 32.0F, 0.0F, 1.0F);
   }

   @Override
public void tick() {
      this.xo = this.x;
      this.yo = this.y;
      this.zo = this.z;
      if (this.age++ >= this.lifetime) {
         this.remove();
      } else {
         this.move(this.xd, this.yd, this.zd);
         this.gCol = (float)((double)this.gCol * 0.96D);
         this.bCol = (float)((double)this.bCol * 0.9D);
         this.xd *= (double)0.7F;
         this.yd *= (double)0.7F;
         this.zd *= (double)0.7F;
         this.yd -= (double)0.02F;
         if (this.onGround) {
            this.xd *= (double)0.7F;
            this.zd *= (double)0.7F;
         }

      }
   }

   @Override
public IParticleRenderType getRenderType() {
      return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
   }

   @OnlyIn(Dist.CLIENT)
   public static class DamageIndicatorFactory implements IParticleFactory<BasicParticleType> {
      private final IAnimatedSprite spriteSet;

      public DamageIndicatorFactory(IAnimatedSprite spriteSet) {
         this.spriteSet = spriteSet;
      }
      
      @Nullable
      @Override
      public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         FreezeParticle freezeparticle = new FreezeParticle(worldIn, x, y, z, xSpeed, ySpeed + 1.0D, zSpeed);
         freezeparticle.setLifetime(20);
         freezeparticle.pickSprite(this.spriteSet);
         return freezeparticle;
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static class Factory implements IParticleFactory<BasicParticleType> {
      private final IAnimatedSprite spriteSet;

      public Factory(IAnimatedSprite spriteSet) {
         this.spriteSet = spriteSet;
      }
      
      @Nullable
      @Override
      public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         FreezeParticle freezeparticle = new FreezeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
         freezeparticle.pickSprite(this.spriteSet);
         return freezeparticle;
      }
   }

   @OnlyIn(Dist.CLIENT)
   public static class MagicFactory implements IParticleFactory<BasicParticleType> {
      private final IAnimatedSprite spriteSet;

      public MagicFactory(IAnimatedSprite spriteSet) {
         this.spriteSet = spriteSet;
      }
      
      @Nullable
      @Override
      public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
         FreezeParticle freezeparticle = new FreezeParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
         freezeparticle.rCol *= 0.3F;
         freezeparticle.gCol *= 0.8F;
         freezeparticle.pickSprite(this.spriteSet);
         return freezeparticle;
      }
   }
}