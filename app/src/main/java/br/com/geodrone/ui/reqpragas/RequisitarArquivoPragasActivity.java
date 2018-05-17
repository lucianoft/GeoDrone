package br.com.geodrone.ui.reqpragas;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.geodrone.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RequisitarArquivoPragasActivity extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisitar_arquivo_pragas);
        ButterKnife.bind(this);

    }

   @OnClick(R.id.text_view_dt_inicio_req_praga)
   public void createDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,R.style.ThemeOverlay_AppCompat_Dialog, this, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH)+1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        String zeroDay = "";
        String zeroMonth ="";
        month++;
        String currentDateString = currentDay + "/" + currentMonth + "/" + currentYear;
        if(month<=9){
            zeroMonth="0";
        }
        if(dayOfMonth <=9){
            zeroDay="0";
        }
        String dateSetString = zeroDay+dayOfMonth + "/" + zeroMonth+month + "/" + year;
        try {
            Date currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(currentDateString);
            Date dateSet = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(dateSetString);
            String dtMsgError = "";
            //User adding a new Finalize date
            if(dateSet.after(currentDate)) {//error if dt final is smaller then current date
                //dtMsgError  = (this.getResources().getString(R.string.dt_error_bigger_then_today));
                Toast.makeText(this, dtMsgError, Toast.LENGTH_SHORT).show();
            }
            else{
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
