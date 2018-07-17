package coffeecatteam.gen_o_rator.objects.tileentity.render;

import coffeecatteam.gen_o_rator.objects.tileentity.generators.TileEndCrystalGenerator;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class EndCrystalGeneratorRenderer extends TileEntitySpecialRenderer<TileEndCrystalGenerator> {

    private static final ResourceLocation ENDER_CRYSTAL_TEXTURES = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
    private final ModelBase modelEnderCrystal = new ModelEnderCrystal(0.0F, false);

    @Override
    public void render(TileEndCrystalGenerator te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (te.isBurning() && te.burn) {
            GlStateManager.pushMatrix();
            {
                // Logic
                te.innerRotation += 0.5f;
                float f = te.innerRotation + partialTicks;
                float f1 = MathHelper.sin(f * 0.2F) / 2.0F + 0.45F;
                f1 = f1 * f1 + f1;

                // Base position & texture
                GlStateManager.translate((float) x + 0.5f, (float) y + 0.25f, (float) z + 0.5f);
                this.bindTexture(ENDER_CRYSTAL_TEXTURES);

                // Scale & render
                float scale = 0.35f;
                GlStateManager.scale(scale, scale, scale);
                this.modelEnderCrystal.render(null, 0.0F, f * 3.0F, f1 * 0.15F, 0.0F, 0.0F, 0.0625F);
            }
            GlStateManager.popMatrix();
        }
    }
}
