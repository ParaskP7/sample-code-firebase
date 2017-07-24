package com.hubrickchallenge.android.tools.dagger.modules;

import com.hubrickchallenge.android.App;
import com.hubrickchallenge.android.datastore.Datastore;
import com.hubrickchallenge.android.datastore.DatastoreAddActions;
import com.hubrickchallenge.android.datastore.DatastoreGetActions;
import com.hubrickchallenge.android.datastore.DatastoreStoreActions;
import com.hubrickchallenge.android.datastore.DatastoreUpdateActions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DatastoreModule {

    @Provides
    @Singleton
    Realm provideRealm(App application) {
        return application.getDefaultRealmInstance();
    }

    @Provides
    @Singleton
    Datastore provideDatastore(DatastoreStoreActions datastoreStoreActions, DatastoreGetActions datastoreGetActions,
                               DatastoreAddActions datastoreAddActions, DatastoreUpdateActions datastoreUpdateActions) {
        return new Datastore(datastoreStoreActions, datastoreGetActions, datastoreAddActions, datastoreUpdateActions);
    }

    @Provides
    @Singleton
    DatastoreStoreActions provideDatastoreStoreActions(DatastoreGetActions datastoreGetActions, DatastoreAddActions datastoreAddActions) {
        return new DatastoreStoreActions(datastoreAddActions, datastoreGetActions);
    }

    @Provides
    @Singleton
    DatastoreGetActions provideDatastoreGetActions(Realm realm) {
        return new DatastoreGetActions(realm);
    }

    @Provides
    @Singleton
    DatastoreAddActions provideDatastoreAddActions(Realm realm) {
        return new DatastoreAddActions(realm);
    }

    @Provides
    @Singleton
    DatastoreUpdateActions provideDatastoreUpdateActions(Realm realm) {
        return new DatastoreUpdateActions(realm);
    }

}
