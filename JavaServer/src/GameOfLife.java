import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.*;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;

/** Endpoint handling apple requests. */
public class GameOfLife implements Servlet {
	private static final String HTML_CONTENT_TYPE = "text/html;charset=utf-8";
	private static final String JSON_CONTENT_TYPE = "application/json";
	private int NUM_ROWS = 10;
	private int NUM_COLS = 10;

	@Override
	public void service(ServletRequest baseRequest, ServletResponse response) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) baseRequest; 
		if(httpRequest.getMethod() == "OPTIONS") {
			System.out.println("options request returning");
			return;
		}

		boolean[][] grid = parseInput(baseRequest);
		
		System.out.print(DatabaseConnection.queryDatabase("select * from users where id=1", String.class, "first_name"));
		

			//your code goes here - start


			//end
			
		String output = parseMatrixToJson(future);
		response.setContentType(JSON_CONTENT_TYPE);
		response.getWriter().println(output);
	}

	String parseMatrixToJson(boolean[][] grid) {
		StringBuffer str = new StringBuffer("{\n\"grid\":[\n");

		for(int row = 0; row < NUM_ROWS; row++) {
			str.append("[");
			for(int col = 0; col < NUM_COLS; col++) {
				if(grid[row][col])
					str.append("1");
				else{
					str.append("0");
				}
				if(col < NUM_COLS - 1) {
					str.append(",");
				}
			}
			str.append("]");
			if(row < NUM_ROWS - 1) {
				str.append(",\n");
			}
		}

		str.append("\n]\n}");

		return str.toString();
	}

	boolean[][] parseInput(ServletRequest baseRequest) throws IOException{
		String reqBody = baseRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		JsonParser parser = new JsonParser();
		JsonObject input = parser.parse(reqBody).getAsJsonObject();
		JsonArray jsonGrid = input.getAsJsonArray("grid");
		JsonArray currRow = jsonGrid.get(0).getAsJsonArray();
		NUM_ROWS = jsonGrid.size();
		NUM_COLS = currRow.size();

		boolean[][] grid = new boolean[NUM_ROWS][NUM_COLS];

		for(int row = 0; row < NUM_ROWS; row++) {
			currRow = jsonGrid.get(row).getAsJsonArray();
			NUM_COLS = currRow.size();
			for(int col = 0; col < NUM_COLS; col++) {
				grid[row][col] = currRow.get(col).getAsInt() == 1;
			}
		}

		return grid;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}


}
