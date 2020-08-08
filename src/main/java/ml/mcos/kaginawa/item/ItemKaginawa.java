package ml.mcos.kaginawa.item;

import ml.mcos.kaginawa.Kaginawa;
import ml.mcos.kaginawa.entity.EntityKaginawa;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemKaginawa extends Item {
    public ItemKaginawa() {
        setMaxDamage(0); //func_77656_e/setMaxDurability
        setMaxStackSize(1); //func_77625_d
        setCreativeTab(CreativeTabs.TOOLS);
        setUnlocalizedName("kaginawa");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) { //func_77659_a
        /*EntityKaginawa et = new EntityKaginawa(worldIn, playerIn);
        if (!worldIn.isRemote) {
            et.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            System.out.println("11111111111111111111111");
            worldIn.spawnEntity(et);
        } else {
            et.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.joinEntityInSurroundings(et);
            System.out.println("2222222222222222222222");
        }*/


        EntityKaginawa et = new EntityKaginawa(worldIn, playerIn);
        if (Kaginawa.proxy.kagimap.containsKey(playerIn)) {
            if (Kaginawa.proxy.kagimap.get(playerIn).isDead) {
                Kaginawa.proxy.kagimap.put(playerIn, et);
                et.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                if (!worldIn.isRemote) {
                    worldIn.spawnEntity(et);
                } else {
                    worldIn.joinEntityInSurroundings(et);
                }
            }
        } else {
            Kaginawa.proxy.kagimap.put(playerIn, et);
            et.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            if (!worldIn.isRemote) {
                worldIn.spawnEntity(et);
            } else {
                worldIn.joinEntityInSurroundings(et);
            }
        }
        //return super.onItemRightClick(worldIn, playerIn, handIn);
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
