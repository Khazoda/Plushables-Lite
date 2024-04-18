package com.seacroak.plushables.block.plushable;
import com.seacroak.plushables.block.BasePlushable;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class PotsyBlock extends BasePlushable {
  public PotsyBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0, 0.125, 0.875, 0.6875, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.6875, 0.1875, 0.8125, 0.75, 0.8125));

    return shape;
  }
}
