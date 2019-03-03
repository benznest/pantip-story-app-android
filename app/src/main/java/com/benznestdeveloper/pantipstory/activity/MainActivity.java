package com.benznestdeveloper.pantipstory.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benznestdeveloper.pantipstory.MyCache;
import com.benznestdeveloper.pantipstory.MyConstant;
import com.benznestdeveloper.pantipstory.MyGlide;
import com.benznestdeveloper.pantipstory.MyNavigationMenu;
import com.benznestdeveloper.pantipstory.MyRoom;
import com.benznestdeveloper.pantipstory.MyTheme;
import com.benznestdeveloper.pantipstory.R;
import com.benznestdeveloper.pantipstory.dao.RoomDao;
import com.benznestdeveloper.pantipstory.dao.UserDao;
import com.benznestdeveloper.pantipstory.dialog.LoginDialog;
import com.benznestdeveloper.pantipstory.fragment.AboutFragment;
import com.benznestdeveloper.pantipstory.fragment.MainFragment;
import com.benznestdeveloper.pantipstory.fragment.RoomManagementFragment;
import com.benznestdeveloper.pantipstory.fragment.SettingFragment;
import com.benznestdeveloper.pantipstory.fragment.TagTopicListFragment;
import com.benznestdeveloper.pantipstory.fragment.TopicFragment;
import com.benznestdeveloper.pantipstory.fragment.TopicHistoryListFragment;
import com.benznestdeveloper.pantipstory.fragment.TopicListFragment;
import com.benznestdeveloper.pantipstory.fragment.TopicMainFragment;
import com.benznestdeveloper.pantipstory.fragment.TopicOfflineListFragment;
import com.benznestdeveloper.pantipstory.fragment.UserTopicMainFragment;
import com.benznestdeveloper.pantipstory.utils.MyUtils;
import com.benznestdeveloper.pantipstory.view.MyRowNavigationMenuView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static Activity activity;
    private LinearLayout llNavigationMenuContainer;
    private DrawerLayout drawer;
    private FrameLayout llNavUserContainer;
    private ImageView imgAvartar;
    private TextView tvName;
    private TextView tvUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setTheme(MyTheme.getCurrentTheme().getTheme());
        setContentView(R.layout.activity_main);

        initView();
        updateNavDrawer();
        navigateFragment(MyNavigationMenu.MENU_ID_HOME);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int topicId = bundle.getInt(MyConstant.KEY_TOPIC, -1);
            if (topicId != -1) {
                showTopicFragment(topicId);
            }

            String tag = bundle.getString(MyConstant.KEY_TOPIC_TAG, null);
            if (tag != null) {
                showTagTopicFragment(tag);
            }
        }
    }

    private void navigateFragment(int id) {
        if (id == MyNavigationMenu.MENU_ID_HOME) {
            setContent(MainFragment.newInstance(), MyConstant.TAG_HOME);
        } else if (id == MyNavigationMenu.MENU_ID_PROFILE) {
            setContent(UserTopicMainFragment.newInstance(), MyConstant.TAG_PROFILE);
        } else if (id == MyNavigationMenu.MENU_ID_ROOM_MANAGEMENT) {
            setContent(RoomManagementFragment.newInstance(), MyConstant.TAG_ROOM_MANAGEMENT);
        } else if (id == MyNavigationMenu.MENU_ID_ABOUT) {
            setContent(AboutFragment.newInstance(), MyConstant.TAG_ABOUT);
        } else if (id == MyNavigationMenu.MENU_ID_SETTING) {
            setContent(SettingFragment.newInstance(), MyConstant.TAG_SETTING);
        } else if (id == MyNavigationMenu.MENU_ID_TOPIC_OFFLINE) {
            setContent(TopicOfflineListFragment.newInstance(), MyConstant.TAG_TOPIC_OFFLINE);
        } else if (id == MyNavigationMenu.MENU_ID_TOPIC_HISTORY) {
            setContent(TopicHistoryListFragment.newInstance(), MyConstant.TAG_TOPIC_HISTORY);
        }
    }

    private void setContent(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_container, fragment, tag)
                .commit();
    }

    public void updateNavDrawer() {
        llNavigationMenuContainer.removeAllViews();
        ArrayList<MyNavigationMenu.MenuDao> listMenu = MyNavigationMenu.getListMenu();
        for (final MyNavigationMenu.MenuDao menu : listMenu) {
            if (MyCache.isLogin() && menu.getId() == MyNavigationMenu.MENU_ID_LOGIN) {
                continue;
            }
            if (!MyCache.isLogin() && menu.getId() == MyNavigationMenu.MENU_ID_LOGOUT) {
                continue;
            }
            if (!MyCache.isLogin() && menu.getId() == MyNavigationMenu.MENU_ID_PROFILE) {
                continue;
            }

            final MyRowNavigationMenuView row = new MyRowNavigationMenuView(MainActivity.this);
            row.setNavigationMenu(menu);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onNavigationMenuSelected(row, menu);
                }
            });
            llNavigationMenuContainer.addView(row);
        }

        if (MyCache.isLogin()) {
            llNavUserContainer.setVisibility(View.VISIBLE);
            UserDao user = MyCache.getUser(MainActivity.this);
            tvName.setText(user.getName());
            tvUserId.setText("" + user.getId());
            MyGlide.load(MainActivity.this, imgAvartar, user.getUrlAvartar());
        } else {
            llNavUserContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(MyConstant.TAG_HOME);
                if (fragment != null) {
                    super.onBackPressed();
                } else {
                    navigateFragment(MyNavigationMenu.MENU_ID_HOME);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
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

    public void showTopicMainFragment(RoomDao room) {
        room.setCount(room.getCount() + 1);
        MyRoom.update(room);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container, TopicMainFragment.newInstance(room))
                .addToBackStack("")
                .commit();
    }

    public void showTopicListFragment(RoomDao room) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container, TopicListFragment.newInstance(room))
                .addToBackStack("")
                .commit();
    }

    public void showTopicFragment(int topicId) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container, TopicFragment.newInstance(topicId))
                .addToBackStack("")
                .commit();
    }

    public void showTopicFragment(int topicId, boolean isLoadOffline) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container, TopicFragment.newInstance(topicId, isLoadOffline))
                .addToBackStack("")
                .commit();
    }

    public void showTagTopicFragment(String tag) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.content_container, TagTopicListFragment.newInstance(tag))
                .addToBackStack("")
                .commit();
    }


    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        llNavigationMenuContainer = (LinearLayout) findViewById(R.id.ll_navigation_menu_container);

//        llNavContainer = (LinearLayout) findViewById(R.id.ll_nav_container);
        llNavUserContainer = (FrameLayout) findViewById(R.id.ll_nav_user_container);
        imgAvartar = (ImageView) findViewById(R.id.img_avartar);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvUserId = (TextView) findViewById(R.id.tv_user_id);
    }

    private void onNavigationMenuSelected(MyRowNavigationMenuView view, MyNavigationMenu.MenuDao menu) {
        drawer.closeDrawers();

        if (menu.getId() == MyNavigationMenu.MENU_ID_HOME) {
            navigateFragment(MyNavigationMenu.MENU_ID_HOME);
        } else if (menu.getId() == MyNavigationMenu.MENU_ID_LOGIN) {
            LoginDialog dialog = new LoginDialog(MainActivity.this);
            dialog.setOnLoginListener(new LoginDialog.OnLoginListener() {
                @Override
                public void onLogin(UserDao user) {
                    updateNavDrawer();
                    MyUtils.showSnackBarSuccess(MainActivity.this, drawer, "เข้าสู่ระบบสำเร็จแล้ว");
                }
            });
            dialog.show();

        } else if (menu.getId() == MyNavigationMenu.MENU_ID_PROFILE) {
            navigateFragment(menu.getId());
        } else if (menu.getId() == MyNavigationMenu.MENU_ID_ROOM_MANAGEMENT) {
            navigateFragment(menu.getId());
        } else if (menu.getId() == MyNavigationMenu.MENU_ID_TOPIC_OFFLINE) {
            navigateFragment(menu.getId());
        } else if (menu.getId() == MyNavigationMenu.MENU_ID_TOPIC_HISTORY) {
            navigateFragment(menu.getId());
        } else if (menu.getId() == MyNavigationMenu.MENU_ID_SETTING) {
            navigateFragment(menu.getId());
        } else if (menu.getId() == MyNavigationMenu.MENU_ID_ABOUT) {
            navigateFragment(menu.getId());
        } else if (menu.getId() == MyNavigationMenu.MENU_ID_LOGOUT) {
            MyCache.setUser(MainActivity.this, null);
            updateNavDrawer();
            MyUtils.showSnackBarSuccess(MainActivity.this, drawer, "ออกจากระบบแล้ว");
        }
    }

    public static Activity getMainActivity() {
        return activity;
    }
}
