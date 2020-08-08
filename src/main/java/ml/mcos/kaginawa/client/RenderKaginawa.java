package ml.mcos.kaginawa.client;

import ml.mcos.kaginawa.entity.EntityKaginawa;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

public class RenderKaginawa extends Render<EntityKaginawa> {
    private static final ResourceLocation RESOURCE = new ResourceLocation("textures/particle/particles.png");

    public RenderKaginawa() {
        super(Minecraft.getMinecraft().getRenderManager());
    }

    public void doRenderKaginawa(EntityKaginawa entity, double x, double y, double z, float entityYaw, float partialTicks) {
        //GL11.glPushMatrix();
        GlStateManager.pushMatrix();
        //GL11.glTranslatef((float) x, (float) y, (float) z);
        GlStateManager.translate((float) x, (float) y, (float) z);
        //GL11.glEnable(32826);
        GlStateManager.enableRescaleNormal();
        //GL11.glScalef(0.5F, 0.5F, 0.5F);
        GlStateManager.scale(0.5F, 0.5F, 0.5F);
        //byte b0 = 1;
        //byte b1 = 2;
        bindEntityTexture(entity); //func_110777_b //Caused by: java.lang.NullPointerException
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        int i = 1;
        int j = 2;
        float f2 = 0.0625F; //(b0 * 8 + 0) / 128.0F;
        float f3 = 0.125F; //(b0 * 8 + 8) / 128.0F;
        float f4 = 0.125F; //(b1 * 8 + 0) / 128.0F;
        float f5 = 0.1875F;// (b1 * 8 + 8) / 128.0F;
        float f6 = 1.0F;
        float f7 = 0.5F;
        float f8 = 0.5F;
        //GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        //GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        //tessellator.func_78382_b(); //startDrawingQuads
        //tessellator.func_78375_b(0.0F, 1.0F, 0.0F); //setNormal
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
        //tessellator.func_78374_a((0.0F - f7), (0.0F - f8), 0.0D, f2, f5); //addVertexWithUV
        bufferbuilder.pos(-0.5D, -0.5D, 0.0D).tex(0.0625D, 0.1875D).normal(0.0F, 1.0F, 0.0F).endVertex();
        //tessellator.func_78374_a((f6 - f7), (0.0F - f8), 0.0D, f3, f5); //addVertexWithUV
        bufferbuilder.pos(0.5D, -0.5D, 0.0D).tex(0.125D, 0.1875D).normal(0.0F, 1.0F, 0.0F).endVertex();
        //tessellator.func_78374_a((f6 - f7), (1.0F - f8), 0.0D, f3, f4); //addVertexWithUV
        bufferbuilder.pos(0.5D, 0.5D, 0.0D).tex(0.125D, 0.125D).normal(0.0F, 1.0F, 0.0F).endVertex();
        //tessellator.func_78374_a((0.0F - f7), (1.0F - f8), 0.0D, f2, f4); //addVertexWithUV
        bufferbuilder.pos(-0.5D, 0.5D, 0.0D).tex(0.0625D, 0.125D).normal(0.0F, 1.0F, 0.0F).endVertex();
        //tessellator.func_78381_a(); //draw
        tessellator.draw();

        //GL11.glDisable(32826);
        GlStateManager.disableRescaleNormal();
        //GL11.glPopMatrix();
        GlStateManager.popMatrix();

        if (entity.getThrower() != null) { //func_85052_h
            EntityLivingBase entityplayer = entity.getThrower();
            float f9 = entity.getThrower().getSwingProgress(partialTicks); //func_85052_h.func_70678_g
            float f10 = MathHelper.sin(MathHelper.sqrt(f9) * 3.1415927F); //func_76126_a func_76129_c
            //Vec3 vec3 = Vec3.func_72443_a(-0.5D, 0.03D, 0.8D); //createVectorHelper
            Vec3d vec3 = new Vec3d(-0.5D, 0.03D, 0.8D);
            //vec3.func_72440_a(-((entity.getThrower()).prevRotationPitch + ((entity.getThrower()).rotationPitch - (entity.getThrower()).prevRotationPitch) * partialTicks) * 3.1415927F / 180.0F); //rotateAroundX
            vec3 = vec3.rotatePitch(-(entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * partialTicks) * 0.017453292F);
            //vec3.func_72442_b(-((entity.getThrower()).prevRotationYaw + ((entity.getThrower()).rotationYaw - (entity.getThrower()).prevRotationYaw) * partialTicks) * 3.1415927F / 180.0F); //rotateAroundY
            vec3 = vec3.rotateYaw(-(entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * partialTicks) * 0.017453292F);
            //vec3.func_72442_b(f10 * 0.5F); //rotateAroundY
            vec3 = vec3.rotateYaw(f10 * 0.5F);
            //vec3.func_72440_a(-f10 * 0.7F); //rotateAroundX
            vec3 = vec3.rotatePitch(-f10 * 0.7F);
            double d3 = (entity.getThrower()).prevPosX + ((entity.getThrower()).posX - (entity.getThrower()).prevPosX) * partialTicks + vec3.x;
            double d4 = (entity.getThrower()).prevPosY + ((entity.getThrower()).posY - (entity.getThrower()).prevPosY) * partialTicks + vec3.y;
            double d5 = (entity.getThrower()).prevPosZ + ((entity.getThrower()).posZ - (entity.getThrower()).prevPosZ) * partialTicks + vec3.z;
            double d6 = (entity.getThrower() != Minecraft.getMinecraft().player) ? entity.getThrower().getEyeHeight() : 0.0D;
            if (this.renderManager.options.thirdPersonView > 0 || entity.getThrower() != (Minecraft.getMinecraft()).player) {
                float f11 = ((entity.getThrower()).prevRenderYawOffset + ((entity.getThrower()).renderYawOffset - (entity.getThrower()).prevRenderYawOffset) * partialTicks) * 3.1415927F / 180.0F;
                double d7 = MathHelper.sin(f11);
                double d8 = MathHelper.cos(f11);
                d3 = entityplayer.prevPosX + (entity.getThrower().posX - entity.getThrower().prevPosX) * partialTicks - d8 * 0.25D - d7 * 0.75D;
                d4 = entityplayer.prevPosY + d6 + (entity.getThrower().posY - entity.getThrower().prevPosY) * partialTicks - 0.45D;
                d5 = entityplayer.prevPosZ + (entity.getThrower().posZ - entity.getThrower().prevPosZ) * partialTicks - d7 * 0.25D + d8 * 0.75D;
            }
            double d9 = entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks;
            double d10 = entity.prevPosY + (entity.posY - entity.prevPosY) * partialTicks + 0.75D;
            double d11 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks;
            double d12 = (float) (d3 - d9);
            double d13 = (float) (d4 - d10);
            double d14 = (float) (d5 - d11);
            //GL11.glDisable(3553);
            GlStateManager.disableTexture2D();
            //GL11.glDisable(2896);
            GlStateManager.disableLighting();//tessellator.func_78371_b(3); //startDrawing
            //tessellator.func_78378_d(0); //setColorOpaque_I
            bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
            int b2 = 16;
            for (int i1 = 0; i1 <= b2; i1++) {
                float f12 = (float) i1 / b2;
                //tessellator.func_78377_a(x + d12 * f12, y + d13 * (f12 * f12 + f12) * 0.5D + 0.25D, z + d14 * f12); //addVertex
                bufferbuilder.pos(x + d12 * f12, y + d13 * (f12 * f12 + f12) * 0.5D + 0.25D, z + d14 * f12).color(0, 0, 0, 255).endVertex();
            }
            tessellator.draw(); //func_78381_a
            //GL11.glEnable(2896);
            GlStateManager.enableLighting();
            //GL11.glEnable(3553);
            GlStateManager.enableTexture2D();
        }
    }

    @Override
    public void doRender(@Nullable EntityKaginawa entity, double x, double y, double z, float entityYaw, float partialTicks) {
        doRenderKaginawa(entity, x, y, z, entityYaw, partialTicks);
    }

    /*public void doRender(EntityKaginawa entity, double x, double y, double z, float entityYaw, float partialTicks) {
        EntityPlayer entityplayer = (EntityPlayer) entity.getThrower();
        if (super.renderManager == null) {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!null");
        }

        if (!this.renderOutlines) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float) x, (float) y, (float) z);
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            this.bindEntityTexture(entity);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            int i = 1;
            int j = 2;
            float f = 0.0625F;
            float f1 = 0.125F;
            float f2 = 0.125F;
            float f3 = 0.1875F;
            float f4 = 1.0F;
            float f5 = 0.5F;
            float f6 = 0.5F;
            GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);

            if (this.renderOutlines) {
                GlStateManager.enableColorMaterial();
                GlStateManager.enableOutlineMode(this.getTeamColor(entity));
            }

            bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
            bufferbuilder.pos(-0.5D, -0.5D, 0.0D).tex(0.0625D, 0.1875D).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, -0.5D, 0.0D).tex(0.125D, 0.1875D).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(0.5D, 0.5D, 0.0D).tex(0.125D, 0.125D).normal(0.0F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(-0.5D, 0.5D, 0.0D).tex(0.0625D, 0.125D).normal(0.0F, 1.0F, 0.0F).endVertex();
            tessellator.draw();

            if (this.renderOutlines) {
                GlStateManager.disableOutlineMode();
                GlStateManager.disableColorMaterial();
            }

            GlStateManager.disableRescaleNormal();
            GlStateManager.popMatrix();
            int k = entityplayer.getPrimaryHand() == EnumHandSide.RIGHT ? 1 : -1;
            ItemStack itemstack = entityplayer.getHeldItemMainhand();

            if (!(itemstack.getItem() instanceof net.minecraft.item.ItemFishingRod)) {
                k = -k;
            }

            float f7 = entityplayer.getSwingProgress(partialTicks);
            float f8 = MathHelper.sin(MathHelper.sqrt(f7) * (float) Math.PI);
            float f9 = (entityplayer.prevRenderYawOffset + (entityplayer.renderYawOffset - entityplayer.prevRenderYawOffset) * partialTicks) * 0.017453292F;
            double d0 = (double) MathHelper.sin(f9);
            double d1 = (double) MathHelper.cos(f9);
            double d2 = (double) k * 0.35D;
            double d3 = 0.8D;
            double d4;
            double d5;
            double d6;
            double d7;

            if ((this.renderManager.options == null || this.renderManager.options.thirdPersonView <= 0) && entityplayer == Minecraft.getMinecraft().player) {
                float f10 = this.renderManager.options.fovSetting;
                f10 = f10 / 100.0F;
                Vec3d vec3d = new Vec3d((double) k * -0.36D * (double) f10, -0.045D * (double) f10, 0.4D);
                vec3d = vec3d.rotatePitch(-(entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * partialTicks) * 0.017453292F);
                vec3d = vec3d.rotateYaw(-(entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * partialTicks) * 0.017453292F);
                vec3d = vec3d.rotateYaw(f8 * 0.5F);
                vec3d = vec3d.rotatePitch(-f8 * 0.7F);
                d4 = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double) partialTicks + vec3d.x;
                d5 = entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double) partialTicks + vec3d.y;
                d6 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double) partialTicks + vec3d.z;
                d7 = (double) entityplayer.getEyeHeight();
            } else {
                d4 = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double) partialTicks - d1 * d2 - d0 * 0.8D;
                d5 = entityplayer.prevPosY + (double) entityplayer.getEyeHeight() + (entityplayer.posY - entityplayer.prevPosY) * (double) partialTicks - 0.45D;
                d6 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double) partialTicks - d0 * d2 + d1 * 0.8D;
                d7 = entityplayer.isSneaking() ? -0.1875D : 0.0D;
            }

            double d13 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double) partialTicks;
            double d8 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double) partialTicks + 0.25D;
            double d9 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double) partialTicks;
            double d10 = (double) ((float) (d4 - d13));
            double d11 = (double) ((float) (d5 - d8)) + d7;
            double d12 = (double) ((float) (d6 - d9));
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
            int l = 16;

            for (int i1 = 0; i1 <= 16; ++i1) {
                float f11 = (float) i1 / 16.0F;
                bufferbuilder.pos(x + d10 * (double) f11, y + d11 * (double) (f11 * f11 + f11) * 0.5D + 0.25D, z + d12 * (double) f11).color(0, 0, 0, 255).endVertex();
            }

            tessellator.draw();
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
        }
    }*/

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityKaginawa entity) {
        return RESOURCE;
    }
}
