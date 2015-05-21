package com.apress.gerber.reminders;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static com.apress.gerber.reminders.RemindersDbAdapter.*;


public class RemindersActivity extends ActionBarActivity {

    private static final int EDIT = 0;
    private static final int DELETE = 1;

    private ListView mListView;
    private RemindersDbAdapter mDbAdapter;
    private RemindersSimpleCursorAdapter mCursorAdapter;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
        mListView = (ListView) findViewById(R.id.reminders_list_view);
        mListView.setDivider(null);
        mDbAdapter = new RemindersDbAdapter(this);
        mDbAdapter.open();
        if (savedInstanceState == null) {
            //Clean all data
            mDbAdapter.deleteAllReminders();
            //Add some data
            insertSomeReminders();
        }

        Cursor cursor = mDbAdapter.fetchAllReminders();

        //from columns defined in the db
        String[] from = new String[]{
                COL_CONTENT
        };

        //to the ids of views in the layout
        int[] to = new int[]{
                R.id.row_text
        };

        mCursorAdapter = new RemindersSimpleCursorAdapter(
                //context
                RemindersActivity.this,
                //the layout of the row
                R.layout.reminders_row,
                //cursor
                cursor,
                //from columns defined in the db
                from,
                //to the ids of views in the layout
                to,
                //flag - not used
                0);


        //the cursorAdapter (controller) is now updating the listView (view) with data from the db (model)
        mListView.setAdapter(mCursorAdapter);
        registerForContextMenu(mListView);
    }

    private void insertSomeReminders() {
        mDbAdapter.createReminder("Buy Learn Android Studio", true);
        mDbAdapter.createReminder("Send Dad birthday gift", false);
        mDbAdapter.createReminder("Dinner at the Gage on Friday", false);
        mDbAdapter.createReminder("String squash racket", false);
        mDbAdapter.createReminder("Shovel and salt walkways", false);
        mDbAdapter.createReminder("Prepare Advanced Android syllabus", true);
        mDbAdapter.createReminder("Buy new office chair", false);
        mDbAdapter.createReminder("Call Auto-body shop for quote", false);
        mDbAdapter.createReminder("Renew membership to club", false);
        mDbAdapter.createReminder("Buy new Galaxy Android phone", true);
        mDbAdapter.createReminder("Sell old Android phone - auction", false);
        mDbAdapter.createReminder("Buy new paddles for kayaks", false);
        mDbAdapter.createReminder("Call accountant about tax returns", false);
        mDbAdapter.createReminder("Buy 300,000 shares of Google", false);
        mDbAdapter.createReminder("Call the Dalai Lama back", true);
    }

    public void insert(String str, boolean b){
        mDbAdapter.createReminder(str, b);
    }

    public void update(String description, boolean importance, int id){
        Reminder reminder = new Reminder(id, description, importance?1:0);
        mDbAdapter.updateReminder(reminder);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminders, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_new:
                //create new Reminder
                Log.d(getLocalClassName(),"create new Reminder");
                CustomizedDialog cdNew = new CustomizedDialog(this, "NEW",R.color.green, -1);
                cdNew.show();
                return true;
            case R.id.add_icon:
                CustomizedDialog cdAdd = new CustomizedDialog(this, "NEW", R.color.green, -1);
                cdAdd.show();
                return true;
            case R.id.action_exit:
                finish();
                return true;
            default:
                return false;
        }
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info){
        super.onCreateContextMenu(menu,v, info);

        menu.add(0, EDIT, 0, "EDIT");
        menu.add(0, DELETE, 0, "DELETE");

        position = ((AdapterView.AdapterContextMenuInfo) info).position;
    }

    public boolean onContextItemSelected(MenuItem item){
        switch(item.getItemId()){
            case DELETE:
                deleteSelectedReminder();
                break;
            case EDIT:
                updateSelectedReminder();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    public void updateList(){
        Cursor c = mDbAdapter.fetchAllReminders();
        mCursorAdapter.changeCursor(c);
        mCursorAdapter.notifyDataSetChanged();
    }
    private int getSelectedReminderId(){
        Cursor cursor = (Cursor) (mListView.getItemAtPosition(position));
        return cursor.getInt(cursor.getColumnIndex(COL_ID));
    }

    private void deleteSelectedReminder(){
        mDbAdapter.deleteReminderById(getSelectedReminderId());
        updateList();
    }

    private void updateSelectedReminder(){
        CustomizedDialog cd = new CustomizedDialog(this, "EDIT", R.color.blue, getSelectedReminderId());
        cd.show();
        Reminder reminder = mDbAdapter.fetchReminderById(getSelectedReminderId());
        cd.setTextContent(reminder.getContent(), reminder.getImportant() > 0);
    }

}
