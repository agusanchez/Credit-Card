package com.creditcard.android.service.deserealizer;

import com.creditcard.android.model.ResponseJsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Agustin on 30/9/2017.
 */

public class ResponseGsonDeserializer implements JsonDeserializer<ResponseJsonArray> {

    @Override
    public ResponseJsonArray deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ResponseJsonArray response = new ResponseJsonArray();
        response.jsonArray = json.getAsJsonArray();
        return response;
    }
}
