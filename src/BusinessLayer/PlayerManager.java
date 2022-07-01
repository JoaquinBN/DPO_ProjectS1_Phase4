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
    private ArrayList<Player> players;
    private ExecutionFileManager executionFileManager;

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
            case "Engineer" -> players.add(new Engineer(playerName, investigationPoints));
            case "Master" -> players.add(new Master(playerName, investigationPoints));
            case "Doctor" -> players.add(new Doctor(playerName, investigationPoints));
        }
    }

    /**
     * Get index of player in the list of players
     * @param index the index of the player
     * @return the player at the given index
     */
    public Player getPlayerByIndex(int index) {
        return players.get(index);
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
     * Check if player is dead
     * @param index the index of the player
     * @return true if the player is dead, false otherwise
     */
    public boolean playerIsDead(int index){
        return players.get(index).getStatus();
    }

    /**
     * Check if any player is alive
     * @return false if any player is alive, true otherwise
     */
    public boolean allPlayersAreDead(){
        for(Player player: players){
            if(!player.getStatus()){
                return false;
            }
        }
        return true;
    }

    /**
     * Load data from the player system
     * @throws IOException if there is an error reading the file
     * @throws CsvException if there is an error reading the file
     */
    public void loadPlayersData() throws IOException, CsvException {
        List<String[]> playersData = executionFileManager.readPlayersData().subList(1, executionFileManager.readPlayersData().size());
        for (String[] playerData : playersData) {
            retrievePlayer(playerData[0], Integer.parseInt(playerData[1]), playerData[2]);
        }
    }

    /**
     * Save data to the player system
     * @throws IOException if there is an error writing to the file
     */
    public void saveData() throws IOException {
        players.removeIf(Player::getStatus);
        List<String[]> playersData = new ArrayList<>();
        for(Player player: players){
            playersData.add(player.getInfo());
        }
        executionFileManager.writePlayersData(playersData);

    }

    public void setExecutionFileManager(ExecutionFileManager executionFileManager) {
        this.executionFileManager = executionFileManager;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
