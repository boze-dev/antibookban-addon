package addons.boze.antibookban.mixin;

import addons.boze.antibookban.AntiBookBanModule;
import net.minecraft.nbt.NbtSizeTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NbtSizeTracker.class)
public class MixinNbtSizeTracker {

    @Inject(method = "of", at = @At("HEAD"), cancellable = true)
    private static void modifyMaxBytes(long maxBytes, CallbackInfoReturnable<NbtSizeTracker> cir) {
        if (AntiBookBanModule.INSTANCE.getState()) {
            cir.setReturnValue(NbtSizeTracker.ofUnlimitedBytes());
        }
    }
}
