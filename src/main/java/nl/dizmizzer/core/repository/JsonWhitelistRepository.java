package nl.dizmizzer.core.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nl.dizmizzer.core.entity.WhitelistPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class JsonWhitelistRepository extends WhitelistRepository {

    private final File dataPath;
    private final File whiteListDataFile;

    public JsonWhitelistRepository() {
        this.dataPath = new File("plugins/TRSSMPPlugin");
        this.whiteListDataFile = new File(dataPath, "whitelist_data.json");
    }

    @Override
    public void load() {
        if (!dataPath.exists()) {
            if (!dataPath.mkdirs()) throw new RuntimeException("Cannot create datapath folder!");
        }
        if (!whiteListDataFile.exists()) {
            try {
                if (!whiteListDataFile.createNewFile()) {
                    throw new RuntimeException("Cannot create File!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            Gson gson = new Gson();
            try {
                getPlayerSet().addAll(gson.fromJson(new FileReader(whiteListDataFile), new TypeToken<Set<WhitelistPlayer>>() {}.getType()));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void save() {
        if (!dataPath.exists()) {
            if (!dataPath.mkdirs()) throw new RuntimeException("Cannot create datapath folder!");
        }
        if (!whiteListDataFile.exists()) {
            try {
                if (!whiteListDataFile.createNewFile()) {
                    throw new RuntimeException("Cannot create File!");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Gson gson = new Gson();
        FileWriter writer;
        try {
            writer = new FileWriter(whiteListDataFile);
            writer.write(gson.toJson(getPlayerSet()));
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
