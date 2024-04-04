package com.seacroak.plushables.block.plushie;
import com.seacroak.plushables.block.BasePlushable;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class SeaBunnyBlock extends BasePlushable {
  public SeaBunnyBlock() {
    super();
  }
  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0, 0.375, 0.625, 0.25, 0.625));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.25, 0.375, 0.4375, 0.375, 0.4375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5625, 0.25, 0.375, 0.625, 0.375, 0.4375));

    return shape;
  }
}
