package com.gmail.thelilchicken01.tff.integration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import com.gmail.thelilchicken01.tff.TheFesterForest;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheFesterForest.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCompat {

	private ModCompat() {}
	
	private static final Map<String, Supplier<Runnable>> compatFactories = new HashMap<>();
	static {
		
		compatFactories.put("curios", () -> CuriosIntegration::new);
		
	}
	
	public static void initCompat() {
		
		for (Map.Entry<String, Supplier<Runnable>> entry : compatFactories.entrySet()) {
			
			if (ModList.get().isLoaded(entry.getKey())) {
				
				try {
					
					entry.getValue().get().run();
					
				}
				catch (Exception e) {
					
					System.out.println("Issue with Curios compatability!");
					
				}
				
			}
			
		}
		
	}
	
	
}
