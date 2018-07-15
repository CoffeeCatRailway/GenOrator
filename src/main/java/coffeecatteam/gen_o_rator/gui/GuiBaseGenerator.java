package coffeecatteam.gen_o_rator.gui;

import coffeecatteam.gen_o_rator.Reference;
import coffeecatteam.gen_o_rator.gui.container.ContainerBaseGenerator;
import coffeecatteam.gen_o_rator.objects.tileentity.base.TileBaseGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiBaseGenerator extends GuiContainer {

    protected IInventory inventory;
    protected TileBaseGenerator generator;

    protected ResourceLocation TEXTURES;

    public GuiBaseGenerator(ContainerBaseGenerator containerGenerator, int xSize, int ySize, String texturePath) {
        super(containerGenerator);
        this.inventory = containerGenerator.getPlayer();
        this.generator = containerGenerator.getGenerator();

        this.xSize = xSize;
        this.ySize = ySize;

        TEXTURES = new ResourceLocation(Reference.MODID, texturePath);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }
}
