package chihalu.skipsigninput.mixin;

import chihalu.skipsigninput.SignBlockEntityAccess;
import net.minecraft.block.entity.SignBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(SignBlockEntity.class)
abstract class SignBlockEntityMixin implements SignBlockEntityAccess {
	@Unique
	private boolean skipsigninput$skipEditor;

	@Override
	public boolean skipsigninput$shouldSkipEditor() {
		return this.skipsigninput$skipEditor;
	}

	@Override
	public void skipsigninput$setSkipEditor(boolean skip) {
		this.skipsigninput$skipEditor = skip;
	}
}
