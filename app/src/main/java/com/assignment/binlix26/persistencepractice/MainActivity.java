package com.assignment.binlix26.persistencepractice;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.assignment.binlix26.persistencepractice.data.BMCContract.VisitorEntry;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private ListView visitorList;
    private SimpleCursorAdapter adapter;
    public static final int VISITOR_LOADER_ID = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setupCursorAdapter();

        getLoaderManager().initLoader(VISITOR_LOADER_ID, null, this);

        // find the list and bind it to the adapter
        visitorList = (ListView) findViewById(R.id.visitor_list);
        visitorList.setEmptyView(findViewById(R.id.empty));
        visitorList.setAdapter(adapter);
    }

    private void setupCursorAdapter() {
        String[] from = {
                VisitorEntry._ID,
                VisitorEntry.COLUMN_NAME,
                VisitorEntry.COLUMN_BUSINESS_NAME
        };

        int[] to = {
                R.id.visitor_id,
                R.id.visitor_name,
                R.id.visitor_business
        };

        adapter = new SimpleCursorAdapter(this,
                R.layout.visitor_record_view, null, from, to, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Define the columns to retrieve
        String[] projectionFields = new String[]{
                VisitorEntry._ID,
                VisitorEntry.COLUMN_NAME,
                VisitorEntry.COLUMN_BUSINESS_NAME,
                VisitorEntry.COLUMN_PHONE,
                VisitorEntry.COLUMN_DATE_CREATED
        };

        // Construct the loader
        CursorLoader cursorLoader = new CursorLoader(MainActivity.this,
                VisitorEntry.CONTENT_URI, // URI
                projectionFields, // projection fields
                null, // the selection criteria
                null, // the selection args
                null // the sort order
        );
        // Return the loader for use
        return cursorLoader;
    }

    // When the system finishes retrieving the Cursor through the CursorLoader,
    // a call to the onLoadFinished() method takes place.
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        // The swapCursor() method assigns the new Cursor to the adapter
        adapter.swapCursor(data);
    }

    // This method is triggered when the loader is being reset
    // and the loader data is no longer available. Called if the data
    // in the provider changes and the Cursor becomes stale.
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Clear the Cursor we were using with another call to the swapCursor()
        adapter.swapCursor(null);
    }
}
