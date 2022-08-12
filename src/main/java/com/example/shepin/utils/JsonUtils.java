package com.example.shepin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class JsonUtils {
    private static final SerializerFeature[] SERIALIZER_FEATURES;

    public JsonUtils() {
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        } else if (obj instanceof String) {
            return obj.toString();
        } else {
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(new String[0]);
            filter.getExcludes().add("metas");
            return JSON.toJSONString(obj, new SerializeConfig(), filter, SERIALIZER_FEATURES);
        }
    }

    public static <T> T deJson(String json, Class<T> cls) {
        return null != json && !json.isEmpty() ? JSON.parseObject(json, cls) : null;
    }

    public static <T> T deJson(String json, TypeReference<T> type) {
        return null != json && !json.isEmpty() ? JSON.parseObject(json, type, new Feature[0]) : null;
    }

    static {
        SERIALIZER_FEATURES = new SerializerFeature[]{SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteEnumUsingToString};
    }
}
