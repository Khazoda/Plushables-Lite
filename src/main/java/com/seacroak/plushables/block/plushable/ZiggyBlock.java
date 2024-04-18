package com.seacroak.plushables.block.plushable;

import com.seacroak.plushables.block.BasePlushable;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class ZiggyBlock extends BasePlushable {
  public ZiggyBlock() {
    super();
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.5, 0.75, 0.25, 0.8125));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0.0625, 0.8125, 0.5625, 0.125, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.8125, 0.6875, 0.0625, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.4375, 0.5, 0.0625, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0.0625, 0.4375, 0.375, 0.125, 0.5));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0, 0.8125, 0.375, 0.0625, 0.875));

  return shape;
  }

}