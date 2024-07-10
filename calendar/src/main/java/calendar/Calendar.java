package calendar;

import java.util.ArrayList;

// never producing .00 - getting .15 instead check slotToTime function
// incorrect times 
// some slots chosen longer than specified duration

public class Calendar {
    int [] bounds;
    boolean [] timeSlots = new boolean [96];

    public void printCalendar() {
        for (int i = 0; i < this.timeSlots.length; i++) {
            System.out.print(i + " " + slotToTime(i) + " ");
            System.out.println(this.timeSlots[i]);
        }
    }

    public void printBothCalendars(Calendar otherCalendar) {
        for (int i = 0; i < this.timeSlots.length; i++) {
            System.out.print(i + " " + slotToTime(i) + " ");
            System.out.print(this.timeSlots[i]);
            System.out.print(" ----------- ");
            System.out.println(otherCalendar.timeSlots[i]);
        }
    }

    // build calendar and turn all the slots outside the bounds to false
    public Calendar(int start, int end) {
        System.out.println("start " + start);
        System.out.println("end slot" + ((end * 100) / 2400 - 3));
        System.out.println("start slot " + ((start * 100) / 2400 - 1));
        // false until start
        for (int i = 0; i < ((start * 100) / 2400 - 1); i++) {
            timeSlots[i] = true;
        }
        // false from end
        for (int i = (end * 100) / 2400 - 3; i < 96; i++) {
            timeSlots[i] = true;
        }
        this.bounds = new int [] {start, end};

        System.out.println(this.bounds[0] + " " + this.bounds[1]);

    }

    // identify the slot and if not already booked book false
    public boolean bookSlot(int start, int end) {
        int startIndex = start / 15;
        int endIndex = end / 15;

        // check whether gap has any slots booked in 
        for (int i = 0; i < (startIndex - endIndex); i++) {
            if (timeSlots[i] == true) {
                return false;
            }
        }
        return true;
    }


    public boolean bookMeeting(int start, int end, Calendar otherCalendar) {
        int startIndex = (start * 100) / 2400 - 1;
        int endIndex = (end * 100) / 2400 - 1;

        System.out.println("start Index " + startIndex);
        System.out.println("end Index " + endIndex);

        // check slots on both calendars 
        if (this.bookSlot(start, end) == false || 
        otherCalendar.bookSlot(start, end) == false) {
            System.out.println("reached false");
            return false;
        }

        // change the slots from true to false
        for (int i = startIndex; i < endIndex; i++) {
            System.out.println("i" + i);
            this.timeSlots[i] = true;
            otherCalendar.timeSlots[i] = true;
        }
        System.out.println("reached true");
        return true;
    }

    public String slotToTime(double number) {

        // slot number converted into decimal 2400 time
        double value = (number * 25) / 100;

        double roundedDown = Math.floor(value);

        // Check if the fractional part matches .25, .5, or .75
        if (value - roundedDown == 0) {
            return (int)roundedDown + ".00";
        }
        else if ((value - roundedDown - 0.25) < 0.001) {
            return (int)roundedDown + ".15";
        } else if ((value - roundedDown - 0.5) < 0.001) {
            return (int)roundedDown + ".30";
        } else if ((value - roundedDown - 0.75) < 0.001) {
            return (int)roundedDown + ".45";
        } else {
            return "error";
        }
    }

    public boolean checkValidMeeting(int start, int duration, Calendar otherCalendar) {
        int durationSlots = duration / 15;

        System.out.println("duration " + durationSlots);
        
        // check both calendars 
        for (int i = 0; i < durationSlots; i++) {
            if (this.timeSlots[start + i] = false) {
                return false;
            }
        }
        for (int i = 0; i < durationSlots; i++) {
            if (otherCalendar.timeSlots[start + i] = false) {
                return false;
            }
        }
        return true;
    }

    // currently scans between startpoint and endpoint but does not identify when slots are taken

    public void printAllPossibleMeetings(int duration, Calendar otherCalendar) {
        // find the number of slots between the earliest start and the latest finish
        System.out.println(); 
        int startPoint = 0;
        int endPoint = 0;


        if (this.bounds[0] < otherCalendar.bounds[0]) {
            startPoint = (otherCalendar.bounds[0] * 100) / 2400 - 1;
            System.out.println("startPoint " + startPoint);
        }
        else {
            startPoint = (this.bounds[0] * 100) / 2400 - 1;
            System.out.println("startpoint " + startPoint);
        }
        if (this.bounds[1] > otherCalendar.bounds[1]) {
            endPoint = (otherCalendar.bounds[1] * 100) / 2400 - 3;
            System.out.println("endPoint " + endPoint);
        }
        else {
            endPoint = (this.bounds[1] * 100) / 2400 - 3;
            System.out.println("endpoint " + endPoint);
        }
        // 
        int lengthToSearch = endPoint - startPoint;

        System.out.println("length to search " + lengthToSearch);
        System.out.println(startPoint);
        System.out.println(endPoint);


        // create an array list to store the times 
        ArrayList <int[]> possibleTimes = new ArrayList<int[]>();

        // search through the array for available slot 
        for (int i = 0; i < lengthToSearch; i++) {
            System.out.println(startPoint + i + " " + this.timeSlots[startPoint + i]);
            if (this.timeSlots[startPoint + i] == false) {
                System.out.println("reached");
                if (checkValidMeeting(startPoint + i, duration, otherCalendar)) {
                    // if slot available in both calenders add to array
                    System.out.println("slot found");
                    int [] newArray = {(startPoint + i), (startPoint + i + (duration / 15))};
                    possibleTimes.add(newArray);
                }
            }
            else {
                System.out.println("this time is not available");
            }
        }
        // currently nothing is being added to the array 

        // print out the array
        System.out.println();
        System.out.println("possible slots");
        for (int i = 0; i < possibleTimes.size(); i++) {
            System.out.println(slotToTime(possibleTimes.get(i)[0]) + " - " + slotToTime(possibleTimes.get(i)[1]));
        }
    }
}






















// daily bounds are start and end. 

// meetings is an array that contains arrays that hold two numbers, start time and end time.

// compare the two arrays, 
    // bounds
        // compare the two and pick the larger number
        // compare the two and pick the smaller number
    
    // finding time slots 
        // count up from the lowest number in elements of 15 
        // if a number matches the start time find the length of meeting
        // order the array of meetings [0] smallest to largest

            // take the first meeting from calendar one 
                // check if the start time of the first meeting from calendar two is smaller or fits between.
                // if smaller find size of space 
                    // if larger check next array in calendar one 
                    
                
    
                    // public String checkMeeting(int start, int end, Calendar otherCalendar) {
                    //     if (start < this.bounds[0] || end > this.bounds[1]) {
                    //         return "out of bounds of this calendar";
                    //     }
                    //     else if (start < otherCalendar.bounds[0] || end > otherCalendar.bounds[1]) {
                    //         return "out of bounds of other calendar";
                    //     }
                        
                    //     for (int i = 0; i < this.meetings.size() - 1; i++) {
                    //         // if a meeting falls between the end of one meeting and the start of another
                    //         if (start > this.meetings.get(i)[1] && end < this.meetings.get(i+1)[0]) {
                    //             for (int j = 0; i < otherCalendar.meetings.size() - 1; j++) {
                    //                 // if the second calendar has no meetings
                    //                 if (otherCalendar.meetings.size() == 0) {
                    //                     addMeeting(start, end);
                    //                     otherCalendar.addMeeting(start, end);
                    //                     return "meeting added";
                    //                 }
                    //                 // if the meeting does the same for the other calendar
                    //                 else if (start > otherCalendar.meetings.get(j)[1] && end < otherCalendar.meetings.get(j)[0]) {
                    //                     addMeeting(start, end);
                    //                     otherCalendar.addMeeting(start, end);
                    //                     return "meeting added";
                    //                 }
                    //             }
                                
                    //         }
                
                    //     }
                    //     return "false other";
                    // }

    // ArrayList<int []> meetings = new ArrayList<int []>();
    // int [] bounds = new int [2];

    // public Calendar(int start,int end) {
    //     this.bounds [0] = start;
    //     this.bounds [1] = end;
    // }

    // public void addMeeting(int startTime, int endTime) {
    //     int [] meeting = {startTime, endTime};
    //     this.meetings.add(meeting);
    // }

    // public void setBounds(int start, int end) {
    //     this.bounds [0] = start;
    //     this.bounds [1] = end;
    // }

    // public void printMeetings() {
    //     for (int i = 0; i < meetings.size(); i++) {
    //         int meetingNumber = i+1;
    //         System.out.println("start of meeting " + meetingNumber + " is "  + meetings.get(i)[0]);
    //         System.out.println("end of meeting " +  meetingNumber  + " is " + meetings.get(i)[1]);
    //     }
    // }

    // public void checkMeeting(int duration, Calendar otherCalendar) {
    //     ArrayList<Integer> possibleTimes = new ArrayList<Integer>();
    //     ArrayList<Integer[]> totalTimes = new ArrayList<Integer []>();

    //     // organise the meetings in order 
    //         int numberOfMeetings = this.meetings.size() + otherCalendar.meetings.size();

    //         for (int i = 0; i < numberOfMeetings; i++) {
    //             if (totalTimes.size() == 0) {
    //                 totalTimes.add()
    //             }

    //         }
    // }