package com.mobdeve.s14.group4;

import com.google.firebase.database.DataSnapshot;

public interface CallbackListener {
    void onSuccess(User user);
    void onFailure();
}
