package com.hasanural.containercalculator;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.hasanural.Fragements.Calculation_container_Fragment;
import com.hasanural.Fragements.Calculation_production_Fragment;
import com.hasanural.Fragements.Calculation_result_Fragment;
import com.hasanural.containercalculator.Adapters.Fpa_calculator;
import com.hasanural.containercalculator.DataAccess.Entity.Order;
import com.hasanural.containercalculator.DataAccess.Entity.OrderInProduct;
import com.hasanural.containercalculator.DataAccess.Entity.OrderResult;
import com.hasanural.containercalculator.DataAccess.Entity.OrderResultStep;
import com.hasanural.containercalculator.DataAccess.Entity.OrderResultStepDetail;
import com.hasanural.containercalculator.DataAccess.Model.ContainerPostModel;
import com.hasanural.containercalculator.DataAccess.Model.PostModel;
import com.hasanural.containercalculator.DataAccess.Model.ProductPostModel;
import com.hasanural.containercalculator.DataAccess.Repositories.Setting_Repository;
import com.hasanural.containercalculator.Events.FragementFillEvent;
import com.hasanural.containercalculator.Events.FragmentValidateEvent;
import com.hasanural.containercalculator.Utilities.Constants;
import com.hasanural.containercalculator.Utilities.Helper;
import com.hasanural.containercalculator.Utilities.LanguageHelper;
import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParserException;

import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;


public class CalculationActivity extends BaseActivity {

    // #Variables Area
    Context context;
    int currentPage;
    public Order calculate_order;
    SweetAlertDialog swDialog;
    //Internet Permission Code
    private static final int API_PERMSISSION_CODE = 1010;

    //Permission List
    String[] perms = new String[]{Manifest.permission.INTERNET};

    //Components
    LockableViewPager vp;
    LinearLayout dotsLayout;
    Fpa_calculator fpa;
    Button nextButton, prevButton;
    TextView[] mdots;

    //Events
    public FragmentValidateEvent activityListener;
    public FragementFillEvent fillEvent;

    //#Variables End

    //#EventListener Methods
    public void setActivityListener(FragmentValidateEvent activityListener) {
        this.activityListener = activityListener;
    }

    public void setActivityFragmentFillEvent(FragementFillEvent fillEvent) {
        this.fillEvent = fillEvent;
    }

    //#API Request Methods
    private PostModel CreatePostModel() {
        try {
            PostModel pm = new PostModel();
            //Container
            pm.Container = new ContainerPostModel(calculate_order.container.id,
                    calculate_order.container.length, calculate_order.container.width,
                    calculate_order.container.height);
            //Packages
            pm.ItemsToPack = new ArrayList<>();
            for (OrderInProduct oip : calculate_order.products) {
                ProductPostModel ppm = new ProductPostModel(oip.id, oip.length,
                        oip.width, oip.height, oip.quantity);
                pm.ItemsToPack.add(ppm);
            }
            return pm;
        } catch (Exception e) {
            Log.e(Constants.TAG_API_REQUEST, e.getMessage());
            return null;
        }
    }

    private void Api_Post_Calculate() {
        try {
            swDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
            swDialog.setTitleText(getResources().getString(R.string.errormessage_sw_loading_Title))
                    .show();
            PostModel pm = CreatePostModel();
            if (pm != null) {
                RequestQueue queue = Volley.newRequestQueue(this);
                queue.add(Api_Post_Request(pm));
            } else {
                Log.e(Constants.TAG_API_REQUEST, "Post Model Not Valid!");
            }
        } catch (Exception e) {
            Log.e(Constants.TAG_API_REQUEST, e.getMessage());
        } finally {

        }
    }

    public StringRequest Api_Post_Request(final PostModel model) {
        try {
            final Gson gson = new Gson();
            Setting_Repository repo = new Setting_Repository(context);
            String apiUrl = repo.Get_By_Key(Constants.Setting_API_URL_Key).value;
            StringRequest request = new StringRequest(Request.Method.POST, apiUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject job = new JSONObject(response);
                                if (job == null) {
                                    Log.e(Constants.TAG_API_REQUEST, "Response not found");
                                    throw new Exception("Response not found!");
                                } else {
                                    OrderResult or = new OrderResult();

                                    or.setIsComplatePack(job.getBoolean("IsCompletePack"));
                                    or.setTotalContainerCount(job.getInt("TotalContainerCount"));
                                    or.setPackTimeInMillisecond(job.getDouble("PackTimeInMilliseconds"));
                                    or.setPercentContainerVolumePacked(job.getDouble("PercentContainerVolumePacked"));
                                    or.setPercentItemValumePacked(job.getDouble("PercentItemVolumePacked"));
                                    or.setTotalPacked(job.getInt("TotalPackages"));
                                    or.setPackedItemsCount(job.getInt("PackedItemsCount"));
                                    or.setPackedItemsVolume(job.getDouble("PackedItemsVolume"));
                                    or.setError(job.getBoolean("IsError"));
                                    or.setMessage(job.getString("Message"));
                                    JSONArray steps= job.getJSONArray("Steps");

                                    if(steps.length()>0)
                                    {
                                        ArrayList<OrderResultStep> stepArray=new ArrayList<>();
                                        int stepCounter=0;
                                       do
                                       {
                                           JSONObject  jsonStep= steps.getJSONObject(stepCounter);

                                           OrderResultStep step = new OrderResultStep();
                                           step.setPackedCount(jsonStep.getInt("PackedCount"));
                                           step.setPackedVolume(jsonStep.getDouble("PackedVolume"));
                                           step.setPercentContainerVolumePacked(jsonStep.getDouble("PercentContainerVolumePacked"));
                                           step.setPercentItemVolumePacked(jsonStep.getDouble("PercentItemVolumePacked"));
                                           step.setPercentItemPacked(jsonStep.getDouble("PercentItemPacked"));
                                           JSONArray details= job.getJSONArray("Details");
                                           if(details.length()>0)
                                           {
                                               ArrayList<OrderResultStepDetail> detailsArray=new ArrayList<>();
                                               int counter=0;
                                               do
                                               {
                                                   JSONObject  jsonDetail=details.getJSONObject(counter);
                                                   OrderResultStepDetail detail = new OrderResultStepDetail();
                                                   detail.setItemId(jsonDetail.getInt("ItemId"));
                                                   detail.setPackedCount(jsonDetail.getDouble("PackedCount"));
                                                   detail.setPercentPackedItem(jsonDetail.getDouble("PercentPackedItem"));
                                                   detailsArray.add(detail);
                                                   counter++;
                                               }while (counter<details.length());

                                               step.setDetails(detailsArray);
                                           }
                                           stepArray.add(step);
                                           stepCounter++;
                                       }while (stepCounter<steps.length());
                                    }

                                    calculate_order.result = or;
                                    if (fillEvent != null)
                                        fillEvent.Fill(or);
                                    vp.setCurrentItem(2);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (Exception e) {
                                Log.e(Constants.TAG_API_REQUEST, e.getMessage());
                            } finally {
                                if(swDialog!=null)
                                    swDialog.dismiss();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if(swDialog!=null)
                                swDialog.dismiss();
                            VolleyErrorDisplay(error);
                        }
                    }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    String j = gson.toJson(model);
                    return j.getBytes();
                }

            };
            request.setRetryPolicy(new DefaultRetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 300000;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 300000;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {
                    if(swDialog!=null)
                        swDialog.dismiss();
                    VolleyErrorDisplay(error);
                }
            });
            return request;
        } catch (Exception e) {
            Log.e(Constants.TAG_API_REQUEST, e.getMessage());
            throw e;
        }
    }
    private void VolleyErrorDisplay(VolleyError error){
        try{
            String errorMessage;
            if (error instanceof NoConnectionError) {
                ConnectivityManager cm = (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = null;
                if (cm != null)
                    activeNetwork = cm.getActiveNetworkInfo();

                if (activeNetwork != null && activeNetwork.isConnectedOrConnecting())
                    errorMessage = context.getResources().getString(R.string.errormessage_volley_server_not_connected_to_internet);
                else
                    errorMessage = context.getResources().getString(R.string.errormessage_volley_device_not_connected_to_internet);
            } else if (error instanceof NetworkError || error.getCause() instanceof ConnectException
                    || (error.getCause().getMessage() != null
                    && error.getCause().getMessage().contains("connection")))
                errorMessage = context.getResources().getString(R.string.errormessage_volley_device_not_connected_to_internet);
            else if (error.getCause() instanceof MalformedURLException)
                errorMessage = context.getResources().getString(R.string.errormessage_volley_bad_request);
            else if (error instanceof ParseError || error.getCause() instanceof IllegalStateException
                    || error.getCause() instanceof JSONException
                    || error.getCause() instanceof XmlPullParserException)
                errorMessage = context.getResources().getString(R.string.errormessage_volley_parse_error);
            else if (error.getCause() instanceof OutOfMemoryError)
                errorMessage = context.getResources().getString(R.string.errormessage_volley_out_of_memory);
            else if (error instanceof AuthFailureError)
                errorMessage = context.getResources().getString(R.string.errormessage_volley_authenticated_error);
            else if (error instanceof ServerError || error.getCause() instanceof ServerError)
                errorMessage = context.getResources().getString(R.string.errormessage_volley_server_not_responding);
            else if (error instanceof TimeoutError || error.getCause() instanceof SocketTimeoutException
                    || error.getCause() instanceof ConnectTimeoutException
                    || error.getCause() instanceof SocketException
                    || (error.getCause().getMessage() != null
                    && error.getCause().getMessage().contains("Connection timed out")))
                errorMessage = context.getResources().getString(R.string.errormessage_volley_connection_time_out);
            else
                errorMessage = context.getResources().getString(R.string.errormessage_volley_unknown_error);

            SweetAlertDialog swd = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
            swd.setTitleText(errorMessage).show();
        }catch (Exception e)
        {
            Log.e(Constants.TAG_API_REQUEST,e.getMessage());
        }
    }
    //#API Request End
    //#Persmission Methods
    public void CheckPermissionAndRun() {
        //API 23 öncesi Uygulama kurulumda izin istediği için uygulama içerisinde izin istemeye gerek yok
        //Bu sebeple kullanıcı SDK versiyonunun 23'ten küçük olup olmaduğu kontrol ediliyor
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (PermissionConfirmation())
                Api_Post_Calculate();
            else
                RequestPermission();
        } else
            Api_Post_Calculate();
    }

    public boolean PermissionConfirmation() {
        int status = 0;
        for (String prm : perms) {
            status = ActivityCompat.checkSelfPermission(this, prm);
            if (!(status == PackageManager.PERMISSION_GRANTED)) {
                return false;
            }
        }
        return true;
    }

    public void RequestPermission() {
        ActivityCompat.requestPermissions(this, perms, API_PERMSISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean permissionConfirmed = true;
        //İzin isteği sonrası gelen istek kodu bizim istediğimiz izine ait
        switch (requestCode) {
            case API_PERMSISSION_CODE:
                for (int status : grantResults) {
                    permissionConfirmed = permissionConfirmed && (status == PackageManager.PERMISSION_GRANTED);
                }
                break;
            default:
                permissionConfirmed = false;
                break;
        }

        if (permissionConfirmed)
            Api_Post_Calculate();
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                Toast.makeText(this, getResources().getString(R.string.errormessage_API_permission_request), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, getResources().getString(R.string.errormessage_API_permission_request), Toast.LENGTH_LONG).show();
            }
        }
    }
    //#Permission Methods End

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageHelper.LoadLocale(getBaseContext());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.title_activity_calculation));
        setContentView(R.layout.activity_calculation);
        vp = findViewById(R.id.a_calc_vp_pager);
        dotsLayout = findViewById(R.id.dotLayout);
        nextButton = findViewById(R.id.btn_next);
        prevButton = findViewById(R.id.btn_prev);
        this.context = this;
        calculate_order = new Order();
        calculate_order.setOrderNo("A00000001");
        initilalizeViewPager();
    }

    // #region ViewPagerMethods


    private void initilalizeViewPager() {
        fpa = new Fpa_calculator(getSupportFragmentManager());
        fpa.addFragement(new Calculation_container_Fragment(), "Container");
        fpa.addFragement(new Calculation_production_Fragment(), "Production");
        fpa.addFragement(new Calculation_result_Fragment(), "Result");
        vp.setAdapter(fpa);
        vp.setSwipeLocked(true);
        addDotsIndicator(0);
        vp.addOnPageChangeListener(viewPagerPageChangeListener);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (currentPage == 0) {
                        if (null != activityListener) {
                            if (!Helper.isNullOrWhitespace(calculate_order.container.definition))
                                vp.setCurrentItem(1);
                            else {
                                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText(getResources().getString(R.string.errormessage_not_selected_container))
                                        .show();
                            }
                        } else {
                            Log.e(Constants.TAG_CALCULATION_VALIDATION, "ActivityEventListener Not Found");
                        }
                    } else if (currentPage == 1) {
                        if (null != activityListener) {
                            if (!calculate_order.products.isEmpty()) {
                                CheckPermissionAndRun();
                            } else {
                                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText(getResources().getString(R.string.errormessage_not_selected_products))
                                        .show();
//                                Toast.makeText(context, getResources().getString(R.string.errormessage_not_selected_products), Toast.LENGTH_LONG.show();
                            }
                        } else {
                            Log.e(Constants.TAG_CALCULATION_VALIDATION, "ActivityEventListener Not Found");
                        }
                    } else {
                        Toast.makeText(context, getResources().getString(R.string.errormessage_invalid_request), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Log.e(Constants.TAG_CALCULATION_VALIDATION, e.getMessage());
                }

            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(currentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position) {
        mdots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0; i < mdots.length; i++) {
            mdots[i] = new TextView(this);
            mdots[i].setText(Html.fromHtml("&#8226;"));
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getResources().getColor(R.color.colorDots));
            dotsLayout.addView(mdots[i]);
        }
        if (mdots.length > 0)
            mdots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
    }
    // #endregion ViewPagerMethods


    // #region Listeners

    ViewPager.OnPageChangeListener viewPagerPageChangeListener =
            new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {
                }

                @Override
                public void onPageSelected(int i) {
                    addDotsIndicator(i);
                    currentPage = i;
                    if (i == 0) {
                        nextButton.setEnabled(true);
                        prevButton.setEnabled(false);
                        prevButton.setVisibility(View.INVISIBLE);
                        nextButton.setVisibility(View.VISIBLE);
                        nextButton.setText(getResources().getString(R.string.calculation_nav_button_next));
                        prevButton.setText("");
                    } else if (i == mdots.length - 1) {
                        nextButton.setEnabled(true);
                        prevButton.setEnabled(true);
                        prevButton.setVisibility(View.VISIBLE);
                        nextButton.setVisibility(View.INVISIBLE);
                        nextButton.setText("");
                        prevButton.setText(getResources().getString(R.string.calculation_nav_button_prev));

                    } else {
                        nextButton.setEnabled(true);
                        prevButton.setEnabled(true);
                        prevButton.setVisibility(View.VISIBLE);
                        nextButton.setVisibility(View.VISIBLE);
                        nextButton.setText(getResources().getString(R.string.calculation_nav_button_calculate));
                        prevButton.setText(getResources().getString(R.string.calculation_nav_button_prev));
                    }
                }

                @Override
                public void onPageScrollStateChanged(int i) {
                }
            };

    //#endregion
}
