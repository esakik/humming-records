package com.application.humming.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

    /**
     * プロパティファイルに含まれるメッセージを取得する.
     *
     * @param key
     * @return メッセージ
     */
    public static String getProperties(final String key) {
        Properties properties = new Properties();
        String filePass = "./src/main/resources/common/message.properties";
        try {
            InputStream inputStream = new FileInputStream(filePass);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            properties.load(inputStreamReader);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
