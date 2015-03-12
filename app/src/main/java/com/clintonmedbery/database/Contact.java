package com.clintonmedbery.database;

/**
 * Created by clintonmedbery on 3/11/15.
 */
public class Contact {

    public int _id;
    public String name;
    public String phoneNumber;
    public String dirtySecret;

    public Contact(){}

    public Contact(String contactName, String contactPhoneNumber, String contactDirtySecret){

        this.name = contactName;
        this.phoneNumber = contactPhoneNumber;
        this.dirtySecret = contactDirtySecret;
    }



}
