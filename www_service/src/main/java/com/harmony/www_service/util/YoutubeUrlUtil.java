package com.harmony.www_service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeUrlUtil {
	public static String extractVideoId(String url) {
		String videoId = null;
		String[] patterns = {
				"(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*",
				"(?<=shorts/)([a-zA-Z0-9_-]+)" };

		for (String pattern : patterns) {
			Pattern compiledPattern = Pattern.compile(pattern);
			Matcher matcher = compiledPattern.matcher(url);
			if (matcher.find()) {
				videoId = matcher.group();
				break;
			}
		}

		return videoId;
	}

}
