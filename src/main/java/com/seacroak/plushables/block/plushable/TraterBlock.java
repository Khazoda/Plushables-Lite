package com.seacroak.plushables.block.plushable;

import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.block.BasePlushable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

import java.util.List;

public class TraterBlock extends BasePlushable {
  public TraterBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0, 0.4375, 0.79375, 0.3125, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.2078125, 0.3125, 0.26875, 0.7953125, 1.0625, 0.803125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.21875, 0.703125, 0.28125, 0.78125, 1.328125, 0.78125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.2046875, 0, 0.4375, 0.4375, 0.3125, 0.6875));

    return shape;
  }

  @Override
  public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
    tooltip.add(Text.translatable("block." + PlushablesMod.MOD_ID + ".trater.tooltip"));
    super.appendTooltip(stack, context, tooltip, options);
  }
}