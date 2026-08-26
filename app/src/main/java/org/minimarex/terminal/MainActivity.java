package org.minimarex.terminal;

import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;
import org.minimarex.minimaapi.MinimaAPI;
import org.minimarex.minimaapi.MinimaAPIListener;
import org.minimarex.minimaapi.MinimaAPILogger;

/**
 * Minima Terminal — a two-tab window onto the on-device Minima Core node.
 *
 * Tab 0 (Terminal) sends node commands and prints the JSON replies; tab 1 (Logs)
 * shows the node's MINIMALOG stream, captured by {@code MinimaNotifyReceiver}
 * into a small SQLite cache so log lines survive while this app is closed.
 * All node access goes through the minimaapi broadcast-Intent IPC — the app
 * never opens a socket and works only when the Minima Core app is installed
 * and this app is enabled under Minima Core → Apps.
 */
public class MainActivity extends AppCompatActivity {

    ViewPager mMainPager;

    TerminalAdapter mTerminalAdapter;

    // One MinimaAPI registration per activity: created here, handed to the
    // TerminalView for commands, and unregistered in onDestroy. Registering in
    // each view instead would leak duplicate broadcast receivers on tab churn.
    MinimaAPI mMinimaAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("Minima Terminal");
        //tb.getOverflowIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        setSupportActionBar(tb);

        mTerminalAdapter = new TerminalAdapter(this);

        mMainPager = findViewById(R.id.main_pager);
        mMainPager.setAdapter(mTerminalAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mMainPager);

        tabs.getTabAt(0).setText("Terminal");

        tabs.getTabAt(1).setText("Logs");

        // The Logs tab renders on demand (it re-reads the receiver DB in
        // refreshView), not live — so every select AND re-select refreshes it.
        // Without the reselect hook, tapping the already-open Logs tab would
        // show a stale snapshot with no way to update it.
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //Refresh View
                mTerminalAdapter.refreshPagerView(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //Refresh View
                mTerminalAdapter.refreshPagerView(tab.getPosition());
            }
        });

        //Register the MinimaAPI
        mMinimaAPI = new MinimaAPI(this, new MinimaAPIListener() {
            @Override
            public void response(JSONObject jsonObject) {
                MinimaAPILogger.log(jsonObject.toString());
            }
        });

        //Set this API handler
        mTerminalAdapter.getTerminalView().setMinimaAPI(mMinimaAPI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMinimaAPI.onDestroy();
    }
}

