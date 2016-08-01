package com.bitrubio.prototipoebitrubio;

import android.net.Uri;

/**
 * Created by Mario on 03/02/2016.
 */
public class ContactVO {
    private int ID;
    private String ContactName;
    private String ContactNumber;
    private String ContactUri;
    private int ContactNum;

    public ContactVO(int ID, String contactName, String contactNumber, String contactUri,int contactNum) {
        this.ID = ID;
        ContactName = contactName;
        ContactNumber = contactNumber;
        ContactUri = contactUri;
        ContactNum = contactNum;
    }

    public ContactVO() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public String getContactUri() {
        return ContactUri;
    }

    public void setContactUri(String contactUri) {
        ContactUri = contactUri;
    }

    public int getContactNum() {
        return ContactNum;
    }

    public void setContactNum(int contactNum) {
        ContactNum = contactNum;
    }
}
