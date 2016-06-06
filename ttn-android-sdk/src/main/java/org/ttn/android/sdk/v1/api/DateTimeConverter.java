package org.ttn.android.sdk.v1.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;

/*
 * Copyright 2016 Fabio Tiriticco / Fabway
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by fabiotiriticco on 5 June 2016.
 *
 */
public class DateTimeConverter implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    // No need for an InstanceCreator since DateTime provides a no-args constructor
    @Override
    public JsonElement serialize(DateTime src, Type srcType, JsonSerializationContext context) {

        // TODO: convert to ISO
        return new JsonPrimitive(src.getMillis());
    }

    @Override
    public DateTime deserialize(JsonElement json, Type type, JsonDeserializationContext context)
            throws JsonParseException {
        String dateStr = json.getAsString();
        DateTime time = null;
        try {
            time = ISODateTimeFormat.dateTime().parseDateTime(dateStr);
        } catch (IllegalArgumentException e1) {
            // maybe we need to add a Z at the end? Someone might be sending wrong dates
            try {
                time = ISODateTimeFormat.dateTime().parseDateTime(dateStr + "Z");
            } catch (IllegalArgumentException e2) {
                // pity, nothing to do
            }
        }
        return time;
    }
}