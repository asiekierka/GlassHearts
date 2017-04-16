package com.elytradev.glasshearts;

import net.minecraft.util.math.BlockPos;

public interface IGlassHeart {
	int getLifeforce();
	int getLifeforceBuffer();
	EnumGem getGem();
	EnumGlassColor getColor();
	BlockPos getPos();
	
	void setLifeforce(int lifeforce);
	void setLifeforceBuffer(int lifeforceBuffer);
	void setGem(EnumGem gem);
	void setColor(EnumGlassColor color);
}