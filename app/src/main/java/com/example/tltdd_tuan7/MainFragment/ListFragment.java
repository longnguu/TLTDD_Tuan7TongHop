package com.example.tltdd_tuan7.MainFragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tltdd_tuan7.Adapter.Adapter;
import com.example.tltdd_tuan7.Adapter.SlideModel;
import com.example.tltdd_tuan7.Class.BTP;
import com.example.tltdd_tuan7.Class.Items;
import com.example.tltdd_tuan7.Detail;
import com.example.tltdd_tuan7.R;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private android.widget.ListView listView;
    ArrayList<Items> items = new ArrayList<>();
    ImageView imgthem ;
    Adapter adapter;
    Uri uriimgt,urii;
    Boolean kt=false;
    int j=0;
    Bitmap bitmap,bm;


    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
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
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (android.widget.ListView) view.findViewById(R.id.list_view);
        Button btthem = (Button) view.findViewById(R.id.bt_them);
        Button btca = (Button) view.findViewById(R.id.btchonanh);
        imgthem = (ImageView) view.findViewById(R.id.imgthem);
        EditText item_ten = (EditText) view.findViewById(R.id.edt_them);
        EditText item_mota = (EditText) view.findViewById(R.id.edt_hint);
        items.add(new Items("Quảng Trị","Năm 2018, Quảng Trị là đơn vị hành chính Việt Nam đông thứ 57 về số dân, xếp thứ 55 về Tổng sản phẩm trên địa bàn (GRDP), xếp thứ 37 về GRDP bình quân đầu người, đứng thứ 51 về tốc độ tăng trưởng GRDP. Với 630,6 nghìn dân[6], GRDP đạt 27.494 tỉ Đồng (tương ứng với 1,1940 tỉ USD), GRDP bình quân đầu người đạt 43,6 triệu đồng (tương ứng với 1.894 USD), tốc độ tăng trưởng GRDP đạt 7,12%.[7]\n" +
                "\n" +
                "Tỉnh Quảng Trị có Khu phi quân sự vĩ tuyến 17, là giới tuyến chia cắt miền Bắc Việt Nam Việt Nam Dân chủ Cộng hòa và Việt Nam Cộng hòa, do đó cũng là một chiến trường ác liệt nhất trong suốt 21 năm của cuộc Chiến tranh Việt Nam (1954 - 1975).",R.drawable.img_4,bm)) ;
        items.add(new Items("Hà Nội","Hà Nội là thủ đô, thành phố trực thuộc trung ương và là một đô thị loại đặc biệt của Việt Nam. Hà Nội nằm về phía tây bắc của trung tâm vùng đồng bằng châu thổ sông Hồng, với địa hình bao gồm vùng đồng bằng trung tâm và vùng đồi núi ở phía bắc và phía tây thành phố. Với diện tích 3.359,82 km²,[2] và dân số 8,33 triệu người,[4] Hà Nội là thành phố trực thuộc trung ương có diện tích lớn nhất Việt Nam, đồng thời cũng là thành phố đông dân thứ hai và có mật độ dân số cao thứ hai trong 63 đơn vị hành chính cấp tỉnh của Việt Nam, nhưng phân bố dân số không đồng đều. Hà Nội có 30 đơn vị hành chính cấp huyện, gồm 12 quận, 17 huyện và 1 thị xã.",R.drawable.img_1,bm)) ;
        items.add(new Items("Đà Nẵng","Đà Nẵng là một thành phố trực thuộc trung ương, nằm trong vùng Duyên hải Nam Trung Bộ Việt Nam, là thành phố trung tâm và lớn nhất khu vực miền Trung - Tây Nguyên.\n" +
                "\n" +
                "Thành phố Đà Nẵng là thành phố tổng hợp đa ngành, đa lĩnh vực; trung tâm chính trị - kinh tế - xã hội với vai trò là trung tâm công nghiệp, tài chính, du lịch, dịch vụ, văn hóa, giáo dục - đào tạo, y tế chất lượng cao, khoa học - công nghệ, khởi nghiệp, đổi mới sáng tạo của khu vực Miền Trung - Tây Nguyên và cả nước; trung tâm tổ chức các sự kiện tầm khu vực và quốc tế. Thành phố Đà Nẵng đóng vai trò hạt nhân, quan trọng trong Vùng kinh tế trọng điểm miền Trung, đồng thời cũng là một trong 5 thành phố trực thuộc Trung ương ở Việt Nam, đô thị loại I, trung tâm cấp quốc gia, cùng với Hải Phòng và Cần Thơ.",R.drawable.img_2,bm)) ;
        items.add(new Items("TP Hồ Chí Minh","Thành phố Hồ Chí Minh, còn gọi bằng tên cũ phổ biến là Sài Gòn, là thành phố lớn nhất ở Việt Nam về dân số và quy mô đô thị hóa. Đây còn là trung tâm kinh tế, chính trị, văn hóa, giải trí và giáo dục tại Việt Nam. Thành phố Hồ Chí Minh là thành phố trực thuộc trung ương thuộc loại đô thị đặc biệt của Việt Nam cùng với thủ đô Hà Nội.[8] Nằm trong vùng chuyển tiếp giữa Đông Nam Bộ và Tây Nam Bộ, thành phố này hiện có 16 quận, 1 thành phố và 5 huyện, tổng diện tích 2.095 km2 (809 dặm vuông Anh).[9] Theo kết quả điều tra dân số chính thức vào thời điểm ngày 1 tháng 4 năm 2019 thì dân số thành phố là 8.993.082 người (chiếm 9,35% dân số Việt Nam), mật độ dân số trung bình 4.293 người/km² (cao nhất cả nước)[10]. Tuy nhiên, nếu tính những người cư trú không đăng ký hộ khẩu thì dân số thực tế của thành phố này năm 2018 là gần 14 triệu người.[11]",R.drawable.img_3,bm)) ;

        if (BTP.items.size()==0) {
            BTP.items=items;
        }


        btthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                if (bitmap!=null){
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhAnh = byteArrayOutputStream.toByteArray();
                hinhAnh=imagemTratada(hinhAnh);
                BTP.items.add(new Items(item_ten.getText().toString(),item_mota.getText().toString(),0,hinhAnh));
                BTP.slideModelList.add(new SlideModel(0,hinhAnh,item_ten.getText().toString()));
                }
                else
                    BTP.items.add(new Items(item_ten.getText().toString(),item_mota.getText().toString(),0));
                adapter.notifyDataSetChanged();
                HomeFragment.slideAdapter.notifyDataSetChanged();
            }
        });
        btca.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CapQuyenCA();
            }
        }));

        adapter = new Adapter(getActivity(),BTP.items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Detail.class);
                Items item = BTP.items.get(i);
                intent.putExtra("dulieu",item);
                if (kt!=true)
                    startActivity(intent);
                kt=false;
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                kt=true;
                Xacnhanxoa(i);
                return false;
            }
        });
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
        if(requestCode == 1 && data != null){
            uriimgt=data.getData();
            bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uriimgt);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgthem.setImageBitmap(bitmap);
        }
    }


    public void Xacnhanxoa(final int pos){
        AlertDialog.Builder alertDiaLog = new AlertDialog.Builder(getActivity());
        alertDiaLog.setTitle("Thông báo");
        alertDiaLog.setIcon(R.drawable.icon_delete);
        alertDiaLog.setMessage("Bạn có muốn xóa "+items.get(pos).getTen()+" ?"    );
        alertDiaLog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                BTP.items.remove(pos);
                BTP.slideModelList.remove(pos);
                adapter.notifyDataSetChanged();
                HomeFragment.slideAdapter.notifyDataSetChanged();
            }
        });
        alertDiaLog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDiaLog.show();

    }
    private byte[] imagemTratada(byte[] imagem_img){

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