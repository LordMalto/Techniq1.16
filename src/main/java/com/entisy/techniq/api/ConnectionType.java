package com.entisy.techniq.api;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum ConnectionType implements IStringSerializable {
	
	NONE,
    IN,
    OUT,
    BOTH;

    @Override
    public String getSerializedName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public boolean canReceive() {
        return this == IN || this == BOTH;
    }

    public boolean canExtract() {
        return this == OUT || this == BOTH;
    }
}
