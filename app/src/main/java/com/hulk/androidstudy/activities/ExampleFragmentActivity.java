package com.hulk.androidstudy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.hulk.androidstudy.R;

import java.util.List;

/**
 * Created by tzh on 2020/11/11.
 */
public class ExampleFragmentActivity extends FragmentActivity {
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ExampleFragmentActivity", "ExampleFragmentActivity onCreate");
        setContentView(R.layout.activity_example_fragment);
//        fragment = fragmentManager.findFragmentById(R.id.fragment_example);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ExampleFragmentActivity", "ExampleFragmentActivity onStart");
    }

    /**
     * FragmentTransaction提供对Fragment的事务处理:
     * add()
     * replace()
     * remove()
     * hide()
     * show()
     * detach()
     * attach()
     */
    @Override
    protected void onResume() {
        super.onResume();
        findViewById(R.id.bt_test).setOnClickListener(v -> {
            replaceWithAnim();
        });
        findViewById(R.id.bt_test_intent).setOnClickListener(v -> {
//            testIntent();
            testChooserIntent();
        });
        Log.d("ExampleFragmentActivity", "ExampleFragmentActivity onResume");
    }

    private void replaceWithAnim() {
        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
        if (fragment != null) {
            fragment = new FragmentB();
            transaction.replace(R.id.fl_fragment, fragment).addToBackStack(null);
        } else {
            fragment = new FragmentA();
            transaction.add(R.id.fl_fragment, fragment).addToBackStack(null);
        }
        transaction.commit();
    }

    private void testIntent() {
        // Build the intent
        Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(mapIntent);
        }
    }

    private void testChooserIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        //没有设置Uri就需要指定MIME数据类型
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"jon@example.com"}); // 收件人
        intent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
        intent.putExtra(Intent.EXTRA_TEXT, "Email message text");
        // Always use string resources for UI text.
        // This says something like "Share this photo with"
//        String title = getResources().getString(R.string.chooser_title);
        // Create intent to show chooser
//        Intent chooser = Intent.createChooser(intent, title);

        // Verify the intent will resolve to at least one activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ExampleFragmentActivity", "ExampleFragmentActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("ExampleFragmentActivity", "ExampleFragmentActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("ExampleFragmentActivity", "ExampleFragmentActivity onDestroy");
    }

    public static class FragmentA extends Fragment {
        @Override
        public void onAttach(@NonNull Context context) {
            super.onAttach(context);
            Log.d("ExampleFragment", "ExampleFragment onAttach");
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Log.d("ExampleFragment", "ExampleFragment onCreate");
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            Log.d("ExampleFragment", "ExampleFragment onCreateView");
            return inflater.inflate(R.layout.fragment_example, container, false);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            Log.d("ExampleFragment", "ExampleFragment onActivityCreated");
            super.onActivityCreated(savedInstanceState);
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.d("ExampleFragment", "ExampleFragment onStart");
        }

        @Override
        public void onResume() {
            super.onResume();
            Log.d("ExampleFragment", "ExampleFragment onResume");
        }

        @Override
        public void onPause() {
            super.onPause();
            Log.d("ExampleFragment", "ExampleFragment onPause");
        }

        @Override
        public void onStop() {
            super.onStop();
            Log.d("ExampleFragment", "ExampleFragment onStop");
        }

        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.d("ExampleFragment", "ExampleFragment onDestroyView");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.d("ExampleFragment", "ExampleFragment onDestroy");
        }

        @Override
        public void onDetach() {
            super.onDetach();
            Log.d("ExampleFragment", "ExampleFragment onDetach");
        }
    }


    public static class FragmentB extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_example_b, container, false);
        }
    }
}
