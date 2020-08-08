package ml.mcos.kaginawa;

import ml.mcos.kaginawa.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Kaginawa.MODID, name = Kaginawa.NAME, version = Kaginawa.VERSION)
public class Kaginawa {
    public static final String MODID = "kaginawa";
    public static final String NAME = "Kaginawa Mod";
    public static final String VERSION = "1.0";
    public static final String resourceDomain = "kaginawa";

    @SidedProxy(clientSide = "ml.mcos.kaginawa.client.ClientProxy", serverSide = "ml.mcos.kaginawa.common.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("kaginawa")
    public static Kaginawa instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit();
    }
}
