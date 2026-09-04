package com.astrax.app;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ToolsJsonLoader {
    public static List<ToolModel> load(Context ctx) {
        try (InputStream is = ctx.getAssets().open("tools.json")) {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            String json = new String(buffer, StandardCharsets.UTF_8);
            Type listType = new TypeToken<List<ToolModel>>(){}.getType();
            return new Gson().fromJson(json, listType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
