package de.leonkoth.blockparty.util;

import de.leonkoth.blockparty.BlockParty;
import de.leonkoth.blockparty.player.PlayerInfo;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Package de.leonkoth.blockparty.util
 *
 * @author Leon Koth
 * © 2019
 */
public class BlockPartyExpansion extends PlaceholderExpansion {

    private JavaPlugin plugin;

    /**
     * Since we register the expansion inside our own plugin, we
     * can simply use this method here to get an instance of our
     * plugin.
     *
     * @param plugin
     *        The instance of our plugin.
     */
    public BlockPartyExpansion(BlockParty plugin){
        this.plugin = plugin.getPlugin();
    }

    /**
     * Because this is an internal class,
     * you must override this method to let PlaceholderAPI know to not unregister your expansion class when
     * PlaceholderAPI is reloaded
     *
     * @return true to persist through reloads
     */
    @Override
    public boolean persist(){
        return true;
    }

    /**
     * Because this is a internal class, this check is not needed
     * and we can simply return {@code true}
     *
     * @return Always true since it's an internal class.
     */
    @Override
    public boolean canRegister(){
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     * <br>For convienience do we return the author from the plugin.yml
     *
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest
     * method to obtain a value if a placeholder starts with our
     * identifier.
     * <br>This must be unique and can not contain % or _
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier(){
        return "blockparty";
    }

    /**
     * This is the version of the expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * For convienience do we return the version from the plugin.yml
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param  player
     *         A {@link org.bukkit.entity.Player Player}.
     * @param  identifier
     *         A String containing the identifier/value.
     *
     * @return possibly-null String of the requested identifier.
     */
    @Override
    public String onPlaceholderRequest(Player player, String identifier){

        if(player == null){
            return "";
        }

        // %blockparty_wins%
        if(identifier.equals("wins")){
            PlayerInfo playerInfo = PlayerInfo.getFromPlayer(player);
            return playerInfo == null ? "0" : "" + playerInfo.getWins();
        }

        // %someplugin_gamesplayed%
        if(identifier.equals("gamesplayed")){
            PlayerInfo playerInfo = PlayerInfo.getFromPlayer(player);
            return playerInfo == null ? "0" : "" + playerInfo.getGamesPlayed();
        }

        // %someplugin_points%
        if(identifier.equals("points")){
            PlayerInfo playerInfo = PlayerInfo.getFromPlayer(player);
            return playerInfo == null ? "0" : "" + playerInfo.getPoints();
        }

        return null;
    }

}
