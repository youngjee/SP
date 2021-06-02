package http.server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setStatus(200);
		res.getWriter().write("Hello World!");
	}
}
