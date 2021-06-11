package com.teamaurora.bayou_blues.common.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class DirectionalBlockPos {
    public BlockPos pos;
    public Direction direction;

    public DirectionalBlockPos(BlockPos p, Direction a) {
        pos = p;
        direction = a;
    }
}