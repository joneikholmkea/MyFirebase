package com.example.myfirebase;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirebaseService {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseService() {
        startListener();
    }
    public void addNote(String text){
        DocumentReference ref = db.collection("notes").document();
        Map<String,String> map = new HashMap<>();
        map.put("text", text);
        ref.set(map);
    }
    public void startListener(){
        System.out.println("start called");
        db.collection("notes").addSnapshotListener((snap,error) ->{
            for(DocumentSnapshot doc: snap.getDocuments()){
                System.out.println("Doc:" + doc.getData().get("text"));
            }
        });
    }
}
