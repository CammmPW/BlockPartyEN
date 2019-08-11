package de.leonkoth.blockparty.listener;

import de.leonkoth.blockparty.BlockParty;
import de.leonkoth.blockparty.player.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private BlockParty blockParty;

    public BlockBreakListener(BlockParty blockParty) {
        this.blockParty = blockParty;

        Bukkit.getPluginManager().registerEvents(this, blockParty.getPlugin());
    }

    @EventHandler
    public void onBlockPlace(BlockBreakEvent event) { // TODO: Remove Sign on break - Removed in updateSigns
        Player player = event.getPlayer();

        if (PlayerInfo.isInArena(player)) {
            event.setCancelled(true);
        }
    }

}
