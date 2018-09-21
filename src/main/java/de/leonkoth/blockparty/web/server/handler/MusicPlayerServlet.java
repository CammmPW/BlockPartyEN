package de.leonkoth.blockparty.web.server.handler;

import de.leonkoth.blockparty.arena.Arena;
import de.leonkoth.blockparty.player.PlayerInfo;
import org.bukkit.Bukkit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Leon on 19.09.2018.
 * Project BlockParty-2.0
 * © 2016 - Leon Koth
 */

@WebServlet(urlPatterns = "/Musicplayer")
public class MusicPlayerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        String playerName = (String)request.getSession(true).getAttribute("name");
        PlayerInfo playerInfo;
        if(playerName != null && !playerName.equals("") && (playerInfo = PlayerInfo.getFromPlayer(playerName)) != null && Bukkit.getPlayer(playerName) != null)
        {
            String currentArena = playerInfo.getCurrentArena();
            Arena arena = Arena.getByName(playerInfo.getCurrentArena());
            response.getWriter().write(
                    "{\n" +
                            "  \"name\": \"" + playerName + "\",\n" +
                            "  \"inArena\": " + PlayerInfo.isInArena(playerName) + ",\n" +
                            "  \"arena\": {\n" +
                            "    \"name\": \"" + ((currentArena != null && !currentArena.equals("")) ? currentArena : "_null_")  + "\",\n" +
                            "    \"status\": \"" + ((currentArena != null && !currentArena.equals("")) ? arena.getGameState().name() : "_null_") + "\",\n" +
                            "    \"song\": \"" + ((currentArena != null && !currentArena.equals("") && arena.getSongManager().getVotedSong() != null) ? arena.getSongManager().getVotedSong().getName() : "_null_") + "\"\n" +
                            "  }\n" +
                            "}\n\n");
            /*if(currentArena != null && !currentArena.equals(""))
            {
                response.getWriter().write(
                             "data: " + "{\n" +
                                "data: " + "  \"name\": \"" + playerName + "\",\n" +
                                "data: " + "  \"inArena\": " + PlayerInfo.isInArena(playerName) + ",\n" +
                                "data: " + "  \"arena\": {\n" +
                                "data: " + "    \"name\": \"" + playerInfo.getCurrentArena() + "\",\n" +
                                "data: " + "    \"status\": \"" + Arena.getByName(playerInfo.getCurrentArena()).getGameState().name() + "\"\n" +
                                "data: " + "  }\n" +
                                "data: " + "}\n\n");
                response.getWriter().write(
                             "{\n" +
                                "  \"name\": \"" + playerName + "\",\n" +
                                "  \"inArena\": " + PlayerInfo.isInArena(playerName) + ",\n" +
                                "  \"arena\": {\n" +
                                "    \"name\": \"" + playerInfo.getCurrentArena() + "\",\n" +
                                "    \"status\": \"" + Arena.getByName(playerInfo.getCurrentArena()).getGameState().name() + "\"\n" +
                                "  }\n" +
                                "}\n\n");
                System.out.println("in arena");
            }
            else
            {
                System.out.println("arena null");
            }*/
        }
        else
        {
            System.out.println("no player info or offline");
        }
    }

}
