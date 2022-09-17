package com.epherical.bozo;

import com.epherical.octoecon.api.event.EconomyEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class NumismaticCurrency implements ModInitializer {

    public static final ResourceLocation COINS = new ResourceLocation("numismatic-overhaul", "currency");
    public static com.epherical.bozo.Coins COINS_CURRENCY;

    @Override
    public void onInitialize() {
        com.epherical.bozo.NumismaticBalanceProvider provider = new com.epherical.bozo.NumismaticBalanceProvider();
        COINS_CURRENCY = new com.epherical.bozo.Coins(provider);
        ServerLifecycleEvents.SERVER_STARTING.register(provider::setServer);

        EconomyEvents.CURRENCY_ADD_EVENT.register(() -> {
            return List.of(COINS_CURRENCY);
        });
    }
}
