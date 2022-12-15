package nl.dizmizzer.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
public class WhitelistPlayer {

    private UUID playerId;
    private String playerUsername;

    private WhitelistPlayer referral;

    private boolean insertedByGuild;

    public WhitelistPlayer(UUID playerId, boolean insertedByGuild) {
        this.playerId = playerId;
        this.referral = null;
        this.insertedByGuild = insertedByGuild;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WhitelistPlayer player = (WhitelistPlayer) o;
        return playerId.equals(player.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId);
    }
}
