package chihalu.skipsigninput.mixin;

import chihalu.skipsigninput.SignBlockEntityAccess;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
abstract class ServerPlayerEntityMixin {
	@Inject(method = "openEditSignScreen", at = @At("HEAD"), cancellable = true)
	private void skipsigninput$skipFreshEditor(SignBlockEntity sign, boolean front, CallbackInfo ci) {
		SignBlockEntityAccess access = (SignBlockEntityAccess) sign;
		if (access.skipsigninput$shouldSkipEditor()) {
			access.skipsigninput$setSkipEditor(false);
			sign.setEditor(null);
			ci.cancel();
		}
	}
}