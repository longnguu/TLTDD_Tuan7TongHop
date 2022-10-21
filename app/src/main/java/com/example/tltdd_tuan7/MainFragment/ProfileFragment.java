package com.example.tltdd_tuan7.MainFragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tltdd_tuan7.Class.BTP;
import com.example.tltdd_tuan7.Class.User;
import com.example.tltdd_tuan7.MainActivity;
import com.example.tltdd_tuan7.R;
import com.example.tltdd_tuan7.Signin;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Uri uriimgt;
    Bitmap bitmap;
    User usss;

    TextView namepf,sdtpf,usernamepf,emailpf,passpf,nspf,diachipf,cmdpf;
    Button btupdate;
    ImageView avtpf;
    User user;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        user = BTP.user;
        AnhXa(view);
        namepf.setText("  "+user.getTen());
        usernamepf.setText(user.getUsername());
        sdtpf.setText(user.getSdt());
        passpf.setText(user.getPassword());
        nspf.setText(user.getNgaysinh());
        emailpf.setText(user.getEmail());
        if (user.getImage()!=null){
        InputStream is = new ByteArrayInputStream(user.getImage());
        bitmap = BitmapFactory.decodeStream(is);
        avtpf.setImageBitmap(bitmap);}
        diachipf.setText(user.getDiachi());
        cmdpf.setText(user.getCmnd());

        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usss=new User(namepf.getText().toString(),nspf.getText().toString(),sdtpf.getText().toString(),emailpf.getText().toString(),cmdpf.getText().toString(),usernamepf.getText().toString(),passpf.getText().toString());

                BitmapDrawable bitmapDrawable = (BitmapDrawable) avtpf.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhAnh = byteArrayOutputStream.toByteArray();
                hinhAnh=imagemTratada(hinhAnh);
                usss.setImage(hinhAnh);
                usss.setDiachi(diachipf.getText().toString());
                Boolean insertdb = Signin.database.update_img(usss);
                BTP.user.setTen(usss.getTen());
                BTP.user.setCmnd(usss.getCmnd());
                BTP.user.setEmail(usss.getCmnd());
                BTP.user.setUsername(usss.getUsername());
                BTP.user.setNgaysinh(usss.getNgaysinh());
                BTP.user.setPassword(usss.getPassword());
                BTP.user.setSdt(usss.getSdt());
                BTP.user.setImage(usss.getImage());
                BTP.user.setDiachi(usss.getDiachi());
                System.out.println(BTP.user.getSdt());
                if (insertdb==true)
                Toast.makeText(getContext(),"Đã cập nhật",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        });


        avtpf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_custom);
                dialog.show();
                Button btchon = (Button) dialog.findViewById(R.id.chonanhavt);
                Button btxem = (Button) dialog.findViewById(R.id.xemanhavt);
                btchon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        CapQuyenCA();
                        dialog.dismiss();
                    }
                });
                btxem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Dialog dialog1 = new Dialog(getActivity());
                        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog1.setContentView(R.layout.dialog_custom1);
                        dialog1.show();
                        dialog.dismiss();
                        ImageView imgz = (ImageView) dialog1.findViewById(R.id.imgzoom);
                        imgz.setImageBitmap(bitmap);
                        Button butok = (Button) dialog1.findViewById(R.id.Buttonxemanhok);
                        butok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog1.dismiss();
                            }
                        });
                    }
                });

            }
        });






    }
    private void AnhXa(@NonNull View view){
        namepf = (TextView) view.findViewById(R.id.namepf);
        sdtpf = (TextView) view.findViewById(R.id.sdtpf);
        usernamepf = (TextView) view.findViewById(R.id.usernamepf);
        passpf = (TextView) view.findViewById(R.id.passpf);
        nspf = (TextView) view.findViewById(R.id.nspf);
        emailpf = (TextView) view.findViewById(R.id.emailpf);
        diachipf = (TextView) view.findViewById(R.id.diachipf);
        avtpf = (ImageView) view.findViewById(R.id.avtprofile);
        cmdpf = (TextView) view.findViewById(R.id.cmndpf);
        btupdate = (Button) view.findViewById(R.id.btcapnhat);
    }
    private void CapQuyenCA() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                OpenImagePicker();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(getActivity(), "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                .check();

//        if (ContextCompat.checkSelfPermission(
//                getActivity(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(getActivity(),new String[]{""+
//                    "android.permission.CAMERA"},1002);
//        }

    }

    private void OpenImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Title"),1);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            uriimgt=data.getData();
            bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uriimgt);
            } catch (IOException e) {
                e.printStackTrace();
            }
            avtpf.setImageBitmap(bitmap);
        }
    }
    private byte[] imagemTratada(@NonNull byte[] imagem_img){

        while (imagem_img.length > 500000){
            Bitmap bitmap = BitmapFactory.decodeByteArray(imagem_img, 0, imagem_img.length);
            Bitmap resized = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()*0.8), (int)(bitmap.getHeight()*0.8), true);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            resized.compress(Bitmap.CompressFormat.PNG, 100, stream);
            imagem_img = stream.toByteArray();
        }
        return imagem_img;

    }
}