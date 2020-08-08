package ml.mcos.kaginawa.common;

import ml.mcos.kaginawa.Kaginawa;
import ml.mcos.kaginawa.entity.EntityKaginawa;
import ml.mcos.kaginawa.item.ItemLoader;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;

import java.util.WeakHashMap;

public class CommonProxy {
    public WeakHashMap<Entity, EntityKaginawa> kagimap = new WeakHashMap<>();

    public void preInit(FMLPreInitializationEvent event) {
        new ItemLoader(event);
        registerEntity(EntityKaginawa.class, "Kaginawa", 11, 80, 3, true);
    }

    public void init() {

    }

    private void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
        //EntityRegistry.registerModEntity(new ResourceLocation("kaginawa","kaginawa"),entityClass, entityName, id, (Object)Kaginawa.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
        EntityRegistry.registerModEntity(new ResourceLocation(Kaginawa.resourceDomain, "kaginawa"), entityClass, entityName, id, Kaginawa.instance, trackingRange, updateFrequency, sendsVelocityUpdates);
    }

    public void postInit() {

    }
}
