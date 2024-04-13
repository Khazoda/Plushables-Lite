package com.seacroak.plushables.block.plushable;

import com.seacroak.plushables.block.BasePlushable;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class WispBlock extends BasePlushable {
  public WispBlock() {
    super(FabricBlockSettings.create().sounds(BlockSoundGroup.WOOL).strength(0.7f).nonOpaque().luminance(14).pistonBehavior(PistonBehavior.DESTROY));
  }

  @Override
  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.3125, 0, 0.3125, 0.6875, 0.375, 0.6875));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.125, 0.1875, 0.75, 0.25, 0.3125, 0.875));

    return shape;
  }
}