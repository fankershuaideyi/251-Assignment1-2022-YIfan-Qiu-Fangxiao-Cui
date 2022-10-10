import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class YamlUtil {
    Map<String, Object> map = new HashMap<>();
    // Read user settings from yaml file
    public void read() throws IOException {
        Yaml yml = new Yaml();
        FileReader reader;
        try{
            reader = new FileReader("src\\main\\java\\assignment1.yml");
        }catch (FileNotFoundException e) {  // Yml configuration file does not exist
            File file = new File("src\\main\\java");
            if(!file.exists()){ // Folder does not exist
                file.mkdir();
            }
            //Default font
            map.put("font-style", 0);
            map.put("font-size", 40);
            map.put("font-color", "java.awt.Color[r=0,g=0,b=0]");
            write();
        }
        reader = new FileReader("src\\main\\java\\assignment1.yml");
        BufferedReader buffer = new BufferedReader(reader);
        map = yml.load(buffer);
        buffer.close();
        reader.close();
    }

    // Write user settings to yaml file
    public void write() throws IOException {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);//给yml设置一个格式
        Yaml yml = new Yaml(dumperOptions);
        FileWriter writer = new FileWriter("src\\main\\java\\assignment1.yml");
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.newLine();
        yml.dump(map, buffer);
        buffer.close();
        writer.close();
    }

}