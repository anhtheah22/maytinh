package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button; // Thêm import cho Button
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {
    private Button btn1, btn2, btn3, btn4, btn5,btn6, btn7, btn8, btn9, btn0, btnAC,btncongtru,btnphantram,btnchia,btnnhan,btntru,btncong,btnbang,btnphay;

    private TextView textviewHistory, textviewResult;

    private String number=null;
    boolean operator=false;

    String status=null;

    String pattern ="###,###.####";
    DecimalFormat df = new DecimalFormat(pattern);
    double lastnumber=0, firstnumber=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ánh xạ nè
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnAC = findViewById(R.id.btnAC);
        btncongtru = findViewById(R.id.btncongtru);
        btnphantram = findViewById(R.id.btnphantram);
        btnchia = findViewById(R.id.btnchia);
        btnnhan = findViewById(R.id.btnnhan);
        btntru = findViewById(R.id.btntru);
        btncong = findViewById(R.id.btncong);
        btnbang = findViewById(R.id.btnbang);
        btnphay = findViewById(R.id.btnphay);

            // Ánh xạ các TextView từ layout
        textviewHistory = findViewById(R.id.textviewHistory);
        textviewResult = findViewById(R.id.textviewResult);

        btn0.setOnClickListener(view -> numberclick("0"));
        btn1.setOnClickListener(view -> numberclick("1"));
        btn2.setOnClickListener(view -> numberclick("2"));
        btn3.setOnClickListener(view -> numberclick("3"));
        btn4.setOnClickListener(view -> numberclick("4"));
        btn5.setOnClickListener(view -> numberclick("5"));
        btn6.setOnClickListener(view -> numberclick("6"));
        btn7.setOnClickListener(view -> numberclick("7"));
        btn8.setOnClickListener(view -> numberclick("8"));
        btn9.setOnClickListener(view -> numberclick("9"));
        btnAC.setOnClickListener(view -> {
            // Xử lý sự kiện cho nút AC
            number=null;
            operator=false;
            textviewResult.setText("0");
            textviewHistory.setText("");
            firstnumber=0;
            lastnumber=0;

        });
        btncongtru.setOnClickListener(view -> {
            // Xử lý sự kiện cho nút +/-
        });
        btnphantram.setOnClickListener(view -> {
            // Xử lý sự kiện cho nút %
            // Lấy giá trị hiện tại của biến firstnumber
            double currentNumber = Double.parseDouble(textviewResult.getText().toString());

            // Kiểm tra xem firstnumber có khác 0 hay không trước khi thực hiện phép chia
            if (firstnumber != 0) {
                // Thực hiện phép chia có dư
                firstnumber = firstnumber % lastnumber;

                // Cập nhật kết quả lên TextView
                textviewResult.setText(String.valueOf(firstnumber));
            }
        });
        btnchia.setOnClickListener(view -> {
            // Xử lý sự kiện cho nút /
            if(operator) {
                if(status=="multi"){
                    Multi();
                }else{
                    if(status=="sum") {
                        Plus();
                    }else{
                        if(status=="minus") {
                            Minus();
                        }else{
                            Div();
                        }            }
                }
            }operator=false;
            number=null;
            status="div";
        });
        btnnhan.setOnClickListener(view -> {
            // Xử lý sự kiện cho nút *
            if(operator) {
                if(status=="sum"){
                    Plus();
                }else{
                    if(status=="div") {
                        Div();
                    }else{
                        if(status=="minus") {
                            Minus();
                        }else{
                            Multi();
                        }            }
                }
            }operator=false;
            number=null;
            status="multi";

        });
        btntru.setOnClickListener(view -> {
            // Xử lý sự kiện cho nút -
            if(operator) {
                if(status=="multi"){
                    Multi();
                }else{
                    if(status=="div") {
                        Div();
                    }else{
                        if(status=="sum") {
                            Plus();
                        }else{
                            Minus();
                        }            }
                }
            }operator=false;
            number=null;
            status="minus";
        });
        btncong.setOnClickListener(view -> {
            // Xử lý sự kiện cho nút +
            if(operator) {
                if(status=="multi"){
                    Multi();
                }else{
                    if(status=="div") {
                        Div();
                    }else{
                        if(status=="minus") {
                            Minus();
                        }else{
                            Plus();
                        }            }
                }
            }operator=false;
            number=null;
            status="sum";
        });
        btnbang.setOnClickListener(view -> {
            // Xử lý sự kiện cho nút =
            if(operator) {
                if(status=="multi"){
                    Multi();
                }else{
                    if(status=="sum") {
                        Plus();
                    }else{
                        if(status=="minus") {
                            Minus();
                        }else {
                            if(status=="div") {
                                Div();
                            }else{
                                firstnumber=Double.parseDouble(textviewResult.getText().toString());
                            }
                        }            }
                }
            }operator=false;

        });
        btnphay.setOnClickListener(view -> {
            // Xử lý sự kiện cho nút ,
            if(number==null){
                number="0.";
            }else{
                number=number+".";
            }textviewResult.setText(number);
        });


    }

    public void numberclick (String view){
        if(number==null){
            number=view;
        }else{
            number=number+view;
        }
        textviewResult.setText(number);
        operator=true;
    }

    public void Minus(){
        if(firstnumber==0) {
            firstnumber = Double.parseDouble(textviewResult.getText().toString());
        }else{
            lastnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber = firstnumber - lastnumber;
        }
        textviewResult.setText(df.format(firstnumber));
        }
    public void Plus(){
       lastnumber=Double.parseDouble(textviewResult.getText().toString());
        firstnumber = firstnumber + lastnumber;
        textviewResult.setText(df.format(firstnumber));


    }
    public void Div(){
        if(firstnumber==0) {
            firstnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber =lastnumber;
        }else{
            lastnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber = firstnumber / lastnumber;
        }
        textviewResult.setText(df.format(firstnumber));
    }
    public void Multi(){
        if(firstnumber==0){
            firstnumber=1;
        }
        lastnumber=Double.parseDouble(textviewResult.getText().toString());
        firstnumber = firstnumber * lastnumber;
        textviewResult.setText(df.format(firstnumber));


    }

    public void Du(){
        if(firstnumber==0) {
            firstnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber =lastnumber;
        }else{
            lastnumber = Double.parseDouble(textviewResult.getText().toString());
            firstnumber = firstnumber % lastnumber;
        }
        textviewResult.setText(df.format(firstnumber));
    }

}