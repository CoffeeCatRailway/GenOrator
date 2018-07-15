package coffeecatteam.gen_o_rator.gui;

import coffeecatteam.gen_o_rator.gui.container.ContainerCoalGenerator;
import coffeecatteam.gen_o_rator.init.InitBlock;
import coffeecatteam.gen_o_rator.objects.tileentity.TileCoalGenerator;
import coffeecatteam.gen_o_rator.util.Utils;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class GuiCoalGenerator extends GuiBaseGenerator {

     public GuiCoalGenerator(IInventory inventory, TileCoalGenerator generator) {
        super(new ContainerCoalGenerator(inventory, generator), 176, 166, "textures/gui/container/coal_generator.png");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String teName = I18n.format(InitBlock.COAL_GENERATOR.getUnlocalizedName() + ".name");
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
            int k = Utils.getBurnTime(this.generator, 13);
            this.drawTexturedModalRect(this.guiLeft + 52, this.guiTop + 40 - k, 176, 12 - k, 14, k + 1);
        }

        int l = Utils.getEnergyReading(this.generator, 37);
        this.drawTexturedModalRect(this.guiLeft + 84, this.guiTop + 63 - l, 176, 51 - l, 36, l + 1); // 38
    }
}
