/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rok
 */
@WebServlet(name="Vaja5", urlPatterns={"/Vaja5"})
public class Vaja5 extends HttpServlet {
    Cookie piskot;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void izpisiGlavo(PrintWriter o,HttpServletRequest request){
        o.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
        //z getContextPath() najdemo pot do web direktorija na strežniku
        //uporabim getContextPath namesto vnašanja absolutne poti
        o.println("<link rel='stylesheet' type='text/css' href='"
                +request.getContextPath() +"/style.css'>");
        o.println("<html>");
        o.println("<head>");
        o.println("<title>Servlet Login</title>");
        o.println("</head>");
        o.println("<body>");
    }
    protected void izpisiNogo(PrintWriter o){
        o.println("</body>");
        o.println("</html>");
    }
    protected void izpisiMenu(PrintWriter o){
        o.println("<div id='menu'>");
        o.println("<a href='?page=domov'>Domov</a><br><a href='?page=odjavi'>Odjavi</a>");
        o.println("</div>");
    }
    protected void izpisiLogin(PrintWriter o){

        o.println("<div id='login'><form method='POST'>");
        o.println("Ime: <br/><input type='text' name='uime'><br/>");
        o.println("Priimek: <br/><input type='text' name='upriimek'><br/>");
        o.println("Spol: <br/>Moski <input type='radio' name='uspol' value='m' CHECKED><br/>");
        o.println("Zenski <input type='radio' name='uspol' value='z'><br/>");

        o.println("<input type='submit' value='Prijavi'>");

        o.println("</form></div>");
    }
    protected void izpisiDomov(PrintWriter o,HttpSession seja){
        //ko izpisujemo prvo stran
        //potrebujemo podatke o osebi, ki smo jih shranili v sejo
        o.println("<h2>Domov</h2><div id='vsebina'>");
        //če atribut obstaja izpišemo pozdravljen ime
        //System.out.println(seja.getAttribute("ime"));
        if(seja.getAttribute("ime")!=null){
            Oseba obj=(Oseba)seja.getAttribute("ime");
            o.println("Podatki:<br> Ime :"+obj.vrniIme() + "<br>Priimek: " + obj.vrniPriimek());
                if(piskot.getName().equals("m"))
                    o.println("<br>Spol: Moski");
                else
                    o.println("<br>Spol: Zenski");

        }else{

            //če atribut ne obstaja ga uporabnik še ni vnesel
            o.println("Vpišite svoje ime v vnosnem obrazcu");
        }
        o.println("</div>");
    }
     protected void izpisiVnos(PrintWriter o){
       //
       o.println("<h2>Vnos</h2><div id='vsebina'>");
       o.println("<form action='?page=domov' method='POST'>");
       o.println("Vaše ime: <br/><br/><input type='text' name='uporabnik'><br/> <br/>");
       o.println("<input type='submit' value='Vnesi'>");
       o.println("</form></div>");
       o.println("</div>");
    }
      protected void izpisiOdjavi(PrintWriter o){

       o.println("<div id='vsebina'><h4>Uspešno ste se odjavili!</h4></div>");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            Oseba o;
            HttpSession seja;
            //izpišemo glavo
            izpisiGlavo(out,request);
            //izpišemo menu
            izpisiMenu(out);

            //če je uporabnik potrdil obrazec login preverimo
            //podatke
            if(request.getParameter("uime")!=null && request.getParameter("upriimek")!=null){
                //geslo je v tem primeru kar v izvorni kodi
                //to ni priporočljivo
                //geslo shranimo v bazo podatkov
                //pred tem ga kriptiramo z ustreznim algoritmom (MD5/SHA1)
              // if(request.getParameter("uime").equals("marko") && request.getParameter("upriimek").equals("oman"))
                   //če sta geslo in uporabniško ime pravilna ustavarimo sejo
                    request.getSession();
           }



            //pogledamo če obstaja seja
            //false ker ne želimo ustvariti nove seje
            //če ta ne obstaja
            if(request.getSession(false)!=null){
                //pridobimo objekt ki predstavlja sejo
                seja=request.getSession();
                if(request.getParameter("uime")!=null && request.getParameter("upriimek")!=null){
                 o=new Oseba(request.getParameter("uime"),request.getParameter("upriimek"));
                 request.getSession().setAttribute("ime", o);
                 piskot=new Cookie(request.getParameter("uspol"),request.getParameter("vrednost"));
                 response.addCookie(piskot);

                   //System.out.println("Piskot:"+piskot.getName());
                // System.out.println("USPOL:"+request.getParameter("uspol"));

                 //System.out.println(request.getParameter("uime")+","+request.getParameter("upriimek"));

                }
                //preverimo če smo kaj vpisali v vnosno polje
                //če obrazec ni bil potrjen
               /* if(request.getParameter("uporabnik")!=null){
                    o=new Oseba(request.getParameter("uporabnik"));
                    request.getSession().setAttribute("ime", o);
                }*/
                //strani imamo ločene po parameter page
                //če ni nastavljen prikažemo osnovno stran
                if(request.getParameter("page")!=null){
                    //če je page=domov prikažemo prvo stran
                    if(request.getParameter("page").equals("domov"))
                        izpisiDomov(out,request.getSession(false));
                    //če je page=vnos prikažemo vnosno polje
                   // if(request.getParameter("page").equals("vnos"))
                     //   izpisiVnos(out);
                    //če je page=odjavi prikažemo odjavno sporočilo
                    if(request.getParameter("page").equals("odjavi")){
                        izpisiOdjavi(out);
                        //prekinemo sejo
                        seja.invalidate();
                    }

                }else
                {
                    izpisiDomov(out,request.getSession(false));
                }
            }else{
                //izpišemo login obrazec
                izpisiLogin(out);
            }

            izpisiNogo(out);



        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
class Oseba{
    String ime;
    String priimek;
    Oseba(String i, String p){
        this.ime=i;
        this.priimek = p;
    }
    Oseba(String i){
        this.ime = i;
    }
    String vrniIme(){
        return this.ime;
    }
    String vrniPriimek(){
        return this.priimek;
    }


}