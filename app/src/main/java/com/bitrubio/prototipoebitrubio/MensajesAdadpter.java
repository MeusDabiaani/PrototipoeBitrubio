package com.bitrubio.prototipoebitrubio;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bitrubio.prototipoebitrubio.Bitrubian.ConectaServidor;
import com.bitrubio.prototipoebitrubio.Bitrubian.Mensajes;
import com.bitrubio.prototipoebitrubio.Bitrubian.ProfileActivity;
import com.ogaclejapan.arclayout.ArcLayout;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 11/01/2016.
 */
public class MensajesAdadpter extends RecyclerView.Adapter<MensajesAdadpter.ViewHolder>  {
    private ArrayList<Mensajes> data;
    private OnItemClickListener mItemClickListener;
    private Context context;
    private Mensajes registros;
    ConectaServidor servidor ;
    String TAG = getClass().getSimpleName();

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public MensajesAdadpter(ArrayList<Mensajes> modelData, Context context) {
        data = modelData;
        this.context = context;
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    @Override
    public MensajesAdadpter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.content_recycler_home, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MensajesAdadpter.ViewHolder holder, final int position) {
        registros = data.get(position);
        servidor = new ConectaServidor();
        String UrlServer = servidor.getUrl();
        //String ruta = "http://www.meustech.com:8080/bitrubio/movil/fotosPerfil/" + data.get(position).getIdContacto() + ".jpg";
        Log.e(TAG,"id"+data.get(position).getIdContacto());
        String ruta = UrlServer+"fotosPerfil/" + data.get(position).getIdContacto() + ".jpg";

        Picasso.with(context).load(ruta).error(R.drawable.ic_sin_foto).into(holder.getImgUser());
        final String _idContacto;
        _idContacto = registros.getIdContacto();
        holder.getTxt_personName().setText(registros.getNommbre());
        holder.getTxt_personMsg().setText(registros.getMensaje());
        holder.getTxt_personFecha().setText(registros.getFecha());
        holder.getImgUser().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("idContacto", _idContacto);
                context.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {
        return data.size();
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txt_personName;
        private TextView txt_personMsg;
        private TextView txt_personFecha;
        private ImageView imgUser;
        private VideoView videoPost;
        private TextView btn_comnetar;
        Typeface tf;
        View menuLayout;
        ArcLayout arcLayout;
        Toast toast = null;
        public ImageView getImgUser() {
            return imgUser;
        }

        public TextView getTxt_personName() {
            return txt_personName;
        }

        public TextView getTxt_personMsg() {
            return txt_personMsg;
        }

        public TextView getTxt_personFecha() {
            return txt_personFecha;
        }

        public VideoView getVideoPost() {
            return videoPost;
        }

        public TextView getBtn_comnetar() {
            return btn_comnetar;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            txt_personName = (TextView) itemView.findViewById(R.id.person_name);
            txt_personMsg = (TextView) itemView.findViewById(R.id.person_msg);
            txt_personFecha = (TextView) itemView.findViewById(R.id.person_fecha);
            imgUser = (ImageView) itemView.findViewById(R.id.person_foto);
            btn_comnetar = (TextView) itemView.findViewById(R.id.agregar_comentario);
           // videoPost = (VideoView) itemView.findViewById(R.id.video_post);
            tf = Typeface.createFromAsset(context.getAssets(), "fonts/avenir-light.ttf");
            btn_comnetar.setTypeface(tf);
            menuLayout = itemView.findViewById(R.id.menu_layout);
            arcLayout = (ArcLayout) itemView.findViewById(R.id.arc_layout);

            for (int i = 0, size = arcLayout.getChildCount(); i < size; i++) {
                arcLayout.getChildAt(i).setOnClickListener(this);
            }
            btn_comnetar.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    /*if (v.getId() == R.id.agregar_comentario) {
                        onFabClick(v);
                        return;
                    }

                    if (v instanceof Button) {
                        showToast((Button) v);
                    }*/
                }
            });

        }
        private void showToast(Button btn) {
            if (toast != null) {
                toast.cancel();
            }

            String text = "Clicked: " + btn.getText();
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            toast.show();

        }

        private void onFabClick(View v) {
            if (v.isSelected()) {
                hideMenu();
            } else {
                showMenu();
            }
            v.setSelected(!v.isSelected());
        }
        @SuppressWarnings("NewApi")
        private void showMenu() {
            menuLayout.setVisibility(View.VISIBLE);

            List<Animator> animList = new ArrayList<>();

            for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
                animList.add(createShowItemAnimator(arcLayout.getChildAt(i)));
            }

            AnimatorSet animSet = new AnimatorSet();
            animSet.setDuration(400);
            animSet.setInterpolator(new OvershootInterpolator());
            animSet.playTogether(animList);
            animSet.start();
        }

        @SuppressWarnings("NewApi")
        private void hideMenu() {

            List<Animator> animList = new ArrayList<>();

            for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
                animList.add(createHideItemAnimator(arcLayout.getChildAt(i)));
            }

            AnimatorSet animSet = new AnimatorSet();
            animSet.setDuration(400);
            animSet.setInterpolator(new AnticipateInterpolator());
            animSet.playTogether(animList);
            animSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    menuLayout.setVisibility(View.INVISIBLE);
                }
            });
            animSet.start();

        }
        private Animator createShowItemAnimator(View item) {

            float dx = btn_comnetar.getX() - item.getX();
            float dy = btn_comnetar.getY() - item.getY();

            item.setRotation(0f);
            item.setTranslationX(dx);
            item.setTranslationY(dy);

            Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                    item,
                    AnimatorUtils.rotation(0f, 720f),
                    AnimatorUtils.translationX(dx, 0f),
                    AnimatorUtils.translationY(dy, 0f)
            );

            return anim;
        }

        private Animator createHideItemAnimator(final View item) {
            float dx = btn_comnetar.getX() - item.getX();
            float dy = btn_comnetar.getY() - item.getY();

            Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                    item,
                    AnimatorUtils.rotation(720f, 0f),
                    AnimatorUtils.translationX(0f, dx),
                    AnimatorUtils.translationY(0f, dy)
            );

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    item.setTranslationX(0f);
                    item.setTranslationY(0f);
                }
            });

            return anim;
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
