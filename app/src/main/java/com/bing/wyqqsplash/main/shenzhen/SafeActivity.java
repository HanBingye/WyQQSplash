package com.bing.wyqqsplash.main.shenzhen;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bing.wyqqsplash.R;
import com.bing.wyqqsplash.base.BaseActivity;
import com.bing.wyqqsplash.base.ViewInject;
import com.bing.wyqqsplash.main.shenzhen.util.ByteUtil;
import com.bing.wyqqsplash.main.shenzhen.util.MD5;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.activity_safe)
public class SafeActivity extends BaseActivity {


    @BindView(R.id.et_MD5plainText)
    EditText etMD5plainText;
    @BindView(R.id.bt_secret1)
    Button btSecret1;
    @BindView(R.id.tv_MD5cipherText)
    TextView tvMD5cipherText;
    @BindView(R.id.et_AESPlainText)
    EditText etAESPlainText;
    @BindView(R.id.bt_secret2)
    Button btSecret2;
    @BindView(R.id.tv_AESCipherText)
    TextView tvAESCipherText;
    @BindView(R.id.tv_AESPlainText)
    TextView tvAESPlainText;
    private boolean IsIdle = true;
    private String str;

    @Override
    public void afterBindView() {

        btSecret1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etMD5plainText.getText().toString().trim();
                if (content != null) {
                    String result = MD5.md5Decode32(content);

                    tvMD5cipherText.setText("加密后\n" + result.toUpperCase());
                }
            }
        });
        btSecret2.setOnClickListener(new View.OnClickListener() {
            //密钥
            String key = "todayinformation";
            //算法/工作模式/填充模式
            //ECB多次加密 密文一样
            String TYPE = "AES/ECB/PKCS5Padding";

            @Override
            public void onClick(View v) {
                if (IsIdle) {
                    try {
                        String content = etAESPlainText.getText().toString().trim();
                        //生成密钥
                        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("utf-8"), "AES");

                        //加密解密器
                        Cipher cipher = Cipher.getInstance(TYPE);
                        //Cipher.ENCRYPT_MODE加密模式
                        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
                        if (content != null) {
                            //加密后的字节数组
                            byte[] bytes = cipher.doFinal(content.getBytes());
                            //如果不转成16进制 会出现乱码 因为明文可能有中文
                            str = ByteUtil.parseByte2HexStr(bytes);
                            tvAESCipherText.setText("密文: " + str);
                            btSecret2.setText("AES解密");
                            tvAESPlainText.setText("明文**********");

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    try {

                        //生成密钥
                        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("utf-8"), "AES");

                        //加密解密器
                        Cipher cipher = Cipher.getInstance(TYPE);
                        //Cipher.DECRYPT_MODE解密模式
                        cipher.init(Cipher.DECRYPT_MODE, keySpec);
                        //解密后的字节数组
                        if (str != null) {
                            byte[] bytes = cipher.doFinal(ByteUtil.parseHexStr2Byte(str));
                            str = new String(bytes, "UTF-8");
                            tvAESPlainText.setText("明文: " + str);
                            btSecret2.setText("AES加密");
                            tvAESCipherText.setText("密文**********");

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                IsIdle = !IsIdle;
            }
        });
    }
}