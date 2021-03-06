package test;


import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import builder.Transfers;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import serv.Similar;
import serv.Simplify;

public class Playground {
	
	public static String executePost(String targetURL) {
		  HttpURLConnection connection = null;

		  try {
		    //Create connection
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    //connection.setRequestProperty("Content-Type", 
		       // "application/x-www-form-urlencoded");

//		    connection.setRequestProperty("Content-Length", 
//		        Integer.toString(urlParameters.getBytes().length));
//		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    //wr.writeBytes(urlParameters);
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if (connection != null) {
		      connection.disconnect();
		    }
		  }
		}



	public static void main(String[] args) {
		
		System.out.println((char)304);
		
		
		System.out.println(Similar.similarity("Olympique Lyonnais", "Olympique Lyon"));
		
		/*String a = executePost("http://www.espnfc.us/api/transfers?limit=20");
		
		JsonObject jsonObj = new JsonParser().parse(a).getAsJsonObject();
		
		
		JsonArray data = jsonObj.getAsJsonObject("data").get("transferGroups").getAsJsonArray();
		
		
		for (int i=0; i<data.size(); i++) {
			JsonObject chunk = data.get(i).getAsJsonObject();
			JsonArray transfers = chunk.get("transfers").getAsJsonArray();
			for (int j=0; j<transfers.size(); j++) {
				JsonObject playerChunk = transfers.get(j).getAsJsonObject();
				String playerName = playerChunk.get("playerName").getAsString();
				String fromTeam = playerChunk.get("fromTeamName").getAsString();
				String toTeam = playerChunk.get("toTeamName").getAsString();
				System.out.println(playerName + " | " + fromTeam + " | " + toTeam);
			}

		}*/
		
//		JsonObject data2 = data.get(0).getAsJsonObject();
//		
//		JsonArray data3 = data2.get("transfers").getAsJsonArray();
//		
//		System.out.println(data3);
		
		
		
//		System.out.println(Similar.similarity("AS Saint-Étienne", "St Etienne"));
		
		/*String[] a = {"Atalanta", "Bologna", "Cagliari","Chievo Verona","Crotone","Empoli","Fiorentina","Genoa","Inter","Juventus","Lazio","Legia Warszawa","Milan","Napoli","Palermo","Pescara","Roma","Sampdoria","Sassuolo","Torino","Udinese"};
		
		for (String x: a) {
			System.out.println(Similar.similarity("St Etienne", x) >= .7);
		}*/
		
		/*HashMap<String, String> a = Transfers.populateLeagueTeams();
		
		System.out.println(a.get(" Watford"));
		
		System.out.println(Similar.similarity(" Watford", "Watford") >= .500);
		
		System.out.println(Transfers.getLeague("Watford", a));*/

/*
		HashMap<String, String> leagueTeams = new HashMap<String, String>();

		try {
			BufferedReader reader = new BufferedReader (new FileReader("regenerate-players/fullTable.csv")); 
			String line;

			String[] s;

			while ((line = reader.readLine()) != null) {

				s = line.split(",");

				leagueTeams.put(s[1], s[0]);


			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (HashMap.Entry<String, String> entry : leagueTeams.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			
			//System.out.println(key + " | " + value);
		}
*/


			//		printSimilarity("", "");
			//	    printSimilarity("1234567890", "1");
			//	    printSimilarity("1234567890", "123");
			//	    printSimilarity("1234567890", "1234567");
			//	    printSimilarity("1234567890", "1234567890");
			//	    printSimilarity("1234567890", "1234567980");
			//	    printSimilarity("47/2010", "472010");
			//	    printSimilarity("47/2010", "472011");
			//	    printSimilarity("47/2010", "AB.CDEF");
			//	    printSimilarity("47/2010", "4B.CDEFG");
			//	    printSimilarity("47/2010", "AB.CDEFG");
			//	    printSimilarity("The quick fox jumped", "The fox jumped");
			//	    printSimilarity("The quick fox jumped", "The fox");
			//	    printSimilarity("Neymar JR", "Neymar");


			//		String a  = "FCdeMetz";
			//		
			//		System.out.println(a.replaceAll("([a-zA-Z])de([A-Z])", "$1$2"));

			/*String cleanedPD = "Arsenal 1-1 Sevilla - Lacazette 62' (Emirates Cup)";

		String x[] = {"Arsenal","Sevilla"};

		for (String s : x) {

			cleanedPD = cleanedPD.replaceAll("\\s" + s + "\\s|^" + s + "\\s|\\s" + s + "$", "");
		}

		System.out.println(cleanedPD);
			 */

			/*System.out.println(simplify.simplifyName("Džeko"));

		System.out.println((char)122);

		String a = "Nice goal for Andre Balada goal vs lisbon 4-3";

		HashMap<String, Integer> list = new HashMap<String, Integer>();

		list.put("Andre Belada", 80);
		list.put("Andre", 80);



		String[] keys = list.keySet().toArray(new String[list.size()]);
		for (int i = 0; i < keys.length; i++) {
		  for (int j = 0; j<keys.length; j++) {
		    if (i != j) {
		      if (keys[j].contains(keys[i])) {
		    	  list.put(keys[i], list.get(keys[i]) - 30);
		      }
		    }
		  }
		}

		for (HashMap.Entry<String, Integer> entry : list.entrySet()) {
			String key = entry.getKey();
			Integer value = entry.getValue();

			System.out.println(key + " at " + value);
		}*/



			//		if (m.find()) {
			//			System.out.println(a.substring(m.start(), m.end()));
			//		} else {
			//			System.out.println(a);
			//		}

			//		int points = 80;
			//		String a = "Sevilla FC vs Borussia Dortmund Max Sevilla 4-1 second goal";
			//		String player = "Sevilla";
			//		
			//		int vsLoc = 0;
			//		ArrayList<Integer> pLoc = new ArrayList<Integer>();
			//		
			//		String[] b = a.split(" ");
			//		for (int i=0; i<b.length - 1; i++) {
			//			if (b[i].equals("vs")) {
			//				System.out.println("vs found at: " + i);
			//				vsLoc = i;
			//			} 
			//			if (b[i].equals(player.split(" ")[0])) {
			//				if (player.split(" ").length == 1) {
			//					System.out.println("name found at pos: " + i);
			//					pLoc.add(i);
			//				} else if (player.split(" ").length > 1) {
			//					if (b[i+1].equals(player.split(" ")[1])) {
			//						System.out.println("name found at pos: " + i);
			//						pLoc.add(i);
			//					}
			//				}
			//			}
			//		}
			//		
			//		for (int l : pLoc) {
			//			if (Math.abs(l - vsLoc) < 3) {
			//				points -= 10;
			//				break;
			//			}
			//		}
			//		
			//		System.out.println(points);


			//		ArrayList<String> teamsSameLeague = new ArrayList<String>();
			//		
			//		Connection connection1 = null;
			//		ResultSet resultSet1 = null;
			//		Statement statement1 = null;
			//		
			//		String player = "Cristiano Ronaldo";
			//		
			//		try {
			//			System.out.println("trying to connect");
			//			String url = "jdbc:sqlite:../server/db/player.db";
			//			connection1 = DriverManager.getConnection(url);
			//			String sql = "Select league from player where player = ' " + player + "';"; 
			//			statement1 = connection1.createStatement();
			//			resultSet1 = statement1.executeQuery(sql);
			//			String league = "";
			//			while(resultSet1.next()) {
			//				league  = resultSet1.getString("league");
			//				System.out.println(league);
			//			}
			//			league = league.trim();
			//			System.out.println(league);
			//			
			//			
			//			Connection connection2 = null;
			//			ResultSet resultSet2 = null;
			//			Statement statement2 = null;
			//						
			//			try {
			//				System.out.println("trying to connect 2");
			//				connection2 = DriverManager.getConnection(url);
			////				keyword = keyword.replace("'", "''");
			//				String sql2 = "Select distinct Team from player where league= ' " + league +  "';";
			//				statement2 = connection2.createStatement();
			//				resultSet2 = statement2.executeQuery(sql2);
			//				
			//				while(resultSet2.next()) {
			//					teamsSameLeague.add(simplify.simplifyName(resultSet2.getString("team").trim().replaceAll("^[a-zA-Z]{1,3}\\s|\\s[a-zA-Z]{1,3}$|\\s[a-zA-Z]{1,3}\\s|[0-9]+", "").trim()));
			//				}
			//				
			//
			//
			////				return subscribedUsers;
			//
			//			} catch (SQLException e) {
			////				logger.error(e.toString());
			//				e.printStackTrace();
			//			} finally {
			//				try {
			//					if (connection2 != null) {
			//						resultSet2.close();
			//
			//						statement2.close();
			//						connection2.close();
			//					}
			//				} catch (SQLException ex) {
			//					ex.printStackTrace();
			////					logger.error(ex.toString());
			//				}
			//			}
			//			
			//
			//
			////			return subscribedUsers;
			//
			//		} catch (SQLException e) {
			////			logger.error(e.toString());
			//			e.printStackTrace();
			//		} finally {
			//			try {
			//				if (connection1 != null) {
			//					resultSet1.close();
			//
			//					statement1.close();
			//					connection1.close();
			//				}
			//			} catch (SQLException ex) {
			//				ex.printStackTrace();
			////				logger.error(ex.toString());
			//			}
			//		}
			//		
			//		for (String a : teamsSameLeague) {
			//			System.out.println(a);
			//		}
			//		

			//		URL yahoo = null;
			//		try {
			//			yahoo = new URL("https://api.streamable.com/videos/wwbna");
			//		} catch (MalformedURLException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}
			//        URLConnection yc = null;
			//		try {
			//			yc = yahoo.openConnection();
			//		} catch (IOException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}
			//        BufferedReader in = null;
			//		try {
			//			in = new BufferedReader(
			//			                        new InputStreamReader(
			//			                        yc.getInputStream()));
			//		} catch (IOException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}
			//		
			//        String inputLine = null;
			//        String output = "";
			//        try {
			//			while ((inputLine = in.readLine()) != null) {
			//			    System.out.println(inputLine);
			//			    output = inputLine;
			//			}
			//			System.out.println(output);
			//        }
			//        catch (IOException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}
			//        try {
			//			in.close();
			//		} catch (IOException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}
			//        
			//        System.out.println("output is " + output);
			//        JsonObject jsonObj = new JsonParser().parse(output).getAsJsonObject();
			//		String url = jsonObj.get("files").getAsJsonObject().get("mp4").getAsJsonObject().get("url").getAsString();
			//		System.out.println(url);
			//		
			//		String fullUrl = "https:"+url;
			//		System.out.println(fullUrl);
			//        

			//		VideoUpload vu = new VideoUpload();
			//		try {
			//			vu.tweetTweetWithVideo("https://my.mixtape.moe/eeogvd.mp4", "test content");
			//		} catch (IOException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		} catch (InterruptedException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		} catch (JSONException e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}

			//		Twitter twitter = new TwitterFactory().getInstance();
			////		twitter.setOAuthConsumer(consumerKey, consumerSecret);
			////        AccessToken accessToken = new AccessToken(accessTokenStr,accessTokenSecret);
			////        twitter.setOAuthAccessToken(accessToken);
			//		
			//        //twitter.setOAuthConsumer(consumerKey, consumerSecret);
			//        //AccessToken accessToken = new AccessToken(accessTokenStr,accessTokenSecret);
			//        //twitter.setOAuthAccessToken(accessToken);
			//        File file = new File("/home/jonathan/Downloads/video.mp4");
			//        //UploadedMedia media = twitter.uploadMedia(file);
			//        
			//        StatusUpdate update = new StatusUpdate("testing");
			//        UploadedMedia media = twitter.uploadMedia(arg0, arg1);
			//        
			//        long mediaIds[] = new long[1];
			//        mediaIds[0] = media.getMediaId();
			//        update.setMediaIds(mediaIds);
			//        
			//        twitter.updateStatus(update);
			//        
			//        System.out.println("it may have worked...");

			//StatusUpdate statusUpdate = new StatusUpdate(text);
			//statusUpdate.setMedia(media.getMediaId());
			//twitter.updateStatus(statusUpdate.inReplyToStatusId(tweetId));`





			//		String postDescription = "celtic Man United goal vs Celtic 4-5";
			//		
			//		String tm = "celtic";
			//		
			//		String cleanedPostDescription = postDescription.replaceAll("[Uu]nited","");
			//		
			//		String teamRegex = "((^|\\s|\\()" + tm + "(\\)|'|\\s|$))|((^|\\s|\\()" + simplify.simplifyName(tm) + "(\\)|'|\\s|$))";
			//		
			//		Pattern teamP = Pattern.compile(teamRegex, Pattern.CASE_INSENSITIVE);
			//		Matcher teamM = teamP.matcher(cleanedPostDescription);
			//		
			//		if (teamM.find()) {
			//			System.out.println("regex found [" + cleanedPostDescription.substring(teamM.start(), teamM.end()).trim() + "] treated as [" + tm + "]");
			//		} else {
			//			System.out.println("found nothing");
			//		}



			//		String postDescription = "Germany 1-0 Cameroon - Demirbay 48' (FIFA Confederations Cup - Group B) - Streamable links in the comment section";
			//		
			//		String url = "https://my.mixtape.moe/bjdlnm.mp4";
			//		
			//		String playerHashtag = " #Demirbay";
			//		
			//		String team = "1899Hoffenheim";
			//				
			//		String teamHashtag = " #" + team.replaceAll("\\s|[-]|[!]|[$]|[%]|[\\^]|[&]|[\\*]|[\\+]|[']|\\d","");
			//		
			//		String countryHashtag = " #Germany";
			//		
			//		int maxPostLength = 140 - url.length() - countryHashtag.length() - teamHashtag.length() - playerHashtag.length() - 6 - 1; //140 max twitter, 3 is 1x" | ", second 3 is ..., 1 is off by 1 error below due to whitespace added
			//				
			//		String ellipsePost = "";
			//		String[] postParts = postDescription.split(" ");
			//		for (int i=0; i<postParts.length;i++) {
			//			if (ellipsePost.length() + postParts[i].length() < maxPostLength) {
			//				ellipsePost += postParts[i] + " ";
			//			} else {
			//				System.out.println(ellipsePost);
			//				break;
			//			}
			//		}
			//		ellipsePost = ellipsePost.trim();
			//		ellipsePost += "...";
			//		
			//		System.out.println("ellipse length is: " + ellipsePost.length());
			//		String stat = ellipsePost + " | " + url;
			//
			//		if (!playerHashtag.equals("") && (stat.length() + playerHashtag.length() <= 140)) {
			//			stat += playerHashtag;
			//		}
			//		if (!teamHashtag.equals("") && (stat.length() + teamHashtag.length() <= 140)) {
			//			stat += teamHashtag;
			//		}
			//		if (!countryHashtag.equals("") && (stat.length() + countryHashtag.length() <= 140)) {
			//			stat += countryHashtag;
			//		}		
			//		
			//		System.out.println(stat.length());
			//		System.out.println(stat);
			//		
			//		System.out.println(playerHashtag + " | " + playerHashtag.length());
			//		System.out.println(teamHashtag + " | " + teamHashtag.length());
			//		System.out.println(countryHashtag + " | " + countryHashtag.length());

			//		String postDescription = "Wissam Ben Yedder 3:6 against Marseille";
			//		
			//		String minName = "Marco Reus";
			//		
			//		String teamName = "AZ"; //not actually the name
			//		
			//		postDescription = postDescription.replaceAll("([A-Z])\\.(\\s\\w)", "$1$2"); //M. Reus -> M Reus
			//		if ((postDescription.charAt(0) == 'M' || postDescription.charAt(0) == 'D') && postDescription.charAt(1) == ' ') {
			//			postDescription = postDescription.substring(2); //M Reus - > Reus
			//		}
			//		
			//		String hashtag = "";
			//		String[] fullN = minName.split(" ");
			//		if (fullN.length == 1) {
			//			hashtag = fullN[0];
			//		} else if (fullN.length == 2) {
			//			hashtag = fullN[1];
			//		} else {
			//			hashtag = fullN[fullN.length - 2] + fullN[fullN.length - 1];
			//		}
			//		/////////////////////////
			//		String[] teamParts = teamName.split(" ");
			//		String teamHashtag = "";
			//		if (teamParts.length == 1) {
			//			teamHashtag = teamParts[0];
			//		} else {
			//			for (int i=0; i<teamParts.length;i++) {
			//				if (!teamParts[i].equals(teamParts[i].toUpperCase())) {
			//					teamHashtag += teamParts[i];
			//				}
			//			}
			//		}
			//		
			//		
			//		System.out.println(teamHashtag);
			//		
			//		String stat = postDescription + " | " + "http://google.com" + " #" + hashtag.replaceAll("\\s|[-]|[!]|[$]|[%]|[\\^]|[&]|[\\*]|[\\+]","");
			//		
			//		System.out.println(stat);

			//		Calendar calendar = Calendar.getInstance();
			//		int hours = calendar.get(Calendar.HOUR_OF_DAY);
			//		
			//		System.out.println("hours is" + hours);

			//		System.out.println(simplify.simplifyName("Mesut Özil"));

			/*String postDescription = "Juventus 1-3 Real Madrid (Ronaldo 63')";

		String player = "Ronaldo";

		String reg = "((^|\\s|\\()" + player + "(\\)|'|\\s|$))|((^|\\s|\\()" + simplify.simplifyName(player) + "(\\)|'|\\s|$))";
		Pattern p = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(postDescription);

		if (m.find()) {
			System.out.println(postDescription.substring(m.start(), m.end()));
		}*/


			/*String player = "Mickaël Le Bihan";

		System.out.println(player.toUpperCase());*/

			/*String score = "Reus goal 5-4 against Bayern";

		String regex = "[\\[|(]?[0-9][\\]|)]?-[0-9]";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(score);

		if (m.find()) {
			System.out.println(score.substring(m.start(), m.end()));
		}*/

			/*String postDescription = "Marco Reus Mickael Le Bihan's 2nd goal vs. Ronaldo Sporting Gijon (2-3) against Cristiano Ronaldo Ronaldinho Ronaldo";

		//String player = "Mickaël Le Bihan";


//		String reg = "((^|\\s)" + player + "('|\\s|$))|((^|\\s)" + simplify.simplifyName(player) + "('|\\s|$))";
//		Pattern p = Pattern.compile(reg);
//		Matcher m = p.matcher(postDescription);
//
//		
//		if (m.find()) {
//			System.out.println(postDescription.substring(m.start(), m.end()));
//		} else {
//			System.out.println("not found");
//		}


//		System.out.println(simplify.simplifyName("Mickaël Le Bihan"));
//		System.out.println(postDescription.contains(simplify.simplifyUTF8Name("Mickaël Le Bihan")));
		String line2 = "Mickaël Le Bihan";
		//char c = 'á';
		//System.out.println(line2);

		//System.out.println(simplify.simplifyName(line2));

		HashMap<String, Integer> playersFound = new HashMap<String, Integer>();

		try {
			BufferedReader reader = new BufferedReader (new FileReader("output.csv")); //backup version of this is "list-of-players2.csv"
			String line;



			while ((line = reader.readLine()) != null) {

				//System.out.println(line);
//				byte ptext[] = line.getBytes(ISO_8859_1);
//				String newline = new String(ptext, UTF_8);

				//System.out.println(newline);

				String[] s = line.split(",");
				for (String player : s) {
					String reg = "((^|\\s)" + player + "('|\\s|$))|((^|\\s)" + simplify.simplifyName(player) + "('|\\s|$))";
					Pattern p = Pattern.compile(reg);
					Matcher m = p.matcher(postDescription);

					if (m.find()) {
						System.out.println("regex found " + postDescription.substring(m.start(), m.end()));
						System.out.println("player found " + s[0]);

						if (playersFound.containsKey(s[0])) {
							if (playersFound.get(s[0]) > m.end()) {
								playersFound.put(s[0], m.end());
							}
						} else {
							playersFound.put(s[0], m.end());
						}

//						logger.info("Player found inside csv: " + s[0]);
//						return s[0]; 
					}
				}


			}

			reader.close();

		} catch (Exception e) {
			//logger.error("Error trying to read player file");
		}*/

			//		String postDescription = "Juventus 1-[1] Real Madrid ( Mandzukic M. 27')";
			//		
			//		Pattern p = Pattern.compile("[\\[|(]?[0-9][\\]|)]?-[\\[|(]?[0-9][\\]|)]?"); //does the link text have something like (2-0) displaying the score of a game ^[0-9]+(-[0-9]+)
			//	
			//		Matcher m = p.matcher(postDescription);
			//		
			//		if (m.find()) { 
			//			String score = postDescription.substring(m.start(), m.end());
			//			System.out.println(score.replaceAll("\\(|\\)|\\[|\\]|\\{|\\}", ""));
			//		}



		}


	}


