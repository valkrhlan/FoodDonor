package com.coky.app.test;

import com.coky.app.Prijava;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class PrijavaDonorNoviPaket extends ActivityInstrumentationTestCase2<Prijava> {
  	private Solo solo;
  	
  	public PrijavaDonorNoviPaket() {
		super(Prijava.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Wait for activity: 'com.coky.app.Prijava'
		solo.waitForActivity(com.coky.app.Prijava.class, 2000);
        //Set default small timeout to 21743 milliseconds
		Timeout.setSmallTimeout(21743);
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.coky.app.R.id.textEmail));
        //Enter the text: 'donor@test.hr'
		solo.clearEditText((android.widget.EditText) solo.getView(com.coky.app.R.id.textEmail));
		solo.enterText((android.widget.EditText) solo.getView(com.coky.app.R.id.textEmail), "donor@test.hr");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.coky.app.R.id.textPassword));
        //Enter the text: 'test'
		solo.clearEditText((android.widget.EditText) solo.getView(com.coky.app.R.id.textPassword));
		solo.enterText((android.widget.EditText) solo.getView(com.coky.app.R.id.textPassword), "test");
        //Click on Prijavi se
		solo.clickOnView(solo.getView(com.coky.app.R.id.prijavaBtn));
        //Wait for activity: 'com.coky.app.GlavnaAktivnost'
		assertTrue("com.coky.app.GlavnaAktivnost is not found!", solo.waitForActivity(com.coky.app.GlavnaAktivnost.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.coky.app.R.id.btnDesno));
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.coky.app.R.id.editNazivHraneNP));
        //Enter the text: 'Jabuke'
		solo.clearEditText((android.widget.EditText) solo.getView(com.coky.app.R.id.editNazivHraneNP));
		solo.enterText((android.widget.EditText) solo.getView(com.coky.app.R.id.editNazivHraneNP), "Jabuke");
        //Click on Empty Text View
		solo.clickOnView(solo.getView(com.coky.app.R.id.editKolicinaNP));
        //Enter the text: '5'
		solo.clearEditText((android.widget.EditText) solo.getView(com.coky.app.R.id.editKolicinaNP));
		solo.enterText((android.widget.EditText) solo.getView(com.coky.app.R.id.editKolicinaNP), "5");
        //Click on Dodaj stavku
		solo.clickOnView(solo.getView(com.coky.app.R.id.btnDodajStavkuNP));
        //Click on Dodaj paket
		solo.clickOnView(solo.getView(com.coky.app.R.id.btnDodajPaketNP));
        //Click on Odustani
		solo.clickOnView(solo.getView(com.coky.app.R.id.btnNatragNoviPaket));
	}
}
