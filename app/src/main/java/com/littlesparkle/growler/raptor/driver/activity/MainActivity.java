package com.littlesparkle.growler.raptor.driver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;

import com.amap.api.maps2d.model.LatLng;
import com.littlesparkle.growler.library.activity.BaseActivity;
import com.littlesparkle.growler.library.bean.Car;
import com.littlesparkle.growler.library.bean.CarBrand;
import com.littlesparkle.growler.library.bean.CarSerie;
import com.littlesparkle.growler.library.bean.Driver;
import com.littlesparkle.growler.library.dialog.DialogHelper;
import com.littlesparkle.growler.library.user.UserManager;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.fragment.CurrentFragment;
import com.littlesparkle.growler.raptor.driver.fragment.MeFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tab_host)
    SmartTabLayout mSmartTabLayout;

    @BindView(R.id.map_mode)
    AppCompatButton mMapMode;

    @OnClick(R.id.map_mode)
    public void onMapModeClick(AppCompatButton button) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("passenger", new LatLng(39.979004, 116.508715));
        startActivity(intent);
    }

    @BindView(R.id.order_mode)
    AppCompatButton mOrderMode;

    @OnClick(R.id.order_mode)
    public void onOrderModeClick(AppCompatButton button) {
        DialogHelper.showDialogWithMessage(this, getString(R.string.order_mode));
    }

    @BindView(R.id.shutdown_mode)
    AppCompatButton mShutdownMode;

    @OnClick(R.id.shutdown_mode)
    public void onShutDownModeClick(AppCompatButton button) {
        DialogHelper.showDialogWithMessage(this, getString(R.string.shutdown_mode));
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    private void initUI(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.tab_item_current, CurrentFragment.class)
                .add(R.string.tab_item_me, MeFragment.class)
                .create());

        mViewPager.setAdapter(adapter);
        mSmartTabLayout.setViewPager(mViewPager);
        mSmartTabLayout.setOnPageChangeListener(mOnPageChangeListener);

        Car car = new Car();
        car.car_id = 123;
        car.car_number = "A12345";
        car.vehicle_license_pic = "A33333";

        CarBrand cb = new CarBrand();
        cb.car_brand_id = 333;
        cb.name = "xxxx";

        CarSerie cs = new CarSerie();
        cs.car_serie_id = 444;
        cs.name = "ewrewrew";

        Driver driver = new Driver();
        driver.driver_type = 1;
        driver.driving_license = "fdsfds";
        driver.driving_license_pic = "fwfew";
        driver.driving_license_type = "kkjkj";
        driver.idcard_number = "009809";
        driver.is_online = 2;
//        driver.car = car;
//        driver.car_brand = cb;
//        driver.car_serie = cs;
        driver.persist(this);

        Driver d = new Driver();
        d.load(this);
        d.dump();

        UserManager.signOut(this);

        Driver d2 = new Driver();
        d2.load(this);
        d2.dump();
    }
}