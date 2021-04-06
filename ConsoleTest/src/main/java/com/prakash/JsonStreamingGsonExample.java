package com.prakash;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonStreamingGsonExample {
	static int input;
	static int input1;
	static int input2;

	public static void main(String[] args) {
		JsonStreamingGsonExample example = new JsonStreamingGsonExample();
		// calling and Displaying Organization data
		Map<Integer, Organizations> data = example.readdata();
		displayData.accept(data);

		// calling and Displaying User data
		Map<Integer, Users> userdata = example.readuserdata();
		displayDatauser.accept(userdata);

		// calling and Displaying Ticket data
		Map<Integer, Tickets> ticketdata = example.readticketdata();
		displayDataticket.accept(ticketdata);

		Scanner sc = new Scanner(System.in);
		System.out.print("Please Enter OrganizationID :- ");
		input = sc.nextInt();
		
		Scanner sc1 = new Scanner(System.in);
		System.out.print("please Enter UserID :- ");
		input1 = sc1.nextInt();
		
		Scanner sc2 = new Scanner(System.in);
		System.out.print("Please Enter TicketID :- ");
		input2 = sc2.nextInt();
		
		Map<Object, Object> collect4 = Stream.of(data).flatMap(map -> map.entrySet().stream())
				.filter(map -> map.getKey() == input )
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (strings, strings2) -> {
					List<String> newList = new ArrayList<>();
					newList.addAll((Collection<? extends String>) strings);
					newList.addAll((Collection<? extends String>) strings2);
					return newList;
				}));
	//	collect4.forEach((s, strings) -> System.out.println(s + "->" + strings));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		String jsonOutput = gson.toJson(collect4);
		System.out.println(jsonOutput);
		
		
		Map<Object, Object> collect5 = Stream.of(userdata).flatMap(map -> map.entrySet().stream())
				.filter(map -> map.getKey() == input1)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (strings, strings2) -> {
					List<String> newList = new ArrayList<>();
					newList.addAll((Collection<? extends String>) strings);
					newList.addAll((Collection<? extends String>) strings2);
					return newList;
				}));
	//	collect5.forEach((s, strings) -> System.out.println(s + "->" + strings));
		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput1 = gson.toJson(collect5);
		System.out.println(jsonOutput1);
		
		
		Map<Object, Object> collect6 = Stream.of(ticketdata).flatMap(map -> map.entrySet().stream())
				.filter(map -> map.getKey() == input2)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (strings, strings2) -> {
					List<String> newList = new ArrayList<>();
					newList.addAll((Collection<? extends String>) strings);
					newList.addAll((Collection<? extends String>) strings2);
					return newList;
				}));
		//collect6.forEach((s, strings) -> System.out.println(s + "->" + strings));
		//Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonOutput2 = gson.toJson(collect6);
		System.out.println(jsonOutput2);
		
		
		// short method
		/*Map<Object, Object> oraganizationFilter = Stream.of(data,userdata,ticketdata).flatMap(map -> map.entrySet().stream())
				//.filter(map -> map.getKey() == input)
                .collect(Collectors.toMap(p -> p.getKey(), p -> p.getValue()));
		
		System.out.println(oraganizationFilter);*/
		
			
		}
	
	Function<String, File> inputfile = fname -> {
		return new File(getClass().getClassLoader().getResource(fname).getFile());
	};

	Function<JSONObject, Integer> idfiled = id -> Integer.parseInt((String) ((JSONObject) id).get("_id"));

	// for organizations
	static Consumer<Map<Integer, Organizations>> displayData = orgnization -> {
		orgnization.size();
	}; // END

	// for User

	static Consumer<Map<Integer, Users>> displayDatauser = User -> {
		User.size();

	}; // END

	// for User
	static Consumer<Map<Integer, Tickets>> displayDataticket = Ticket -> {
		Ticket.size();
	}; // // END

	// fetch Organization data

	Function<JSONObject, Organizations> fetchorg = json -> {
		Organizations orgnization = new Organizations();
		Integer id = Integer.parseInt((String) json.get("_id"));
		orgnization.set_id(id);
		orgnization.setUrl((String) json.get("url"));
		orgnization.setExternal_id((String) json.get("external_id"));
		orgnization.setName((String) json.get("name"));
		JSONArray array = (JSONArray)json.get("domain_names");
		Iterator<String> iterator = array.iterator();
		while (iterator.hasNext()) {
		  orgnization.setDomain_names(iterator.next().toString());
		}
		orgnization.setCreated_at((String) json.get("created_at"));
		orgnization.setDetails((String) json.get("details"));
		orgnization.setShared_tickets((boolean) json.get("shared_tickets"));
		JSONArray array1 = (JSONArray)json.get("tags");
		Iterator<String> iterator1 = array1.iterator();
		while (iterator1.hasNext()) {
		  orgnization.setTags(iterator1.next().toString());
		}

		return orgnization;
	};

	// fetch User data

	Function<JSONObject, Users> fetchuser = json1 -> {
		Users user = new Users();
		Integer id = Integer.parseInt((String) json1.get("_id"));
		user.set_id(id);
		user.setUrl((String) json1.get("url"));
		user.setExternal_id((String) json1.get("external_id"));
		user.setName((String) json1.get("name"));
		user.setAlias((String) json1.get("alias"));
		user.setCreated_at((String) json1.get("created_at"));
		user.setActive((boolean) json1.get("active"));
		user.setVerified((json1.get("verified") != null) ? (boolean) json1.get("verified") : "" != null);
		user.setShared((boolean) json1.get("shared"));
		user.setLocale((String) json1.get("locale"));
		user.setTimezone((String) json1.get("timezone"));
		user.setLast_login_at((String) json1.get("last_login_at"));
		user.setEmail((String) json1.get("email"));
		user.setPhone((String) json1.get("phone"));
		user.setSignature((String) json1.get("signature"));
		Integer org_id;
		if (json1.get("organization_id") == null) {
			//System.out.println("organization_id not present");
			user.setOrganization_id(null);
		} else {
			org_id = Integer.parseInt((String) json1.get("organization_id"));
			user.setOrganization_id(org_id);
		}
		user.setLast_login_at((String) json1.get("last_login_at"));
		JSONArray array = (JSONArray)json1.get("tags");
		Iterator<String> iterator = array.iterator();
		while (iterator.hasNext()) {
			user.setTags(iterator.next().toString());
		}
		user.setSuspended((boolean) json1.get("suspended"));
		user.setRole((String) json1.get("role"));

		return user;
	};

	// fetch Ticket data

	Function<JSONObject, Tickets> fetticket = json2 -> {
		Tickets ticket = new Tickets();
		Integer id = Integer.parseInt((String) json2.get("_id"));
		ticket.set_id(id);
		ticket.setUrl((String) json2.get("url"));
		ticket.setExternal_id((String) json2.get("external_id"));
		ticket.setCreated_at((String) json2.get("created_at"));
		ticket.setType((String) json2.get("type"));
		ticket.setSubject((String) json2.get("subject"));
		ticket.setDescription((String) json2.get("description"));
		ticket.setStatus((String) json2.get("status"));
		Integer submit_id;
		if (json2.get("submitter_id") == null) {
			//System.out.println("submitter_id not present");
			ticket.setSubmitter_id(null);
		} else {
			submit_id = Integer.parseInt((String) json2.get("submitter_id"));
			ticket.setSubmitter_id(submit_id);
		}
		Integer assign_id;
		if (json2.get("assignee_id") == null) {
			//System.out.println("assignee_id not present");
			ticket.setAssignee_id(null);
		} else {
			assign_id = Integer.parseInt((String) json2.get("assignee_id"));
			ticket.setAssignee_id(assign_id);
		}
		Integer org_id;
		if (json2.get("organization_id") == null) {
			//System.out.println("organization_id not present");
			ticket.setOrganization_id(null);
		} else {
			org_id = Integer.parseInt((String) json2.get("organization_id"));
			ticket.setOrganization_id(org_id);
		}
		JSONArray array = (JSONArray)json2.get("tags");
		Iterator<String> iterator = array.iterator();
		while (iterator.hasNext()) {
			ticket.setTags(iterator.next().toString());
		}
		ticket.setHas_incidents((boolean) json2.get("has_incidents"));
		ticket.setDue_at((String) json2.get("due_at"));
		ticket.setVia((String) json2.get("via"));

		return ticket;
	};

	// method for read logic of organization
	@SuppressWarnings("unchecked")
	Map<Integer, Organizations> readdata() {
		File file = inputfile.apply("organizations.json");
		JSONParser parser = new JSONParser();
		Map<Integer, Organizations> orga = null;
		try (Reader reader = new FileReader(file)) {
			JSONArray jarray = (JSONArray) parser.parse(reader);
			orga = (Map<Integer, Organizations>) jarray.stream().collect(Collectors.toMap(idfiled, fetchorg));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return orga;
	}

	// method for read logic of User
	@SuppressWarnings("unchecked")
	Map<Integer, Users> readuserdata() {
		File file = inputfile.apply("users.json");
		JSONParser parser = new JSONParser();
		Map<Integer, Users> usermap = null;
		try (Reader reader = new FileReader(file)) {
			JSONArray jarray = (JSONArray) parser.parse(reader);
			usermap = (Map<Integer, Users>) jarray.stream().collect(Collectors.toMap(idfiled, fetchuser));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return usermap;
	}

	// method for read logic of Ticket
	@SuppressWarnings("unchecked")
	Map<Integer, Tickets> readticketdata() {
		File file = inputfile.apply("tickets.json");
		JSONParser parser = new JSONParser();
		Map<Integer, Tickets> ticketmap = null;
		try (Reader reader = new FileReader(file)) {
			JSONArray jarray = (JSONArray) parser.parse(reader);
			ticketmap = (Map<Integer, Tickets>) jarray.stream().collect(Collectors.toMap(idfiled, fetticket));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ticketmap;
	}

}
