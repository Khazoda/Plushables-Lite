package com.seacroak.plushables.block;

import com.seacroak.plushables.registry.assets.SoundRegistry;
import com.seacroak.plushables.registry.uncommon.TileRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import org.jetbrains.annotations.Nullable;

public class RupertBlock extends BaseInteractablePlushable {

  public RupertBlock() {
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.406, 0.109, 0.25, 0.531, 0.296, 0.75));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.390, 0.265, 0.171, 0.546, 0.390, 0.328));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0.015, 0.672, 0.5625, 0.203, 0.734));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.5, 0, 0.265, 0.5625, 0.1875, 0.328));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.390, 0, 0.265, 0.453, 0.1875, 0.328));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.375, 0.015, 0.671, 0.4375, 0.203, 0.734));
    return shape;
  }
}

