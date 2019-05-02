package com.example.twitter3;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
//Clase que manejar las clases de Fragments
public class ViewPagerAdapter extends FragmentPagerAdapter {

    //Variables
    private final List<Fragment> fragmentsList = new ArrayList<>();
    private final List<String> FragmentListTitles= new ArrayList<>();
    //Constructor
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentsList.get(i);//obtiene la posicion del elemento de la lista
    }

    @Override
    public int getCount() {//se adapta dependiendo del numero de tabs que tengamos
        return FragmentListTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {//devolveria el titulo del tab en que uno se encuentre
        return FragmentListTitles.get(position);
    }

    public void addFragments(Fragment fragment, String Title){ //metodo que añadiria titulos a tabs
        fragmentsList.add(fragment);
        FragmentListTitles.add(Title);
    }
}
