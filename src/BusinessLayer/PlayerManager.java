package BusinessLayer;

import BusinessLayer.Entities.Players.Doctor;
import BusinessLayer.Entities.Players.Engineer;
import BusinessLayer.Entities.Players.Master;
import BusinessLayer.Entities.Players.Player;
import PersistenceLayer.ExecutionFileDAO.ExecutionFileManager;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayerManager is a class that manages the players of the game.
 */
public class PlayerManager {
    private final ArrayList<Player> players;
    private ExecutionFileManager executionFileManager;
    private String errorMessage;

    /**
     * Constructor for PlayerManager
     */
    public PlayerManager() {
        players = new ArrayList<>();
    }

    /**
     * Add a new player to the list of players
     * @param playerName the name of the player
     */
    public void addPlayer(String playerName) {
        players.add(new Engineer(playerName));
    }

    /**
     * Retrieve player from the list of players
     * @param playerName the name of the player
     * @param investigationPoints the amount of investigation points the player has
     * @param playerType the type of the player
     */
    public void retrievePlayer(String playerName, int investigationPoints, String playerType) {
        switch (playerType) {
            case "engineer" -> players.add(new Engineer(playerName, investigationPoints));
            case "master" -> players.add(new Master(playerName, investigationPoints));
            case "doctor" -> players.add(new Doctor(playerName, investigationPoints));
        }
    }

    /**
     * Get player by the name of the player.
     * @param playerName the name of the player
     * @return the player with the given name
     */
    public Player getPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                return player;
            }
        }
        return null;
    }

    /**
     * Evolve player attributes
     * @param player the player to evolve
     * @param hasPassed the boolean that indicates if the player has passed the turn
     * @param trialType the type of the trial
     * @return the player with the evolved attributes
     */
    public Player evolvePlayer(Player player, boolean hasPassed, String trialType){
        int indexPlayer = players.indexOf(player);
        if(player.getType().equals("master") && trialType.equals("Doctoral thesis defense") && hasPassed){
            players.set(indexPlayer, new Doctor(players.get(indexPlayer).getName()));
            return players.get(indexPlayer);
        }else if(player.getType().equals("master") && player.getInvestigationPoints() >= 10) {
            players.set(indexPlayer, new Doctor(players.get(indexPlayer).getName()));
            return getPlayerByName(player.getName());
        }

        if(player.getType().equals("engineer") && trialType.equals("Master studies") && hasPassed){
            players.set(indexPlayer, new Master(players.get(indexPlayer).getName()));
            return getPlayerByName(players.get(indexPlayer).getName());
        }else if(player.getType().equals("engineer") && player.getInvestigationPoints() >= 10){
            players.set(indexPlayer, new Master(players.get(indexPlayer).getName()));
            return getPlayerByName(players.get(indexPlayer).getName());
        }
        return null;
    }

    /**
     * Get the sum of all the players' IPs
     * @return the sum of all the investigation points (players that lost have 0 IP)
     */
    public int getSumIPs() {
        int sum = 0;
        for (Player player : players) {
            sum += player.getInvestigationPoints();
        }
        return sum;
    }

    /**
     * Get the number of players in the system
     * @return the amount of players in the system
     */
    public int getTotalPlayers() {
        return players.size();
    }

    /**
     * Check if any player is alive
     * @return false if any player is alive, true otherwise
     */
    public boolean allPlayersAreDead(){
        for(Player player: players){
            if(!player.isDead()){
                return false;
            }
        }
        return true;
    }

    /**
     * Load data from the player system
     * @return true if the data was loaded, false otherwise
     */
    public boolean loadPlayersData(){
        List<String[]> playersData;
        try {
            playersData = executionFileManager.readPlayersData();
            for (String[] playerData : playersData) {
                retrievePlayer(playerData[0], Integer.parseInt(playerData[1]), playerData[2]);
            }
            return true;
        } catch (IOException | CsvException e) {
            errorMessage = "Error loading data of players";
            return false;
        }
    }

    /**
     * Save data to the player system
     * @return true if the data was saved, false otherwise
     */
    public boolean saveData(){
        players.removeIf(Player::isDead);
        List<String[]> playersData = new ArrayList<>();
        for(Player player: players){
            playersData.add(player.getInfo());
        }
        try {
            executionFileManager.writePlayersData(playersData);
            return true;
        } catch (IOException e) {
            errorMessage = "Error saving players' data inside the execution file";
            return false;
        }

    }

    /**
     * Set the execution file manager
     * @param executionFileManager the execution file manager
     */
    public void setExecutionFileManager(ExecutionFileManager executionFileManager) {
        this.executionFileManager = executionFileManager;
    }

    /**
     * Get the error message
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Get all the players
     * @return the list of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
