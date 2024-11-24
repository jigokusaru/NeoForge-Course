package net.jigokusaru.mccourse.item;

import net.jigokusaru.mccourse.MCCourseMod;
import net.jigokusaru.mccourse.item.custom.ChainsawItem;
import net.jigokusaru.mccourse.item.custom.FuelItem;
import net.jigokusaru.mccourse.item.custom.HammerItem;
import net.jigokusaru.mccourse.item.custom.PaxelItem;
import net.jigokusaru.mccourse.util.ModTags;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MCCourseMod.MOD_ID);

    public static final DeferredItem<Item> BLACK_OPAL = ITEMS.registerSimpleItem("black_opal");
    public static final DeferredItem<Item> TOMATO = ITEMS.registerItem("tomato",
            Item::new, new Item.Properties().food(ModFoodProperties.TOMATO));
    public static final DeferredItem<Item> FROSTFIRE_ICE =
            ITEMS.registerItem("frostfire_ice",properties -> new FuelItem(properties, 800),new Item.Properties());
    public static final DeferredItem<Item> RAW_BLACK_OPAL = ITEMS.registerItem("raw_black_opal",
            Item::new, new Item.Properties());
    public static final DeferredItem<Item> CHAINSAW =
            ITEMS.registerItem("chainsaw", ChainsawItem::new, new Item.Properties().durability(32));

    public static final DeferredItem<Item> BLACK_OPAL_SWORD = ITEMS.register("black_opal_sword",
            () -> new SwordItem(ModToolTiers.BLACK_OPAL,
                    new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.BLACK_OPAL,3,-2.4f))));
    public static final DeferredItem<Item> BLACK_OPAL_PICKAXE = ITEMS.register("black_opal_pickaxe",
            () -> new PickaxeItem(ModToolTiers.BLACK_OPAL,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.BLACK_OPAL,1,-2.8f))));
    public static final DeferredItem<Item> BLACK_OPAL_AXE = ITEMS.register("black_opal_axe",
            () -> new AxeItem(ModToolTiers.BLACK_OPAL,
                    new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.BLACK_OPAL,1.5f,-3.0f))));
    public static final DeferredItem<Item> BLACK_OPAL_SHOVEL = ITEMS.register("black_opal_shovel",
            () -> new ShovelItem(ModToolTiers.BLACK_OPAL,
                    new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.BLACK_OPAL,6,-3.2f))));
    public static final DeferredItem<Item> BLACK_OPAL_HOE = ITEMS.register("black_opal_hoe",
            () -> new HoeItem(ModToolTiers.BLACK_OPAL,
                    new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.BLACK_OPAL,0,-3f))));
    public static final DeferredItem<Item> BLACK_OPAL_PAXEL = ITEMS.register("black_opal_paxel",
            () -> new PaxelItem(ModToolTiers.BLACK_OPAL,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.BLACK_OPAL,1,-2.8f))));
    public static final DeferredItem<Item> BLACK_OPAL_HAMMER = ITEMS.register("black_opal_hammer",
            () -> new HammerItem(ModToolTiers.BLACK_OPAL,
                    new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.BLACK_OPAL,8,-3.3f))));



    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
