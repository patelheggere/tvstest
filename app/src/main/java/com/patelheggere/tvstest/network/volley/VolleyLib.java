package com.patelheggere.tvstest.network.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.patelheggere.tvstest.network.volley.NetworkRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolleyLib {
    private static final String TAG = "VolleyLib";

    public interface NResultListener<T> {

        void onResult(int resultCode, T tResult);

        void onFailure(int mError);
    }

    public static void jsonObjectRequest(final Context mContext, int method, String url, final HashMap paramas, final String mSessionToken, final NResultListener<JSONObject> mStringNResultListener) {
        //int type = method == 0 ? Request.Method.GET : Request.Method.POST;
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (method,  url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mStringNResultListener.onResult(200, response);

                        } catch (Exception e) {
                            Log.d(TAG, "onResponse: json object" + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mStringNResultListener.onFailure(500);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();

                return headers;
            }

            @Override
            public byte[] getBody() {
                if(mSessionToken==null && paramas==null)
                {
                    return null;
                }
                if(mSessionToken!=null) {
                    try {
                        return new JSONObject(mSessionToken).toString().getBytes();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return new JSONObject(paramas).toString().getBytes();

            }


            @Override
            public String getBodyContentType() {
                if(mSessionToken==null && paramas == null)
                {
                    return null;
                }
                else
                {
                    return "application/json";
                }
            }
        };

        NetworkRequest.getInstance(mContext).addToRequestQueue(jsObjRequest);
    }
}
