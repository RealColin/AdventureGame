package game.item;

import game.entity.Dragon;
import game.entity.Player;
import game.map.Castle;
import game.map.Gate;

public class Sword implements Item{

    @Override
    public void onPlayerInteract(Player player) {
        // TODO pickup logic
    }

    @Override
    public void onDragonInteract(Dragon dragon) {
        // TODO logic for killing dragon
    }

    @Override
    public void onGateInteract(Gate gate) {
        // Nothing gets done when Sword interacts with the Gate
    }

    @Override
    public void onBroughtToCastle(Castle castle) {
        // Nothing gets done when Sword is brought into Castle
    }
}
