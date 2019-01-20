package unislask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Parser {
	static final String[] OPTIONS = { "series", "authorpath", "bookspath", "author" };

	Data parse(String path) {
		File root = new File(path);

		String[] subFiles = root.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return !(new File(dir, name).isDirectory());
			}
		});

		for (String str : subFiles) {
			if (str.compareTo("settings.ini") == 0) {
				try {
					HashMap<String, String> settings = parseSetting(new File("settings.ini"));
					for (Map.Entry<String, String> entry : settings) {
						boolean exist = false;
						for (String option : OPTIONS) {
							if ((entry.getValue().compareTo(option) == 0)) {
								exist = true;
							}
						}
						if (exist)
							;
					}
					if ("TRUE".compareTo(settings.get("series").toUpperCase()) == 0) {

					}
				} catch (FileNotFoundException e) {

				}
			}

		}
		String[] subDirectory = root.list(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});

	}

	HashMap<String, String> parseSetting(File settingsf) throws FileNotFoundException {
		Scanner scanner = new Scanner(settingsf);
		HashMap<String, String> settings = new HashMap<>();
		while (scanner.hasNext()) {
			String tmp = scanner.nextLine();
			String[] r = tmp.split("=");
			settings.put(r[0], r[1]);
		}
		scanner.close();
		return settings;
	}
}