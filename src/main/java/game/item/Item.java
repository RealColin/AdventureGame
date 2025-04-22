package game.item;

import game.entity.Dragon;
import game.entity.Player;
import game.map.Castle;
import game.map.Gate;

public interface Item {
    void onPlayerInteract(Player player);
    void onDragonInteract(Dragon dragon);
    void onGateInteract(Gate gate);
    void onBroughtToCastle(Castle castle);
}
