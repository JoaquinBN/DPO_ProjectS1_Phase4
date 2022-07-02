package BusinessLayer;

import BusinessLayer.Entities.Doctor;
import BusinessLayer.Entities.Engineer;
import BusinessLayer.Entities.Master;
import BusinessLayer.Entities.Player;
import PersistenceLayer.ExecutionFileManager;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     */
    public boolean loadPlayersData(){
        List<String[]> playersData;
        try {
            playersData = executionFileManager.readPlayersData().subList(1, executionFileManager.readPlayersData().size());
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

    public void setExecutionFileManager(ExecutionFileManager executionFileManager) {
        this.executionFileManager = executionFileManager;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
