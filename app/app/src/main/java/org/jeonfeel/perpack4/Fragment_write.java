package org.jeonfeel.perpack4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class Fragment_write extends Fragment {

    RadioButton radioButton;
    Context context;
    OnTabItemSelectedListener listener;
    private String str;
    Intent intent;
    Button nextStep;
    EditText edit_title;
    TextView dateText;
    String myear,mmonth,mday;
    ToggleButton toggleButton1;
    ToggleButton toggleButton2;
    ToggleButton toggleButton3;
    ToggleButton toggleButton4;
    ToggleButton toggleButton5;
    ToggleButton toggleButton6;
    ToggleButton toggleButton7;
    ToggleButton toggleButton8;
    ToggleButton toggleButton9;
    ToggleButton toggleButton10;
    ToggleButton toggleButton11;
    ToggleButton toggleButton12;
    Button textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView11,textView12;
    RadioGroup radioGroup;
    View btn_Group;
    TextView z;
    ArrayList<String> checkList;
    String purpose;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        this.context = context;

        if(context instanceof OnTabItemSelectedListener) {
            listener = (OnTabItemSelectedListener) context;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();

        if(context != null){
            context = null;
            listener = null;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_write,container,false);

        purpose = "0";

        initUI(rootView);

        toggleButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton1.isChecked()){
                    toggleButton1.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.toiletries));
                            checkList.add("샴푸");
                            checkList.add("린스");
                            checkList.add("폼 클렌징");
                            checkList.add("칫솔");
                            checkList.add("치약");
                            checkList.add("면봉");
                            checkList.add("바디워시");
                            checkList.add("면도기");
                            checkList.add("스킨");
                            checkList.add("로션");
                            checkList.add("렌즈세척액");
                }else{
                    toggleButton1.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.toiletries_unclicked));
                            checkList.remove("샴푸");
                            checkList.remove("린스");
                            checkList.remove("폼 클렌징");
                            checkList.remove("칫솔");
                            checkList.remove("치약");
                            checkList.remove("면봉");
                            checkList.remove("바디워시");
                            checkList.remove("면도기");
                            checkList.remove("스킨");
                            checkList.remove("로션");
                            checkList.remove("렌즈세척액");
                }
            }
        });
        toggleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton2.isChecked()){
                    toggleButton2.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.clothes));
                    checkList.add("여벌 상의");
                    checkList.add("여벌 하의");
                    checkList.add("잠옷");
                    checkList.add("간단히 걸칠것");
                    checkList.add("속옷");
                    checkList.add("양말");
                    checkList.add("점퍼");
                    checkList.add("벨트");
                    checkList.add("안경");

                }else{
                    toggleButton2.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.cloth_unclicked));
                    checkList.remove("여벌 상의");
                    checkList.remove("여벌 하의");
                    checkList.remove("잠옷");
                    checkList.remove("간단히 걸칠것");
                    checkList.remove("속옷");
                    checkList.remove("양말");
                    checkList.remove("점퍼");
                    checkList.remove("벨트");
                    checkList.remove("안경");
                }
            }
        });
        toggleButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton3.isChecked()){
                    toggleButton3.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.baby));
                    checkList.add("기저귀");
                    checkList.add("담요");
                    checkList.add("아기 물티슈");
                    checkList.add("아기 화장품");
                    checkList.add("아기 세면도구");
                    checkList.add("아기 장난감");
                    checkList.add("아기 옷");
                    checkList.add("아기 신발");
                    checkList.add("아기 양말");
                    checkList.add("아기 음식");
                    checkList.add("아기 컵");
                    checkList.add("휴대용 유모차");
                    checkList.add("아기 비상약");
                    checkList.add("아기띠");
                }else{
                    toggleButton3.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.baby_unclicked));
                    checkList.remove("기저귀");
                    checkList.remove("담요");
                    checkList.remove("아기 물티슈");
                    checkList.remove("아기 화장품");
                    checkList.remove("아기 세면도구");
                    checkList.remove("아기 장난감");
                    checkList.remove("아기 옷");
                    checkList.remove("아기 신발");
                    checkList.remove("아기 양말");
                    checkList.remove("아기 음식");
                    checkList.remove("아기 컵");
                    checkList.remove("휴대용 유모차");
                    checkList.remove("아기 비상약");
                    checkList.remove("아기띠");
                }
            }
        });
        toggleButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton4.isChecked()){
                    toggleButton4.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.flight));
                    checkList.add("여권");
                    checkList.add("비자(출입국 증명서)");
                    checkList.add("해외겸용 신용카드 혹은 환전");
                    checkList.add("보조배터리");
                    checkList.add("포켓와이파이 혹은 유심");
                    checkList.add("건강보험증");
                    checkList.add("예방접종 증명서");
                    checkList.add("여행지 전압 확인");
                    checkList.add("전압에 맞는 어댑터");
                }else{
                    toggleButton4.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.flight_unclicked));
                    checkList.remove("여권");
                    checkList.remove("비자(출입국 증명서)");
                    checkList.remove("해외겸용 신용카드 혹은 환전");
                    checkList.remove("보조배터리");
                    checkList.remove("포켓와이파이 혹은 유심");
                    checkList.remove("건강보험증");
                    checkList.remove("예방접종 증명서");
                    checkList.remove("여행지 전압 확인");
                    checkList.remove("전압에 맞는 어댑터");
                }
            }
        });
        toggleButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton5.isChecked()){
                    toggleButton5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.parasol));
                    checkList.add("선글라스");
                    checkList.add("썬크림");
                    checkList.add("모자");
                    checkList.add("샌들");
                    checkList.add("수영복");
                    checkList.add("구급약");
                    checkList.add("비치타월");
                    checkList.add("태닝오일");
                    checkList.add("스마트폰 방수팩");
                }else{
                    toggleButton5.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.beach_unclicked));
                    checkList.remove("선글라스");
                    checkList.remove("썬크림");
                    checkList.remove("모자");
                    checkList.remove("샌들");
                    checkList.remove("수영복");
                    checkList.remove("구급약");
                    checkList.remove("비치타월");
                    checkList.remove("태닝오일");
                    checkList.remove("스마트폰 방수팩");
                }
            }
        });
        toggleButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton6.isChecked()){
                    toggleButton6.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.trekking));
                    checkList.add("카메라");
                    checkList.add("카메라 삼각대");
                    checkList.add("등산화");
                    checkList.add("여벌 등산복");
                    checkList.add("등산 지팡이");
                    checkList.add("등산 장갑");
                    checkList.add("등산 가방");
                    checkList.add("물");
                    checkList.add("도시락");
                    checkList.add("간식");
                }else{
                    toggleButton6.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.trekking_unclicked));
                    checkList.remove("카메라");
                    checkList.remove("카메라 삼각대");
                    checkList.remove("등산화");
                    checkList.remove("여벌 등산복");
                    checkList.remove("등산 지팡이");
                    checkList.remove("등산 장갑");
                    checkList.remove("등산 가방");
                    checkList.remove("물");
                    checkList.remove("도시락");
                    checkList.remove("간식");
                }
            }
        });
        toggleButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton7.isChecked()){
                    toggleButton7.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.sakura));
                    checkList.add("스마트폰 삼각대 혹은 셀카봉");
                    checkList.add("돗자리");
                    checkList.add("맛있는 도시락");
                    checkList.add("음료수");
                    checkList.add("간식거리");
                    checkList.add("벚꽃엔딩.mp3");

                }else{
                    toggleButton7.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.sakura_unclicked));
                    checkList.remove("스마트폰 삼각대 혹은 셀카봉");
                    checkList.remove("돗자리");
                    checkList.remove("맛있는 도시락");
                    checkList.remove("음료수");
                    checkList.remove("간식거리");
                    checkList.remove("벚꽃엔딩.mp3");
                }
            }
        });
        toggleButton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton8.isChecked()){
                    toggleButton8.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.camping_tent));
                    checkList.add("텐트");
                    checkList.add("침낭");
                    checkList.add("의자");
                    checkList.add("테이블");
                    checkList.add("랜턴");
                    checkList.add("버너");
                    checkList.add("술");
                    checkList.add("컵,식기");
                    checkList.add("숟가락,젓가락");
                    checkList.add("벌레 스프레이");
                    checkList.add("맥가이버 칼");
                    checkList.add("토치");
                    checkList.add("바베큐 거리");


                }else{
                    toggleButton8.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.campingtent_unclicked));
                    checkList.remove("텐트");
                    checkList.remove("침낭");
                    checkList.remove("의자");
                    checkList.remove("테이블");
                    checkList.remove("랜턴");
                    checkList.remove("버너");
                    checkList.remove("술");
                    checkList.remove("컵,식기");
                    checkList.remove("숟가락,젓가락");
                    checkList.remove("벌레 스프레이");
                    checkList.remove("맥가이버 칼");
                    checkList.remove("토치");
                    checkList.remove("바베큐 거리");
                }
            }
        });
        toggleButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton9.isChecked()){
                    toggleButton9.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.barbell_clicked));
                    checkList.add("수건");
                    checkList.add("운동양말");
                    checkList.add("운동복");
                    checkList.add("물병");
                    checkList.add("프로틴");
                    checkList.add("운동화");

                }else{
                    toggleButton9.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.barbell_unclicked));
                    checkList.remove("수건");
                    checkList.remove("운동양말");
                    checkList.remove("운동복");
                    checkList.remove("물병");
                    checkList.remove("프로틴");
                    checkList.remove("운동화");
                }
            }
        });
        toggleButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton10.isChecked()){
                    toggleButton10.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.backpack_clicked));
                    checkList.add("수첩,필기도구");
                    checkList.add("빨래비누");
                    checkList.add("물티슈");
                    checkList.add("모자");
                    checkList.add("편한운동화");
                    checkList.add("여분배터리");

                }else{
                    toggleButton10.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.backpack_unclicked));
                    checkList.remove("수첩,필기도구");
                    checkList.remove("빨래비누");
                    checkList.remove("물티슈");
                    checkList.remove("모자");
                    checkList.remove("편한운동화");
                    checkList.remove("여분배터리");
                }
            }
        });
        toggleButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton11.isChecked()){
                    toggleButton11.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.ski_clicked));
                    checkList.add("겨울장갑");
                    checkList.add("자외선 차단제");
                    checkList.add("내복");
                    checkList.add("모자 혹은 비니");
                    checkList.add("스키복");
                    checkList.add("스키바지");
                    checkList.add("스키폴");
                    checkList.add("스키 플레이트");
                    checkList.add("전용 신발");
                    checkList.add("스노우 보드");
                }else{
                    toggleButton11.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.ski_unclicked));
                    checkList.remove("겨울장갑");
                    checkList.remove("자외선 차단제");
                    checkList.remove("내복");
                    checkList.remove("모자 혹은 비니");
                    checkList.remove("스키복");
                    checkList.remove("스키바지");
                    checkList.remove("스키폴");
                    checkList.remove("스키 플레이트");
                    checkList.remove("전용 신발");
                    checkList.remove("스노우 보드");

                }
            }
        });
        toggleButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton12.isChecked()){
                    toggleButton12.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.bike_clicked));
                    checkList.add("헬멧");
                    checkList.add("장갑");
                    checkList.add("재킷");
                    checkList.add("우비");
                    checkList.add("오토바이 공구세트");
                    checkList.add("케이블 타이");
                    checkList.add("초강력 순간 접착제");
                    checkList.add("윤활유");

                }else{
                    toggleButton12.setBackgroundDrawable(
                            getResources().getDrawable(R.drawable.bike_unclicked));
                    checkList.remove("헬멧");
                    checkList.remove("장갑");
                    checkList.remove("재킷");
                    checkList.remove("우비");
                    checkList.remove("오토바이 공구세트");
                    checkList.remove("케이블 타이");
                    checkList.remove("초강력 순간 접착제");
                    checkList.remove("윤활유");

                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String result;
                if(checkedId == R.id.radiobutton){
                    btn_Group.setVisibility(View.VISIBLE);
                    z.setVisibility(View.VISIBLE);
                    purpose = "0";
                }else{
                    btn_Group.setVisibility(View.INVISIBLE);
                    z.setVisibility(View.INVISIBLE);
                    toggleButton1.setChecked(false);
                    toggleButton2.setChecked(false);
                    toggleButton3.setChecked(false);
                    toggleButton4.setChecked(false);
                    toggleButton5.setChecked(false);
                    toggleButton6.setChecked(false);
                    toggleButton7.setChecked(false);
                    toggleButton8.setChecked(false);
                    toggleButton9.setChecked(false);
                    toggleButton10.setChecked(false);
                    toggleButton11.setChecked(false);
                    toggleButton12.setChecked(false);
                    toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.toiletries_unclicked));
                    toggleButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cloth_unclicked));
                    toggleButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.baby_unclicked));
                    toggleButton4.setBackgroundDrawable(getResources().getDrawable(R.drawable.flight_unclicked));
                    toggleButton5.setBackgroundDrawable(getResources().getDrawable(R.drawable.beach_unclicked));
                    toggleButton6.setBackgroundDrawable(getResources().getDrawable(R.drawable.trekking_unclicked));
                    toggleButton7.setBackgroundDrawable(getResources().getDrawable(R.drawable.sakura_unclicked));
                    toggleButton8.setBackgroundDrawable(getResources().getDrawable(R.drawable.campingtent_unclicked));
                    toggleButton9.setBackgroundDrawable(getResources().getDrawable(R.drawable.barbell_unclicked));
                    toggleButton10.setBackgroundDrawable(getResources().getDrawable(R.drawable.backpack_unclicked));
                    toggleButton11.setBackgroundDrawable(getResources().getDrawable(R.drawable.ski_unclicked));
                    toggleButton12.setBackgroundDrawable(getResources().getDrawable(R.drawable.bike_unclicked));
                    if(checkList != null){
                        checkList.clear();
                        }
                    purpose = "1";
                    }
                }

        });
        nextStep = (Button) rootView.findViewById(R.id.nextStep);
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edit_title.getText().toString().length() == 0){
                    Toast.makeText(getActivity(), "제목을 입력해 주세요", Toast.LENGTH_SHORT).show();
                }else if(dateText.length() == 0){
                    Toast.makeText(getActivity(), "날짜를 설정해 주세요", Toast.LENGTH_SHORT).show();
                }else {
                    if(checkList.size() > 0){

                        str = edit_title.getText().toString();
                        intent = new Intent(getActivity(), listAddDel.class);
                        intent.putExtra("str", str);
                        intent.putExtra("year", myear);
                        intent.putExtra("month", mmonth);
                        intent.putExtra("day", mday);
                        intent.putExtra("purpose", purpose);
                        intent.putExtra("checkList", checkList);


                    }else{
                        str = edit_title.getText().toString();
                        intent = new Intent(getActivity(), listAddDel.class);
                        intent.putExtra("str", str);
                        intent.putExtra("year", myear);
                        intent.putExtra("month", mmonth);
                        intent.putExtra("day", mday);
                        intent.putExtra("purpose", purpose);
                    }

                    toggleButton1.setChecked(false);
                    toggleButton2.setChecked(false);
                    toggleButton3.setChecked(false);
                    toggleButton4.setChecked(false);
                    toggleButton5.setChecked(false);
                    toggleButton6.setChecked(false);
                    toggleButton7.setChecked(false);
                    toggleButton8.setChecked(false);
                    toggleButton9.setChecked(false);
                    toggleButton10.setChecked(false);
                    toggleButton11.setChecked(false);
                    toggleButton12.setChecked(false);
                    toggleButton1.setBackgroundDrawable(getResources().getDrawable(R.drawable.toiletries_unclicked));
                    toggleButton2.setBackgroundDrawable(getResources().getDrawable(R.drawable.cloth_unclicked));
                    toggleButton3.setBackgroundDrawable(getResources().getDrawable(R.drawable.baby_unclicked));
                    toggleButton4.setBackgroundDrawable(getResources().getDrawable(R.drawable.flight_unclicked));
                    toggleButton5.setBackgroundDrawable(getResources().getDrawable(R.drawable.beach_unclicked));
                    toggleButton6.setBackgroundDrawable(getResources().getDrawable(R.drawable.trekking_unclicked));
                    toggleButton7.setBackgroundDrawable(getResources().getDrawable(R.drawable.sakura_unclicked));
                    toggleButton8.setBackgroundDrawable(getResources().getDrawable(R.drawable.campingtent_unclicked));
                    toggleButton9.setBackgroundDrawable(getResources().getDrawable(R.drawable.barbell_unclicked));
                    toggleButton10.setBackgroundDrawable(getResources().getDrawable(R.drawable.backpack_unclicked));
                    toggleButton11.setBackgroundDrawable(getResources().getDrawable(R.drawable.ski_unclicked));
                    toggleButton12.setBackgroundDrawable(getResources().getDrawable(R.drawable.bike_unclicked));

                    radioButton.setChecked(true);
                    edit_title.setText("");
                    dateText.setText("");

                    startActivity(intent);
                }
            }
        });
        return rootView;
    }

    private void initUI(ViewGroup rootView){

        radioButton = rootView.findViewById(R.id.radiobutton);
        toggleButton1 = rootView.findViewById(R.id.toggleButton1);
        toggleButton2 = rootView.findViewById(R.id.toggleButton2);
        toggleButton3 = rootView.findViewById(R.id.toggleButton3);
        toggleButton4 = rootView.findViewById(R.id.toggleButton4);
        toggleButton5 = rootView.findViewById(R.id.toggleButton5);
        toggleButton6 = rootView.findViewById(R.id.toggleButton6);
        toggleButton7 = rootView.findViewById(R.id.toggleButton7);
        toggleButton8 = rootView.findViewById(R.id.toggleButton8);
        toggleButton9 = rootView.findViewById(R.id.toggleButton9);
        toggleButton10 = rootView.findViewById(R.id.toggleButton10);
        toggleButton11 = rootView.findViewById(R.id.toggleButton11);
        toggleButton12 = rootView.findViewById(R.id.toggleButton12);
        textView1 = rootView.findViewById(R.id.textView1);
        textView2 = rootView.findViewById(R.id.textView2);
        textView3 = rootView.findViewById(R.id.textView3);
        textView4 = rootView.findViewById(R.id.textView4);
        textView5 = rootView.findViewById(R.id.textView5);
        textView6 = rootView.findViewById(R.id.textView6);
        textView7 = rootView.findViewById(R.id.textView7);
        textView8 = rootView.findViewById(R.id.textView8);
        textView9 = rootView.findViewById(R.id.textView9);
        textView10 = rootView.findViewById(R.id.textView10);
        textView11= rootView.findViewById(R.id.textView11);
        textView12= rootView.findViewById(R.id.textView12);
        radioGroup = rootView.findViewById(R.id.radioGroup);
        z = rootView.findViewById(R.id.z);
        checkList = new ArrayList<String>();
        btn_Group = rootView.findViewById(R.id.btn_group);
        edit_title = rootView.findViewById(R.id.edit_title);
        dateText = rootView.findViewById(R.id.dateText);
    }

    public void newSetDate(String year,String month,String day){
        myear = year;
        mmonth = month;
        mday = day;
        dateText.setText("  "+year+"년 "+month+"월 "+day+"일");
    }
    @Override
    public void onResume() {
        super.onResume();
        if(checkList != null)
        checkList.clear();
    }
}


