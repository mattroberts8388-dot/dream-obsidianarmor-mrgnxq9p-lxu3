package com.obsidianarmor;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ObsidianArmorMod implements ModInitializer {
    public static final String MOD_ID = "obsidianarmor";

    public static final Item OBSIDIAN_INGOT = new Item(new Item.Settings());

    public static final ArmorItem OBSIDIAN_HELMET =
            new ArmorItem(ObsidianArmorMaterial.INSTANCE, ArmorItem.Type.HELMET, new Item.Settings());
    public static final ArmorItem OBSIDIAN_CHESTPLATE =
            new ArmorItem(ObsidianArmorMaterial.INSTANCE, ArmorItem.Type.CHESTPLATE, new Item.Settings());
    public static final ArmorItem OBSIDIAN_LEGGINGS =
            new ArmorItem(ObsidianArmorMaterial.INSTANCE, ArmorItem.Type.LEGGINGS, new Item.Settings());
    public static final ArmorItem OBSIDIAN_BOOTS =
            new ArmorItem(ObsidianArmorMaterial.INSTANCE, ArmorItem.Type.BOOTS, new Item.Settings());

    public static final ItemGroup OBSIDIAN_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(OBSIDIAN_CHESTPLATE))
            .displayName(Text.translatable("itemGroup.obsidianarmor.group"))
            .entries((displayContext, entries) -> {
                entries.add(OBSIDIAN_INGOT);
                entries.add(OBSIDIAN_HELMET);
                entries.add(OBSIDIAN_CHESTPLATE);
                entries.add(OBSIDIAN_LEGGINGS);
                entries.add(OBSIDIAN_BOOTS);
            })
            .build();

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "obsidian_ingot"), OBSIDIAN_INGOT);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "obsidian_helmet"), OBSIDIAN_HELMET);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "obsidian_chestplate"), OBSIDIAN_CHESTPLATE);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "obsidian_leggings"), OBSIDIAN_LEGGINGS);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "obsidian_boots"), OBSIDIAN_BOOTS);

        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "group"), OBSIDIAN_GROUP);

        // Apply fire resistance and slowness while wearing the full set.
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (isWearingFullSet(player)) {
                    player.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.FIRE_RESISTANCE, 60, 0, true, false, true));
                    player.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.SLOWNESS, 60, 0, true, false, true));
                } else {
                    // Partial pieces still give brief fire resistance for each piece worn.
                    int pieces = countPieces(player);
                    if (pieces > 0) {
                        player.addStatusEffect(new StatusEffectInstance(
                                StatusEffects.FIRE_RESISTANCE, 40, 0, true, false, true));
                    }
                }
            }
        });

        // Silence unused import warning noise while keeping API reference valid.
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killed) -> {});
    }

    private static int countPieces(ServerPlayerEntity player) {
        int count = 0;
        if (player.getEquippedStack(EquipmentSlot.HEAD).getItem() == OBSIDIAN_HELMET) count++;
        if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == OBSIDIAN_CHESTPLATE) count++;
        if (player.getEquippedStack(EquipmentSlot.LEGS).getItem() == OBSIDIAN_LEGGINGS) count++;
        if (player.getEquippedStack(EquipmentSlot.FEET).getItem() == OBSIDIAN_BOOTS) count++;
        return count;
    }

    private static boolean isWearingFullSet(ServerPlayerEntity player) {
        return player.getEquippedStack(EquipmentSlot.HEAD).getItem() == OBSIDIAN_HELMET
                && player.getEquippedStack(EquipmentSlot.CHEST).getItem() == OBSIDIAN_CHESTPLATE
                && player.getEquippedStack(EquipmentSlot.LEGS).getItem() == OBSIDIAN_LEGGINGS
                && player.getEquippedStack(EquipmentSlot.FEET).getItem() == OBSIDIAN_BOOTS;
    }
}