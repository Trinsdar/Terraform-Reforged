package com.terraformersmc.terraform.boat.impl.mixin;

import java.util.Map;

import com.mojang.datafixers.util.Pair;
import com.terraformersmc.terraform.boat.impl.TerraformBoatEntity;
import com.terraformersmc.terraform.boat.impl.client.TerraformBoatEntityRenderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.Identifier;

@Mixin(BoatEntityRenderer.class)
@OnlyIn(Dist.CLIENT)
public class MixinBoatEntityRenderer {
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"))
	private Object getTerraformBoatTextureAndModel(Map<BoatEntity.Type, Pair<Identifier, BoatEntityModel>> map, Object type, BoatEntity entity) {
		if (entity instanceof TerraformBoatEntity && ((Object) this) instanceof TerraformBoatEntityRenderer) {
			return ((TerraformBoatEntityRenderer) (Object) this).getTextureAndModel((TerraformBoatEntity) entity);
		}
		return map.get(type);
	}
}