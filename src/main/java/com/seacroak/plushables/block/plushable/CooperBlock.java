package com.seacroak.plushables.block.plushable;
import com.seacroak.plushables.block.BasePlushable;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class CooperBlock extends BasePlushable {
  public CooperBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.296875, 0, 0.125, 0.6875, 0.625, 0.875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.4375, 0.3640625, 0.05, 0.55, 0.4765625, 0.125));

    return shape;
  }
}
