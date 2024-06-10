package com.seacroak.plushables.util;

import com.seacroak.plushables.PlushablesMod;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class GenericUtils {
    @NotNull
    public static Identifier ID(@NotNull String path) {
        return Identifier.of(PlushablesMod.MOD_ID, path);
    }


}
