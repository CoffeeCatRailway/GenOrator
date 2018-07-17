package coffeecatteam.gen_o_rator.gui;

import coffeecatteam.gen_o_rator.gui.container.ContainerCoalGenerator;
import coffeecatteam.gen_o_rator.gui.container.ContainerEndCrystalGenerator;
import coffeecatteam.gen_o_rator.init.InitBlock;
import coffeecatteam.gen_o_rator.objects.tileentity.generators.TileEndCrystalGenerator;
import coffeecatteam.gen_o_rator.util.Utils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiEndCrystalGenerator extends GuiBaseGenerator {

    public GuiEndCrystalGenerator(IInventory inventory, TileEndCrystalGenerator generator) {
        super(new ContainerEndCrystalGenerator(inventory, generator), 176, 166, "textures/gui/container/end_crystal_generator.png");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String teName = I18n.format(this.generator.getName());
        fontRenderer.drawString(teName, 8, 6, 0x040404);
        fontRenderer.drawString(inventory.getDisplayName().getUnformattedText(), 8, 72, 0x040404);

        if(mouseX >= this.guiLeft + 131 && mouseX <= this.guiLeft + 166 && mouseY >= this.guiTop + 9 && mouseY <= this.guiTop + 76) {
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

        if (TileEndCrystalGenerator.isBurning(this.generator)) {
            int k = Utils.getBurnTime(this.generator, 56);
            this.drawTexturedModalRect(this.guiLeft + 35, this.guiTop + 69 - k, 212, 55 - k, 18, k + 1);
            this.drawTexturedModalRect(this.guiLeft + 84, this.guiTop + 69 - k, 212, 55 - k, 18, k + 1);
        }

        int l = Utils.getEnergyReading(this.generator, 67);
        this.drawTexturedModalRect(this.guiLeft + 131, this.guiTop + 76 - l, 176, 67 - l, 36, l + 1);
    }
}
