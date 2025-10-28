package chihalu.skipsigninput.mixin;

import chihalu.skipsigninput.SignBlockEntityAccess;
import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SignItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Marks freshly placed signs so the first sign edit screen can be skipped.
 */
@Mixin(SignItem.class)
abstract class SignItemMixin {
	@Redirect(
		method = "postPlacement",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/block/AbstractSignBlock;openEditScreen(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/block/entity/SignBlockEntity;Z)V"
		)
	)
	private void skipsigninput$markFresh(AbstractSignBlock instance, PlayerEntity player, SignBlockEntity sign, boolean front) {
		((SignBlockEntityAccess) sign).skipsigninput$setSkipEditor(true);
		instance.openEditScreen(player, sign, front);
	}
}
