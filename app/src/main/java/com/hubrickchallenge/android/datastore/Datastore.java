package com.hubrickchallenge.android.datastore;


public class Datastore {

    private final DatastoreStoreActions datastoreStoreActions;
    private final DatastoreGetActions datastoreGetActions;
    private final DatastoreAddActions datastoreAddActions;
    private final DatastoreUpdateActions datastoreUpdateActions;

    public Datastore(DatastoreStoreActions datastoreStoreActions, DatastoreGetActions datastoreGetActions,
                     DatastoreAddActions datastoreAddActions, DatastoreUpdateActions datastoreUpdateActions) {
        this.datastoreStoreActions = datastoreStoreActions;
        this.datastoreGetActions = datastoreGetActions;
        this.datastoreAddActions = datastoreAddActions;
        this.datastoreUpdateActions = datastoreUpdateActions;
    }

    public StoreActions store() {
        return datastoreStoreActions;
    }

    public GetActions get() {
        return datastoreGetActions;
    }

    public AddActions add() {
        return datastoreAddActions;
    }

    public UpdateActions update() {
        return datastoreUpdateActions;
    }

}
