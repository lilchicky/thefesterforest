package com.gmail.thelilchicken01.tff.integration;

import javax.annotation.Nonnull;

import com.gmail.thelilchicken01.tff.util.InventoryHelper;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.ItemStackHandler;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

public class CuriosIntegration {
	
	private static final EmptyCuriosHandler EMPTY_HANDLER = new EmptyCuriosHandler();

	public CuriosIntegration() {
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		bus.addListener(this::setup);
		
	}
		
	public void setup(FMLCommonSetupEvent event) {
			
		InventoryHelper.addBaublesItemHandlerFactory((player, type) -> (CuriosApi.getCuriosHelper().getCuriosHandler(player)
				.map(handler -> handler.getStacksHandler(type.getIdentifier()).map(ICurioStacksHandler::getStacks).orElse(EMPTY_HANDLER)).orElse(EMPTY_HANDLER)));
			
	}
	
	private static class EmptyCuriosHandler extends ItemStackHandler implements IDynamicStackHandler {
		@Override
		public void setPreviousStackInSlot(int i, @Nonnull ItemStack itemStack) {
			//noop
		}

		@Override
		public ItemStack getPreviousStackInSlot(int i) {
			return ItemStack.EMPTY;
		}

		@Override
		public void grow(int i) {
			//noop
		}

		@Override
		public void shrink(int i) {
			//noop
		}
	}
	
}
