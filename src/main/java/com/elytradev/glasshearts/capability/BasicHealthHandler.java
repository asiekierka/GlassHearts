package com.elytradev.glasshearts.capability;

import java.util.Iterator;

import com.elytradev.glasshearts.logic.HeartContainer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;

public class BasicHealthHandler implements IHealthHandler {
	
	protected final NonNullList<HeartContainer> list = NonNullList.create();

	@Override
	public void damage(float amount, DamageSource src) {
		for (int i = getContainers()-1; i >= 0; i--) {
			if (amount <= 0) break;
			HeartContainer hc = getContainer(i);
			float consumed = Math.min(amount, hc.getFillAmount());
			if (consumed > 0) {
				hc = hc.copy();
				hc.damage(consumed, src);
				setContainer(i, hc);
				amount -= consumed;
			}
		}
	}
	
	@Override
	public void heal(float amount) {
		for (int i = 0; i < getContainers(); i++) {
			if (amount <= 0) break;
			HeartContainer hc = getContainer(i);
			if (hc.canHeal()) {
				float consumed = Math.min(amount, 1-hc.getFillAmount());
				if (consumed > 0) {
					hc = hc.copy();
					hc.heal(consumed);
					setContainer(i, hc);
					amount -= consumed;
				}
			}
		}
	}
	
	protected NonNullList<HeartContainer> getList() {
		return list;
	}
	
	
	@Override
	public int getContainers() {
		return getList().size();
	}

	@Override
	public HeartContainer getContainer(int index) {
		return getList().get(index);
	}
	
	@Override
	public HeartContainer copyContainer(int index) {
		return getContainer(index).copy();
	}

	@Override
	public HeartContainer popContainer(int index) {
		return getList().remove(index);
	}

	@Override
	public void setContainer(int index, HeartContainer container) {
		getList().set(index, container);
	}

	@Override
	public void removeContainer(int index) {
		getList().remove(index);
	}

	@Override
	public void addContainer(HeartContainer container) {
		getList().add(container);
	}

	@Override
	public void addContainer(int index, HeartContainer container) {
		getList().add(index, container);
	}
	
	@Override
	public void clear() {
		getList().clear();
	}
	
	@Override
	public Iterator<HeartContainer> iterator() {
		return getList().iterator();
	}

}
