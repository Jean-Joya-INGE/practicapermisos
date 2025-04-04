package co.edu.ue.practicapermisos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;
import androidx.core.app.ActivityCompat;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

   public static final int REQUEST_CODE = 1;

//1. decalrracion de atributos (primitivos/objetos)

    private Button btnCheckPermission;
    private Button btnRequestPermission;
    private TextView tvResponse;
    private TextView tvDactilar;
    private TextView tvCamera;
    private TextView tvBT;
    private TextView tvEws;
    private TextView tvRS;
    private TextView tvInternet;
    private TextView tvContactos;
    private TextView tvTitulo;
    private TextView tvFingerPrint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los botones
        btnCheckPermission = findViewById(R.id.btnCheckPermission);
        btnRequestPermission = findViewById(R.id.btnRequestPermission);

        // Configurar listeners
        btnCheckPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para verificar permisos
            }
        });

        btnRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para solicitar permisos
            }
        });
        beginObjets();
        btnCheckPermission.setOnClickListener(this::voidcheckPermission);
        btnRequestPermission.setOnClickListener(this::voidrequestPermission);

    }
    //3. Verificar permisos
    private void voidcheckPermission(View view) {
        // 4. verificacion de estado de cada permiso--> hay permiso -->0 o hay permiso -->1 **//
        int fingerPrint = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.USE_BIOMETRIC);
        int camara = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        int bluT = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.BLUETOOTH);
        int ews = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int res = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        int contact = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS);
        int internet = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET);
        tvFingerPrint.setText("status Permission fingerprint: " + fingerPrint);
        tvCamera.setText("status Permission Camera: " + camara);
        tvBT.setText("status Permission BT: " + blut);
        tvEws.setText("status Permission External Storage: " + ews);
        tvRS.setText("status Permission Read Storage: " + res);
        tvInternet.setText("status Permission Internet: " + internet);
        tvContactos.setText("status Permission Contacts: " + contact);
    }
    //2. Enlace de objetos con la IG e inicializacion de objetos
    private void beginObjets() {
        btnCheckPermission = findViewById(R.id.btnCheckPermission);
        btnRequestPermission = findViewById(R.id.btnRequestPermission);
        tvResponse = findViewById(R.id.tvResponse);
        tvDactilar = findViewById(R.id.tvDactilar);
        tvCamera = findViewById(R.id.tvCamera);
        tvBT = findViewById(R.id.tvBT);
        tvEws = findViewById(R.id.tvEws);
        tvRS = findViewById(R.id.tvRS);
        tvInternet = findViewById(R.id.tvInternet);
        tvContactos = findViewById(R.id.tvContactos);
        tvTitulo = findViewById(R.id.tvTitulo);
        btnCheckPermission.setSaveEnabled(true);
    }

    private void voidrequestPermission(View view) {
        /*4 Verificacion y solicitud de permiso para la camara */
        if (ActivityCompat.checkSelfPermission
                (this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }

    }
    //5. Gestion de respuesta de solicuitud de permiso
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        tvResponse.setText(""+grantResults[0]);
        if(requestCode == REQUEST_CODE){
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
                //perdir permiso nuevamente
                new AlertDialog.Builder(this)
                        .setTitle("Alerta de Permisos")
                        .setMessage("No ha otorgado los permiso a la cámara.  Configure el permiso en ajuste")
                        .setPositiveButton("Ajustes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package",getPackageName(),null));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("Salida", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                finish();
                            }
                        }).create().show();
            }else{
                
            }
        }
        
    }
}