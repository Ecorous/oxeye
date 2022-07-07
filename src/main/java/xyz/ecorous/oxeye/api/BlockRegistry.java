package xyz.ecorous.oxeye.api;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {
	public static void block(Block block, String namespace, String id) {
		Registry.register(Registry.BLOCK, new Identifier(namespace, id), block);
	}
}
