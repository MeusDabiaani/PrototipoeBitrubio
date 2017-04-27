package com.bitrubio.prototipoebitrubio;


import android.content.ContentResolver;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bitrubio.prototipoebitrubio.Entidades.ContactVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 02/02/2016.
 */
public class Tab_familiares extends Fragment {
    public RecyclerView rvContacts;
    public AllContactsAdapter contactAdapter;
    private ImageButton _btnAceptarContactos ;
    Typeface tf;
    String TAG = getClass().getName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_all_contacts, container, false);
        rvContacts = (RecyclerView) v.findViewById(R.id.rvContacts);
        _btnAceptarContactos = (ImageButton) v.findViewById(R.id.fab_aceptar_contactos);

         tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/avenir-light.ttf");

        _btnAceptarContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Aqui deberiamos guardar los contactos en alguna parte BDinterna o servidor */
                Intent intent = new Intent(getActivity(),SignupContactos.class);
                startActivity(intent);
            }
        });

        getAllContacts();

        return v;
    }
    private void getAllContacts() {
        List<ContactVO> contactVOList = new ArrayList();
        ContactVO contactVO;
        ContentResolver contentResolver = getActivity().getContentResolver();


        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME +" ASC");

        if (cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String photoID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID));
                    String photoURI = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_URI));
                    contactVO = new ContactVO();
                    contactVO.setID(Integer.parseInt(id));
                    contactVO.setContactName(name);
                    contactVO.setContactUri(photoURI);
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null);
                    contactVO.setContactNum(phoneCursor.getCount());

                        if (phoneCursor.moveToNext()) {
                            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            int type = phoneCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));

                            contactVO.setContactNumber(phoneNumber);
                        }

                    phoneCursor.close();
                    contactVO.setContactUri(photoURI);

                    contactVOList.add(contactVO);

                }
            }
            contactAdapter = new AllContactsAdapter(contactVOList, getActivity(),tf);
            rvContacts.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvContacts.setAdapter(contactAdapter);

        }
    }

}
