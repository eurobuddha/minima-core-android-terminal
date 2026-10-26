package com.eurobuddha.terminal.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;
import com.eurobuddha.minimaapi.MinimaAPI;
import com.eurobuddha.minimaapi.MinimaAPILogger;
import com.eurobuddha.minimaapi.MinimaAPIMessages;

import java.util.Objects;

/**
 * Manifest-registered receiver for Minima Core's MINIMA_API_NOTIFY broadcasts.
 * Runs with no activity alive, verifies the MinimaID (so a broadcast from
 * anything other than OUR registered node pairing is dropped), and files
 * MINIMALOG events into ReceiverDB for the Logs tab. Other event types are
 * deliberately ignored — this app only shows logs.
 */
public class MinimaNotifyReceiver extends BroadcastReceiver {

    // Static on purpose: a BroadcastReceiver instance lives only for one
    // onReceive() call, but reopening SQLite per broadcast is wasteful — the
    // process outlives the receiver, so the handle is kept and reOpen()ed only
    // if Android closed it in between.
    private static ReceiverDB mDB = null;

    @Override
    public void onReceive(Context zContext, Intent zIntent) {

        String action = zIntent.getAction();

        if (MinimaAPI.isNotify(action)) {

            //Check MinimaID - this is set by you when you register
            if(!MinimaAPI.checkMinimaID(zContext, zIntent)){
                return;
            }

            //Get the DB
            if(mDB == null){
                mDB = new ReceiverDB(zContext);
            }else{
                if(!mDB.isOpen()){
                    mDB.reOpen();
                }
            }

            //Delete the 0ld
            mDB.deleteOldMessages();

            //Get the data
            String message = MinimaAPI.notifyData(zIntent);

            //Convert to a JSON
            try {
                JSONObject jsonresp = new JSONObject(message);

                String event        = jsonresp.getString("event");
                JSONObject data     = jsonresp.getJSONObject("data");

                //Only the LOGS for now
                if(event.equals("MINIMALOG")){

                    //Insert into DB
                    mDB.insertEvent("MINIMALOG", data.toString());
                }

            } catch (JSONException e) {
                MinimaAPILogger.log("Invalid JSON received from Minima-Core");
            }

        }else{
            MinimaAPILogger.log("Minima Terminal RECEIVED UNKNOWN action : "+action);
        }
    }
}
