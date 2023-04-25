package group.megamarket.gateway.component;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

/**
 * Интерфейс для добаления комманд, отображающихся в чате бота
 */

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info")
    );
}
