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
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0, 0.25, 0.75, 0.5, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.25, 0.5, 0.25, 0.375, 0.75, 0.375));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.625, 0.5, 0.25, 0.75, 0.75, 0.375));

    return shape;
  }
}
