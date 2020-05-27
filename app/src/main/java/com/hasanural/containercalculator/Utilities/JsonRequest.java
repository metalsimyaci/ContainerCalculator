package com.hasanural.containercalculator.Utilities;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


public class JsonRequest<T> extends Request<T> {
    private static final String TAG = "JsonRequestHelper";
    private final Gson gson = new Gson();
    private final Class<T> returnType;
    private final Object model;
    private final Map<String, String> headers;
    private final Response.Listener<T> listener;

    public JsonRequest(Integer httpVerb, String url, Class<T> returnType, Object model,
                       Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(httpVerb, url, errorListener);
        this.returnType = returnType;
        this.model = model;
        this.headers = headers;
        this.listener = listener;
    }
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    public static Map<String, String> getMapFromEntity(Object entity) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            Field[] fields = entity.getClass().getFields();
            for (Field f : fields)
                if (f != null && f.getModifiers() == Modifier.PUBLIC)
                    map.put(f.getName(), f.get(entity).toString());
            return map;
        } catch (IllegalAccessException error) {
            Log.e(TAG, error.getMessage());
            return null;
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    public String getBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override
    public byte[] getBody() {
        return gson.toJson(this.model).getBytes();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    gson.fromJson(json, returnType),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }
}
