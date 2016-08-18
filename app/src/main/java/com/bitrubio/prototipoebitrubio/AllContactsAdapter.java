package com.bitrubio.prototipoebitrubio;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitrubio.prototipoebitrubio.Entidades.ContactVO;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Mario on 03/02/2016.
 */
public class AllContactsAdapter extends RecyclerView.Adapter<AllContactsAdapter.ContactViewHolder> {

    private List<ContactVO> contactVOList;
    private FragmentActivity mContext;
    public static OnItemClickListener mItemClickListener;
    Typeface tf;
    private String strNombre;
    private String TAG = getClass().getName();




    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public AllContactsAdapter(List<ContactVO> contactVOList, FragmentActivity mContext, Typeface tf) {
        this.contactVOList = contactVOList;
        this.mContext = mContext;
        this.tf = tf;
    }

    @Override
    public long getItemId(int position) {
        return contactVOList.get(position).getID();
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_contact_view, null);
        ContactViewHolder contactViewHolder = new ContactViewHolder(view);
        return contactViewHolder;
    }


    @Override
    public void onBindViewHolder(final ContactViewHolder holder, final int position) {
        final ContactVO contactVO = contactVOList.get(position);
        holder.getTvContactName().setText(contactVO.getContactName());


        if (contactVO.getContactUri() != null) {
            Picasso.with(mContext).load(contactVO.getContactUri()).into(holder.getIvContactImage());
        } else {
            strNombre = contactVO.getContactName();
            String strLetra = String.valueOf(strNombre.charAt(0));
            cambioLetraColor(strLetra, holder);
        }
        if (contactVO.getContactNum() > 1) {
            holder.getCheckBox().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle a = new Bundle();
                    FragmentManager dialogFrag = mContext.getFragmentManager();
                    ContactoNumero dialog = new ContactoNumero();
                    a.putString("contactoID", "" + contactVO.getID());
                    a.putString("contactoNom", contactVO.getContactName());
                    dialog.setArguments(a);
                    dialog.show(dialogFrag, "dialog number");

                }
            });
        } else {
            holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked == true) {
                        holder.getCheckBox().setBackground(mContext.getResources().getDrawable(R.drawable.ic_opcion_on));
                    } else {
                        holder.getCheckBox().setBackground(mContext.getResources().getDrawable(R.drawable.ic_opcion_off));
                    }
                }

            });
        }


    }

    @Override
    public int getItemCount() {
        return contactVOList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void cambioLetraColor(String letra, ContactViewHolder holder) {

        if (letra.equals("A") || letra.equals("J") || letra.equals("R")) {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_naranja));
            holder.txtLetra.setTextColor(mContext.getResources().getColor(R.color.white));
        } else if (letra.equals("B") || letra.equals("K") || letra.equals("S")) {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_verde1));
        } else if (letra.equals("C") || letra.equals("L") || letra.equals("T")) {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_verde2));
        } else if (letra.equals("D") || letra.equals("M") || letra.equals("U")) {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_verde3));
        } else if (letra.equals("E") || letra.equals("N") || letra.equals("V")) {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_verde4));
        } else if (letra.equals("F") || letra.equals("Ñ") || letra.equals("W")) {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_azul));
        } else if (letra.equals("G") || letra.equals("O") || letra.equals("X")) {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_azul1));
        } else if (letra.equals("H") || letra.equals("P") || letra.equals("Y")) {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_lila));
        } else if (letra.equals("I") || letra.equals("Q") || letra.equals("Z")) {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_rojo));
        } else {
            holder.txtLetra.setText(letra);
            holder.tvContactName.setTypeface(tf);
            holder.ivContactImage.setBackground(mContext.getResources().getDrawable(R.drawable.bg_contacto_azul1));
        }

    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivContactImage;
        TextView tvContactName, txtLetra;
        CheckBox checkBox;

        public ImageView getIvContactImage() {
            return ivContactImage;
        }

        public TextView getTvContactName() {
            return tvContactName;
        }

        public TextView getTxtLetra() {
            return txtLetra;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public ContactViewHolder(View itemView) {
            super(itemView);
            ivContactImage = (ImageView) itemView.findViewById(R.id.ivContactImage);
            txtLetra = (TextView) itemView.findViewById(R.id.txtletraContacto);
            tvContactName = (TextView) itemView.findViewById(R.id.tvContactName);
            checkBox = (CheckBox) itemView.findViewById(R.id.chkSelected);

            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, (int) getItemId());
            }
            return;
        }


    }

}