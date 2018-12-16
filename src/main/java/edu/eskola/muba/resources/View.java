package edu.eskola.muba.resources;

import java.util.ListResourceBundle;

public class View extends ListResourceBundle {
	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return contents;
	}
	
	static final Object[][] contents = {
			// Header resources
			{"header.home", "Home"},
			{"header.team", "Team"},
			{"header.league", "League"},
			{"header.play", "Play"},
			{"header.account", "Account"},
			// Languages
			{"language.en", "English"},
			{"language.es", "Spanish"},
			{"language.eu", "Basque"},
			// Actions
			{"action.login", "Login"},
			{"action.logout", "Logout"},
			{"action.register", "Register"},
	};
}
