package org.charlie.chess.players;

public class PlayerStats {

    private final String name;
    private final int wins;
    private final int losses;

    public PlayerStats(String name, int wins, int losses) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerStats that = (PlayerStats) o;

        if (losses != that.losses) return false;
        if (wins != that.wins) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + wins;
        result = 31 * result + losses;
        return result;
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "name='" + name + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                '}';
    }
}
