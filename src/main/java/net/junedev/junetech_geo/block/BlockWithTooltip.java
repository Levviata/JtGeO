package net.junedev.junetech_geo.block;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlockWithTooltip extends Block {
    private final String tooltip;
    private final boolean displayBelowName;

    /**
     * @param tooltip The tooltip to display
     * @param displayBelowName Whether to display the tooltip above the list of creative tabs containing this block in the creative menu
     * @see net.junedev.junetech_geo.mixin.CreativeModeInventoryScreenMixin
     */
    public BlockWithTooltip(Properties properties, String tooltip, boolean displayBelowName) {
        super(properties);
        this.tooltip = tooltip;
        this.displayBelowName = displayBelowName;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        if (!pFlag.isCreative() || !displayBelowName)
            pTooltip.add(getTooltip());
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

    public Component getTooltip() {
        return Component.literal(tooltip).withStyle(ChatFormatting.GRAY);
    }

    public boolean displaysBellowName() {
        return displayBelowName;
    }
}