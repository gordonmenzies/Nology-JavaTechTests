package calendar;

public class App 
{
    public static void main( String[] args )
    {
        Calendar calendarOne = new Calendar(900,2200);
        Calendar calendarTwo = new Calendar(800, 2100);
        
        calendarOne.bookMeeting(915,1000, calendarTwo);

        
        calendarOne.printBothCalendars(calendarTwo);
        //calendarTwo.printCalendar();
        calendarOne.printAllPossibleMeetings(15, calendarTwo);

    

    }
}
