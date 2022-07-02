package PersistenceLayer.EditionFileDAO;

import BusinessLayer.Entities.Edition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditionJSONManager implements EditionsFileManager {
    private static final String filename = "files/Editions.json";
    private final Gson gson;

    public EditionJSONManager(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }


    @Override
    public void writeEditions(ArrayList<Edition> Editions) throws IOException {
            FileWriter writer = new FileWriter(filename);
            writer.write(gson.toJson(Editions));
            writer.close();
    }

    @Override
    public List<String[]> readEditions() throws FileNotFoundException {
            FileReader reader = new FileReader(filename);
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            List<String[]> editions = new ArrayList<>();
            if(jsonArray != null){
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject object = jsonArray.get(i).getAsJsonObject();
                    List<String> aux = new ArrayList<>();
                    aux.add(object.get("year").getAsString());
                    aux.add(object.get("numberOfPlayers").getAsString());
                    for (int j = 0; j < object.get("trials").getAsJsonArray().size(); j++) {
                        aux.add(object.get("trials").getAsJsonArray().get(j).getAsString());
                    }
                    // convert aux to new String[] and add to editions
                    String[] aux2 = new String[aux.size()];
                    for (int j = 0; j < aux.size(); j++) {
                        aux2[j] = aux.get(j);
                    }
                    editions.add(aux2);
                }
            }
            return editions;
    }
}