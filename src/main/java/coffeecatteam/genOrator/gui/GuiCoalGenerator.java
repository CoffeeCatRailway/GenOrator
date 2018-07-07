package coffeecatteam.genOrator.gui;

import coffeecatteam.genOrator.Reference;
import coffeecatteam.genOrator.gui.container.ContainerCoalGenerator;
import coffeecatteam.genOrator.objects.tileentity.TileCoalGenerator;
import coffeecatteam.genOrator.util.Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiCoalGenerator extends GuiContainer {

    private IInventory inventory;
    private TileCoalGenerator generator;

    private ResourceLocation TEXTURES = new ResourceLocation(Reference.MODID, "textures/gui/container/coal_generator.png");

    public GuiCoalGenerator(IInventory inventory, TileCoalGenerator generator) {
        super(new ContainerCoalGenerator(inventory, generator));
        this.inventory = inventory;
        this.generator = generator;

        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String teName = this.generator.getDisplayName().getUnformattedText();
        fontRenderer.drawString(teName, 8, 6, 0x040404);
        fontRenderer.drawString(inventory.getDisplayName().getUnformattedText(), 8, 72, 0x040404);

        if(mouseX >= this.guiLeft + 84 && mouseX <= this.guiLeft + 119 && mouseY >= this.guiTop + 26 && mouseY <= this.guiTop + 63) {
            List<String> text = new ArrayList<>();
            text.add(this.generator.getField(0) + "RF / " + this.generator.getField(1) + "RF");
            this.drawHoveringText(text, mouseX - this.guiLeft, mouseY - this.guiTop);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        if (TileCoalGenerator.isBurning(this.generator)) {
            int k = getBurnTime(13);
            this.drawTexturedModalRect(this.guiLeft + 52, this.guiTop + 40 - k, 176, 12 - k, 14, k + 1);
        }

        int l = Utils.getEnergyReading(37, this.generator.getField(0), this.generator.getField(1));
        this.drawTexturedModalRect(this.guiLeft + 84, this.guiTop + 63 - l, 176, 51 - l, 36, l + 1); // 38
    }

    private int getBurnTime(int pixels) {
        if (this.generator.getField(2) == this.generator.getField(3))
            return -1;
        return this.generator.getField(2) * pixels / 100;
    }
}
