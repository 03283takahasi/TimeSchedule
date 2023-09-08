package Servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MonthViewServlet")
public class MonthViewServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//文字コードをShift_Jisに設定
//		response.setContentType("text/html;charset=Shift_Jis");
		//文字列出力のためのオブジェクトを取得
//		PrintWriter out = response.getWriter();
		
		int[] calendarDay;
		int count;
		
		calendarDay = new int[42]; /*最大で7日×６週*/
		count = 0;
		
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);
		
		//リクエストスコープセット
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		request.setAttribute("count", count);
		HttpSession session = request.getSession();
		session.setAttribute("calendarDay", calendarDay);
		//日付データを配列に格納
		count = setDateArray(year,month,day,calendarDay,count);
		
		
		String url = "/monthview.jsp";
		
		request.getRequestDispatcher(url).forward(request,response);
	}

	protected int setDateArray(int year, int month, int day, int[] calendarDay, int count) {
		Calendar calendar = Calendar.getInstance();

        /* 今月が何曜日から開始されているか確認する */
        calendar.set(year, month, 1);
        int startWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("今月の曜日は" + startWeek + "から");

        /* 先月が何日までだったかを確認する */
        calendar.set(year, month, 0);
        int beforeMonthlastDay = calendar.get(Calendar.DATE);
        System.out.println("先月は" + beforeMonthlastDay + "日まで");

        /* 今月が何日までかを確認する */
        calendar.set(year, month + 1, 0);
        int thisMonthlastDay = calendar.get(Calendar.DATE);
        System.out.println("今月は" + thisMonthlastDay + "日まで\r\n");

        /* 先月分の日付を格納する */
        for (int i = startWeek - 2 ; i >= 0 ; i--){
            calendarDay[count++] = beforeMonthlastDay - i + 35;
        }

        /* 今月分の日付を格納する */
        for (int i = 1 ; i <= thisMonthlastDay ; i++){
            calendarDay[count++] = i;
        }

        /* 翌月分の日付を格納する */
        int nextMonthDay = 1;
        while (count % 7 != 0){
            calendarDay[count++] = 35 + nextMonthDay++;
        }
		
		return count;
	}
}
