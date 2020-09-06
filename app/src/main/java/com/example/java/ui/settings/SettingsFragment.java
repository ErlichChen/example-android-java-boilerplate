package com.example.java.ui.settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.java.R;
import com.example.java.base.BaseActivity;
import com.example.java.base.BaseFragment;
import com.google.android.material.tabs.TabLayout;

public class SettingsFragment extends BaseFragment {

    private SettingsViewModel settingsViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void init() {
        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getActivity(), getActivity().getSupportFragmentManager());

//        View root = inflater.inflate(R.layout.fragment_settings, container, false);

//        ViewPager viewPager = root.findViewById(R.id.view_pager);
//        viewPager.setAdapter(sectionsPagerAdapter);
//
//        TabLayout tabs = root.findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);


//        final TextView textView = root.findViewById(R.id.text_settings);
        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
    }

}
