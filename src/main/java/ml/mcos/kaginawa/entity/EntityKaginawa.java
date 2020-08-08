package ml.mcos.kaginawa.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityKaginawa extends EntityThrowable {
    public int timeLimit;
    private double vMotionX;
    private double vMotionY;
    private double vMotionZ;

    public EntityKaginawa(World worldIn) {
        super(worldIn);
        outOfWorld(); //func_70076_C/kill
    }

    public EntityKaginawa(World worldIn, EntityLivingBase throwerIn) {
        super(worldIn, throwerIn);
        this.timeLimit = 0;
    }

    @Override
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) { //func_70186_c/setThrowableHeading
        float f2 = MathHelper.sqrt(x * x + y * y + z * z);
        x /= f2;
        y /= f2;
        z /= f2;
        velocity *= 2.0F;
        x += 0.0037499999161809683D * inaccuracy;
        y += 0.0037499999161809683D * inaccuracy;
        z += 0.0037499999161809683D * inaccuracy;
        x *= velocity;
        y *= velocity;
        z *= velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f3 = MathHelper.sqrt(x * x + z * z);
        this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(x, z) * (180.0D / Math.PI));
        this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(y, f3) * (180.0D / Math.PI));
    }

    @Override
    public void onUpdate() { //func_70071_h_
        super.onUpdate();
        if (this.timeLimit++ > 15) {
            setDead(); //func_70106_y
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) { //func_70184_a
        this.vMotionX = (this.posX - getThrower().posX) / 7.0D;
        if (this.posY - getThrower().posY > 0.0D) {
            this.vMotionY = (this.posY - (getThrower()).posY) / (10.0D - Math.pow(this.posY - (getThrower()).posY - 30.0D, 2.0D) / 95.0D);
        } else {
            this.vMotionY = 0.4D;
        }
        this.vMotionZ = (this.posZ - (getThrower()).posZ) / 7.0D;
        if (this.world.isRemote) {
            getThrower().motionX += this.vMotionX;
            getThrower().motionY = this.vMotionY;
            getThrower().motionZ += this.vMotionZ;
        }
        getThrower().fallDistance = 0.0F;
        setDead(); //func_70106_y
    }

    @Override
    public void setDead() { //func_70106_y
        this.isDead = true;
    }
}
