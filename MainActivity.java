package com.sas.delhibusnavigator;



import java.io.IOException;

import java.util.ArrayList;

import java.util.LinkedList;

import java.util.Locale;

import android.app.ActionBar;

import android.app.Activity;

import android.app.AlertDialog;

import android.app.FragmentTransaction;

import android.content.ContentValues;

import android.content.DialogInterface;



public class MainActivity extends FragmentActivity implements ActionBar.TabListener {



   SectionsPagerAdapter mSectionsPagerAdapter;



   ViewPager mViewPager;







@Override

protected void onCreate(Bundle savedInstanceState) {

super.onCreate(savedInstanceState);

      setContentView(R.layout.activity_main);



final ActionBar actionBar = getActionBar();

      actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);





mSectionsPagerAdapter = new SectionsPagerAdapter(

            getSupportFragmentManager());



mViewPager = (ViewPager) findViewById(R.id.pager);

mViewPager.setAdapter(mSectionsPagerAdapter);



mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

@Override

public void onPageSelected(int position) {

actionBar.setSelectedNavigationItem(position);

               }

            });



for (int i = 0; i <mSectionsPagerAdapter.getCount(); i++) {

         actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));

      }



// Toast t=Toast.makeText(getApplicationContext(), "here", Toast.LENGTH_LONG);

       //t.show();



Intent x = new Intent("com.sas.delhibusnavigator.TUTORIALACTIVITY");

      String query = "SELECT * FROM " + SplashActivity.table;

      Cursor c = SplashActivity.check.rawQuery(query, null);

if(c.getCount() == 0) {

         c.close();

         ContentValues values = new ContentValues();

         values.put(SplashActivity.Column_val, "1");

         SplashActivity.check.insert(SplashActivity.table, null, values);

         x.putExtra("val", 1);

         startActivity(x);

      } else {

         c.moveToFirst();

int val = Integer.parseInt("" + c.getString(0));

if (val <= 2) {

            x.putExtra("val", val);

            startActivity(x);

         }

      }

   }



@Override

public boolean onCreateOptionsMenu(Menu menu) {

      getMenuInflater().inflate(R.menu.main, menu);

return true;

   }



@Override

public boolean onOptionsItemSelected(MenuItem item) {

switch (item.getItemId()) {

case R.id.rate:

         Toast.makeText(this, "Rate Us", Toast.LENGTH_LONG).show();



return true;

case R.id.share:

         Intent localIntent = new Intent("android.intent.action.SEND");

          localIntent.setType("text/plain");

          localIntent.putExtra("android.intent.extra.SUBJECT", "Delhi Bus (DTC) Navigator");

          localIntent.putExtra("android.intent.extra.TEXT", "New to Delhi? Having difficulty in travelling? Now, travel across DELHI (NCR) through DTC Buses with 'Delhi Bus Navigator' - Get detail about every Bus Route of the DTC Network.");

          startActivity(Intent.createChooser(localIntent, "Share Via"));

return true;

case R.id.about:

         Intent about = new Intent("com.sas.delhibusnavigator.ABOUTACTIVITY");

         startActivity(about);

return true;

case R.id.disclaimer:

         Intent disclaimer = new Intent("com.sas.delhibusnavigator.DISCLAIMERACTIVITY");

         startActivity(disclaimer);

return true;

case R.id.tourism:

         Intent tour = new Intent("com.sas.delhibusnavigator.TOURISMACTIVITY");

         startActivity(tour);

return true;



case R.id.exit:

this.finish();

return true;

default:

return super.onOptionsItemSelected(item);

      }

   }



@Override

public boolean onKeyDown(int keyCode, KeyEvent event) {

if (keyCode == KeyEvent.KEYCODE_BACK) {

new AlertDialog.Builder(this)

         .setIcon(R.drawable.ic_action_alert)

         .setTitle(R.string.dialog_title)

         .setMessage(R.string.dialog_message)

         .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

public void onClick(DialogInterface dialog, int id) {

               MainActivity.this.finish();

            }

         })

         .setNegativeButton(R.string.no, null)

         .setCancelable(false)

         .show();

      }

return super.onKeyDown(keyCode, event);

   }



@Override

protected void onDestroy() {



super.onDestroy();

   }



@Override

public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

mViewPager.setCurrentItem(tab.getPosition());

   }



@Override

public void onTabUnselected(ActionBar.Tab tab,

         FragmentTransaction fragmentTransaction) {

   }



@Override

public void onTabReselected(ActionBar.Tab tab,

         FragmentTransaction fragmentTransaction) {

   }



public class SectionsPagerAdapter extends FragmentPagerAdapter {



public SectionsPagerAdapter(FragmentManager fm) {

super(fm);

      }



@Override

public Fragment getItem(int position) {

         Fragment fragment = new DummySectionFragment();

         Bundle args = new Bundle();

         args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position);

         fragment.setArguments(args);

return fragment;

      }



@Override

public int getCount() {

return 3;

      }



@Override

public CharSequence getPageTitle(int position) {

         Locale l = Locale.getDefault();

switch (position) {

case 0:

return getString(R.string.title_section1).toUpperCase(l);

case 1:

return getString(R.string.title_section2).toUpperCase(l);

case 2:

return getString(R.string.title_section3).toUpperCase(l);

         }

return null;

      }

   }



public static class DummySectionFragment extends Fragment {



public static final String ARG_SECTION_NUMBER = "section_number";

int position;

      View rootView;


                  } else if (actv1.getText().toString().equals("")) {

                     Toast.makeText(getActivity(), "Source cannot be blank", Toast.LENGTH_SHORT).show();

                  } else if (actv2.getText().toString().equals("")) {

                     Toast.makeText(getActivity(), "Destination cannot be blank", Toast.LENGTH_SHORT).show();

                  } else if (actv1.getText().toString().equalsIgnoreCase(actv2.getText().toString())) {

                     Toast.makeText(getActivity(), "Source and Destination cannot be same", Toast.LENGTH_LONG).show();

                  } else {

                     Intent i = new Intent("com.sas.delhibusnavigator.VIEWROUTEACTIVITY");

                     i.putExtra("source", actv1.getText().toString());

                     i.putExtra("destination", actv2.getText().toString());

                     startActivity(i);

                  }

               }

            });



ibSrc.setOnClickListener(new OnClickListener(){



@Override

public void onClick(View arg0) {

                  Intent i1 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                  i1.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                  i1.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak The Source!!");

                  startActivityForResult(i1,100);



               }





            });



ibDest.setOnClickListener(new OnClickListener(){



@Override

public void onClick(View arg0) {

                  Intent i2 = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                  i2.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                  i2.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak The Destination!!");

                  startActivityForResult(i2,150);



               }





            });



break;

case 2:

rootView = inflater.inflate(R.layout.fragment_main_dummy3, container, false);

actv3 = (AutoCompleteTextView) rootView.findViewById(R.id.actvStop);

bFind = (ImageButton) rootView.findViewById(R.id.bFindStop);

lvStops = (ListView) rootView.findViewById(R.id.lvStops);

llSearch = myDbHelper.getAutoStop();

aa1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, llSearch);

actv3.setAdapter(aa1);

aa2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, llStops);

bFind.setOnClickListener(new OnClickListener() {



@Override

public void onClick(View v) {

                  String stop = actv3.getText().toString();

                  InputMethodManager imm = (InputMethodManager) getActivity().getSystemService("input_method");

                  imm.hideSoftInputFromWindow(actv3.getWindowToken(), 0);

llStops.clear();

aa2.clear();

lvStops.setAdapter(null);

if (stop.contentEquals(""))

                     Toast.makeText(getActivity(), "Enter Stop Name", Toast.LENGTH_LONG).show();

else {

llStops = myDbHelper.searchStop(stop);

if (llStops.isEmpty())

                        Toast.makeText(getActivity(), "Nothing found", Toast.LENGTH_LONG).show();

else {

aa2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, llStops);

lvStops.setAdapter(aa2);

                     }

                  }

               }

            });

lvStops.setOnItemClickListener(new OnItemClickListener() {



@Override

public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                  Intent i = new Intent("com.sas.delhibusnavigator.ROUTEACTIVITY");

                  i.putExtra("bno", "" + lvStops.getItemAtPosition(arg2).toString());

                  startActivity(i);

               }

            });

break;

default:

break;

         }

return rootView;

      }

   }





}
