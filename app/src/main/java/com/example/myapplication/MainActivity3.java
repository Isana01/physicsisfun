package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class MainActivity3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private long backPressedtime;
    private Toast backtoast;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView accountEmail;
    TextView accountName;
    MenuItem signoutButton;
    Customlistview customlistview;
    ListView lst;
    String[] fruitname = { "Chapter 1","Chapter 2","Chapter 3","Chapter 4","Chapter 5"};
    String[] desc = { "Forces","Vectors","Oscillations","Quantum Physics","Space"};
    Integer[] imgid = {R.drawable.force,R.drawable.vect,R.drawable.pend,R.drawable.nma,R.drawable.galaxy};
    RecyclerView chaptersView;

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

//        if(backPressedtime + 2000 > System.currentTimeMillis()){
//            backtoast.cancel();
//            super.onBackPressed();
//            return;
//        }
//        else{
//            backtoast =Toast.makeText(getBaseContext(),"Press back again to exit",Toast.LENGTH_SHORT);
//            backtoast.show();
//
//        }
//        backPressedtime = System.currentTimeMillis();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
//      getSupportActionBar().hide();
        final ActionBar actionBar = getSupportActionBar();

        signoutButton = (MenuItem)findViewById(R.id.nav_signout);


        Customlistview adapter = new
                Customlistview(MainActivity3.this, fruitname ,imgid,desc);
        lst=(ListView)findViewById(R.id.list);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(MainActivity3.this,ChapterDetailView.class);
                i.putExtra("chapter", fruitname[position]);
                i.putExtra("desc", desc[position]);
                i.putExtra("image",imgid[position]);

                startActivity(i);
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_mail);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
//        signoutButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                return false;
//            }
//        });
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        drawerLayout.addDrawerListener(toggle);
        View headerView  = navigationView.getHeaderView(0);
        accountEmail = headerView.findViewById(R.id.txt13);
        accountName = headerView.findViewById(R.id.txt12);

        accountEmail.setText(firebaseUser.getEmail());
        accountName.setText(firebaseUser.getDisplayName());

//        chaptersView = findViewById(R.id.chaptersRecyclerView);
//
//        ArrayList<ChapterItemViewModel> model = new ArrayList<>();
//        for (int i = 0 ; i < 5; i++) model.add(new ChapterItemViewModel("Chapter 1","Chapter 1 Desc"));
//
//        ChapterRecyclerAdapter adapter = new ChapterRecyclerAdapter(model);
//        chaptersView.setLayoutManager(new LinearLayoutManager(this));
//        chaptersView.setHasFixedSize(true);
//        chaptersView.setAdapter(adapter);
//        toggle.syncState();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater =  getMenuInflater();
       inflater.inflate(R.menu.main_menu,menu);

       signoutButton = menu.findItem(R.id.nav_signout);

       return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_signout) {
            logOut();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    private void logOut() {
        firebaseAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {
        if(firebaseAuth.getCurrentUser() == null) {
            Intent loginIntent = new Intent(MainActivity3.this, MainActivity.class);
            startActivity(loginIntent);
            finish();
        }

    }


}