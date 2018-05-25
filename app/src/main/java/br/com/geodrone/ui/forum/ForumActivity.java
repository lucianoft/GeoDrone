package br.com.geodrone.ui.forum;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.geodrone.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForumActivity extends AppCompatActivity {

    private static final String TAG = ForumActivity.class.getName();
    public static final String MESSAGES_CHILD = "messages";

    private static final int SIGN_IN_REQUEST_CODE = 111;
    private FirebaseListAdapter<ChatMessage> adapter;
    private ListView listView;
    private String loggedInUserName = "";

    @BindView(R.id.recyclerView_forum) RecyclerView recyclerView;

    ForumAdapter forumAdapter;
    private FirebaseDatabase database = null;
    private DatabaseReference myRef = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        ButterKnife.bind(this);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(MESSAGES_CHILD);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        forumAdapter = new ForumAdapter();
        forumAdapter.setActivity(this);
        recyclerView.setAdapter(forumAdapter);

        //find views by Ids
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final EditText input = (EditText) findViewById(R.id.input);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .build(), SIGN_IN_REQUEST_CODE);
        } else {
            // User is already signed in, show list of messages
            setupConnection();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input.getText().toString().trim().equals("")) {
                    Toast.makeText(ForumActivity.this, "Please enter some texts!", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child(MESSAGES_CHILD)
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                    FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                                    FirebaseAuth.getInstance().getCurrentUser().getUid())
                            );
                    input.setText("");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if (item.getItemId() == R.id.menu_sign_out) {
            AuthUI.getInstance().signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(ForumActivity.this, "You have logged out!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
        }*/
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Signed in successful!", Toast.LENGTH_LONG).show();
                setupConnection();
            } else {
                Toast.makeText(this, "Sign in failed, please try again later", Toast.LENGTH_LONG).show();

                // Close the app
                finish();
            }
        }
    }


    public String getLoggedInUserName() {
        return loggedInUserName;
    }


    private void setupConnection() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mReference = database.getReference().child(MESSAGES_CHILD);

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,"SUCCESS!");
                handleReturn(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG,"ERROR: " + databaseError.getMessage());
                Toast.makeText(ForumActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleReturn(DataSnapshot dataSnapshot) {
        loggedInUserName = FirebaseAuth.getInstance().getCurrentUser().getUid();

        forumAdapter.clearData();

        int i = 0;
        for(DataSnapshot item : dataSnapshot.getChildren()) {
            ++i;
            ChatMessage data = item.getValue(ChatMessage.class);

            forumAdapter.addData(data);
        }

        forumAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(i);
    }
}
