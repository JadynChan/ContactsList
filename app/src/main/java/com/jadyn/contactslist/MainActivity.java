package com.jadyn.contactslist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jadyn.contactslist.contact.ChineseToEnglish;
import com.jadyn.contactslist.contact.CompareSort;
import com.jadyn.contactslist.contact.SideBarView;
import com.jadyn.contactslist.contact.User;
import com.jadyn.contactslist.contact.UserAdapter;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends ActionBarActivity implements SideBarView.LetterSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();



    }

    ListView mListview;
    UserAdapter mAdapter;
    TextView mTip;
    private void init() {
        mListview = (ListView) findViewById(R.id.listview);
        SideBarView sideBarView = (SideBarView) findViewById(R.id.sidebarview);
        String[] contactsArray = getResources().getStringArray(R.array.data);
        String[] headArray = getResources().getStringArray(R.array.head);
        mTip = (TextView) findViewById(R.id.tip);

        int length = contactsArray.length;
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            User user = new User();
            user.setName(contactsArray[i]);
            String firstSpell = ChineseToEnglish.getFirstSpell(contactsArray[i]);
            String substring = firstSpell.substring(0, 1).toUpperCase();
            if(substring.matches("[A-Z]")){
                user.setLetter(substring);
            }else {
                user.setLetter("#");
            }
            users.add(user);
        }

        for (int i = 0; i < headArray.length; i++) {
            User user = new User();
            user.setName(headArray[i]);
            user.setLetter("@");
            users.add(user);
        }

        Collections.sort(users, new CompareSort());

        mAdapter = new UserAdapter(this);
        mAdapter.setData(users);
        mListview.setAdapter(mAdapter);

        sideBarView.setOnLetterSelectListen(this);

    }









    @Override
    public void onLetterSelected(String letter) {
        setListviewPosition(letter);
        mTip.setText(letter);
        mTip.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLetterChanged(String letter) {
        setListviewPosition(letter);
        mTip.setText(letter);
    }

    @Override
    public void onLetterReleased(String letter) {
        mTip.setVisibility(View.GONE);
    }

    private void setListviewPosition(String letter){
        int firstLetterPosition = mAdapter.getFirstLetterPosition(letter);
        if(firstLetterPosition != -1){
            mListview.setSelection(firstLetterPosition);
        }
    }

}
