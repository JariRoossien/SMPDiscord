package nl.dizmizzer.core.repository;


import lombok.Getter;
import nl.dizmizzer.core.entity.WhitelistPlayer;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public abstract class WhitelistRepository {

    @Getter
    private final Set<WhitelistPlayer> playerSet = new HashSet<>();

    /**
     * Checks if player is whitelisted.
     *
     * @param playerId UUID to check.
     * @return If UUID is whitelisted.
     */
    public boolean isWhitelisted(UUID playerId) {
        return playerSet.stream().anyMatch(p -> p.getPlayerId().equals(playerId));
    }

    /**
     * Adds a player to the whitelisted players.
     *
     * @param player Player to add.
     * @return True if player has been added, False if player was already whitelisted.
     */
    public boolean addPlayer(WhitelistPlayer player) {
        return playerSet.add(player);
    }

    public WhitelistPlayer getPlayerFromUUID(UUID playerId) {
        return playerSet.stream().filter(player -> player.getPlayerId().equals(playerId)).findAny().orElse(null);
    }

    public WhitelistPlayer getPlayerFromUsername(String username) {
        return playerSet.stream().filter(player -> player.getPlayerUsername().equalsIgnoreCase(username)).findAny().orElse(null);
    }

    public Set<WhitelistPlayer> getAllPlayers() {
        return playerSet;
    }

    public abstract void load();

    public abstract void save();

}
