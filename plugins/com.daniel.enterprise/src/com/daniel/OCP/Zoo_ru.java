package com.daniel.OCP;

import java.util.ListResourceBundle;

public class Zoo_ru extends ListResourceBundle{

	@Override
	protected Object[][] getContents() {
		return new Object[][]{
			{"hello", "Здравствуйте"},
			{"open", "Зоопарк открыт для посещения"}
		};
	}

}
