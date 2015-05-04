package fra.ss.cns;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.administrator.xiangha.MainActivity;
import com.example.administrator.xiangha.R;


/**
 * Created by aaa on 15-5-4.
 */
public class MyFragmen_0 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_0,container,false);
        Button ivbut= (Button) view.findViewById(R.id.fragment_imagebutton);
        //点击跳转页面
         ivbut.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(getActivity(), MainActivity.class);
                 startActivity(intent);
             }
         });

        return view;
    }


}
