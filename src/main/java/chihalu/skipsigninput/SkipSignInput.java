package chihalu.skipsigninput;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.minecraft.server.command.CommandManager.literal;

public class SkipSignInput implements ModInitializer {
        public static final String MOD_ID = "skipsigninput";

        // This logger is used to write text to the console and the log file.
        // It is considered best practice to use your mod id as the logger's name.
        // That way, it's clear which mod wrote info, warnings, and errors.
        public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

        private static boolean skipEnabled = true;

        public static boolean isSkipEnabled() {
                return skipEnabled;
        }

        public static void setSkipEnabled(boolean enabled) {
                skipEnabled = enabled;
        }

        @Override
        public void onInitialize() {
                CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(
                        literal("SignSkip")
                                .requires(source -> source.hasPermissionLevel(2))
                                .then(literal("on").executes(context -> {
                                        setSkipEnabled(true);
                                        context.getSource().sendFeedback(
                                                () -> Text.literal("看板入力スキップ機能を有効にしました。"),
                                                false
                                        );
                                        LOGGER.info("看板入力スキップ機能を有効化しました。");
                                        return 1;
                                }))
                                .then(literal("off").executes(context -> {
                                        setSkipEnabled(false);
                                        context.getSource().sendFeedback(
                                                () -> Text.literal("看板入力スキップ機能を無効にしました。"),
                                                false
                                        );
                                        LOGGER.info("看板入力スキップ機能を無効化しました。");
                                        return 1;
                                }))
                ));

                LOGGER.info("SkipSignInput initialized.");
        }
}