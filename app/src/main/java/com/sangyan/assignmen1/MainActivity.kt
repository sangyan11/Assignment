package com.sangyan.assignmen1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter: UserAdapter
    private lateinit var list : ArrayList<User>
    private lateinit var dbRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         list = arrayListOf<User>()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dbRef = FirebaseDatabase.getInstance().getReference("users")
        dbRef.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               list.clear()
                if (snapshot.exists()){
                    for(userSnap in snapshot.children){
                        val userData = userSnap.getValue(User::class.java)
                        list?.add(userData!!)
                    }
                    val userAdapter = UserAdapter(list)
                    recyclerView.adapter = userAdapter

                }



            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "onCancelled: ${error.message}")
            }

        })


    }



}
