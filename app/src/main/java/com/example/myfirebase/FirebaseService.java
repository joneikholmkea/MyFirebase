package com.example.myfirebase;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseService {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<String> items = new ArrayList<>();
    private MainActivity mainActivity;
    public FirebaseService(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        startListener();
    }
    public void addNote(String text){
        DocumentReference ref = db.collection("notes").document();
        Map<String,String> map = new HashMap<>();
        map.put("text", text);
        ref.set(map);
    }

    public void editNote(String text, String id){
        DocumentReference ref = db.collection("notes").document(id);
        Map<String,String> map = new HashMap<>();
        map.put("text", text);
        ref.set(map);
    }

    public void deleteNote(String id){
        DocumentReference ref = db.collection("notes").document(id);
        ref.delete();
    }

    public void startListener(){
        System.out.println("start called");
        db.collection("notes").addSnapshotListener((snap,error) ->{
            items.clear();
            for(DocumentSnapshot doc: snap.getDocuments()){
                //System.out.println("Doc:" + doc.getData().get("text") + doc.getId());
                items.add(doc.getData().get("text").toString());
            }
            mainActivity.adapter.notifyDataSetChanged(); // will make the adapter reload
        });
    }
}
