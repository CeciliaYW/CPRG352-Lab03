package servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Note;


public class NoteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
        String title = br.readLine();
        String content = br.readLine();
        br.close();
        
        Note note = new Note(title, content);
        request.setAttribute("note", note);
        
        String edit = request.getParameter("edit");
        
        if(edit == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request,response);
            return;
            
        } else if ("".equals(edit)) {
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request,response);
            
        }
        return;
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        
        String title = br.readLine();
//        String content = br.readLine();
        StringBuffer content = new StringBuffer(br.readLine());
        br.close();
        
        title = request.getParameter("edit_title");
//        content = request.getParameter("edit_contents");
        content = new StringBuffer(request.getParameter("edit_contents"));
        
        int loc = (new String(content).indexOf('\n'));
        while(loc > 0){
            content.replace(loc, loc + 1, "<BR>");
            loc = (new String(content).indexOf('\n'));
        }
        
        String formattedContent = content.toString();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false))); 
        
        pw.println(title);
        pw.println(content);
        pw.close();
        
        Note note = new Note(title, formattedContent);
        request.setAttribute("note", note);
        
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request,response);
        return;
        
    }

}
