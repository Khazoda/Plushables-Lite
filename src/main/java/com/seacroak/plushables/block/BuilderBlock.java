package com.seacroak.plushables.block;

import com.mojang.serialization.MapCodec;
import com.seacroak.plushables.PlushablesMod;
import com.seacroak.plushables.util.VoxelShapeUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BuilderBlock extends HorizontalFacingBlock {

  public BuilderBlock() {

    super(FabricBlockSettings.create().strength(2.5f).sounds(BlockSoundGroup.COPPER).requiresTool());
    setDefaultState(this.stateManager.getDefaultState());
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos,
                            PlayerEntity player, Hand hand, BlockHitResult hit) {
    if (world.isClient) {
      player.sendMessage(Text.translatable("block." + PlushablesMod.MOD_ID + ".builder.broken"));
    }

    return ActionResult.SUCCESS;
  }

  @Override
  public BlockRenderType getRenderType(BlockState state) {
    return BlockRenderType.MODEL;
  }

//  @Nullable
//  @Override
//  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//    return TileRegistry.BUILDER_TILE.instantiate(pos, state);
//  }
//
//  @Nullable
//  @Override
//  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
//                                                                BlockEntityType<T> type) {
//    return validateTicker(type, TileRegistry.BUILDER_TILE, BuilderTileEntity::tick);
//  }

  @Override
  public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
    super.onBreak(world, pos, state, player);
    return state;
  }

//  @Override
//  public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
//    if (state.isOf(newState.getBlock())) {
//      return;
//    }
//    BlockEntity blockEntity = world.getBlockEntity(pos);
//    if (blockEntity instanceof Inventory) {
//      ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
//      world.updateComparators(pos, this);
//    }
//    super.onStateReplaced(state, world, pos, newState, moved);
//  }




  public VoxelShape getShape() {
    VoxelShape shape = VoxelShapes.empty();
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.0625, 0.0625, 0, 0.1875, 1, 1));
    shape = VoxelShapes.union(shape, VoxelShapes.cuboid(0.1875, 0.0625, 0, 0.9375, 0.875, 1));

    return shape;
  }

  final VoxelShape blockShape = getShape();
  final VoxelShape[] blockShapes = VoxelShapeUtils.calculateBlockShapes(blockShape);

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    Direction direction = state.get(FACING);
    return VoxelShapeUtils.getSidedOutlineShape(direction, blockShape, blockShapes);
  }

  // Initial state upon placing
  @Override
  public BlockState getPlacementState(ItemPlacementContext context) {
    return this.getDefaultState().with(Properties.HORIZONTAL_FACING, context.getHorizontalPlayerFacing().getOpposite());
  }

  // Append initial properties
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  @Override
  protected MapCodec<? extends HorizontalFacingBlock> getCodec() {
    return null;
  }
}