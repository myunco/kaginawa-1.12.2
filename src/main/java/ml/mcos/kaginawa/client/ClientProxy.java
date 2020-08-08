package ml.mcos.kaginawa.client;

import ml.mcos.kaginawa.common.CommonProxy;
import ml.mcos.kaginawa.entity.EntityKaginawa;
import ml.mcos.kaginawa.item.ItemLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ItemLoader.registerRenders();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void init() {
        super.init();
        RenderingRegistry.registerEntityRenderingHandler(EntityKaginawa.class, new RenderKaginawa());
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
