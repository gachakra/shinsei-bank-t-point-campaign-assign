package dev.notonza.shinsei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author gachakra
 * Created on 2020/12/06.
 */
public class LoadFileTest {

    public LoadFileTest() {
//        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("/resources/config-out.yml");
//        System.out.println(stream);

        try (BufferedReader r = new BufferedReader(new InputStreamReader(
            ClassLoader.getSystemResourceAsStream("config-out.yml")))) {
            String line;
            while ((line = r.readLine()) != null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //        InputStream streamp = this.getClass().getClassLoader().getResourceAsStream("config.properties");
//        System.out.println(streamp);
//
//        Properties properties = new Properties();
//        try {
//            properties.load(Files.newBufferedReader(Paths.get("resources/config.properties"), StandardCharsets.UTF_8));
//            System.out.println(properties.get("account"));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
